package com.gzw.debit.web.controller;

import com.gzw.debit.core.ao.HtmlReplace;
import com.gzw.debit.core.ao.TestAO;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.UserManager;
import com.gzw.debit.core.utils.HttpsClientUtils;
import com.gzw.debit.dal.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestAO testAO;
    @Autowired
    private UserManager userManager;
    @Autowired
    private HtmlReplace htmlReplace;

    @GetMapping("/testRedis.json")
    public BaseResponse<String> testRedis(){
        return testAO.testRedis();
    }

    @GetMapping("/testTransaction.json")
    @Transactional
    public BaseResponse<Boolean> testTransaction(){

        UserDO userDO = new UserDO();
        userDO.setUsername("gujian");
        userDO.setPassword("123");
        userManager.insertSelective(userDO);
        testException();
        return BaseResponse.create();
    }

    private void testException() {

        String string = null;
        string.equals("");
    }

    @GetMapping("/html.json")
    public String getHtml(){
        return htmlReplace.htmlUrlReplace("http://ybqbzc.newhopeai.com/?channel=yuebang","http://ybqbzc.newhopeai.com/","http");
    }
}
