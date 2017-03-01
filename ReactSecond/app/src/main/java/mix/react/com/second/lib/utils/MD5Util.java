package mix.react.com.second.lib.utils;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by codemanwang on 2017/2/28.
 */

public class MD5Util {
    private static final String TAG = "md5";

    public static boolean checkMD5(String md5, String path) {

        if (TextUtils.isEmpty(md5) || TextUtils.isEmpty(path)) {
            LogUtil.logMd5("MD5 string empty or updateFile null");
            return false;
        }
        File updateFile = new File(path);
        String calculatedDigest = calculateMD5(updateFile);
        if (calculatedDigest == null) {
            LogUtil.logMd5("calculatedDigest null");
            return false;
        }

        LogUtil.logMd5("Calculated digest: " + calculatedDigest);
        LogUtil.logMd5("Provided digest: " + md5);

        return calculatedDigest.equalsIgnoreCase(md5);
    }

    public static String calculateMD5(File updateFile) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LogUtil.logMd5("Exception while getting digest");
            return null;
        }

        InputStream is;
        try {
            is = new FileInputStream(updateFile);
        } catch (FileNotFoundException e) {
            LogUtil.logMd5("Exception while getting FileInputStream");
            return null;
        }

        byte[] buffer = new byte[8192];
        int read;
        try {
            while ((read = is.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }
            byte[] md5sum = digest.digest();
            BigInteger bigInt = new BigInteger(1, md5sum);
            String output = bigInt.toString(16);
            // Fill to 32 chars
            output = String.format("%32s", output).replace(' ', '0');
            return output;
        } catch (IOException e) {
            throw new RuntimeException("Unable to process file for MD5", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LogUtil.logMd5("Exception on closing MD5 input stream");
            }
        }
    }
}
