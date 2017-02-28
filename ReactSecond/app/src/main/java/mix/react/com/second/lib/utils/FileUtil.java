package mix.react.com.second.lib.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 文件相关工具类
 * Created by codemanwang on 2017/2/28.
 */

public class FileUtil {


    /***
     * 解压缩zip文件
     * @param path
     * @param context
     */
    public static void decompression(String path, Context context){
        LogUtil.LogCommon("文件校验成功，开始解压");
        if (TextUtils.isEmpty(path)){
            return;
        }
        try {
            ZipInputStream zIs = new ZipInputStream(new FileInputStream(path));
            ZipEntry zipEntry;
            String szName;
            while ((zipEntry = zIs.getNextEntry()) != null){
                szName = zipEntry.getName();
                if (zipEntry.isDirectory()){
                    szName = szName.substring(0, szName.length() -  1);
                    File folder = new File(StoreUtil.getBundleDir(context) + File.separator + szName);
                    folder.mkdirs();
                }else {
                    File file = new File(StoreUtil.getBundleDir(context) + File.separator + szName);
                    file.createNewFile();
                    StreamUtil.iStreamToFile(zIs, file);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void deleteFile(String path){
        if (TextUtils.isEmpty(path)){
            return;
        }

        File file = new File(path);
        if (file.exists()){
            file.delete();
        }

    }
}
