package com.tobin.top.utils;

import android.text.TextUtils;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;

/**
 * @author lijunbin
 * @date 2020/8/20
 * @email 616041023@qq.com
 * @description 文件工具类
 */
public class FileUtils {
    private final static String PREFIX_VIDEO = "video/";
    private final static String PREFIX_IMAGE = "image/";

    /**
     * 据文件后缀名判断 文件是否是视频文件
     */
    public static boolean isVideoFile(String fileName) {
        String mimeType = getMimeType(fileName);
        return !TextUtils.isEmpty(fileName) && mimeType.contains(PREFIX_VIDEO);
    }

    public static boolean isImageFile(String fileName) {
        String mimeType = getMimeType(fileName);
        return !TextUtils.isEmpty(fileName) && mimeType.contains(PREFIX_IMAGE);
    }

    private static String getMimeType(String fileName) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return fileNameMap.getContentTypeFor(fileName);
    }


    /**
     * 判断文件是否存在
     */
    public static boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
