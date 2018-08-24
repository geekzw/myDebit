package com.gzw.debit.core.ao.impl;

import com.gzw.debit.core.ao.FileAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.ErrorEnum;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.DownLoadForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.DownUrlManager;
import com.gzw.debit.core.utils.FilePathUtil;
import com.gzw.debit.dal.model.DownUrlDO;
import com.gzw.debit.dal.query.DownUrlQuery;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * auth:gujian
 * time:2018/7/5
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class FileAOImpl implements FileAO {

    @Autowired
    private DownUrlManager downUrlManager;

    @Override
    public BaseResponse<String> downLoadAndroidFile(HttpServletRequest request, HttpServletResponse response) {

        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        String fileName= "/myDebit.apk";
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

    @Override
    public BaseResponse<String> getDownLoadUrl(DownLoadForm form,HttpServletRequest request,HttpServletResponse response) {

        if(form.getDevicesType() == null){
            return BaseResponse.create(Const.PARAMS_ERROR,"类型不能为空");
        }
        if(form.getDevicesType()!=1 && form.getDevicesType()!=2){
            return BaseResponse.create(Const.PARAMS_ERROR,"非法的类型");
        }

        DownUrlQuery query = new DownUrlQuery();
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                .andTypeEqualTo(form.getDevicesType());

        List<DownUrlDO> downUrlDOS = downUrlManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(downUrlDOS)){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到对应的下载地址");
        }

        if(form.getDevicesType() == 2){
            try {
                response.sendRedirect(downUrlDOS.get(0).getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            downLoadAndroidFile(request,response);
        }



        return BaseResponse.create(downUrlDOS.get(0).getUrl());
    }
}
