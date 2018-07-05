package com.gzw.debit.core.manager.impl;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.VersionSwitchDO;
import com.gzw.debit.dal.query.VersionSwitchQuery;
import com.gzw.debit.dal.mapper.ext.VersionSwitchExtMapper;
import com.gzw.debit.core.manager.VersionSwitchManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manager for VersionSwitch.
 */

@Primary
@Component
public class VersionSwitchManagerImpl implements VersionSwitchManager{

    
    @Autowired
    protected VersionSwitchExtMapper versionSwitchExtMapper;
    /**
     * query count by query condition.
     */
    @Override
    public int countByQuery(VersionSwitchQuery query){
        return versionSwitchExtMapper.countByQuery(query);
    }

    /**
     * delete by query condition.
     */
    @Override
    public int deleteByQuery(VersionSwitchQuery query){
        return versionSwitchExtMapper.deleteByQuery(query);
    }

    /**
     * delete by primary key.
     */
    @Override
    public int deleteByPrimaryKey(VersionSwitchDO record){
        return versionSwitchExtMapper.deleteByPrimaryKey(record);
    }

    /**
     * insert selective.
     */
    @Override
    public long insertSelective(VersionSwitchDO record){
        return versionSwitchExtMapper.insertSelective(record);
    }

    /**
     * select by query condition.
     */
    @Override
    public List<VersionSwitchDO> selectByQuery(VersionSwitchQuery query){
        return versionSwitchExtMapper.selectByQuery(query);
    }

    /**
     * select by query condition with page.
    */
    @Override
    public PageResult<VersionSwitchDO> selectByQueryWithPage(VersionSwitchQuery query) {
        PageResult<VersionSwitchDO> result = new PageResult<VersionSwitchDO>();
        result.setPageSize(query.getPageSize());
        result.setPageNo(query.getPageNo());
        result.setTotalCount(this.countByQuery(query));
        result.setResult(this.selectByQuery(query));
        return result;
    }

    /**
     * select by primary key.
     */
    @Override
    public VersionSwitchDO selectByPrimaryKey(Long id){
        return versionSwitchExtMapper.selectByPrimaryKey(id);
    }

    /**
     * update by query condition selective.
     */
    @Override
    public int updateByQuerySelective( VersionSwitchDO record,  VersionSwitchQuery query){
        return versionSwitchExtMapper.updateByQuerySelective(record,  query);
    }

    /**
     * update by query condition.
     */
    @Override
    public int updateByQuery( VersionSwitchDO record,  VersionSwitchQuery query){

        return versionSwitchExtMapper.updateByQuery(record, query);
    }

    /**
     * update by primary key selective.
     */
    @Override
    public int updateByPrimaryKeySelective(VersionSwitchDO record){
        return versionSwitchExtMapper.updateByPrimaryKeySelective(record);
    }
}