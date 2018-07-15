package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.EditAnalyzeRuleForm;
import com.gzw.debit.core.form.UserInfoForm;
import com.gzw.debit.core.form.base.BasePageForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.vo.AnalyzeRuleVO;
import com.gzw.debit.core.vo.UserInfoVO;

import java.util.List;

/**
 * auth:gujian
 * time:2018/7/10
 * email:gujian@maihaoche.com
 * describe:
 */
public interface AnalyzeAO {

    /**
     * 获取注册用户
     * @param form
     * @return
     */
    BaseResponse<List<UserInfoVO>> getRegister(UserInfoForm form);

    /**
     * 获取规则列表
     * @return
     */
    BaseResponse<List<AnalyzeRuleVO>> getAnalyzeRule(BasePageForm form);

    /**
     * 编辑规则列表
     * @return
     */
    BaseResponse<Boolean> editAnalyzeRule(EditAnalyzeRuleForm form);
}
