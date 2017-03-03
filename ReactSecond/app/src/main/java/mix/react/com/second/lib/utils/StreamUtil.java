package mix.react.com.second.lib.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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


    public static void iByteToFile(byte[] buffer, File file){

        if (buffer == null || file == null){
            return;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len = buffer.length;
            fos.write(buffer, 0, len);
            fos.flush();
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

    /***
     * 读取文件转换成字符串
     * @param path
     * @return
     */
    public static String getStringFromPath(String path){
        if (path == null || path.equals("")) {
            return "";
        }

        FileReader fReader;
        BufferedReader bReader = null;
        StringBuilder sBuilder = new StringBuilder();
        try {
            fReader = new FileReader(path);
            bReader = new BufferedReader(fReader);

            String temp;
            while ((temp = bReader.readLine()) != null) {
                sBuilder.append(temp);
            }
        } catch (FileNotFoundException e) {
            // TODO: handle exception
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                if (bReader != null) {
                    bReader.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return sBuilder.toString();
    }

    /***
     * 读取文件转换成字符串，增加换行符
     * @param path
     * @return
     */
    public static String getStringFromPathN(String path){
        if (path == null || path.equals("")) {
            return "";
        }

        FileReader fReader;
        BufferedReader bReader = null;
        StringBuilder sBuilder = new StringBuilder();
        try {
            fReader = new FileReader(path);
            bReader = new BufferedReader(fReader);

            String temp;
            while ((temp = bReader.readLine()) != null) {
                sBuilder.append(temp).append("\n");
            }
        } catch (FileNotFoundException e) {
            // TODO: handle exception
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                if (bReader != null) {
                    bReader.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return sBuilder.toString();
    }


    /***
     * 文件剪贴
     * @param file
     * @param goalDir
     */
    public static void moveFileToGoalDir(File file, String goalDir){
        String imgName = file.getName();
        File goalFile = new File(goalDir + imgName);
        if (goalFile.exists()){
           return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            goalFile.createNewFile();
            fis = new FileInputStream(file);
            fos = new FileOutputStream(goalFile);
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = fis.read(buffer)) != -1){
                fos.write(buffer, 0, length);
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fis != null){
                    fis.close();
                }

                if (fos != null){
                    fos.close();
                }

                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * 把assets指定文件，复制到指定目录下
     * @param fileName
     * @param context
     */
    public static File moveAssetsToGoalDir(String fileName, Context context){
        if (TextUtils.isEmpty(fileName) || context == null){
            return null;
        }

        String goalDirStr = StoreUtil.getBundleDir(context);
        InputStream is = null;
        FileOutputStream fos = null;
        File goalFile = null;
        try{
            File goalDirFile = new File(goalDirStr);
            if (!goalDirFile.exists()){
                goalDirFile.mkdirs();
            }
            goalFile = new File(goalDirStr + fileName);
            if (!goalFile.exists()){
                goalFile.createNewFile();
            }
            is = context.getAssets().open(fileName);
            fos = new FileOutputStream(goalFile);
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = is.read(buffer)) != -1){
                fos.write(buffer, 0, length);
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return goalFile;
    }

}
