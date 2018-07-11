package com.gzw.debit.web.controller;

import com.gzw.debit.core.ao.MerchantAO;
import com.gzw.debit.core.ao.UserAO;
import com.gzw.debit.core.form.DelMerchantForm;
import com.gzw.debit.core.form.EditMerchantForm;
import com.gzw.debit.core.form.LoginForm;
import com.gzw.debit.core.form.MerchantForm;
import com.gzw.debit.core.form.base.BasePageRequest;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.MerchantVO;
import com.gzw.debit.core.vo.PcLoginInfoVO;
import com.gzw.debit.core.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
@RestController
public class UserController {

    @Autowired
    private UserAO userAO;
    @Autowired
    private MerchantAO merchantAO;

    @PostMapping(value = "/login.json")
    public BaseResponse login(@RequestBody LoginForm form, HttpServletRequest request){
        return userAO.login(form,request);
    }

    @PostMapping(value = "/register.json")
    public BaseResponse register(@RequestBody LoginForm form){
        return userAO.register(form);
    }

    @GetMapping(value = "/getSmsCode.json")
    public BaseResponse<String> getSmsCode(@RequestParam("phone") String phone){
        return userAO.getSmsCode(phone);
    }

    @PostMapping(value = "/test.json")
    public BaseResponse test(){
        return BaseResponse.create();
    }


    @PostMapping(value = "/merchant/loginPc.json")
    public BaseResponse<PcLoginInfoVO> loginPc(@RequestBody LoginForm form, HttpServletRequest request){
        return userAO.loginPc(form,request);
    }

    @PostMapping(value = "/merchant/create.json")
    public BaseResponse<Boolean> create(@RequestBody MerchantForm form){
        return merchantAO.createMerchant(form);
    }

    @GetMapping(value = "/auth/merchant/getMerchantList.json")
    public BaseResponse<List<MerchantVO>> getMerchantList(@ModelAttribute BasePageRequest form){
        return merchantAO.getMerchantList(form);
    }

    @PostMapping(value = "/auth/merchant/editMerchant.json")
    public BaseResponse<Boolean> editMerchant(@RequestBody EditMerchantForm form){
        return merchantAO.editMerchant(form);
    }

    @PostMapping(value = "/auth/merchant/deleteMerchant.json")
    public BaseResponse<Boolean> deleteMerchant(@RequestBody DelMerchantForm form){
        return merchantAO.deleteMerchant(form);
    }

}
