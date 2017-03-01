package mix.react.com.second.bean;

import mix.react.com.second.lib.bean.BaseSuccess;

/**
 * Created by codemanwang on 2017/2/27.
 */

public class BundleVersionBean extends BaseSuccess {

    private int bundleVersion;
    private String url;
    private String completeMd5;
    private String patchMd5;

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


    public String getCompleteMd5() {
        return completeMd5;
    }

    public void setCompleteMd5(String completeMd5) {
        this.completeMd5 = completeMd5;
    }

    public String getPatchMd5() {
        return patchMd5;
    }

    public void setPatchMd5(String patchMd5) {
        this.patchMd5 = patchMd5;
    }
}
