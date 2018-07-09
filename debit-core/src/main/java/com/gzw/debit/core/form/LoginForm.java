package com.gzw.debit.core.form;

import com.gzw.debit.core.form.base.BaseForm;
import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/6/24
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class LoginForm extends BaseForm{

    private String username;
    private String password;
    private String verifyCode;

    private Integer channelId;
}
