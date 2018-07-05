package com.gzw.debit.web.config;

import com.gzw.debit.web.interceptor.LoginInterceptor;
import com.gzw.debit.web.interceptor.SmsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
    private List<String> excludePaths = new ArrayList<>();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        excludePaths.add("/getSmsCode.json");
        excludePaths.add("/login.json");
        excludePaths.add("/register.json");
        excludePaths.add("/mainView.json");
        excludePaths.add("/versionStatus.json");
        excludePaths.add("/download.json");
        excludePaths.add("/error");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePaths);

        registry.addInterceptor(new SmsInterceptor())
                .addPathPatterns("/getSmsCode.json");

    }
}
