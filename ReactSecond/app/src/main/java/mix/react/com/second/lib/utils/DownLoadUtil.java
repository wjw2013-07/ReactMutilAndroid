package mix.react.com.second.lib.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by codemanwang on 2017/2/28.
 */

public class DownLoadUtil {


    public static void sysDownLoad(String url, String destinationPath, Activity activity){

        if (TextUtils.isEmpty(url)){
            return;
        }
        File zipFile = new File(destinationPath);
        if (zipFile.exists()){
            zipFile.delete();
        }

        //下载最新的Bundle
        DownloadManager downloadManager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setDestinationUri(Uri.parse("file://" + destinationPath));
        long downloadId = downloadManager.enqueue(request);
        SpUtil.saveLong("downloadId", downloadId);
    }

}
