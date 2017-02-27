package mix.react.com.second.lib.bean;

/**
 * 处理错误对象基类
 * Created by codemanwang on 2017/2/27.
 */

public class BaseError {

    private int code;
    private String message;


    public BaseError(int code, String msg){
        this.code = code;
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
