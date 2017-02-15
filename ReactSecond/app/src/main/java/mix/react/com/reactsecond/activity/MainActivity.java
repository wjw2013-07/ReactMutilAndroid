package mix.react.com.reactsecond.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import mix.react.com.reactsecond.R;

/**
 * Created by codemanwang on 2017/2/4.
 */

public class MainActivity extends BaseActivity {

    private RelativeLayout mRelHello;
    private RelativeLayout mRelDepth;
    private RelativeLayout mRelAboutLagou;

    @Override
    public void initView(){
        mRelHello = (RelativeLayout) findViewById(R.id.rl_hello);
        mRelDepth = (RelativeLayout) findViewById(R.id.rl_depth_component);
        mRelAboutLagou = (RelativeLayout) findViewById(R.id.rl_about_lgou);

    }

    @Override
    public void initListener() {
        mRelHello.setOnClickListener(this);
        mRelDepth.setOnClickListener(this);
        mRelAboutLagou.setOnClickListener(this);
    }

    @Override
    public int getContentId() {
        return R.layout.activity_user_setting;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_hello){
            startActivity(new Intent(this, ReactHelloActivity.class));
        } else if(id == R.id.rl_depth_component){
            startActivity(new Intent(this, ReactComponentActivity.class));
        }else if (id == R.id.rl_about_lgou){
            startActivity(new Intent(this, ReactAboutLagouActivity.class));
        }
    }
}
