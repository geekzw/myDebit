package com.gzw.debit.core.manager.impl;

import com.gzw.debit.dal.model.ext.StrIntKeyValue;
import com.gzw.debit.dal.query.ext.AliveDataQuery;
import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.DayAliveDO;
import com.gzw.debit.dal.query.DayAliveQuery;
import com.gzw.debit.dal.mapper.ext.DayAliveExtMapper;
import com.gzw.debit.core.manager.DayAliveManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manager for DayAlive.
 */

@Primary
@Component
public class DayAliveManagerImpl implements DayAliveManager{

    
    @Autowired
    protected DayAliveExtMapper dayAliveExtMapper;
    /**
     * query count by query condition.
     */
    @Override
    public int countByQuery(DayAliveQuery query){
        return dayAliveExtMapper.countByQuery(query);
    }

    /**
     * delete by query condition.
     */
    @Override
    public int deleteByQuery(DayAliveQuery query){
        return dayAliveExtMapper.deleteByQuery(query);
    }

    /**
     * delete by primary key.
     */
    @Override
    public int deleteByPrimaryKey(DayAliveDO record){
        return dayAliveExtMapper.deleteByPrimaryKey(record);
    }

    /**
     * insert selective.
     */
    @Override
    public long insertSelective(DayAliveDO record){
        return dayAliveExtMapper.insertSelective(record);
    }

    /**
     * select by query condition.
     */
    @Override
    public List<DayAliveDO> selectByQuery(DayAliveQuery query){
        return dayAliveExtMapper.selectByQuery(query);
    }

    /**
     * select by query condition with page.
    */
    @Override
    public PageResult<DayAliveDO> selectByQueryWithPage(DayAliveQuery query) {
        PageResult<DayAliveDO> result = new PageResult<DayAliveDO>();
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
    public DayAliveDO selectByPrimaryKey(Long id){
        return dayAliveExtMapper.selectByPrimaryKey(id);
    }

    /**
     * update by query condition selective.
     */
    @Override
    public int updateByQuerySelective( DayAliveDO record,  DayAliveQuery query){
        return dayAliveExtMapper.updateByQuerySelective(record,  query);
    }

    /**
     * update by query condition.
     */
    @Override
    public int updateByQuery( DayAliveDO record,  DayAliveQuery query){

        return dayAliveExtMapper.updateByQuery(record, query);
    }

    /**
     * update by primary key selective.
     */
    @Override
    public int updateByPrimaryKeySelective(DayAliveDO record){
        return dayAliveExtMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<StrIntKeyValue> getAliveData(AliveDataQuery query) {
        return dayAliveExtMapper.getAliveData(query);
    }
}