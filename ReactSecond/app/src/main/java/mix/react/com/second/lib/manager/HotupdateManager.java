package mix.react.com.second.lib.manager;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import java.io.File;

import mix.react.com.second.Constant;
import mix.react.com.second.bean.BundleVersionBean;
import mix.react.com.second.lib.utils.FileUtil;
import mix.react.com.second.lib.utils.GsonUtil;
import mix.react.com.second.lib.utils.LogUtil;
import mix.react.com.second.lib.utils.MD5Util;
import mix.react.com.second.lib.utils.SpUtil;
import mix.react.com.second.lib.utils.StoreUtil;
import mix.react.com.second.lib.utils.StreamUtil;
import mix.react.com.second.lib.utils.ThreadUtil;

/**
 * 热更新相关工具类
 * Created by codemanwang on 2017/3/3.
 */

public class HotupdateManager {


    /***
     * 移动assets hotupdate.zip到SD卡指定目录，并且解压缩
     * @param bundleFileName
     * @param bundleVersionName
     * @param context
     */
    public static void moveAssetsToGoal(final String bundleFileName, final String bundleVersionName, final Context context){

        if (TextUtils.isEmpty(bundleFileName) || TextUtils.isEmpty(bundleVersionName) || context == null){
            return;
        }

        ThreadUtil.asyncTask(new Runnable() {
            @Override
            public void run() {
                try{
                    //1 首先判断SD中是否已有hotupdate.zip，如果有直接跳过
                    String goalFileStr = StoreUtil.getBundleDir(context) + bundleFileName;
                    if (new File(goalFileStr).exists()){
                        LogUtil.logHotUpdate("hotupdate.zip 在SD卡已经存在");
                        return;
                    }
                    //2 解析assets/bundle_version.json，存储到sp中：校验把assets复制到SD卡后的完整性
                    String bundleVersionJson = StreamUtil.iStreamToString(context.getAssets().open(bundleVersionName));
                    SpUtil.saveBundleVersionBean(GsonUtil.jsonToBean(bundleVersionJson, BundleVersionBean.class), true);
                    //3 复制hotupdatez.zip到SD卡
                    File goalFile = StreamUtil.moveAssetsToGoalDir(bundleFileName, context);
                    if (goalFile == null){
                        LogUtil.logHotUpdate("把hotupdate.zip拷贝到SD卡失败");
                        return;
                    }
                    //校验复制后的文件合法性
                    boolean isAfterCopy = MD5Util.checkMD5(SpUtil.getBundleCMd5(), goalFile.getAbsolutePath());
                    if (!isAfterCopy){
                        LogUtil.logHotUpdate("copy到SD卡的hotupdate.zip不合法，删除");
                        goalFile.delete();
                        return;
                    }
                    FileUtil.decompression(goalFile.getAbsolutePath(), context);
                    LogUtil.logHotUpdate("copy到SD卡的hotupdate.zip解压缩完毕");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }


    /***
     * 初始化方案  合并patch，未考虑有图片变化的情况
     * @param context
     */
    public static void handlePatch(Context context) {
        //先进行MD5校验，如果已经合并过，便不再合并
        String bundlePath = StoreUtil.getBundleIndexBundle(context);
        String tempBundlePath = StoreUtil.getBundleTempBundle(context);
        boolean isPreBundleSafe = MD5Util.checkMD5(SpUtil.getBundleCMd5(), bundlePath);
        if (isPreBundleSafe){
            LogUtil.logHotUpdate("经过MD5校验已经合并，直接跳过执行");
            context.sendBroadcast(new Intent(Constant.PACK_PATCH_SUCCESS));
            return;
        }

        LogUtil.logHotUpdate("下载patch包成功，开始patch md5校验");
        String patchPath = StoreUtil.getBundlePatchPath(context);
        boolean isPatchSafe = MD5Util.checkMD5(SpUtil.getBundlePMd5(),  patchPath);
        if (!isPatchSafe){
            LogUtil.logHotUpdate("patch包被篡改，删除不合法文件");
            FileUtil.deleteFile(patchPath);
            return;
        }
        LogUtil.logHotUpdate("patch md5 校验成功，开始合并为临时的tempBundle，防止合并后出错，回退到原始版本");
        FileUtil.mergePatchBundle(bundlePath, patchPath, tempBundlePath);
        LogUtil.logHotUpdate("tempBundle patch合并成功，开始校验");
        boolean isBundleSafe = MD5Util.checkMD5(SpUtil.getBundleCMd5(), tempBundlePath);
        if (!isBundleSafe){
            LogUtil.logHotUpdate("合并的新bundle有问题, 删除临时合并的tempBundle");
            FileUtil.deleteFile(tempBundlePath);
            return;
        }
        LogUtil.logHotUpdate("合并的bundle校验成功，开始最后一步重命名为index.android.bundle");
        FileUtil.reName(tempBundlePath, bundlePath);
        context.sendBroadcast(new Intent(Constant.PACK_PATCH_SUCCESS));
    }

    /***
     * 完善后方案，将pacth和图片打成zip下发到客户端
     * @param context
     */
    public static void handleBundleZip(final Context context){
        if (context == null){
            return;
        }
        ThreadUtil.asyncTask(new Runnable() {
            @Override
            public void run() {
                //先校验zip包的完整性
                String zipPath = StoreUtil.getZipPath(context);
                //1 首先验证客户端收到的zip文件是否合法完整
                boolean isZipSafe = MD5Util.checkMD5(SpUtil.getBundleZipMd5(), zipPath);
                if (!isZipSafe){
                    LogUtil.logHotUpdate("zip文件被篡改，增量升级过程被迫中止");
                    return;
                }
                //2 zip校验完整，开始解压缩
                LogUtil.logHotUpdate("zipMd5 校验成功，开始解压缩");
                FileUtil.decompression(zipPath, context);
                //3 解压缩完毕，合并patch
                handlePatch(context);
                //4 解压缩完毕，移动图片到hotupdate/drawable-mdpi文件夹
                LogUtil.logHotUpdate("移动图片到hotupdate/drawable-mdpi文件夹");
                FileUtil.movieImgToGoal(context);
                FileUtil.deleteFile(zipPath);
            }
        });

    }


}
