package mix.react.com.second.nativepack;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import mix.react.com.second.activity.ReactComponentActivity;

/**
 * Created by codemanwang on 2017/2/16.
 */

public class IntentModule extends ReactContextBaseJavaModule {


    public IntentModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "IntentModule";
    }

    /***
     * Activity 跳到Js页面，传输数据
     * @param successBack
     * @param errorBack
     */
    @ReactMethod
    public void dataToJs(Callback successBack, Callback errorBack){
        try{
            Activity currentActivity = getCurrentActivity();
            if (currentActivity == null){
                return;
            }
            String result = currentActivity.getIntent().getStringExtra("data");
            if (TextUtils.isEmpty(result)){
                result = "木有数据";
            }
            successBack.invoke(result);
        }catch (Exception e){
            errorBack.invoke(e.getMessage());
        }
    }

    /***同时也可以从JS传递相关数据到原生
     * 从JS页面跳转到原生activity
     * @param name
     * @param params
     */
    @ReactMethod
    public void startActivityFromJs(String name, String params){
        try{
            Activity activity = getCurrentActivity();
            if (activity == null){
                return;
            }

            Class toActivity = Class.forName(name);
            Intent intent = new Intent(activity, toActivity);
            intent.putExtra("params", params);
            activity.startActivity(intent);
        }catch (Exception e){
            throw new JSApplicationIllegalArgumentException("不能打开Activity：" + e.getMessage());
        }
    }

    /***
     * 从JS页面跳转到Activity界面，并且等待从Activity返回的数据给JS
     * @param className
     * @param requestCode
     * @param successBack
     * @param errorBack
     */
    @ReactMethod
    public void startActivityFromJSGetResult(String className,
                                             int requestCode, Callback successBack, Callback errorBack){
        try{
            Activity activity = getCurrentActivity();
            if (activity == null){
                return;
            }

            Class toActivity = Class.forName(className);
            Intent intent = new Intent(activity, toActivity);
            activity.startActivityForResult(intent, requestCode);

            successBack.invoke(ReactComponentActivity.sQueue.take());
        }catch (Exception e){
            errorBack.invoke(e.getMessage());
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void finshActivity(){
        try{
            Activity activity = getCurrentActivity();
            if(activity == null){
                return;
            }
            activity.finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
