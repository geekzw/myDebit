package com.gzw.debit.core.ao.impl;

import com.gzw.debit.core.ao.AnalyzeAO;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.UserInfoForm;
import com.gzw.debit.core.form.base.BasePageRequest;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.UserManager;
import com.gzw.debit.core.utils.DateUtil;
import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.core.vo.UserInfoVO;
import com.gzw.debit.dal.model.UserDO;
import com.gzw.debit.dal.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * auth:gujian
 * time:2018/7/10
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class AnalyzeAOImpl implements AnalyzeAO {

    @Autowired
    private UserManager userManager;

    @Override
    public BaseResponse<List<UserInfoVO>> getRegister(UserInfoForm form) {
        List<UserInfoVO> userInfoVOS = new ArrayList<>();
        if(form.getPageNo() == null){
            form.setPageNo(BasePageRequest.DEFAULT_NO);
        }
        if(form.getPageSize() == null){
            form.setPageSize(BasePageRequest.DEFAULT_SIZE);
        }
        UserQuery query = new UserQuery();
        query.setPageNo(form.getPageNo());
        query.setPageSize(form.getPageSize());
        UserQuery.Criteria criteria = query.createCriteria();
        if(!StringUtil.isEmpty(form.getStartTime())){
            LocalDateTime startTime = DateUtil.String2Local(form.getStartTime(),null);
            if(startTime!=null){
                criteria.andGmtCreateGreaterThanOrEqualTo(startTime);
            }
        }

        if(!StringUtil.isEmpty(form.getEndTime())){
            LocalDateTime endTime = DateUtil.String2Local(form.getEndTime(),null);
            if(endTime!=null){
                criteria.andGmtCreateLessThanOrEqualTo(endTime);
            }
        }

        criteria.andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode());
        List<UserDO> userDOS = userManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(userDOS)){
            return BaseResponse.create(userInfoVOS);
        }

        userDOS.forEach(item->{
            UserInfoVO infoVO = new UserInfoVO();
            infoVO.setUsername(item.getUsername());
            infoVO.setDevicesType(item.getFromWhere());
            infoVO.setFromWhere(item.getChannelId());
            infoVO.setRegisterTime(DateUtil.dateISO8601Format(item.getGmtCreate()));
            userInfoVOS.add(infoVO);
        });
        BaseResponse response = BaseResponse.create(userInfoVOS);
        response.setPageNo(form.getPageNo());
        response.setPageSize(form.getPageSize());
        response.setTotalCount(userManager.countByQuery(query));
        return response;
    }
}
