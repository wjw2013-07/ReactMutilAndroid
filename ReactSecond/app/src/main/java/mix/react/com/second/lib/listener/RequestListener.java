package mix.react.com.second.lib.listener;

/**
 * Created by codemanwang on 2017/2/27.
 */

public interface RequestListener<T> {
    void onSuccess(T responseBean);
    void onError(T errorBean);
}
