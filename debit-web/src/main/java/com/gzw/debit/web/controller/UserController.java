package com.gzw.debit.web.controller;

import com.gzw.debit.core.ao.UserAO;
import com.gzw.debit.core.form.LoginForm;
import com.gzw.debit.core.form.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/test.json")
    public BaseResponse test(@RequestBody LoginForm form){
        return BaseResponse.create();
    }


}
