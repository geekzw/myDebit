package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.LoginForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.UserInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
 * auth:gujian
 * time:2018/6/24
 * email:gujian@maihaoche.com
 * describe:
 */
public interface UserAO {

    /**
     * 登录
     * @param form
     * @return
     */
    BaseResponse login(LoginForm form, HttpServletRequest request);

    /**
     * 注册
     * @param form
     * @return
     */
    BaseResponse<Boolean> register(LoginForm form);

    /**
     * 获取注册验证码
     * @param phone
     * @return
     */
    BaseResponse<String> getSmsCode(String phone);


    /**
     * 登录
     * @param form
     * @return
     */
    BaseResponse loginPc(LoginForm form, HttpServletRequest request);



}
