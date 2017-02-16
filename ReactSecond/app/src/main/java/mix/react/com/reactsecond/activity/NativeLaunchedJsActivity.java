package mix.react.com.reactsecond.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import mix.react.com.reactsecond.R;

/**
 * Created by codemanwang on 2017/2/16.
 */

public class NativeLaunchedJsActivity extends BaseActivity {

    private TextView mTvMsg;

    @Override
    public void initView() {
        mTvMsg = (TextView) findViewById(R.id.tv_msg);

        if (getIntent() != null){
            String fromJs = getIntent().getStringExtra("params");
            if (!TextUtils.isEmpty(fromJs));
            mTvMsg.setText(fromJs);
        }
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
