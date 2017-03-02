package mix.react.com.second.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import mix.react.com.second.bean.BundleVersionBean;

/**
 * Created by codemanwang on 2017/2/28.
 */

public class SpUtil {

    private static Context sContext;
    private static SharedPreferences sPreferences;
    private static final String COMMON_DATA = "common_data.sp";

    public static void init(Context context){
        sContext = context;
        sPreferences = sContext.getSharedPreferences(COMMON_DATA, 0);
    }

    public static void saveLong(String key, long value){
        if (TextUtils.isEmpty(key)){
            return;
        }

        if (sPreferences == null){
            sPreferences = sContext.getSharedPreferences(COMMON_DATA, 0);
        }

        sPreferences.edit().putLong(key, value).commit();
    }

    public static long getLong(String key){
        if (TextUtils.isEmpty(key)){
            return -1;
        }

        if (sPreferences == null){
            sPreferences = sContext.getSharedPreferences(COMMON_DATA, 0);
        }

        return sPreferences.getLong(key, -1);
    }

    public static void saveString(String key, String value){
        if (TextUtils.isEmpty(key)|| TextUtils.isEmpty(value)){
            return;
        }

        if (sPreferences == null){
            sPreferences = sContext.getSharedPreferences(COMMON_DATA, 0);
        }

        sPreferences.edit().putString(key, value).commit();
    }

    public static String getString(String key){
        if (TextUtils.isEmpty(key)){
            return "";
        }

        if (sPreferences == null){
            sPreferences = sContext.getSharedPreferences(COMMON_DATA, 0);
        }

        return sPreferences.getString(key, "");
    }

    //Rn bundle升级相关信息存储
    public static void saveBundleVersionBean(BundleVersionBean versionBean, boolean isSaveVersion){
        if (versionBean == null){
            return;
        }

        if (!TextUtils.isEmpty(versionBean.getCompleteMd5())){
            saveString("bundle_complete_md5", versionBean.getCompleteMd5());
        }
        if (!TextUtils.isEmpty(versionBean.getPatchMd5())){
            saveString("bundle_patch_md5", versionBean.getPatchMd5());
        }
        if (!TextUtils.isEmpty(versionBean.getZipMd5())){
            saveString("bundle_zip_md5", versionBean.getZipMd5());
        }
        if (isSaveVersion){
            saveLong("bundle_version", versionBean.getBundleVersion());
        }
    }
    public static String getBundleCMd5(){
        return getString("bundle_complete_md5");
    }
    public static String getBundlePMd5(){
        return getString("bundle_patch_md5");
    }
    public static String getBundleZipMd5(){
        return getString("bundle_zip_md5");
    }
    public static long getBundleVersion(){
        return getLong("bundle_version");
    }

}
