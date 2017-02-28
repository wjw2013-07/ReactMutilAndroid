package mix.react.com.second.lib.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    public static void iStreamToFile(InputStream is, File file){
        if (is == null || file == null){
            return;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1){
                fos.write(buffer, 0, len);
                fos.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
