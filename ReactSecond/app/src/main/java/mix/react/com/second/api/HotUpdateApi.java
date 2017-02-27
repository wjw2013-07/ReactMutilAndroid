package mix.react.com.second.api;

import mix.react.com.second.Constant;
import mix.react.com.second.bean.BundleVersionBean;
import mix.react.com.second.lib.listener.RequestListener;
import mix.react.com.second.lib.net.HttpRequestBottom;

/**
 * Created by codemanwang on 2017/2/27.
 */

public class HotUpdateApi {

    /***
     * 检验bundleVersion是否要升级
     * @param listener
     */
    public static void checkIsDownloadBundle(RequestListener listener){
        if (listener == null){
            return;
        }

        String url = Constant.URL_PREFIX + "bundle_version.json";
        HttpRequestBottom.sendHttpGet(url, BundleVersionBean.class, listener);
    }


}
