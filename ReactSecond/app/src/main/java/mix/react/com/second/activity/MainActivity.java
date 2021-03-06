package mix.react.com.second.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
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

    private RelativeLayout mRelChancePlus;
    private RelativeLayout mRelDepth;
    private RelativeLayout mRelMovieList;
    private RelativeLayout mRelBusinessmanDeail;
    private RelativeLayout mRelAboutLagou;
    private RelativeLayout mRelUserProtocl;

    private BundleVersionBean mBean;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            SpUtil.saveBundleVersionBean(mBean, true);
        }
    };

    @Override
    public void initView(){
        mRelChancePlus = (RelativeLayout) findViewById(R.id.rl_chance_plus);
        mRelDepth = (RelativeLayout) findViewById(R.id.rl_depth_component);
        mRelMovieList = (RelativeLayout) findViewById(R.id.rl_movie_list);
        mRelBusinessmanDeail = (RelativeLayout) findViewById(R.id.rl_busninessman_detail);
        mRelAboutLagou = (RelativeLayout) findViewById(R.id.rl_about_lgou);
        mRelUserProtocl = (RelativeLayout) findViewById(R.id.rl_user_protocl);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.PACK_PATCH_SUCCESS);
        registerReceiver(mReceiver, filter);

    }

    @Override
    public void initListener() {
        mRelChancePlus.setOnClickListener(this);
        mRelDepth.setOnClickListener(this);
        mRelMovieList.setOnClickListener(this);
        mRelBusinessmanDeail.setOnClickListener(this);
        mRelAboutLagou.setOnClickListener(this);
        mRelUserProtocl.setOnClickListener(this);

        HotUpdateApi.checkIsDownloadBundle(new RequestListener() {
            @Override
            public void onSuccess(Object responseBean) {
                if (responseBean instanceof BundleVersionBean){
                    mBean = (BundleVersionBean) responseBean;
                    long buildVersion = SpUtil.getBundleVersion();
                    if (buildVersion == -1){
                        buildVersion = Constant.BUNDLE_VERSION;
                    }
                    if (mBean.getBundleVersion() > buildVersion){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showDownloadDialog(mBean);
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
                        Constant.mDownloadBundle = 0;
                        SpUtil.saveBundleVersionBean(mBean, false);
                        DownLoadUtil.sysDownLoad(bean.getUrl(),
                                StoreUtil.getZipPath(MainActivity.this), MainActivity.this);
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
        if(id == R.id.rl_depth_component){
            Intent intent = new Intent(this, ReactComponentActivity.class);
            intent.putExtra("data", "Activity传递给Js的消息：34567");
            startActivity(intent);
        }else if (id == R.id.rl_chance_plus){
            startActivity(new Intent(this, ChancePlusActivity.class));
        }else if (id == R.id.rl_about_lgou){
            startActivity(new Intent(this, ReactAboutLagouActivity.class));
        }else if (id == R.id.rl_user_protocl){
            startActivity(new Intent(this, UserProtoclActivity.class));
        }else if (id == R.id.rl_movie_list){
            startActivity(new Intent(this, MovieListActivity.class));
        }else if (id == R.id.rl_busninessman_detail){
            startActivity(new Intent(this, BusinessManDetailActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null){
            unregisterReceiver(mReceiver);
        }
    }
}
