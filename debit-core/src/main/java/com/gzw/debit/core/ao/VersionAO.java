package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.base.BaseResponse;

/**
 * auth:gujian
 * time:2018/6/30
 * email:gujian@maihaoche.com
 * describe:
 */
public interface VersionAO {

    BaseResponse<Boolean> getVersionStatus(Integer version);
}
