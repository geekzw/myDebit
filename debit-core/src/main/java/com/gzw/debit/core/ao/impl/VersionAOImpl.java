package com.gzw.debit.core.ao.impl;

import com.gzw.debit.core.ao.VersionAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.VersionSwitchManager;
import com.gzw.debit.dal.model.VersionSwitchDO;
import com.gzw.debit.dal.query.VersionSwitchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * auth:gujian
 * time:2018/6/30
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class VersionAOImpl implements VersionAO {

    @Autowired
    private VersionSwitchManager switchManager;

    @Override
    @Cacheable(value = "VersionStatus",key = "1")
    public BaseResponse<Boolean> getVersionStatus(Integer version) {

        if(version == null){
            return BaseResponse.create(Const.PARAMS_ERROR,"版本号不能为空");
        }
        VersionSwitchQuery query = new VersionSwitchQuery();
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                .andVersionEqualTo(version);

        List<VersionSwitchDO> switchDOS = switchManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(switchDOS)){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到对应版本号");
        }
        VersionSwitchDO switchDO = switchDOS.get(0);
        if(switchDO.getOpenOnline() == 1){
            return BaseResponse.create(true);
        }else{
            return BaseResponse.create(false);
        }
    }
}
