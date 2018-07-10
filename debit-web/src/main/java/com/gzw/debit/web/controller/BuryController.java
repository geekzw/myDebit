package com.gzw.debit.web.controller;

import com.gzw.debit.core.ao.BuryAO;
import com.gzw.debit.core.form.BuryForm;
import com.gzw.debit.core.form.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * auth:gujian
 * time:2018/7/9
 * email:gujian@maihaoche.com
 * describe:
 */
@RestController
public class BuryController {

    @Autowired
    private BuryAO buryAO;

    @PostMapping(value = "/postBury.json")
    public BaseResponse<Boolean> insertBury(@RequestBody BuryForm form){
        return buryAO.insertBury(form);
    }
}
