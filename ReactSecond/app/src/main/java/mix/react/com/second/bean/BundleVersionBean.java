package mix.react.com.second.bean;

import mix.react.com.second.lib.bean.BaseSuccess;

/**
 * Created by codemanwang on 2017/2/27.
 */

public class BundleVersionBean extends BaseSuccess {

    private int bundleVersion;
    private String url;
    private String md5;

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

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
