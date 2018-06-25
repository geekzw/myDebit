package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.BanAndBorVO;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
public interface AppMainAO {

    BaseResponse<BanAndBorVO> getMainData();
}
