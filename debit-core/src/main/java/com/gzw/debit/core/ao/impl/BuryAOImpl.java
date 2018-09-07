package com.gzw.debit.core.ao.impl;

import com.gzw.debit.common.entry.HeaderEntry;
import com.gzw.debit.common.entry.User;
import com.gzw.debit.common.utils.WebSessionUtil;
import com.gzw.debit.core.ao.BuryAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.BuryForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.BuryManager;
import com.gzw.debit.core.utils.DateUtil;
import com.gzw.debit.core.utils.UserUtil;
import com.gzw.debit.dal.model.BuryDO;
import com.gzw.debit.dal.query.BuryQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
        HeaderEntry header = WebSessionUtil.getHeader();
        User user = UserUtil.getUser();
        //不需要登录的h5
        if(form.getType() == 3 && user == null){
            user = new User();
            user.setUserId(0L);
        }
        if(user == null){
            logger.error("找不到登录信息");
            return BaseResponse.create(Const.PARAMS_ERROR,"找不到登录信息");
        }
        if(form.getProductId() == null){
            logger.error("产品id不能为空");
            return BaseResponse.create(Const.PARAMS_ERROR,"产品id不能为空");
        }

        int isBanner = form.getIsBanner() == null?0:form.getIsBanner()?1:0;
        int fromWhere = form.getFromWhere() == null?0:form.getFromWhere();
        BuryQuery query = new BuryQuery();
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                .andUserIdEqualTo(user.getUserId())
                .andProductIdEqualTo(form.getProductId())
                .andPackagetypeEqualTo(header.getPackageType())
                .andIsBannerEqualTo(isBanner)
                .andGmtCreateGreaterThanOrEqualTo(DateUtil.getToday0Time())
                .andGmtCreateLessThanOrEqualTo(DateUtil.getTodayLastTime());
        List<BuryDO> buryDOS = buryManager.selectByQuery(query);
        BuryDO buryDO;
        int type = form.getType() == null?1:form.getType();
        if(CollectionUtils.isEmpty(buryDOS)){
            buryDO = new BuryDO();
            buryDO.setUserId(user.getUserId());
            buryDO.setProductId(form.getProductId());
            buryDO.setIsBanner(isBanner);
            buryDO.setPackagetype(header.getPackageType());
            buryDO.setFromWhere(fromWhere);
            if(type == 1){
                buryDO.setListCount(1);
            }else{
                buryDO.setDetailCount(1);
            }
            Long col = buryManager.insertSelective(buryDO);
            if(col < 1){
                logger.error("插入埋点信息出错，userId={}",user.getUserId());
                return BaseResponse.create(Const.LOGIC_ERROR,"插入埋点信息出错");
            }

        }else{
            buryDO = buryDOS.get(0);
            if(type == 1){
                buryDO.setListCount(buryDO.getListCount()+1);
            }else{
                buryDO.setDetailCount(buryDO.getDetailCount()+1);
            }
            int col = buryManager.updateByPrimaryKeySelective(buryDO);
            if(col < 1){
                logger.error("更新埋点信息出错，userId={}",user.getUserId());
                return BaseResponse.create(Const.LOGIC_ERROR,"插入埋点信息出错");
            }
        }
        return BaseResponse.create(true);
    }
}
