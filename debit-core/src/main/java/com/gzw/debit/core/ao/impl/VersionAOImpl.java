package com.gzw.debit.core.ao.impl;

import com.gzw.debit.common.entry.HeaderEntry;
import com.gzw.debit.common.utils.WebSessionUtil;
import com.gzw.debit.core.ao.VersionAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.VersionForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.VersionSwitchManager;
import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.dal.model.VersionSwitchDO;
import com.gzw.debit.dal.query.VersionSwitchQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(VersionAOImpl.class);

    @Autowired
    private VersionSwitchManager switchManager;

    @Override
    public BaseResponse<Boolean> getVersionStatus(VersionForm form) {
        HeaderEntry headerEntry = WebSessionUtil.getHeader();
        String version = form.getId()+"";
        if(!StringUtil.isEmpty(headerEntry.getVersion())){
            version = headerEntry.getVersion();
        }
        if(StringUtil.isEmpty(version)){
            return BaseResponse.create(Const.PARAMS_ERROR,"版本号不能为空");
        }
        if(headerEntry.getDeviceType() == null){
            form.setDevicesType(1);
        }
        if(headerEntry.getPackageType() == null){
            headerEntry.setPackageType(1);
        }
        VersionSwitchQuery query = new VersionSwitchQuery();
        VersionSwitchQuery.Criteria criteria = query.createCriteria();
        criteria.andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                .andFromWhereEqualTo(headerEntry.getDeviceType())
                .andPackageTypeEqualTo(headerEntry.getPackageType())
                .andVersionEqualTo(version);

        List<VersionSwitchDO> switchDOS = switchManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(switchDOS)){
            logger.error("找不到对应版本号 version:{}, deviceType:{}",form.getId(),form.getDevicesType());
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
