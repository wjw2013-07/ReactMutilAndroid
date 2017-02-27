package mix.react.com.second.lib.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 字符串转换成bean，bean转换成字符串
 * Created by codemanwang on 2017/2/27.
 */

public class GsonUtil {

    /***
     * json转换成bean
     * @param json
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(String json, Class<T> c){
        if (TextUtils.isEmpty(json) || c == null){
            return null;
        }

        Gson gson = new GsonBuilder().create();
        try{
            return gson.fromJson(json, c);
        }catch (Exception e){

        }

        return null;
    }
}
