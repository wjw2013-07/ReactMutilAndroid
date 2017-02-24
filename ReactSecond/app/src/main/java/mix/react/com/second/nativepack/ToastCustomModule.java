package mix.react.com.second.nativepack;

import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by codemanwang on 2017/2/16.
 */

public class ToastCustomModule extends ReactContextBaseJavaModule {

    private static final String DURATION_SHORT = "SHORT";
    private static final String DURATION_LONG = "LONG";

    public ToastCustomModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ToastCustomModule";
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_LONG, Toast.LENGTH_LONG);
        constants.put(DURATION_SHORT, Toast.LENGTH_SHORT);
        return constants;
    }

    @ReactMethod
    public void show(String message, int duration){
        Toast.makeText(getReactApplicationContext(), message, duration).show();

        WritableMap params = Arguments.createMap();
        params.putString("native_to_js", "来自原生的消息");
        getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("native", params);

    }

    @ReactMethod
    public void measureLayout(Callback errorBack, Callback successBack){
        try {
            successBack.invoke(100, 200, 200, 200);
        }catch (Exception e){
            errorBack.invoke(e.getMessage());
        }
    }
}
