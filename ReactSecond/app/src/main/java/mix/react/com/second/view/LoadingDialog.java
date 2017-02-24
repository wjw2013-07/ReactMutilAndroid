package mix.react.com.second.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageView;

import mix.react.com.second.R;

public class LoadingDialog extends Dialog {
    private ImageView mLoadingBase;
    private AnimationDrawable animationDrawable;

    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_progress_dialog);
        mLoadingBase = (ImageView) findViewById(R.id.iv_loading_base);
        animationDrawable = (AnimationDrawable) mLoadingBase.getBackground();
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        window.setWindowAnimations(R.style.dialog_anim);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void show() {
        super.show();
        animationDrawable.start();
    }

    @Override
    public void cancel() {
        super.cancel();
        animationDrawable.stop();
    }
}