package mix.react.com.second.lib.hotupdate;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;

import mix.react.com.second.lib.utils.StoreUtil;

/**
 * React native 热更新工具类
 * 检测，下载，验证，解压，合并等
 * Created by codemanwang on 2017/2/27.
 */

public class HotUpdateHelper {

    public static void downLoadBundle(Activity activity, String bundleUrl){

        if (TextUtils.isEmpty(bundleUrl)){
            return;
        }
        String destinationPath = StoreUtil.getBundlePath(activity);
        File zipFile = new File(destinationPath);
        if (zipFile.exists()){
            zipFile.delete();
        }

        //下载最新的Bundle
        DownloadManager downloadManager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(bundleUrl));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setDestinationUri(Uri.parse("file://" + destinationPath));
        long downloadId = downloadManager.enqueue(request);

    }
}
