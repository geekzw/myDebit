package com.gzw.debit.core.ao.impl;

import com.gzw.debit.common.entry.User;
import com.gzw.debit.core.ao.BuryAO;
import com.gzw.debit.core.entry.Const;
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
    public BaseResponse<Boolean> insertBury(Long productId) {
        User user = UserUtil.getUser();
        if(user == null){
            logger.error("找不到登录信息");
            return BaseResponse.create(Const.PARAMS_ERROR,"产品id不能为空");
        }
        if(productId == null){
            logger.error("产品id不能为空");
            return BaseResponse.create(Const.PARAMS_ERROR,"产品id不能为空");
        }

        BuryDO buryDO = new BuryDO();
        buryDO.setProductId(productId);
        buryDO.setUserId(user.getUserId());
        long col = buryManager.insertSelective(buryDO);
        if(col < 1){
            logger.error("插入埋点失败,userid:{},productId:{}",user.getUserId(),productId);
            return BaseResponse.create(Const.PARAMS_ERROR,"插入埋点失败");
        }
        return BaseResponse.create(true);
    }
}
