package mix.react.com.second.lib.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import mix.react.com.second.lib.utils.FileUtil;
import mix.react.com.second.lib.utils.LogUtil;
import mix.react.com.second.lib.utils.MD5Util;
import mix.react.com.second.lib.utils.SpUtil;
import mix.react.com.second.lib.utils.StoreUtil;
import mix.react.com.second.lib.utils.ThreadUtil;

/**
 * Created by codemanwang on 2017/2/28.
 */

public class CompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        long completeId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -2);
        LogUtil.logCommon("下载完成");
        if (completeId == SpUtil.getLong("downloadId")){
            ThreadUtil.asyncTask(new Runnable() {
                @Override
                public void run() {
                    handlePatch(context);
                }
            });
        }
    }

    /***
     * 处理patch一系列逻辑
     * @param context
     */
    private void handlePatch(Context context) {
        LogUtil.logMd5("下载patch包成功，开始patch md5校验");
        String patchPath = StoreUtil.getBundlePatchPath(context);
        boolean isPatchSafe = MD5Util.checkMD5(SpUtil.getBundlePMd5(),  patchPath);
        if (!isPatchSafe){
            LogUtil.logMd5("patch包被篡改，删除不合法文件");
            FileUtil.deleteFile(patchPath);
            return;
        }
        LogUtil.logMd5("patch md5 校验成功，开始合并");
        String bundlePath = StoreUtil.getBundleIndexBundle(context);
        FileUtil.mergePatchBundle(bundlePath, patchPath);
        //校验合并后的包和真实的包md5不一样，推测可能合并过程中加入了东西
        //boolean isBundleSafe = MD5Util.checkMD5(SpUtil.getBundleCMd5(), bundlePath);
        //if (!isBundleSafe){
        //    LogUtil.logMd5("合并的新bundle有问题");
            //FileUtil.deleteFile(patchPath);
        //    return;
        //}
        LogUtil.logMd5("bundle md5 合并成功，开始解压缩");
        FileUtil.decompression(bundlePath, context);
    }

}
