package mix.react.com.reactsecond.activity;

import android.content.Intent;
import android.text.TextUtils;

import com.facebook.react.ReactActivity;

import java.util.concurrent.ArrayBlockingQueue;

import javax.annotation.Nullable;

/**
 * Created by codemanwang on 2017/2/8.
 */

public class ReactComponentActivity extends ReactActivity {

    public static ArrayBlockingQueue<String> sQueue = new ArrayBlockingQueue<String>(1);

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "depthcomponent";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 200){
            String result = data.getStringExtra("three_result");
            if (!TextUtils.isEmpty(result)){
                sQueue.add(result);
            }else {
                sQueue.add("无数据啦");
            }
        }else {
            sQueue.add("木有回调...");
        }
    }
}
