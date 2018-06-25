package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.LoginForm;
import com.gzw.debit.core.form.base.BaseResponse;

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
    BaseResponse<String> register(LoginForm form);

}
