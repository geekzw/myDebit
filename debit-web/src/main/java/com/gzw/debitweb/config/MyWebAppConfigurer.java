package com.gzw.debitweb.config;

import com.gzw.debitweb.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;

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
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(new ArrayList<>(Arrays.asList("/login.json","/register.json")));
    }
}
