package com.gzw.debit.core.form;

import com.gzw.debit.core.form.base.BasePageForm;
import lombok.Data;

/**
 * auth:gujian
 * time:2018/7/10
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class UserInfoForm extends BasePageForm {

    private String startTime;

    private String endTime;

}
