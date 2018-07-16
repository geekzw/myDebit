package com.gzw.debit.core.vo;

import com.gzw.debit.dal.model.ext.MerchantDataDO;
import com.gzw.debit.dal.model.ext.StrIntKeyValue;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * auth:gujian
 * time:2018/7/15
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class PcMainViewVO implements Serializable{

    private List<StrIntKeyValue> aliveDatas;

    private List<StrIntKeyValue> registerDatas;

    private List<MerchantDataDO> merchantRegisterDatas;

}
