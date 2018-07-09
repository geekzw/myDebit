package com.gzw.debit.core.ao.impl;

import com.gzw.debit.core.ao.MerchantAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.MerchantForm;
import com.gzw.debit.core.form.base.BasePageRequest;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.MerchantManager;
import com.gzw.debit.core.utils.SmsCodeUtil;
import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.core.vo.MerchantVO;
import com.gzw.debit.dal.model.MerchantDO;
import com.gzw.debit.dal.query.MerchantQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * auth:gujian
 * time:2018/7/9
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class MerchantAOImpl implements MerchantAO {

    private static Logger logger = LoggerFactory.getLogger(MerchantAOImpl.class);

    @Autowired
    private MerchantManager merchantManager;


    @Override
    public BaseResponse<Boolean> createMerchant(MerchantForm form) {
        if(StringUtil.isEmpty(form.getName())){
            return BaseResponse.create(Const.PARAMS_ERROR,"商家名称不能为空");
        }

        MerchantQuery query = new MerchantQuery();
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                .andNameEqualTo(form.getName());
        List<MerchantDO> merchantDOS = merchantManager.selectByQuery(query);

        if(!CollectionUtils.isEmpty(merchantDOS)){
            return BaseResponse.create(Const.LOGIC_ERROR,"商家名称不能重复");
        }
        String channel = SmsCodeUtil.createRandomVcode();
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setName(form.getName());
        merchantDO.setChannelId(channel);
        long col = merchantManager.insertSelective(merchantDO);
        if(col < 1){
            logger.error("插入商家失败,商家名称：{}",form.getName());
            return BaseResponse.create(Const.LOGIC_ERROR,"插入商家失败");
        }

        return BaseResponse.create(true);
    }

    @Override
    public BaseResponse<List<MerchantVO>> getMerchantList(BasePageRequest request) {
        List<MerchantVO> merchantVOS = new ArrayList<>();
        if(request.getPageNo() == null){
            request.setPageNo(BasePageRequest.DEFAULT_NO);
        }
        if(request.getPageSize() == null){
            request.setPageSize(BasePageRequest.DEFAULT_SIZE);
        }

        MerchantQuery query = new MerchantQuery();
        query.setPageNo(request.getPageNo());
        query.setPageSize(request.getPageSize());
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode());
        List<MerchantDO> merchantDOS = merchantManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(merchantDOS)){
            return BaseResponse.create(merchantVOS);
        }

        for(MerchantDO merchantDO : merchantDOS){
            MerchantVO merchantVO = new MerchantVO();
            merchantVO.setId(merchantDO.getId());
            merchantVO.setName(merchantDO.getName());
            merchantVO.setChannelId(merchantDO.getChannelId());
            merchantVOS.add(merchantVO);
        }

        BaseResponse response = BaseResponse.create(merchantVOS);
        response.setPageNo(request.getPageNo());
        response.setPageSize(request.getPageSize());
        response.setTotalCount(merchantManager.countByQuery(query));

        return response;
    }

    @Override
    public BaseResponse<Boolean> deleteMerchant(MerchantForm form) {
        if(form.getId() == null){
            return BaseResponse.create(Const.PARAMS_ERROR,"商家id不能为空");
        }

        MerchantQuery query = new MerchantQuery();
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                .andIdEqualTo(form.getId());
        List<MerchantDO> merchantDOS = merchantManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(merchantDOS)){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到对应的商家");
        }
        MerchantDO merchantDO = merchantDOS.get(0);
        merchantDO.setStatus(StatusEnum.DELETE_STATUS.getCode());
        long col = merchantManager.updateByPrimaryKeySelective(merchantDO);
        if(col < 1){
            logger.error("删除商家失败，商家id:{}",form.getId());
            return BaseResponse.create(Const.LOGIC_ERROR,"删除商家失败");
        }
        return BaseResponse.create(true);
    }
}
