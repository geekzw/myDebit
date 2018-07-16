package com.gzw.debit.core.ao.impl;

import com.gzw.debit.common.annotation.Admin;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    @Admin
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

        fillAllData(aliveDataDOS);
        fillAllData(registerDataDOS);

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

        return BaseResponse.create(pcMainViewVO);
    }

    private void fillAllData(List<StrIntKeyValue> list) {

        LocalDate date = LocalDate.now();
        int count = 30;
        while (count>=0){

            String timeString = date.toString();
            boolean flag = false;
            for(int i = 0; i<list.size();i++){
                StrIntKeyValue keyValue = list.get(i);
                if(keyValue.getResultValue().equals(timeString)){
                    flag = true;
                    break;
                }

            }
            if(!flag){
                StrIntKeyValue keyValue1 = new StrIntKeyValue();
                keyValue1.setCount(0);
                keyValue1.setResultValue(timeString);
                list.add(keyValue1);
            }
            date = date.plusDays(-1);

            count--;
        }

        Collections.sort(list, new Comparator<StrIntKeyValue>() {
            @Override
            public int compare(StrIntKeyValue o1, StrIntKeyValue o2) {
                return o1.getResultValue().compareTo(o2.getResultValue());
            }
        });

    }
}
