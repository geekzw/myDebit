package com.gzw.debit.web.config;

import com.gzw.debit.web.interceptor.LoginInterceptor;
import com.gzw.debit.web.interceptor.SmsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/auth/**");

        registry.addInterceptor(new SmsInterceptor())
                .addPathPatterns("/getSmsCode.json");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(!registry.hasMappingForPattern("/**")){
            registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        }
        if(!registry.hasMappingForPattern("/app/**")){
            registry.addResourceHandler("/app/**").addResourceLocations("classpath:/static/app/");
        }
        if(!registry.hasMappingForPattern("/admin/**")){
            registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/static/admin/");
        }
    }
}
