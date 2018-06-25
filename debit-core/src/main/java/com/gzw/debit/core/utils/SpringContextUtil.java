package com.gzw.debit.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by qinyisheng on 16/9/23.
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        setApplicationContextutil(appContext);
    }

    public static void setApplicationContextutil(ApplicationContext appContext) {
        applicationContext = appContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> Map<String, T> getBeanOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
