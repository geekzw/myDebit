package com.gzw.debit.core.ao;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.gzw.debit.core.form.base.BaseResponse;

/**
 * auth:gujian
 * time:2018/6/26
 * email:gujian@maihaoche.com
 * describe:
 */
public interface SendSmsAO {

    BaseResponse<String> sendSms(String phone,String code);
}
