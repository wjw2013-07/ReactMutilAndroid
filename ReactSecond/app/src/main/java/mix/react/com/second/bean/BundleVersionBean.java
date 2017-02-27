package mix.react.com.second.bean;

import mix.react.com.second.lib.bean.BaseSuccess;

/**
 * Created by codemanwang on 2017/2/27.
 */

public class BundleVersionBean extends BaseSuccess {

    private int bundleVersion;
    private String url;


    public int getBundleVersion() {
        return bundleVersion;
    }

    public void setBundleVersion(int bundleVersion) {
        this.bundleVersion = bundleVersion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
