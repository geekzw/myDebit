package com.gzw.debit.core.ao.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.gzw.debit.core.ao.RedisAO;
import com.gzw.debit.core.ao.SendSmsAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.utils.SmsCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * auth:gujian
 * time:2018/6/26
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class SendSmsAOImpl implements SendSmsAO {

    private static Logger logger = LoggerFactory.getLogger(SendSmsAOImpl.class);

    public static final String SUCCESS = "OK";

    String signName = "管家记账";
    String templateCode = "SMS_138075700";

    @Autowired
    private IAcsClient acsClient;

    @Override
    public BaseResponse<String> sendSms(String phone,String code){
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为

        SmsCodeUtil.CodeEntry codeEntry = new SmsCodeUtil.CodeEntry("code",code);
        request.setTemplateParam(JSON.toJSONString(codeEntry));

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch

        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
            if(sendSmsResponse.getCode().equals(SUCCESS)){
                return BaseResponse.create(code);
            }else{
                logger.error("验证码发送失败："+JSON.toJSONString(sendSmsResponse));
                return BaseResponse.create(Const.LOGIC_ERROR,"验证码发送失败");
            }
        } catch (ClientException e) {
            logger.error("验证码发送失败："+JSON.toJSONString(sendSmsResponse));
            return BaseResponse.create(Const.LOGIC_ERROR,"验证码发送失败");
        }

    }

    public static void main(String[] args){
        BaseResponse response = new SendSmsAOImpl().sendSms("17682310938","123456");
    }
}
