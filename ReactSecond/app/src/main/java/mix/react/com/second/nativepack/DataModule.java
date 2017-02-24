package mix.react.com.second.nativepack;

import android.os.Handler;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import mix.react.com.second.bean.Person;

/**
 * Created by codemanwang on 2017/2/24.
 */

public class DataModule extends ReactContextBaseJavaModule{

    public DataModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "DataModule";
    }


    @ReactMethod
    public void loadData(final Callback successBack){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Person person = new Person("wjw", 27);
                String persons = "{\n" +
                        "\n" +
                        "    \"person\": {\n" +
                        "        \"name\": \"科技园路.\",\n" +
                        "        \"city\": \"江苏苏州\",\n" +
                        "        \"country\": \"中国\"\n" +
                        "    }\n" +
                        "    \n" +
                        "}";
                successBack.invoke(persons);
            }
        }, 2000);
    }
}
