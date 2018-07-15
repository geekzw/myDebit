package com.gzw.debit.core.ao.impl;

import com.gzw.debit.core.ao.BorrowAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.EditBorrowForm;
import com.gzw.debit.core.form.ListSearchForm;
import com.gzw.debit.core.form.base.BasePageForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.BorrowManager;
import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.dal.model.BorrowDO;
import com.gzw.debit.dal.query.BorrowQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * auth:gujian
 * time:2018/7/14
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class BorrowAOImpl implements BorrowAO {

    @Autowired
    private BorrowManager borrowManager;

    @Override
    public BaseResponse<List<BorrowDO>> getBannerList(ListSearchForm form) {

        if(form.getPageNo() == null){
            form.setPageNo(BasePageForm.DEFAULT_NO);
        }
        if(form.getPageSize() == null){
            form.setPageSize(BasePageForm.DEFAULT_SIZE);
        }

        BorrowQuery query = new BorrowQuery();
        BorrowQuery.Criteria criteria = query.createCriteria();
        criteria.andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode());
        if(!StringUtil.isEmpty(form.getSearchParam())){
            criteria.andProductNameLike("%"+form.getSearchParam()+"%");
        }
        List<BorrowDO> borrowDOS = borrowManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(borrowDOS)){
            return BaseResponse.create(new ArrayList<>());
        }

        BaseResponse<List<BorrowDO>> response = BaseResponse.create(borrowDOS);
        response.setPageNo(form.getPageNo());
        response.setPageSize(form.getPageSize());
        response.setTotalCount(borrowManager.countByQuery(query));

        return response;
    }

    @Override
    public BaseResponse<Boolean> editBanner(EditBorrowForm form) {

        if(form.getId() == null){
            return BaseResponse.create(Const.PARAMS_ERROR,"borrowId不能为空");
        }
        BorrowDO borrowDO = borrowManager.selectByPrimaryKey(form.getId());
        if(borrowDO == null || borrowDO.getStatus().equals(StatusEnum.DELETE_STATUS.getCode())){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到对应的borrow信息");
        }

        BaseResponse<Boolean> response = fillData(borrowDO,form);
        if(!response.isSuccess()){
            return response;
        }
        int col = borrowManager.updateByPrimaryKeySelective(borrowDO);
        if(col < 1){
            return BaseResponse.create(Const.LOGIC_ERROR,"borrow信息更新失败");
        }

        return BaseResponse.create(true);
    }

    private BaseResponse<Boolean> fillData(BorrowDO borrowDO, EditBorrowForm form) {
        boolean flag = false;

        if(!StringUtil.isEmpty(form.getImage())&& !form.getImage().equals(borrowDO.getImage())){
            borrowDO.setImage(form.getImage());
            flag = true;
        }
        if(!StringUtil.isEmpty(form.getUrl())&& !form.getUrl().equals(borrowDO.getUrl())){
            borrowDO.setUrl(form.getUrl());
            flag = true;
        }
        if(form.getBorrowOrder()!=null&& form.getBorrowOrder()!=borrowDO.getBorrowOrder()){
            borrowDO.setBorrowOrder(form.getBorrowOrder());
            flag = true;
        }

        if(!StringUtil.isEmpty(form.getDebitDesc())&& !form.getDebitDesc().equals(borrowDO.getDebitDesc())){
            borrowDO.setDebitDesc(form.getDebitDesc());
            flag = true;
        }
        if(!StringUtil.isEmpty(form.getDebitMoney())&& !form.getDebitMoney().equals(borrowDO.getDebitMoney())){
            borrowDO.setDebitMoney(form.getDebitMoney());
            flag = true;
        }
        if(form.getDebitTime()!=null&& !form.getDebitTime().equals(borrowDO.getDebitTime())){
            borrowDO.setDebitTime(form.getDebitTime());
            flag = true;
        }

        if(!StringUtil.isEmpty(form.getFastTime())&& !form.getFastTime().equals(borrowDO.getFastTime())){
            borrowDO.setFastTime(form.getFastTime());
            flag = true;
        }
        if(!StringUtil.isEmpty(form.getNeedData())&& !form.getNeedData().equals(borrowDO.getNeedData())){
            borrowDO.setNeedData(form.getNeedData());
            flag = true;
        }
        if(form.getPeopleNumber()!=null&& form.getPeopleNumber()!=borrowDO.getPeopleNumber()){
            borrowDO.setPeopleNumber(form.getPeopleNumber());
            flag = true;
        }


        if(!StringUtil.isEmpty(form.getProductName())&& !form.getProductName().equals(borrowDO.getProductName())){
            borrowDO.setProductName(form.getProductName());
            flag = true;
        }
        if(!StringUtil.isEmpty(form.getProductDetail())&& !form.getProductDetail().equals(borrowDO.getProductDetail())){
            borrowDO.setProductDetail(form.getProductDetail());
            flag = true;
        }
        if(!StringUtil.isEmpty(form.getQualification())&& !form.getQualification().equals(borrowDO.getQualification())){
            borrowDO.setQualification(form.getQualification());
            flag = true;
        }
        if(!StringUtil.isEmpty(form.getProductDetail())&& !form.getProductDetail().equals(borrowDO.getProductDetail())){
            borrowDO.setProductDetail(form.getProductDetail());
            flag = true;
        }

        if(form.getMonthyRate()!=null&& !form.getMonthyRate().equals(borrowDO.getMonthyRate().toString())){
            borrowDO.setMonthyRate(BigDecimal.valueOf(Double.parseDouble(form.getMonthyRate())));
            flag = true;
        }

        if(!flag){
            return BaseResponse.create(Const.PARAMS_ERROR,"没有可修改的内容");
        }
        return BaseResponse.create(true);
    }

    @Override
    public BaseResponse<Boolean> deleteBanner(EditBorrowForm form) {

        if(form.getId() == null){
            return BaseResponse.create(Const.PARAMS_ERROR,"bannerId不能为空");
        }

        BorrowDO borrowDO = borrowManager.selectByPrimaryKey(form.getId());
        if(borrowDO == null || borrowDO.getStatus() == StatusEnum.DELETE_STATUS.getCode()){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到对应的borrow信息");
        }

        borrowDO.setStatus(0);
        int col = borrowManager.updateByPrimaryKeySelective(borrowDO);
        if(col < 1){
            return BaseResponse.create(Const.LOGIC_ERROR,"banner删除失败");
        }

        return BaseResponse.create(true);
    }
}
