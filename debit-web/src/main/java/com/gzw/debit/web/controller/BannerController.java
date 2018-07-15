package com.gzw.debit.web.controller;

import com.gzw.debit.common.annotation.Admin;
import com.gzw.debit.core.ao.BannerAO;
import com.gzw.debit.core.ao.BorrowAO;
import com.gzw.debit.core.form.EditBannerForm;
import com.gzw.debit.core.form.EditBorrowForm;
import com.gzw.debit.core.form.ListSearchForm;
import com.gzw.debit.core.form.base.BasePageForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.dal.model.BannerDO;
import com.gzw.debit.dal.model.BorrowDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * auth:gujian
 * time:2018/7/14
 * email:gujian@maihaoche.com
 * describe:
 */
@RestController
public class BannerController {

    @Autowired
    private BannerAO bannerAO;
    @Autowired
    private BorrowAO borrowAO;

    @GetMapping(value = "/auth/getBannerList.json")
    @Admin
    public BaseResponse<List<BannerDO>> getBannerList(@ModelAttribute ListSearchForm form){
        return bannerAO.getBannerList(form);
    }

    @PostMapping(value = "/auth/editBanner.json")
    @Admin
    public BaseResponse<Boolean> editBanner(@RequestBody EditBannerForm form){
        return bannerAO.editBanner(form);
    }

    @PostMapping(value = "/auth/deleteBanner.json")
    @Admin
    public BaseResponse<Boolean> deleteBanner(@RequestBody EditBannerForm form){
        return bannerAO.deleteBanner(form);
    }

    @GetMapping(value = "/auth/getBorrowList.json")
    @Admin
    public BaseResponse<List<BorrowDO>> getBorrow(@ModelAttribute ListSearchForm form){
        return borrowAO.getBannerList(form);
    }

    @PostMapping(value = "/auth/editBorrow.json")
    @Admin
    public BaseResponse<Boolean> editBorrow(@RequestBody EditBorrowForm form){
        return borrowAO.editBanner(form);
    }

    @PostMapping(value = "/auth/deleteBorrow.json")
    @Admin
    public BaseResponse<Boolean> deleteBorrow(@RequestBody EditBorrowForm form){
        return borrowAO.deleteBanner(form);
    }

}
