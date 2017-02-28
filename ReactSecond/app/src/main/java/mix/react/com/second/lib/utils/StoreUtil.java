package mix.react.com.second.lib.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 存储相关工具类
 * Created by codemanwang on 2017/2/27.
 */

public class StoreUtil {

    public static String getAvaiablePath(Context context){

        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
        }else {
            path = context.getCacheDir().getAbsolutePath() + File.separator;
        }

        return path;
    }

    public static String getBundlePath(Context context){
        if (context == null){
            return null;
        }

        String path = getAvaiablePath(context) + "bundle/";
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }

        path += "bundle.zip";
        return path;
    }

    public static String getBundleDir(Context context){
        if (context == null){
            return null;
        }

        String path = getAvaiablePath(context) + "bundle/";
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }

        return path;
    }
}
