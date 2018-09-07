package com.gzw.debit.web.controller;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * auth:gujian
 * time:2018/7/9
 * email:gujian@maihaoche.com
 * describe:
 */
@Controller
public class H5Controller {


    @GetMapping(value = "/h5/index.html")
    public String register(){
        return "/register.html";
    }


    @ResponseBody
    @GetMapping(value = "/test")
    public String test(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://t.cn/RDUF7ZL").build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }
}
