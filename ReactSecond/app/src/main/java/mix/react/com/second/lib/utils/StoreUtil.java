package mix.react.com.second.lib.utils;

import android.app.Activity;
import android.os.Environment;

import java.io.File;

/**
 * 存储相关工具类
 * Created by codemanwang on 2017/2/27.
 */

public class StoreUtil {

    public static String getAvaiablePath(Activity activity){

        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            path = Environment.getDataDirectory().getAbsolutePath() + File.separator;
        }else {
            path = activity.getCacheDir().getAbsolutePath() + File.separator;
        }

        return path;
    }

    public static String getBundlePath(Activity activity){
        if (activity == null){
            return null;
        }

        String path = getAvaiablePath(activity) + "bundle/";
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }

        path += "bundle.zip";
        return path;
    }
}
