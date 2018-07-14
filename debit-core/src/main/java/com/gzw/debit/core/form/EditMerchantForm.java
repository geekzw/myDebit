package com.gzw.debit.core.form;

import com.gzw.debit.core.form.base.BaseForm;
import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/7/11
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class EditMerchantForm implements Serializable{

    private Long id;

    private String username;

    private String name;

    private String password;
}
