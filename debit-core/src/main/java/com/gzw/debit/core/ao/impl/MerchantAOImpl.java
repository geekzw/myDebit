package com.gzw.debit.core.ao.impl;

import com.gzw.debit.common.entry.User;
import com.gzw.debit.core.ao.MerchantAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.*;
import com.gzw.debit.core.form.base.BasePageRequest;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.MerchantManager;
import com.gzw.debit.core.manager.UserManager;
import com.gzw.debit.core.utils.DateUtil;
import com.gzw.debit.core.utils.SmsCodeUtil;
import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.core.utils.UserUtil;
import com.gzw.debit.core.vo.MerchantVO;
import com.gzw.debit.core.vo.StreamInfo;
import com.gzw.debit.dal.model.MerchantDO;
import com.gzw.debit.dal.model.UserDO;
import com.gzw.debit.dal.query.MerchantQuery;
import com.gzw.debit.dal.query.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public BaseResponse<Boolean> createMerchant(MerchantForm form) {
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
        User user = UserUtil.getUser(form.getSessionId());
        if(user == null){
            return BaseResponse.create(Const.LOGIC_ERROR,"请先登录");
        }

        if(user.getType()!=null && user.getType()!=0){
            return BaseResponse.create(Const.LOGIC_ERROR,"没有权限操作");
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

        User user = UserUtil.getUser(form.getSessionId());
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
            merchantDO.setName(form.getUsername());
        }
        if(!StringUtil.isEmpty(form.getPassword())){
            merchantDO.setName(form.getPassword());
        }

        int col = merchantManager.updateByPrimaryKeySelective(merchantDO);
        if(col <1){
            logger.error("商家信息更新失败,商家id:{}",form.getId());
            return BaseResponse.create(Const.LOGIC_ERROR,"商家信息更新失败");
        }

        return BaseResponse.create(true);
    }

    @Override
    public BaseResponse<List<StreamInfo>> getMerchantStream(MerchantStreamForm form) {
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

        BaseResponse<List<StreamInfo>> response = BaseResponse.create(streamInfos);
        response.setPageNo(form.getPageNo());
        response.setPageSize(form.getPageSize());
        response.setTotalCount(userManager.countByQuery(query));

        return response;

    }
}
