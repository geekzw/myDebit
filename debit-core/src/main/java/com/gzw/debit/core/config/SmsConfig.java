package com.gzw.debit.core.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * auth:gujian
 * time:2018/6/27
 * email:gujian@maihaoche.com
 * describe:
 */
@Configuration
public class SmsConfig {

    private static Logger logger = LoggerFactory.getLogger(SmsConfig.class);

    @Bean
    public DefaultAcsClient getDefaultAcsClient(){
        //产品名称:云通信短信API产品,开发者无需替换
        String product = "Dysmsapi";
        //产品域名,开发者无需替换
        String domain = "dysmsapi.aliyuncs.com";

        // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
        String accessKeyId;
        String accessKeySecret;

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            logger.error("初始化短信实例失败",e);
        }
        return new DefaultAcsClient(profile);
    }
}
