package com.gzw.debit.core.ao.impl;

import com.gzw.debit.core.ao.PcMainViewAO;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.DayAliveManager;
import com.gzw.debit.core.manager.MerchantManager;
import com.gzw.debit.core.manager.UserManager;
import com.gzw.debit.core.utils.DateUtil;
import com.gzw.debit.core.vo.PcMainViewVO;
import com.gzw.debit.dal.model.ext.StrIntKeyValue;
import com.gzw.debit.dal.query.ext.AliveDataQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * auth:gujian
 * time:2018/7/15
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class PcMainViewAOImpl implements PcMainViewAO {

    @Autowired
    private DayAliveManager dayAliveManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private MerchantManager merchantManager;


    @Override
    public BaseResponse<PcMainViewVO> getMainViewData() {

        PcMainViewVO pcMainViewVO = new PcMainViewVO();
        LocalDateTime endTime = DateUtil.getTodayLastTime();
        LocalDateTime startTime = DateUtil.getBefore30Day(DateUtil.getToday0Time());
        AliveDataQuery query = new AliveDataQuery();
        query.setStartTime(startTime);
        query.setEndTime(endTime);

        List<StrIntKeyValue> aliveDataDOS = dayAliveManager.getAliveData(query);
        List<StrIntKeyValue> registerDataDOS = userManager.getRegisterData(query);
        List<StrIntKeyValue> merchantRegisterDataDOS = merchantManager.getMerchantRegisterData();
        if(CollectionUtils.isEmpty(aliveDataDOS)){
            pcMainViewVO.setAliveDatas(new ArrayList<>());
        }else{
            pcMainViewVO.setAliveDatas(aliveDataDOS);
        }

        if(CollectionUtils.isEmpty(registerDataDOS)){
            pcMainViewVO.setRegisterDatas(new ArrayList<>());
        }else{
            pcMainViewVO.setRegisterDatas(registerDataDOS);
        }
        if(CollectionUtils.isEmpty(merchantRegisterDataDOS)){
            pcMainViewVO.setMerchantRegisterDatas(new ArrayList<>());
        }else{
            pcMainViewVO.setMerchantRegisterDatas(merchantRegisterDataDOS);
        }

        return null;
    }
}
