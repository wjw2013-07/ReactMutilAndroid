package mix.react.com.second.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RelativeLayout;

import mix.react.com.second.Constant;
import mix.react.com.second.R;
import mix.react.com.second.api.HotUpdateApi;
import mix.react.com.second.bean.BundleVersionBean;
import mix.react.com.second.lib.listener.RequestListener;
import mix.react.com.second.lib.utils.DownLoadUtil;
import mix.react.com.second.lib.utils.SpUtil;
import mix.react.com.second.lib.utils.StoreUtil;

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

        HotUpdateApi.checkIsDownloadBundle(new RequestListener() {
            @Override
            public void onSuccess(Object responseBean) {
                if (responseBean instanceof BundleVersionBean){
                    final BundleVersionBean bean = (BundleVersionBean) responseBean;
                    if (bean.getBundleVersion() > Constant.BUNDLE_VERSION){
                        SpUtil.saveString("bundle_md5", bean.getMd5());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showDownloadDialog(bean);
                            }
                        });
                    }
                }
            }

            private void showDownloadDialog(final BundleVersionBean bean) {
                //弹出升级对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("升级提示");
                builder.setMessage("新功能升级");
                builder.setPositiveButton("升级", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DownLoadUtil.sysDownLoad(bean.getUrl(),
                                StoreUtil.getBundlePath(MainActivity.this), MainActivity.this);
                    }
                });
                builder.setNegativeButton("不升级", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setCancelable(true);
                builder.create().show();
            }

            @Override
            public void onError(Object errorBean) {

            }
        });
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
            Intent intent = new Intent(this, ReactComponentActivity.class);
            intent.putExtra("data", "Activity传递给Js的消息：34567");
            startActivity(intent);
        }else if (id == R.id.rl_about_lgou){
            startActivity(new Intent(this, ReactAboutLagouActivity.class));
        }
    }
}
