package com.gzw.debit.core.utils;

import com.gzw.debit.common.entry.User;
import com.gzw.debit.common.enums.UserRoleEnum;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.form.base.BaseResponse;

/**
 * auth:gujian
 * time:2018/7/14
 * email:gujian@maihaoche.com
 * describe:
 */
public class CheckUtil {

    public static BaseResponse<Boolean> checkPermision(){
        User user = UserUtil.getUser();
        if(user == null){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到登录信息，请登录");
        }
        if(UserRoleEnum.ROLE_ADMIN.getCode()!=user.getType()){
            return BaseResponse.create(Const.LOGIC_ERROR,"无权限");
        }
        return BaseResponse.create(true);
    }
}
