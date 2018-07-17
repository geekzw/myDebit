package com.gzw.debit.core.ao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gzw.debit.common.utils.HttpUtil;
import com.gzw.debit.core.ao.CacheAO;
import com.gzw.debit.core.ao.RedisAO;
import com.gzw.debit.core.entry.dto.IpObjectDto;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.manager.IpRecordManager;
import com.gzw.debit.core.utils.StringUtil;
import com.gzw.debit.dal.model.IpRecordDO;
import com.gzw.debit.dal.query.IpRecordQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * auth:gujian
 * time:2018/7/17
 * email:gujian@maihaoche.com
 * describe:
 */
@Slf4j
@Service
public class CacheAOImpl implements CacheAO {


    private static final String IP_LIST = "ip_list";
    private static final String URL_HOST = "http://ip.taobao.com/service/getIpInfo.php?ip=";
    private static final String COUNTRY = "中国";


    @Autowired
    private RedisAO redisAO;

    @Autowired
    private IpRecordManager ipRecordManager;

    @Override
    public boolean isIpAllow(String ip) {
        log.info("isIpAllow,ip={}",ip);
        if(StringUtil.isEmpty(ip)){
            return false;
        }

        List<Object> list = redisAO.lGet(IP_LIST,0,-1);
        if(CollectionUtils.isEmpty(list)){
            log.info("缓存中找不到ip");
            return cacheIp(ip);
        }

        for(Object item:list){
            IpObjectDto dto = (IpObjectDto) item;
            if (dto.getIp().equals(ip)){
                return dto.isAllow();
            }
        }

        return false;
    }

    private boolean cacheIp(String ip) {
        boolean isAllow;
        IpRecordQuery query = new IpRecordQuery();
        query.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                .andIpEqualTo(ip);
        List<IpRecordDO> ipRecordDOS = ipRecordManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(ipRecordDOS)){
            log.info("数据库中找不到ip");
            //插入数据库
            isAllow =  requestIp(ip);
            IpRecordDO recordDO = new IpRecordDO();
            recordDO.setIp(ip);
            recordDO.setType(isAllow == false?0:1);
            long col = ipRecordManager.insertSelective(recordDO);
            if(col < 1){
                log.error("插入ip名单失败：ip={}",ip);
            }
            //插入缓存
            List<Object> list = redisAO.lGet(IP_LIST,0,-1);
            if(CollectionUtils.isEmpty(list)){
                list = new ArrayList<>();
            }
            IpObjectDto dto = new IpObjectDto();
            dto.setIp(ip);
            dto.setAllow(isAllow);
            list.add(dto);
            redisAO.lSet(IP_LIST,list);
            return isAllow;
        }
        isAllow = ipRecordDOS.get(0).getType() == 0?false:true;
        return isAllow;
    }

    private boolean requestIp(String ip) {
        log.info("网络查询ip所属区域");
        String url = URL_HOST+ip;
        JSONObject object = JSON.parseObject(HttpUtil.sendGet(url));
        JSONObject data = object.getJSONObject("data");
        if(data == null){
            return false;
        }
        if(data.containsKey("isp")){
            String isp = data.getString("isp");
            if(isp!=null && isp.equals("内网IP")){
                log.info("内网网段：ip{}",ip);
                return true;
            }
        }
        if(data.containsKey("country")){
            String country = data.getString("country");
            if(COUNTRY.equals(country)){
                log.info("国内网段：ip{}",ip);
                return true;
            }
        }
        log.info("国外网段：ip{}",ip);
        return false;
    }
}
