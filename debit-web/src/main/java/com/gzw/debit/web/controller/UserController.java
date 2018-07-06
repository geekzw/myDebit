package com.gzw.debit.web.controller;

import com.gzw.debit.core.ao.UserAO;
import com.gzw.debit.core.form.LoginForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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


    @GetMapping(value = "/pc/getUserInfo.json")
    public BaseResponse<UserInfoVO> getUserInfo(@RequestParam("sessionId") String sessionId,HttpServletRequest request){
        return userAO.getUserInfo(sessionId);
    }

}
