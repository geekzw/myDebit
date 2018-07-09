package com.gzw.debit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * auth:gujian
 * time:2018/7/9
 * email:gujian@maihaoche.com
 * describe:
 */
@Controller
public class H5Controller {


    @GetMapping(value = "/h5/register.html")
    public String register(){
        return "/promotion_register.html";
    }
}
