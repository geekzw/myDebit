package com.gzw.debit.core.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * auth:gujian
 * time:2018/7/5
 * email:gujian@maihaoche.com
 * describe:
 */
public class FilePathUtil {


    public static String getUpDownFilePath(String fileName){
        //获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!path.exists()) path = new File("");
        System.out.println("path:"+path.getAbsolutePath());

//如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(),"static/apk/");
        if(!upload.exists()) upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());
        return upload.getAbsolutePath()+fileName;
    }
}
