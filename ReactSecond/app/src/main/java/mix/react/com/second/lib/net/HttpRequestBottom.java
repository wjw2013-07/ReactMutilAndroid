package mix.react.com.second.lib.net;

import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import mix.react.com.second.lib.bean.BaseError;
import mix.react.com.second.lib.listener.RequestListener;
import mix.react.com.second.lib.utils.GsonUtil;
import mix.react.com.second.lib.utils.StreamUtil;
import mix.react.com.second.lib.utils.ThreadUtil;

/**
 *
 * Created by codemanwang on 2017/2/27.
 */

public class HttpRequestBottom {


    public static void sendHttpGet(final String url, final Class cl, final RequestListener listener){

        ThreadUtil.asyncTask(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url2 = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
                    int status = connection.getResponseCode();
                    if (status != 200){
                        listener.onError(new BaseError(status, ""));
                        return;
                    }
                    InputStream is = connection.getInputStream();
                    if (is == null){
                        listener.onError(new BaseError(status, ""));
                        return;
                    }

                    String data = StreamUtil.iStreamToString(is);
                    if (TextUtils.isEmpty(data)){
                        listener.onError(new BaseError(status, ""));
                        return;
                    }

                    Object baseSuccess = GsonUtil.jsonToBean(data, cl);
                    if (baseSuccess == null){
                        listener.onError(new BaseError(status, ""));
                        return;
                    }

                    listener.onSuccess(baseSuccess);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
