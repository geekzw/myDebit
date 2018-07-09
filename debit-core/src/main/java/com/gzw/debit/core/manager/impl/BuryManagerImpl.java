package com.gzw.debit.core.manager.impl;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.BuryDO;
import com.gzw.debit.dal.query.BuryQuery;
import com.gzw.debit.dal.mapper.ext.BuryExtMapper;
import com.gzw.debit.core.manager.BuryManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manager for Bury.
 */

@Primary
@Component
public class BuryManagerImpl implements BuryManager{

    
    @Autowired
    protected BuryExtMapper buryExtMapper;
    /**
     * query count by query condition.
     */
    @Override
    public int countByQuery(BuryQuery query){
        return buryExtMapper.countByQuery(query);
    }

    /**
     * delete by query condition.
     */
    @Override
    public int deleteByQuery(BuryQuery query){
        return buryExtMapper.deleteByQuery(query);
    }

    /**
     * delete by primary key.
     */
    @Override
    public int deleteByPrimaryKey(BuryDO record){
        return buryExtMapper.deleteByPrimaryKey(record);
    }

    /**
     * insert selective.
     */
    @Override
    public long insertSelective(BuryDO record){
        return buryExtMapper.insertSelective(record);
    }

    /**
     * select by query condition.
     */
    @Override
    public List<BuryDO> selectByQuery(BuryQuery query){
        return buryExtMapper.selectByQuery(query);
    }

    /**
     * select by query condition with page.
    */
    @Override
    public PageResult<BuryDO> selectByQueryWithPage(BuryQuery query) {
        PageResult<BuryDO> result = new PageResult<BuryDO>();
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
    public BuryDO selectByPrimaryKey(Long id){
        return buryExtMapper.selectByPrimaryKey(id);
    }

    /**
     * update by query condition selective.
     */
    @Override
    public int updateByQuerySelective( BuryDO record,  BuryQuery query){
        return buryExtMapper.updateByQuerySelective(record,  query);
    }

    /**
     * update by query condition.
     */
    @Override
    public int updateByQuery( BuryDO record,  BuryQuery query){

        return buryExtMapper.updateByQuery(record, query);
    }

    /**
     * update by primary key selective.
     */
    @Override
    public int updateByPrimaryKeySelective(BuryDO record){
        return buryExtMapper.updateByPrimaryKeySelective(record);
    }
}