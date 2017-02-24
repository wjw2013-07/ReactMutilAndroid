package mix.react.com.second.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by codemanwang on 2017/2/4.
 */

public abstract class BaseActivity extends Activity implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentId());

        initView();
        initListener();
    }

    public abstract void initView();
    public abstract void initListener();
    public abstract int getContentId();

}
