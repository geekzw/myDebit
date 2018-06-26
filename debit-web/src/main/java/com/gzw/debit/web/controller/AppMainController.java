package com.gzw.debit.web.controller;

import com.gzw.debit.core.ao.AppMainAO;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.BanAndBorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
@RestController
public class AppMainController {

    @Autowired
    private AppMainAO appMainAO;

    @GetMapping(value = "/mainView.json")
    public BaseResponse<BanAndBorVO> getMainView(){
        return appMainAO.getMainData();
    }
}
