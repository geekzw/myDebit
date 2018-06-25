package com.gzw.debit.core.ao.impl;

import com.alibaba.fastjson.JSON;
import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.core.ao.RedisAO;
import com.gzw.debit.core.ao.UserAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.ErrorEnum;
import com.gzw.debit.core.form.LoginForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.UserManager;
import com.gzw.debit.dal.model.UserDO;
import com.gzw.debit.dal.query.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * auth:gujian
 * time:2018/6/24
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class UserAOImpl implements UserAO {
    private static Logger logger =  LoggerFactory.getLogger(UserAOImpl.class);

    @Autowired
    private UserManager userManager;
    @Autowired
    private RedisAO redisAO;

    @Override
    public BaseResponse<String> login(LoginForm form, HttpServletRequest request) {
        String sessionId = request.getSession().getId();

        if(StringUtil.isEmpty(form.getUsername())){
            return BaseResponse.create(Const.PARAMS_ERROR,"用户名不能为空");
        }
        if(StringUtil.isEmpty(form.getPassword())){
            return BaseResponse.create(Const.PARAMS_ERROR,"密码不能为空");
        }

        UserQuery userQuery = new UserQuery();
        userQuery.createCriteria().andUsernameEqualTo(form.getUsername());
        List<UserDO> userDOS = userManager.selectByQuery(userQuery);
        if(CollectionUtils.isEmpty(userDOS)){
            return BaseResponse.create(Const.LOGIC_ERROR,"用户不存在");
        }

        UserDO userDO = userDOS.get(0);
        if(!userDO.getPassword().equals(form.getPassword())){
            return BaseResponse.create(Const.LOGIC_ERROR,"密码错误");
        }
        try{
            String userDOJson = JSON.toJSONString(userDO);
            redisAO.setObject(sessionId,userDOJson);
            redisAO.setExpr(sessionId,Integer.MAX_VALUE);
        }catch (Exception e){
            logger.error("json解析错误",e);
        }
        return BaseResponse.create(sessionId);
    }

    @Override
    public BaseResponse<String> register(LoginForm form) {
        if(StringUtil.isEmpty(form.getUsername())){
            return BaseResponse.create(Const.PARAMS_ERROR,"用户名不能为空");
        }
        if(StringUtil.isEmpty(form.getPassword())){
            return BaseResponse.create(Const.PARAMS_ERROR,"密码不能为空");
        }
        if(StringUtil.isEmpty(form.getPassword())){
            return BaseResponse.create(Const.PARAMS_ERROR,"验证码不能为空");
        }

        UserQuery userQuery = new UserQuery();
        userQuery.createCriteria().andUsernameEqualTo(form.getUsername());
        List<UserDO> userDOS = userManager.selectByQuery(userQuery);
        if(userDOS!=null && userDOS.size() > 0){
            return BaseResponse.create(Const.LOGIC_ERROR,"用户已存在");
        }

        UserDO userDO = new UserDO();
        userDO.setUsername(form.getUsername());
        userDO.setPassword(form.getPassword());
        userManager.insertSelective(userDO);
        return BaseResponse.create(ErrorEnum.SUCCESS);
    }
}
