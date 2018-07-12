package com.gzw.debit.core.form;

import com.gzw.debit.core.form.base.BaseForm;
import com.gzw.debit.core.form.base.BasePageRequest;
import lombok.Data;

/**
 * auth:gujian
 * time:2018/7/12
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class MerchantStreamForm extends BasePageRequest {

    private Long merchantId;
}
