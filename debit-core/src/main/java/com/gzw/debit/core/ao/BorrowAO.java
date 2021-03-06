package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.EditBorrowForm;
import com.gzw.debit.core.form.ListSearchForm;
import com.gzw.debit.core.form.base.BasePageForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.dal.model.BorrowDO;

import java.util.List;

/**
 * auth:gujian
 * time:2018/7/14
 * email:gujian@maihaoche.com
 * describe:
 */
public interface BorrowAO {

    /**
     * 添加borrow
     * @param form
     * @return
     */
    BaseResponse<Boolean> addBorrow(EditBorrowForm form);

    /**
     * 获取Borrow列表
     * @param form
     * @return
     */
    BaseResponse<List<BorrowDO>> getBannerList(ListSearchForm form);

    /**
     * 修改Borrow
     * @param form
     * @return
     */
    BaseResponse<Boolean> editBanner(EditBorrowForm form);

    /**
     * 删除Borrow
     * @param form
     * @return
     */
    BaseResponse<Boolean> deleteBanner(EditBorrowForm form);
}
