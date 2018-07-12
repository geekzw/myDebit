package com.gzw.debit.core.ao.impl;

import com.gzw.debit.common.entry.User;
import com.gzw.debit.core.ao.AnalyzeAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.EditAnalyzeRuleForm;
import com.gzw.debit.core.form.UserInfoForm;
import com.gzw.debit.core.form.base.BasePageRequest;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.AnalyzeRuleManager;
import com.gzw.debit.core.manager.UserManager;
import com.gzw.debit.core.utils.DateUtil;
import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.core.utils.UserUtil;
import com.gzw.debit.core.vo.AnalyzeRuleVO;
import com.gzw.debit.core.vo.UserInfoVO;
import com.gzw.debit.dal.model.AnalyzeRuleDO;
import com.gzw.debit.dal.model.UserDO;
import com.gzw.debit.dal.query.AnalyzeRuleQuery;
import com.gzw.debit.dal.query.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    private static Logger logger = LoggerFactory.getLogger(AnalyzeAOImpl.class);

    @Autowired
    private UserManager userManager;
    @Autowired
    private AnalyzeRuleManager ruleManager;

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

    @Override
    public BaseResponse<List<AnalyzeRuleVO>> getAnalyzeRule() {
        List<AnalyzeRuleVO> ruleVOS = new ArrayList<>();
        AnalyzeRuleQuery query = new AnalyzeRuleQuery();
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode());
        List<AnalyzeRuleDO> ruleDOS = ruleManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(ruleDOS)){
            return BaseResponse.create(ruleVOS);
        }

        ruleDOS.forEach(item->{
            AnalyzeRuleVO ruleVO = new AnalyzeRuleVO();
            BeanUtils.copyProperties(item,ruleVO);
            ruleVOS.add(ruleVO);
        });

        return BaseResponse.create(ruleVOS);
    }

    @Override
    public BaseResponse<Boolean> editAnalyzeRule(EditAnalyzeRuleForm form) {
        User user = UserUtil.getUser();
        if(user == null){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到用户信息，请重新登录");
        }
        if(user.getType()!=0){
            return BaseResponse.create(Const.LOGIC_ERROR,"无权限");
        }
        if(form.getId() == null){
            return BaseResponse.create(Const.PARAMS_ERROR,"规则id不能为空");
        }

        AnalyzeRuleDO ruleDO = ruleManager.selectByPrimaryKey(form.getId());
        if(ruleDO == null || ruleDO.getStatus() == StatusEnum.DELETE_STATUS.getCode()){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到对应的规则");
        }
        //是否有修改
        boolean flag = false;
        if(form.getType()!=null && form.getType()!=ruleDO.getType()){
            ruleDO.setType(form.getType());
            flag = true;
        }
        if(form.getListCount()!=null && form.getListCount()!=ruleDO.getListCount()){
            ruleDO.setListCount(form.getListCount());
            flag = true;
        }
        if(form.getDetailCount()!=null && form.getDetailCount()!=ruleDO.getDetailCount()){
            ruleDO.setDetailCount(form.getDetailCount());
            flag = true;
        }

        if(!flag){
            return BaseResponse.create(Const.LOGIC_ERROR,"无修改内容");
        }

        int col = ruleManager.updateByPrimaryKeySelective(ruleDO);
        if(col < 1){
            logger.error("修改规则失败，规则id={}",form.getId());
        }

        return BaseResponse.create(true);
    }
}
