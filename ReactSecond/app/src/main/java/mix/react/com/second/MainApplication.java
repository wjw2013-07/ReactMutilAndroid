package mix.react.com.second;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import mix.react.com.second.lib.utils.SpUtil;
import mix.react.com.second.lib.utils.StoreUtil;
import mix.react.com.second.nativepack.AnDataReactPackage;
import mix.react.com.second.nativepack.AnDialogReactPackage;
import mix.react.com.second.nativepack.AnImagePickerReactPackage;
import mix.react.com.second.nativepack.AnIntentReactPackage;
import mix.react.com.second.nativepack.AnToastReactPackage;

/**
 * Created by codemanwang on 2017/2/3.
 */

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new CMainReactPackage(),
                    new AnToastReactPackage(),
                    new AnImagePickerReactPackage(),
                    new AnIntentReactPackage(),
                    new AnDialogReactPackage(),
                    new AnDataReactPackage()
            );
        }

        @Nullable
        @Override
        protected String getJSBundleFile() {
            String path = StoreUtil.getBundleDir(getApplicationContext()) + "index.android.bundle";
            File file = new File(path);
            if (file != null && file.exists()){
                return path;
            }
            return super.getJSBundleFile();
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
        SpUtil.init(getApplicationContext());

    }


}
