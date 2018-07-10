package com.gzw.debit.core.ao.impl;

import com.gzw.debit.common.entry.User;
import com.gzw.debit.core.ao.BuryAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.form.BuryForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.BuryManager;
import com.gzw.debit.core.utils.UserUtil;
import com.gzw.debit.dal.model.BuryDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * auth:gujian
 * time:2018/7/9
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class BuryAOImpl implements BuryAO{

    private Logger logger = LoggerFactory.getLogger(BuryAOImpl.class);

    @Autowired
    private BuryManager buryManager;

    @Override
    public BaseResponse<Boolean> insertBury(BuryForm form) {
        User user = UserUtil.getUser(form.getSessionId());
        if(user == null){
            logger.error("找不到登录信息");
            return BaseResponse.create(Const.PARAMS_ERROR,"找不到登录信息");
        }
        if(form.getProductId() == null){
            logger.error("产品id不能为空");
            return BaseResponse.create(Const.PARAMS_ERROR,"产品id不能为空");
        }

        BuryDO buryDO = new BuryDO();
        buryDO.setProductId(form.getProductId());
        buryDO.setUserId(user.getUserId());
        buryDO.setFormWhere(form.getDeviceType() == null?1:form.getDeviceType());
        long col = buryManager.insertSelective(buryDO);
        if(col < 1){
            logger.error("插入埋点失败,userid:{},productId:{}",user.getUserId(),form.getProductId());
            return BaseResponse.create(Const.PARAMS_ERROR,"插入埋点失败");
        }
        return BaseResponse.create(true);
    }
}
