package com.gzw.debit.core.ao.impl;

import com.alibaba.fastjson.JSON;
import com.gzw.debit.common.entry.User;
import com.gzw.debit.common.utils.PhoneFormatCheckUtil;
import com.gzw.debit.core.ao.SendSmsAO;
import com.gzw.debit.core.utils.SmsCodeUtil;
import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.core.ao.RedisAO;
import com.gzw.debit.core.ao.UserAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.ErrorEnum;
import com.gzw.debit.core.form.LoginForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.UserManager;
import com.gzw.debit.core.vo.UserInfoVO;
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
    @Autowired
    private SendSmsAO sendSmsAO;

    @Override
    public BaseResponse<String> login(LoginForm form, HttpServletRequest request) {

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

        String sessionId;

        try{
            String oldSession = (String) redisAO.get(userDO.getUsername());
            if(StringUtil.isEmpty(oldSession)){
                sessionId = request.getSession().getId();
                User user = new User();
                user.setUsername(userDO.getUsername());
                user.setPassword(userDO.getPassword());
                redisAO.set(sessionId,user,Integer.MAX_VALUE);
                redisAO.set(userDO.getUsername(),sessionId,Integer.MAX_VALUE);
            }else{
                sessionId = oldSession;
            }
            return BaseResponse.create(sessionId);

        }catch (Exception e){
            logger.error("json解析错误",e);
            return BaseResponse.create(Const.LOGIC_ERROR,"登录失败");
        }

    }

    @Override
    public BaseResponse<String> register(LoginForm form) {
        if(StringUtil.isEmpty(form.getUsername())){
            return BaseResponse.create(Const.PARAMS_ERROR,"用户名不能为空");
        }
        if(StringUtil.isEmpty(form.getPassword())){
            return BaseResponse.create(Const.PARAMS_ERROR,"密码不能为空");
        }
        if(StringUtil.isEmpty(form.getVerifyCode())){
            return BaseResponse.create(Const.PARAMS_ERROR,"验证码不能为空");
        }

        String code = (String) redisAO.get(form.getUsername());
        if(StringUtil.isEmpty(code)){
            return BaseResponse.create(Const.LOGIC_ERROR,"验证码已过期");
        }
        if(!code.equals(form.getVerifyCode())){
            return BaseResponse.create(Const.LOGIC_ERROR,"验证码不正确");
        }

        UserQuery userQuery = new UserQuery();
        userQuery.createCriteria().andUsernameEqualTo(form.getUsername());
        List<UserDO> userDOS = userManager.selectByQuery(userQuery);
        if(userDOS!=null && userDOS.size() > 0){
            return BaseResponse.create(Const.USER_EXIST,"手机号已注册，请直接登录");
        }

        UserDO userDO = new UserDO();
        userDO.setUsername(form.getUsername());
        userDO.setPassword(form.getPassword());
        userDO.setFromWhere((form.getFormWhere() == null?0:form.getFormWhere()));
        userManager.insertSelective(userDO);
        return BaseResponse.create(ErrorEnum.SUCCESS);
    }

    @Override
    public BaseResponse<String> getSmsCode(String phone) {
        if(StringUtil.isEmpty(phone)){
            return BaseResponse.create(Const.PARAMS_ERROR,"手机号码不能为空");
        }
        if(!PhoneFormatCheckUtil.checkCellphone(phone)){
            return BaseResponse.create(Const.PARAMS_ERROR,"手机号码格式不正确");
        }
        UserQuery userQuery = new UserQuery();
        userQuery.createCriteria().andUsernameEqualTo(phone);
        List<UserDO> userDOS = userManager.selectByQuery(userQuery);
        if(userDOS!=null && userDOS.size() > 0){
            return BaseResponse.create(Const.LOGIC_ERROR,"手机号已注册，请直接登录");
        }
        String code = (String) redisAO.get(phone);
        if(StringUtil.isEmpty(code)){
            code = SmsCodeUtil.createRandomVcode();
        }
        BaseResponse<String> smsResponse = sendSmsAO.sendSms(phone,code);
        if(!smsResponse.isSuccess()){
            return smsResponse;
        }
        redisAO.set(phone,smsResponse.getData(),RedisAOImpl.SMS_CODE_EXPR);

        return smsResponse;
    }

    @Override
    public BaseResponse<UserInfoVO> getUserInfo(String sessionId) {

        if(StringUtil.isEmpty(sessionId)){
            return BaseResponse.create(Const.PARAMS_ERROR,"sessionId不能为空");
        }

        User user = (User) redisAO.get(sessionId);
        if(user == null){
            return BaseResponse.create(Const.LOGIC_ERROR,"session过期");
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUsername(user.getUsername());

        return BaseResponse.create(userInfoVO);
    }
}
