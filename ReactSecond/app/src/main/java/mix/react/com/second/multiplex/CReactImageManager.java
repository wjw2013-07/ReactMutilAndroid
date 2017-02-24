package mix.react.com.second.multiplex;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.image.ReactImageView;

/**
 * Created by codemanwang on 2017/2/7.
 */
@ReactModule(name = CReactImageManager.REACT_IMAGVIEW)
public class CReactImageManager extends SimpleViewManager<ReactImageView> {

    public static final String REACT_IMAGVIEW = "RCTImageView";

    private @Nullable AbstractDraweeControllerBuilder mAbstractDraweeControllerBuilder;
    private @Nullable Object mCallerContext;


    public CReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object callerContext){
        mAbstractDraweeControllerBuilder = abstractDraweeControllerBuilder;
        mCallerContext = callerContext;
    }

    public CReactImageManager(){
        mAbstractDraweeControllerBuilder = null;
        mCallerContext = null;
    }

    public AbstractDraweeControllerBuilder getAbstractDraweeControllerBuilder(){
        if (mAbstractDraweeControllerBuilder == null){
            mAbstractDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        return mAbstractDraweeControllerBuilder;
    }

    public Object getCallerContext(){
        return mCallerContext;
    }

    @ReactProp(name = "src")
    public void setSource(ReactImageView reactImageView, @Nullable ReadableArray sources){
        reactImageView.setSource(sources);
    }

    @ReactProp(name = "loadingIndicator")
    public void setLoadIngIndicatorSource(ReactImageView reactImageView, @Nullable String source){
        reactImageView.setLoadingIndicatorSource(source);
    }

    @ReactProp(name = "borderColor", customType = "Color")
    public void setBorderColor(ReactImageView reactImageView, @Nullable Integer borderColor){
        if (borderColor == null){
            reactImageView.setBorderColor(Color.TRANSPARENT);
        }else {
            reactImageView.setBorderColor(borderColor);
        }
    }

    @ReactProp(name = "overlayColor")
    public void setOverlayColor(ReactImageView reactImageView, @Nullable Integer overLayColor){
        if (overLayColor == null){
            reactImageView.setBorderColor(Color.TRANSPARENT);
        }else {
            reactImageView.setBorderColor(overLayColor);
        }
    }

    public String getName() {
        return REACT_IMAGVIEW;
    }

    @Override
    protected ReactImageView createViewInstance(ThemedReactContext reactContext) {
        return new ReactImageView(reactContext,
                getAbstractDraweeControllerBuilder(), getCallerContext());
    }

}
