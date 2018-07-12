package com.gzw.debit.core.form;

import com.gzw.debit.core.form.base.BaseForm;
import com.gzw.debit.core.form.base.BasePageRequest;
import lombok.Data;

/**
 * auth:gujian
 * time:2018/7/9
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class BuryForm extends BaseForm {

    private Long productId;

    private Integer type;
}
