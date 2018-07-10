package com.gzw.debit.web.controller;

import com.gzw.debit.core.ao.AnalyzeAO;
import com.gzw.debit.core.form.UserInfoForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * auth:gujian
 * time:2018/7/10
 * email:gujian@maihaoche.com
 * describe:
 */
@RestController
public class AnalyzeController {

    @Autowired
    private AnalyzeAO analyzeAO;


    @GetMapping(value = "/auth/getRegisterInfo.json")
    public BaseResponse<List<UserInfoVO>> getRegisterInfo(@RequestBody UserInfoForm form){
        return analyzeAO.getRegister(form);
    }
}
