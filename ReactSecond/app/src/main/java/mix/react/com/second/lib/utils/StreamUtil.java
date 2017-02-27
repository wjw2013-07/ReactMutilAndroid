package mix.react.com.second.lib.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 字节流处理工具类
 * Created by codemanwang on 2017/2/27.
 */

public class StreamUtil {

    /***
     * 将输入流转换成字符串
     * @param is
     * @return
     */
    public static String iStreamToString(InputStream is){
        if (is == null){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String temp;
        BufferedReader br = new BufferedReader(new InputStreamReader((is)));
        try {
            while ((temp = br.readLine()) != null){
                sb.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }


}
