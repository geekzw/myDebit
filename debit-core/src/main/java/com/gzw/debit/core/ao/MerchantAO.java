package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.DelMerchantForm;
import com.gzw.debit.core.form.EditMerchantForm;
import com.gzw.debit.core.form.MerchantForm;
import com.gzw.debit.core.form.base.BasePageRequest;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.MerchantVO;

import java.util.List;

/**
 * auth:gujian
 * time:2018/7/9
 * email:gujian@maihaoche.com
 * describe:
 */
public interface MerchantAO {
    /**
     * 插入商家
     * @param form
     * @return
     */
    BaseResponse<Boolean> createMerchant(MerchantForm form);

    /**
     * 获取商家列表
     * @param request
     * @return
     */
    BaseResponse<List<MerchantVO>> getMerchantList(BasePageRequest request);

    /**
     * 删除商家
     * @param form
     * @return
     */
    BaseResponse<Boolean> deleteMerchant(DelMerchantForm form);

    /**
     * 编辑商家信息
     * @param form
     * @return
     */
    BaseResponse<Boolean> editMerchant(EditMerchantForm form);
}
