package mix.react.com.second.lib.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 统一管理异步任务
 * Created by codemanwang on 2017/2/27.
 */

public class ThreadUtil {

    private static ExecutorService mService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() / 2 + 1);


    public static void asyncTask(Runnable r){
        if (r == null){
            return;
        }

        mService.execute(r);
    }

    public static void clearThread(){
        mService.shutdown();
    }

}
