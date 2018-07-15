package com.gzw.debit.web.controller;

import com.gzw.debit.core.ao.PcMainViewAO;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.PcMainViewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * auth:gujian
 * time:2018/7/15
 * email:gujian@maihaoche.com
 * describe:
 */
@RestController
public class PcmainViewController {

    @Autowired
    private PcMainViewAO pcMainViewAO;

    @GetMapping(value = "/auth/pc/mainView.json")
    public BaseResponse<PcMainViewVO> getMainView(){
        return pcMainViewAO.getMainViewData();
    }
}
