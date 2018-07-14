package com.gzw.debit.web.controller;

import com.gzw.debit.core.ao.MerchantAO;
import com.gzw.debit.core.form.*;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.MerchantVO;
import com.gzw.debit.core.vo.PcLoginInfoVO;
import com.gzw.debit.core.vo.StreamInfo;
import com.gzw.debit.core.vo.StreamInfoWrep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * auth:gujian
 * time:2018/7/12
 * email:gujian@maihaoche.com
 * describe:
 */
@RestController
public class MerchantController {

    @Autowired
    private MerchantAO merchantAO;

    @PostMapping(value = "/auth/merchant/create.json")
    public BaseResponse<Boolean> create(@RequestBody MerchantForm form){
        return merchantAO.createMerchant(form);
    }

    @PostMapping(value = "/merchant/loginPc.json")
    public BaseResponse<PcLoginInfoVO> loginPc(@RequestBody LoginForm form){
        return merchantAO.loginPc(form);
    }

    @GetMapping(value = "/auth/merchant/getMerchantList.json")
    public BaseResponse<List<MerchantVO>> getMerchantList(@ModelAttribute MerchantListForm form){
        return merchantAO.getMerchantList(form);
    }

    @PostMapping(value = "/auth/merchant/editMerchant.json")
    public BaseResponse<Boolean> editMerchant(@RequestBody EditMerchantForm form){
        return merchantAO.editMerchant(form);
    }

    @PostMapping(value = "/auth/merchant/deleteMerchant.json")
    public BaseResponse<Boolean> deleteMerchant(@RequestBody DelMerchantForm form){
        return merchantAO.deleteMerchant(form);
    }

    @GetMapping(value = "/auth/merchant/getMerchantStream.json")
    public BaseResponse<StreamInfoWrep> getMerchantStream(@ModelAttribute MerchantStreamForm form){
        return merchantAO.getMerchantStream(form);
    }
}
