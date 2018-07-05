package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.BorDetailForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.BanAndBorVO;
import com.gzw.debit.core.vo.BorrowVO;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
public interface AppMainAO {

    /**
     * 获取首页列表
     * @return
     */
    BaseResponse<BanAndBorVO> getMainData();

    /**
     * 获取item详情
     * @param form
     * @return
     */
    BaseResponse<BorrowVO> getBorrowDetail(BorDetailForm form);
}
