package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.*;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.MerchantVO;
import com.gzw.debit.core.vo.StreamInfo;
import com.gzw.debit.core.vo.StreamInfoWrep;

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
    BaseResponse<List<MerchantVO>> getMerchantList(MerchantListForm request);

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

    /**
     * 获取商家引流的用户
     */
    BaseResponse<StreamInfoWrep> getMerchantStream(MerchantStreamForm form);
}
