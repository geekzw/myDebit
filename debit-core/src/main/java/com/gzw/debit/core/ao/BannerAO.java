package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.EditBannerForm;
import com.gzw.debit.core.form.base.BasePageRequest;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.dal.model.BannerDO;

import java.util.List;

/**
 * auth:gujian
 * time:2018/7/14
 * email:gujian@maihaoche.com
 * describe:
 */
public interface BannerAO {

    /**
     * 获取banner列表
     * @param form
     * @return
     */
    BaseResponse<List<BannerDO>> getBannerList(BasePageRequest form);

    /**
     * 修改banner
     * @param form
     * @return
     */
    BaseResponse<Boolean> editBanner(EditBannerForm form);

    /**
     * 删除banner
     * @param form
     * @return
     */
    BaseResponse<Boolean> deleteBanner(EditBannerForm form);
}
