package com.gzw.debit.common.utils;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

/**
 * auth:gujian
 * time:2018/6/29
 * email:gujian@maihaoche.com
 * describe:
 */
public class ThreadPoolExecutorUtil {

    private static ThreadPoolExecutor INSTENCE;

    public static ThreadPoolExecutor getInstence(){
        if(INSTENCE == null){

            INSTENCE = new ThreadPoolExecutor(10,100, 1L,TimeUnit.HOURS,new LinkedBlockingQueue(),
                    Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        }
        return INSTENCE;
    }

}
