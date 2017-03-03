package mix.react.com.second.lib.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import mix.react.com.second.lib.manager.HotupdateManager;
import mix.react.com.second.lib.utils.LogUtil;
import mix.react.com.second.lib.utils.SpUtil;

/**
 * Created by codemanwang on 2017/2/28.
 */

public class CompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        long completeId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -2);
        LogUtil.logCommon("下载完成");
        if (completeId == SpUtil.getLong("downloadId")){
            HotupdateManager.handleBundleZip(context);
        }
    }

}
