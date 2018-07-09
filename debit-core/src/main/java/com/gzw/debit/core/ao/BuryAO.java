package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.BuryForm;
import com.gzw.debit.core.form.base.BaseResponse;

/**
 * auth:gujian
 * time:2018/7/9
 * email:gujian@maihaoche.com
 * describe:
 */
public interface BuryAO {

    BaseResponse<Boolean> insertBury(BuryForm form);
}
