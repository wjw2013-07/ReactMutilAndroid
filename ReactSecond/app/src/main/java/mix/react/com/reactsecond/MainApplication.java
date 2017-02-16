package mix.react.com.reactsecond;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

import mix.react.com.reactsecond.nativepack.AnImagePickerReactPackage;
import mix.react.com.reactsecond.nativepack.AnIntentReactPackage;
import mix.react.com.reactsecond.nativepack.AnToastReactPackage;

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
                    new AnIntentReactPackage()
            );
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
    }
}
