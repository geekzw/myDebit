package com.gzw.debit.core.ao.impl;

import com.gzw.debit.core.ao.FileAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.ErrorEnum;
import com.gzw.debit.core.form.DownLoadForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.utils.FilePathUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * auth:gujian
 * time:2018/7/5
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class FileAOImpl implements FileAO {
    @Override
    public BaseResponse<String> downLoadAndroidFile(DownLoadForm form, HttpServletRequest request, HttpServletResponse response) {

        if(form.getType() == null){
            form.setType(1);
        }

//        String fileName = "/myDebit.apk";
//        File file = new File(FilePathUtil.getUpDownFilePath(fileName));
//        return BaseResponse.create(file.getAbsolutePath());


        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        String fileName;
        if(form.getType() != 1){
            return BaseResponse.create(Const.LOGIC_ERROR,"此类文件暂不支持");
        }
        fileName = "/myDebit.apk";
        try {
            File file = new File(FilePathUtil.getUpDownFilePath(fileName));
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
            IOUtils.copy(fis,response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件下载失败");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return BaseResponse.create(ErrorEnum.SUCCESS);
    }
}
