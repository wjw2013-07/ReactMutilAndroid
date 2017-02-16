package mix.react.com.reactsecond.nativepack;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.PromiseImpl;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by codemanwang on 2017/2/16.
 */

public class ImagePickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private static final int IMAGE_PICKER_REQUEST = 467081;
    private static final String E_ACTIVITY_DOES_NOT_EXIST = "E_ACTIVITY_DOES_NOT_EXIST";
    private static final String E_PICKER_CANCELLED = "E_PICKER_CANCELLED";
    private static final String E_FAILED_TO_SHOW_PICKER = "E_FAILED_TO_SHOW_PICKER";
    private static final String E_NO_IMAGE_DATA_FOUND = "E_NO_IMAGE_DATA_FOUND";

    private Promise mPromise = new PromiseImpl(null, null);


    public ImagePickerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(this);
    }

    @ReactMethod
    public void pickImage(){
        Activity currentActivity = getCurrentActivity();

        if (currentActivity == null){
            mPromise.reject(E_ACTIVITY_DOES_NOT_EXIST, "Activity doesn't exist");
            return;
        }

        try{
            final Intent galleryIntent = new Intent(Intent.ACTION_PICK);
            galleryIntent.setType("image/*");

            final Intent chooserIntent = Intent.createChooser(galleryIntent, "pick an image");
            currentActivity.startActivityForResult(chooserIntent, IMAGE_PICKER_REQUEST);
        }catch (Exception e){
            mPromise.reject(E_FAILED_TO_SHOW_PICKER, e);
            mPromise = null;
        }
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_PICKER_REQUEST){
            if (mPromise != null){
                if (resultCode == Activity.RESULT_CANCELED){
                    mPromise.reject(E_PICKER_CANCELLED, "Image picker was canceled");
                }else if (resultCode == Activity.RESULT_OK){
                    Uri uri = data.getData();

                    if (uri == null){
                        mPromise.reject(E_ACTIVITY_DOES_NOT_EXIST, "No image data found");
                    }else {
                        mPromise.resolve(uri.toString());
                        Toast.makeText(getReactApplicationContext(), uri.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                mPromise = null;
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public String getName() {
        return "ImagePickerModule";
    }
}
