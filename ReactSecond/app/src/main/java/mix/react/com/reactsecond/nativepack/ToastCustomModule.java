package mix.react.com.reactsecond.nativepack;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

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
    }


}
