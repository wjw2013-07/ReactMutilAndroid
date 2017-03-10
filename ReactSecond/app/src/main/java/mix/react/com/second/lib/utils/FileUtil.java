package mix.react.com.second.lib.utils;

import android.content.Context;
import android.text.TextUtils;

import com.facebook.common.file.FileUtils;
import com.nothome.delta.Delta;
import com.nothome.delta.DiffWriter;
import com.nothome.delta.GDiffPatcher;
import com.nothome.delta.GDiffWriter;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
                    String parentPath = file.getParent();
                    File dir = new File(parentPath);
                    if (!dir.exists()){
                        dir.mkdirs();
                    }
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

    /***
     * 根据传入的新旧文件以及生成的目标位置，生成patch文件
     * @param oldPath
     * @param newPath
     * @param specifyPath
     */
    public static void productPatch(String oldPath, String newPath, String specifyPath){
        if (TextUtils.isEmpty(oldPath)) {
            return;
        }
        if (TextUtils.isEmpty(newPath)) {
            return;
        }
        if (TextUtils.isEmpty(specifyPath)) {
            return;
        }

        try {
            DiffWriter output = null;
            File sourceFile = null;
            File targetFile = null;
            sourceFile = new File(oldPath);
            targetFile = new File(newPath);
            output = new GDiffWriter(new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(new File(
                            specifyPath)))));
            if (sourceFile.length() > Integer.MAX_VALUE
                    || targetFile.length() > Integer.MAX_VALUE) {
                System.err.println("source or target is too large, max length is "
                                + Integer.MAX_VALUE);
                System.err.println("aborting..");
            }
            Delta d = new Delta();
            d.compute(sourceFile, targetFile, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 合并patch和bundle
     * @param bundlePath
     * @param patchPath
     */
    public static void mergePatchBundle(String bundlePath, String patchPath, String newPath){
        if (TextUtils.isEmpty(bundlePath) || TextUtils.isEmpty(patchPath)){
            return;
        }
        try {
            GDiffPatcher patcher = new GDiffPatcher();
            File deffFile = new File(patchPath);
            File updatedFile = new File(newPath);
            patcher.patch(new File(bundlePath), deffFile, updatedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(patchPath);
        file.delete();
    }

    public static void movieImgToGoal(Context context){
        String zipImgDir = StoreUtil.getBundleDir(context) + "img";//解压缩所得图片路径
        String imgGoalDir = StoreUtil.getBundleDir(context) + "hotupdate/drawable-mdpi/";
        try{
            File zipFile = new File(zipImgDir);
            if (!zipFile.isDirectory()){
                return;
            }

            File[] zipImgFiles = zipFile.listFiles();
            if (zipImgFiles == null || zipImgFiles.length == 0){
                return;
            }
            int length = zipImgFiles.length;
            for (int i = 0; i < length; i++){
                File file = zipImgFiles[i];
                if (file == null || !file.exists()){
                    continue;
                }

                StreamUtil.moveFileToGoalDir(file, imgGoalDir);
            }

            FileUtil.deleteFile(zipImgDir);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void reName(String source, String dest){
        try{
            FileUtils.rename(new File(source), new File(dest));
        } catch (FileUtils.RenameException e) {
            e.printStackTrace();
        }
    }
}
