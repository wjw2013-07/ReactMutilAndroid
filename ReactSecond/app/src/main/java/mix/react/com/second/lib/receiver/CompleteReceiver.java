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

/**
 * Created by codemanwang on 2017/2/28.
 */

public class CompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        long completeId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -2);
        if (completeId == SpUtil.getLong("downloadId")){
            LogUtil.logMd5("下载成功，开始md5校验");
            String path = StoreUtil.getBundlePath(context);
            boolean isSafe = MD5Util.checkMD5(SpUtil.getString("bundle_md5"),  path);
            if (!isSafe){
                LogUtil.logMd5("文件被篡改，删除不合法文件");
                FileUtil.deleteFile(path);
                return;
            }
            FileUtil.decompression(path, context);
        }
    }
}
