package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.PcMainViewVO;

/**
 * auth:gujian
 * time:2018/7/15
 * email:gujian@maihaoche.com
 * describe:
 */
public interface PcMainViewAO {

    BaseResponse<PcMainViewVO> getMainViewData();

}
