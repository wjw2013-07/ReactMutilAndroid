package mix.react.com.second.lib.utils;

import android.text.TextUtils;
import android.util.Log;

import mix.react.com.second.Constant;

/**
 * Created by codemanwang on 2017/2/28.
 */

public class LogUtil {

    public static void logCommon(String message){
        if (TextUtils.isEmpty(message)){
            return;
        }
        if (Constant.IS_DEBUG){
            Log.i("common", message);
        }
    }

    public static void logMd5(String message){
        if (TextUtils.isEmpty(message)){
            return;
        }
        if (Constant.IS_DEBUG){
            Log.i("md5", message);
        }
    }
}
