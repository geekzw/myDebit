package com.gzw.debit.core.ao.impl;

import com.gzw.debit.common.annotation.Admin;
import com.gzw.debit.core.ao.BannerAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.EditBannerForm;
import com.gzw.debit.core.form.base.BasePageRequest;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.BannerManager;
import com.gzw.debit.core.utils.CheckUtil;
import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.dal.model.BannerDO;
import com.gzw.debit.dal.query.BannerQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * auth:gujian
 * time:2018/7/14
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class BannerAOImpl implements BannerAO {

    @Autowired
    private BannerManager bannerManager;

    @Override
    public BaseResponse<List<BannerDO>> getBannerList(BasePageRequest form) {
        if(form.getPageNo() == null){
            form.setPageNo(BasePageRequest.DEFAULT_NO);
        }
        if(form.getPageSize() == null){
            form.setPageSize(BasePageRequest.DEFAULT_SIZE);
        }

        BannerQuery query = new BannerQuery();
        query.setPageNo(form.getPageNo());
        query.setPageSize(form.getPageSize());
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode());
        List<BannerDO> bannerDOS = bannerManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(bannerDOS)){
            return BaseResponse.create(new ArrayList<>());
        }
        BaseResponse<List<BannerDO>> response = BaseResponse.create(bannerDOS);
        response.setPageNo(form.getPageNo());
        response.setPageSize(form.getPageSize());
        response.setTotalCount(bannerManager.countByQuery(query));
        return response;
    }

    @Override
    public BaseResponse<Boolean> editBanner(EditBannerForm form) {

        if(form.getId() == null){
            return BaseResponse.create(Const.PARAMS_ERROR,"bannerId不能为空");
        }
        BannerDO bannerDO = bannerManager.selectByPrimaryKey(form.getId());
        if(bannerDO == null || bannerDO.getStatus().equals(StatusEnum.DELETE_STATUS.getCode())){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到对应的banner信息");
        }

        boolean flag = false;

        if(!StringUtil.isEmpty(form.getImage())&& !form.getImage().equals(bannerDO.getImage())){
            bannerDO.setImage(form.getImage());
            flag = true;
        }
        if(!StringUtil.isEmpty(form.getUrl())&& !form.getUrl().equals(bannerDO.getUrl())){
            bannerDO.setUrl(form.getUrl());
            flag = true;
        }
        if(form.getBannerOrder()!=null&& form.getBannerOrder()!=bannerDO.getBannerOrder()){
            bannerDO.setBannerOrder(form.getBannerOrder());
            flag = true;
        }

        if(flag == false){
            return BaseResponse.create(Const.LOGIC_ERROR,"没有修改内容");
        }

        int col = bannerManager.updateByPrimaryKeySelective(bannerDO);
        if(col < 1){
            return BaseResponse.create(Const.LOGIC_ERROR,"banner信息更新失败");
        }

        return BaseResponse.create(true);
    }

    @Override
    public BaseResponse<Boolean> deleteBanner(EditBannerForm form) {
        if(form.getId() == null){
            return BaseResponse.create(Const.PARAMS_ERROR,"bannerId不能为空");
        }
        BannerDO bannerDO = bannerManager.selectByPrimaryKey(form.getId());
        if(bannerDO == null || bannerDO.getStatus().equals(StatusEnum.DELETE_STATUS.getCode())){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到对应的banner信息");
        }

        bannerDO.setStatus(0);
        int col = bannerManager.updateByPrimaryKeySelective(bannerDO);
        if(col < 1){
            return BaseResponse.create(Const.LOGIC_ERROR,"banner删除失败");
        }

        return BaseResponse.create(true);
    }
}
