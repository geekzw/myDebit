package com.gzw.debit.core.manager.impl;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.DownUrlDO;
import com.gzw.debit.dal.query.DownUrlQuery;
import com.gzw.debit.dal.mapper.ext.DownUrlExtMapper;
import com.gzw.debit.core.manager.DownUrlManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manager for DownUrl.
 */

@Primary
@Component
public class DownUrlManagerImpl implements DownUrlManager{

    
    @Autowired
    protected DownUrlExtMapper downUrlExtMapper;
    /**
     * query count by query condition.
     */
    @Override
    public int countByQuery(DownUrlQuery query){
        return downUrlExtMapper.countByQuery(query);
    }

    /**
     * delete by query condition.
     */
    @Override
    public int deleteByQuery(DownUrlQuery query){
        return downUrlExtMapper.deleteByQuery(query);
    }

    /**
     * delete by primary key.
     */
    @Override
    public int deleteByPrimaryKey(DownUrlDO record){
        return downUrlExtMapper.deleteByPrimaryKey(record);
    }

    /**
     * insert selective.
     */
    @Override
    public long insertSelective(DownUrlDO record){
        return downUrlExtMapper.insertSelective(record);
    }

    /**
     * select by query condition.
     */
    @Override
    public List<DownUrlDO> selectByQuery(DownUrlQuery query){
        return downUrlExtMapper.selectByQuery(query);
    }

    /**
     * select by query condition with page.
    */
    @Override
    public PageResult<DownUrlDO> selectByQueryWithPage(DownUrlQuery query) {
        PageResult<DownUrlDO> result = new PageResult<DownUrlDO>();
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
    public DownUrlDO selectByPrimaryKey(Long id){
        return downUrlExtMapper.selectByPrimaryKey(id);
    }

    /**
     * update by query condition selective.
     */
    @Override
    public int updateByQuerySelective( DownUrlDO record,  DownUrlQuery query){
        return downUrlExtMapper.updateByQuerySelective(record,  query);
    }

    /**
     * update by query condition.
     */
    @Override
    public int updateByQuery( DownUrlDO record,  DownUrlQuery query){

        return downUrlExtMapper.updateByQuery(record, query);
    }

    /**
     * update by primary key selective.
     */
    @Override
    public int updateByPrimaryKeySelective(DownUrlDO record){
        return downUrlExtMapper.updateByPrimaryKeySelective(record);
    }
}