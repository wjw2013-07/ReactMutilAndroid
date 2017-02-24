package mix.react.com.second.nativepack;

import android.app.Activity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import mix.react.com.second.view.LoadingDialog;

/**
 * Created by codemanwang on 2017/2/24.
 */

public class DialogModule extends ReactContextBaseJavaModule {

    private LoadingDialog mLoadingDialog;

    public DialogModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "DialogModule";
    }

    @ReactMethod
    public void showLoadingDialog(){
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing()){
            return;
        }
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(currentActivity);
        }

        if (!mLoadingDialog.isShowing()){
            mLoadingDialog.show();
        }
    }

    @ReactMethod
    public void hideLoadingDialog(){
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing()){
            return;
        }

        if (mLoadingDialog == null){
            return;
        }

        if (mLoadingDialog.isShowing()){
            mLoadingDialog.cancel();
        }
    }

}
