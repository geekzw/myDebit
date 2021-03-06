package com.gzw.debit.web.controller;

import com.gzw.debit.core.ao.AnalyzeAO;
import com.gzw.debit.core.form.EditAnalyzeRuleForm;
import com.gzw.debit.core.form.UserInfoForm;
import com.gzw.debit.core.form.base.BasePageForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.AnalyzeRuleVO;
import com.gzw.debit.core.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/auth/getAnalyzeRule.json")
    public BaseResponse<List<AnalyzeRuleVO>> getAnalyzeRule(@ModelAttribute BasePageForm form){
        return analyzeAO.getAnalyzeRule(form);
    }

    @PostMapping(value = "/auth/editAnalyzeRule.json")
    public BaseResponse<Boolean> editAnalyzeRule(@RequestBody EditAnalyzeRuleForm form){
        return analyzeAO.editAnalyzeRule(form);
    }


}
