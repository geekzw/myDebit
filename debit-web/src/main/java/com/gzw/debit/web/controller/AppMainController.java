package com.gzw.debit.web.controller;

import com.gzw.debit.core.ao.AppMainAO;
import com.gzw.debit.core.ao.FileAO;
import com.gzw.debit.core.ao.VersionAO;
import com.gzw.debit.core.form.BorDetailForm;
import com.gzw.debit.core.form.DownLoadForm;
import com.gzw.debit.core.form.VersionForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.BanAndBorVO;
import com.gzw.debit.core.vo.BorrowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
@RestController
public class AppMainController {

    @Autowired
    private AppMainAO appMainAO;
    @Autowired
    private VersionAO versionAO;
    @Autowired
    private FileAO fileAO;

    @GetMapping(value = "/mainView.json")
    public BaseResponse<BanAndBorVO> getMainView(){
        return appMainAO.getMainData();
    }

    @GetMapping(value = "/auth/borrowDetail.json")
    public BaseResponse<BorrowVO> getBorrowDetail(@ModelAttribute BorDetailForm form){
        return appMainAO.getBorrowDetail(form);
    }

    @GetMapping(value = "/versionStatus.json")
    public BaseResponse<Boolean> versionStatus(@ModelAttribute VersionForm form){
        return versionAO.getVersionStatus(form);
    }

    @GetMapping(value = "/download.json")
    public BaseResponse<String> download(@ModelAttribute DownLoadForm form, HttpServletRequest request, HttpServletResponse response){
        return fileAO.downLoadAndroidFile(request,response);
    }

    @GetMapping(value = "/getDownloadUrl.json")
    public BaseResponse<String> getDownloadUrl(@ModelAttribute DownLoadForm form,HttpServletRequest request,HttpServletResponse response) throws IOException {
        return fileAO.getDownLoadUrl(form,request,response);

    }
}
