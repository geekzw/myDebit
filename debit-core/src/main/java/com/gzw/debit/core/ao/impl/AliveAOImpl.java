package com.gzw.debit.core.ao.impl;

import com.gzw.debit.common.entry.HeaderEntry;
import com.gzw.debit.common.entry.User;
import com.gzw.debit.common.utils.SpringContextUtil;
import com.gzw.debit.common.utils.ThreadPoolExecutorUtil;
import com.gzw.debit.common.utils.WebSessionUtil;
import com.gzw.debit.core.ao.AliveAO;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.manager.DayAliveManager;
import com.gzw.debit.core.utils.DateUtil;
import com.gzw.debit.dal.model.DayAliveDO;
import com.gzw.debit.dal.query.DayAliveQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

/**
 * auth:gujian
 * time:2018/7/14
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class AliveAOImpl implements AliveAO {
    private static Logger logger = LoggerFactory.getLogger(AliveAOImpl.class);

    @Override
    public void insertAlive(User user) {

        ThreadPoolExecutorUtil.getInstence().execute(()->{
            HeaderEntry headerEntry = WebSessionUtil.getHeader();
            DayAliveManager dayAliveManager = SpringContextUtil.getBean(DayAliveManager.class);
            DayAliveQuery query = new DayAliveQuery();
            query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                    .andUserIdEqualTo(user.getUserId())
                    .andPackagetypeEqualTo(headerEntry.getPackageType())
                    .andGmtCreateGreaterThanOrEqualTo(DateUtil.getToday0Time())
                    .andGmtCreateLessThanOrEqualTo(DateUtil.getTodayLastTime());

            List<DayAliveDO> dayAliveDOS = dayAliveManager.selectByQuery(query);
            if(CollectionUtils.isEmpty(dayAliveDOS)){
                DayAliveDO dayAliveDO = new DayAliveDO();
                dayAliveDO.setUserId(user.getUserId());
                dayAliveDO.setCount(1);
                dayAliveDO.setPackagetype(headerEntry.getPackageType());
                LocalDate date = LocalDate.now();
                dayAliveDO.setAliveDate(date);
                long col = dayAliveManager.insertSelective(dayAliveDO);
                if(col < 1){
                    logger.error("日活信息插入失败,userId={}",user.getUserId());
                }
            }else{
                DayAliveDO dayAliveDO = dayAliveDOS.get(0);
                dayAliveDO.setCount(dayAliveDO.getCount()+1);
                int col = dayAliveManager.updateByPrimaryKeySelective(dayAliveDO);
                if(col < 1){
                    logger.error("日活信息插入失败,userId={}",user.getUserId());
                }
            }


        });
    }
}
