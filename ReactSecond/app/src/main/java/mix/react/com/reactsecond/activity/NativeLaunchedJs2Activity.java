package mix.react.com.reactsecond.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import mix.react.com.reactsecond.R;

/**
 * Created by codemanwang on 2017/2/16.
 */

public class NativeLaunchedJs2Activity extends BaseActivity {

    private TextView mTvMsg;

    @Override
    public void initView() {
        mTvMsg = (TextView) findViewById(R.id.tv_msg);
        mTvMsg.setText("当前是Js启动的Activity，等待两秒会返回");
        mTvMsg.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.putExtra("three_result", "来自NativeLaunchedJs2Activity的消息：23456");
                setResult(RESULT_OK, intent);
                finish();
            }
        }, 3000);
    }

    @Override
    public void initListener() {

    }

    @Override
    public int getContentId() {
        return R.layout.activity_launched_js;
    }

    @Override
    public void onClick(View v) {

    }
}
