package com.gzw.debit.core.ao.impl;

import com.gzw.debit.common.entry.User;
import com.gzw.debit.common.enums.UserRoleEnum;
import com.gzw.debit.common.utils.WebSessionUtil;
import com.gzw.debit.core.ao.MerchantAO;
import com.gzw.debit.core.ao.RedisAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.*;
import com.gzw.debit.core.form.base.BasePageRequest;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.AnalyzeRuleManager;
import com.gzw.debit.core.manager.BuryManager;
import com.gzw.debit.core.manager.MerchantManager;
import com.gzw.debit.core.manager.UserManager;
import com.gzw.debit.core.utils.DateUtil;
import com.gzw.debit.core.utils.SmsCodeUtil;
import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.core.utils.UserUtil;
import com.gzw.debit.core.vo.MerchantVO;
import com.gzw.debit.core.vo.PcLoginInfoVO;
import com.gzw.debit.core.vo.StreamInfo;
import com.gzw.debit.core.vo.StreamInfoWrep;
import com.gzw.debit.dal.model.AnalyzeRuleDO;
import com.gzw.debit.dal.model.BuryDO;
import com.gzw.debit.dal.model.MerchantDO;
import com.gzw.debit.dal.model.UserDO;
import com.gzw.debit.dal.query.AnalyzeRuleQuery;
import com.gzw.debit.dal.query.BuryQuery;
import com.gzw.debit.dal.query.MerchantQuery;
import com.gzw.debit.dal.query.UserQuery;
import com.gzw.debit.dal.query.ext.AnalyzeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * auth:gujian
 * time:2018/7/9
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class MerchantAOImpl implements MerchantAO {

    private static Logger logger = LoggerFactory.getLogger(MerchantAOImpl.class);

    @Autowired
    private MerchantManager merchantManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private BuryManager buryManager;
    @Autowired
    private AnalyzeRuleManager ruleManager;
    @Autowired
    private RedisAO redisAO;

    @Override
    public BaseResponse<Boolean> createMerchant(MerchantForm form) {

        BaseResponse permissionResult = checkPermision();
        if(!permissionResult.isSuccess()){
            return BaseResponse.create(permissionResult.getCode(),permissionResult.getDesc());
        }

        BaseResponse paramResponse = checkParams(form);
        if(!paramResponse.isSuccess()){
            return paramResponse;
        }
        String channel = SmsCodeUtil.createRandomVcode();
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setName(form.getName());
        merchantDO.setChannelId(channel);
        merchantDO.setUsername(form.getUsername());
        merchantDO.setPassword(form.getPassword());
        merchantDO.setType(form.getType());
        long col = merchantManager.insertSelective(merchantDO);
        if(col < 1){
            logger.error("插入商家失败,商家名称：{}",form.getName());
            return BaseResponse.create(Const.LOGIC_ERROR,"插入商家失败");
        }

        return BaseResponse.create(true);
    }

    @Override
    public BaseResponse<PcLoginInfoVO> loginPc(LoginForm form) {

        if(StringUtil.isEmpty(form.getUsername())){
            return BaseResponse.create(Const.PARAMS_ERROR,"用户名不能为空");
        }
        if(StringUtil.isEmpty(form.getPassword())){
            return BaseResponse.create(Const.PARAMS_ERROR,"密码不能为空");
        }

        MerchantQuery userQuery = new MerchantQuery();
        userQuery.createCriteria().andUsernameEqualTo(form.getUsername());
        List<MerchantDO> userDOS = merchantManager.selectByQuery(userQuery);
        if(CollectionUtils.isEmpty(userDOS)){
            return BaseResponse.create(Const.LOGIC_ERROR,"用户不存在");
        }

        MerchantDO userDO = userDOS.get(0);
        if(!userDO.getPassword().equals(form.getPassword())){
            return BaseResponse.create(Const.LOGIC_ERROR,"密码错误");
        }

        String sessionId;

        try{
            String oldSession = (String) redisAO.get(userDO.getUsername());
            if(StringUtil.isEmpty(oldSession)){
                sessionId = WebSessionUtil.createSessionId();
                User user = new User();
                user.setUsername(userDO.getUsername());
                user.setPassword(userDO.getPassword());
                user.setUserId(userDO.getId());
                user.setType(userDO.getType());
                redisAO.set(sessionId,user,RedisAOImpl.ONE_DAY);
                redisAO.set(userDO.getUsername(),sessionId,RedisAOImpl.ONE_DAY);
            }else{
                sessionId = oldSession;
            }
        }catch (Exception e){
            logger.error("json解析错误",e);
            return BaseResponse.create(Const.LOGIC_ERROR,"登录失败");
        }

        PcLoginInfoVO infoVO = new PcLoginInfoVO();
        infoVO.setChannelId(userDO.getChannelId());
        infoVO.setId(userDO.getId());
        infoVO.setName(userDO.getName());
        infoVO.setPassword(userDO.getPassword());
        infoVO.setUsername(userDO.getUsername());
        infoVO.setType(userDO.getType());
        infoVO.setSessionId(sessionId);

        return BaseResponse.create(infoVO);

    }

    private BaseResponse<Boolean> checkParams(MerchantForm form){

        if(StringUtil.isEmpty(form.getUsername())){
            return BaseResponse.create(Const.PARAMS_ERROR,"商家用户名不能为空");
        }

        if(StringUtil.isEmpty(form.getPassword())){
            return BaseResponse.create(Const.PARAMS_ERROR,"商家密码不能为空");
        }

        if(StringUtil.isEmpty(form.getName())){
            return BaseResponse.create(Const.PARAMS_ERROR,"商家名称不能为空");
        }

        MerchantQuery query = new MerchantQuery();
        MerchantQuery.Criteria criteria = query.createCriteria();
        criteria.andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode());
        criteria.andNameEqualTo(form.getName());
        query.or().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                .andUsernameEqualTo(form.getUsername());
        List<MerchantDO> merchantDOS = merchantManager.selectByQuery(query);

        if(!CollectionUtils.isEmpty(merchantDOS)){
            return BaseResponse.create(Const.LOGIC_ERROR,"商家名称或账号已存在");
        }

        if(form.getType() == null){
            form.setType(1);
        }

        return BaseResponse.create(true);

    }

    @Override
    public BaseResponse<List<MerchantVO>> getMerchantList(MerchantListForm request) {
        BaseResponse permissionResult = checkPermision();
        if(!permissionResult.isSuccess()){
            return BaseResponse.create(permissionResult.getCode(),permissionResult.getDesc());
        }
        List<MerchantVO> merchantVOS = new ArrayList<>();
        if(request.getPageNo() == null){
            request.setPageNo(BasePageRequest.DEFAULT_NO);
        }
        if(request.getPageSize() == null){
            request.setPageSize(BasePageRequest.DEFAULT_SIZE);
        }

        MerchantQuery query = new MerchantQuery();
        query.setPageNo(request.getPageNo());
        query.setPageSize(request.getPageSize());
        MerchantQuery.Criteria criteria = query.createCriteria();
        criteria.andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode());
        if(!StringUtil.isEmpty(request.getSearchParam())){
            criteria.andNameLike("%"+request.getSearchParam()+"%");
        }
        List<MerchantDO> merchantDOS = merchantManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(merchantDOS)){
            return BaseResponse.create(merchantVOS);
        }

        for(MerchantDO merchantDO : merchantDOS){
            MerchantVO merchantVO = new MerchantVO();
            merchantVO.setId(merchantDO.getId());
            merchantVO.setName(merchantDO.getName());
            merchantVO.setChannelId(merchantDO.getChannelId());
            merchantVO.setPassword(merchantDO.getPassword());
            merchantVO.setUsername(merchantDO.getUsername());
            merchantVO.setType(merchantDO.getType());
            merchantVOS.add(merchantVO);
        }

        BaseResponse response = BaseResponse.create(merchantVOS);
        response.setPageNo(request.getPageNo());
        response.setPageSize(request.getPageSize());
        response.setTotalCount(merchantManager.countByQuery(query));

        return response;
    }

    @Override
    public BaseResponse<Boolean> deleteMerchant(DelMerchantForm form) {
        BaseResponse permissionResult = checkPermision();
        if(!permissionResult.isSuccess()){
            return BaseResponse.create(permissionResult.getCode(),permissionResult.getDesc());
        }

        if(form.getId() == null){
            return BaseResponse.create(Const.PARAMS_ERROR,"商家id不能为空");
        }

        MerchantQuery query = new MerchantQuery();
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                .andIdEqualTo(form.getId());
        List<MerchantDO> merchantDOS = merchantManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(merchantDOS)){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到对应的商家");
        }
        MerchantDO merchantDO = merchantDOS.get(0);
        merchantDO.setStatus(StatusEnum.DELETE_STATUS.getCode());
        long col = merchantManager.updateByPrimaryKeySelective(merchantDO);
        if(col < 1){
            logger.error("删除商家失败，商家id:{}",form.getId());
            return BaseResponse.create(Const.LOGIC_ERROR,"删除商家失败");
        }
        return BaseResponse.create(true);
    }

    @Override
    public BaseResponse<Boolean> editMerchant(EditMerchantForm form) {
        User user = UserUtil.getUser();
        if(user == null){
            return BaseResponse.create(Const.LOGIC_ERROR,"请先登录");
        }

        if(user.getType()!=null && user.getType()!=0){
            return BaseResponse.create(Const.LOGIC_ERROR,"没有权限操作");
        }

        if(form.getId() == null){
            return BaseResponse.create(Const.PARAMS_ERROR,"商家id不能为空");
        }

        MerchantDO merchantDO = merchantManager.selectByPrimaryKey(form.getId());
        if(merchantDO == null){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到对应的商家");
        }

        if(!StringUtil.isEmpty(form.getName())){
            merchantDO.setName(form.getName());
        }
        if(!StringUtil.isEmpty(form.getUsername())){
            merchantDO.setUsername(form.getUsername());
        }
        if(!StringUtil.isEmpty(form.getPassword())){
            merchantDO.setPassword(form.getPassword());
        }

        int col = merchantManager.updateByPrimaryKeySelective(merchantDO);
        if(col <1){
            logger.error("商家信息更新失败,商家id:{}",form.getId());
            return BaseResponse.create(Const.LOGIC_ERROR,"商家信息更新失败");
        }

        return BaseResponse.create(true);
    }

    @Override
    public BaseResponse<StreamInfoWrep> getMerchantStream(MerchantStreamForm form) {
        StreamInfoWrep infoWrep = new StreamInfoWrep();
        if(form.getMerchantId() == null){
            return BaseResponse.create(Const.PARAMS_ERROR,"商家id不能为空");
        }

        MerchantDO merchantDO = merchantManager.selectByPrimaryKey(form.getMerchantId());
        if(merchantDO == null || merchantDO.getStatus() == StatusEnum.DELETE_STATUS.getCode()){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不当对应的商家");
        }

        if(form.getPageNo() == null){
            form.setPageNo(BasePageRequest.DEFAULT_NO);
        }
        if(form.getPageSize() == null){
            form.setPageSize(BasePageRequest.DEFAULT_SIZE);
        }

        UserQuery query = new UserQuery();
        query.setPageNo(form.getPageNo());
        query.setPageSize(form.getPageSize());
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                .andChannelIdEqualTo(merchantDO.getChannelId());

        List<UserDO> userDOS = userManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(userDOS)){
            return BaseResponse.create(Const.LOGIC_ERROR,"没有数据");
        }
        List<StreamInfo> streamInfos = new ArrayList<>();
        userDOS.forEach(item->{
            StreamInfo info = new StreamInfo();
            BeanUtils.copyProperties(item,info);
            info.setFistLoginTime(DateUtil.dateISO8601Format(item.getFistLoginTime()));
            info.setGmtCreate(DateUtil.dateISO8601Format(item.getGmtCreate()));
            streamInfos.add(info);
        });
        List<Long> userIds = userDOS.stream().map(UserDO::getId).collect(Collectors.toList());
        setStreamInfoCount(userIds,streamInfos);
        infoWrep.setStreamInfos(streamInfos);
        if(form.getPageNo() == 1){
            List<Integer> anaCount = getAnalyzeCount(merchantDO.getChannelId());
            infoWrep.setIntentCount(anaCount.get(0));
            infoWrep.setAccurateCount(anaCount.get(1));
        }
        int registerCount = userManager.countByQuery(query);
        infoWrep.setRegisterCount(registerCount);
        BaseResponse<StreamInfoWrep> response = BaseResponse.create(infoWrep);
        response.setPageNo(form.getPageNo());
        response.setPageSize(form.getPageSize());
        response.setTotalCount(registerCount);

        return response;

    }
    //设置用户的点击次数
    private void setStreamInfoCount(List<Long> userIds, List<StreamInfo> streamInfos) {

        BuryQuery query = new BuryQuery();
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                .andUserIdIn(userIds);
        List<BuryDO> buryDOS = buryManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(buryDOS)){
            return;
        }
        streamInfos.forEach(info->{
            buryDOS.forEach(buryDO -> {
                if(buryDO.getUserId().equals(info.getId())){
                    info.setListCount(buryDO.getListCount());
                    info.setDetailCount(buryDO.getDetailCount());
                }
            });
        });
    }

    //获取意向用户和精准用户
    private List<Integer> getAnalyzeCount(String channelId){
        AnalyzeQuery analyzeIntentQuery = new AnalyzeQuery();
        AnalyzeQuery analyzespQuery = new AnalyzeQuery();
        analyzeIntentQuery.setChannelId(channelId);
        analyzespQuery.setChannelId(channelId);
        AnalyzeRuleQuery query = new AnalyzeRuleQuery();
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode());
        List<AnalyzeRuleDO> ruleDOS = ruleManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(ruleDOS)){
            analyzeIntentQuery.setDetailCount(0);
            analyzeIntentQuery.setListCount(0);
            analyzespQuery.setDetailCount(0);
            analyzespQuery.setListCount(0);
        }else{
            ruleDOS.forEach(item->{
                if(item.getType().equals(1)){
                    analyzeIntentQuery.setListCount(item.getListCount());
                    analyzeIntentQuery.setDetailCount(item.getDetailCount());
                }else{
                    analyzespQuery.setListCount(item.getListCount());
                    analyzespQuery.setDetailCount(item.getDetailCount());
                }
            });

        }

        int analyzeIntent = merchantManager.getAnalyzeInfo(analyzeIntentQuery);
        int analyzesp = merchantManager.getAnalyzeInfo(analyzespQuery);
        List<Integer> list = new ArrayList<>();
        list.add(analyzeIntent);
        list.add(analyzesp);
        return list;
    }

    private BaseResponse<Boolean> checkPermision(){
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
