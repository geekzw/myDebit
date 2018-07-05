package com.gzw.debit.web.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * auth:gujian
 * time:2018/7/5
 * email:gujian@maihaoche.com
 * describe:
 */
@Configuration
public class UploadFileConfiguration {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("20Mb");
        factory.setMaxRequestSize("40Mb");
        return factory.createMultipartConfig();
    }
}

