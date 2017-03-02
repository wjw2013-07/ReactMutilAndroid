package mix.react.com.second.lib.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import mix.react.com.second.lib.third.diff_match_patch;

/**
 * 文件相关工具类
 * Created by codemanwang on 2017/2/28.
 */

public class FileUtil {


    /***
     * 解压缩zip文件
     * @param path
     * @param context
     */
    public static void decompression(String path, Context context){
        if (TextUtils.isEmpty(path)){
            return;
        }
        try {
            ZipInputStream zIs = new ZipInputStream(new FileInputStream(path));
            ZipEntry zipEntry;
            String szName;
            while ((zipEntry = zIs.getNextEntry()) != null){
                szName = zipEntry.getName();
                if (zipEntry.isDirectory()){
                    szName = szName.substring(0, szName.length() -  1);
                    File folder = new File(StoreUtil.getBundleDir(context) + File.separator + szName);
                    folder.mkdirs();
                }else {
                    File file = new File(StoreUtil.getBundleDir(context) + File.separator + szName);
                    file.createNewFile();
                    StreamUtil.iStreamToFile(zIs, file);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void deleteFile(String path){
        if (TextUtils.isEmpty(path)){
            return;
        }

        File file = new File(path);
        if (file.exists()){
            file.delete();
        }

    }

    /***
     * 根据传入的新旧文件以及生成的目标位置，生成patch文件
     * @param oldPath
     * @param newPath
     * @param specifyPath
     */
    public static void productPatch(String oldPath, String newPath, String specifyPath){
        if (TextUtils.isEmpty(oldPath)) {
            return;
        }
        if (TextUtils.isEmpty(newPath)) {
            return;
        }
        if (TextUtils.isEmpty(specifyPath)) {
            return;
        }
        //读取文件，并且转换成字符串
        String oldData = StreamUtil.getStringFromPathN(oldPath);
        String newData = StreamUtil.getStringFromPathN(newPath);

        diff_match_patch dmp = new diff_match_patch();
        //对比
        LinkedList<diff_match_patch.Diff> diffs = dmp.diff_main(oldData, newData);
        //生成差异补丁包
        LinkedList<diff_match_patch.Patch> patchs = dmp.patch_make(diffs);
        //解析补丁包
        String patchStr = dmp.patch_toText(patchs);
        if (patchStr == null || patchStr.equals("")) {
            return;
        }

        //存储到指定位置
        try {
            File file = new File(specifyPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            StreamUtil.iByteToFile(patchStr.getBytes(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 合并patch和bundle
     * @param bundlePath
     * @param patchPath
     */
    public static void mergePatchBundle(String bundlePath, String patchPath){
        if (TextUtils.isEmpty(bundlePath) || TextUtils.isEmpty(patchPath)){
            return;
        }
        String bundleData = StreamUtil.getStringFromPathN(bundlePath);
        String patchData = StreamUtil.getStringFromPathN(patchPath);
        if (TextUtils.isEmpty(bundleData) || TextUtils.isEmpty(patchData)){
            return;
        }
        //1 初始化dmp
        diff_match_patch dmp = new diff_match_patch();
        //2 转换patch
        LinkedList<diff_match_patch.Patch> patches =
                (LinkedList<diff_match_patch.Patch>) dmp.patch_fromText(patchData);
        //3 patch与bundle合并，生成新的bundle
        Object[] bundleArray = dmp.patch_apply(patches, bundleData);
        //4 保存新的bundle
        Writer writer = null;
        try{
            writer = new FileWriter(bundlePath);
            String newBundle = (String)bundleArray[0];
            if (!TextUtils.isEmpty(newBundle)) {
                String end = newBundle.charAt(newBundle.length() - 1) + "";
                if (end.equals("\n")) {
                    newBundle = newBundle.substring(0, newBundle.length() - 1);
                }
            }
            writer.write(newBundle.replace("\n", System.getProperty("line.separator")));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(patchPath);
        file.delete();
    }

    public static void movieImgToGoal(Context context){
        String zipImgDir = StoreUtil.getBundleDir(context) + "img";//解压缩所得图片路径
        String imgGoalDir = StoreUtil.getBundleDir(context) + "hotupdate/drawable-mdpi/";
        try{
            File zipFile = new File(zipImgDir);
            if (!zipFile.isDirectory()){
                return;
            }
            
            File[] zipImgFiles = zipFile.listFiles();
            if (zipImgFiles == null || zipImgFiles.length == 0){
                return;
            }
            int length = zipImgFiles.length;
            for (int i = 0; i < length; i++){
                File file = zipImgFiles[i];
                if (file == null || !file.exists()){
                    continue;
                }

                StreamUtil.moveFileToGoalDir(file, imgGoalDir);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
