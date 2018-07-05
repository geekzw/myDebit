package com.gzw.debit.core.manager.impl;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.RequestLogDO;
import com.gzw.debit.dal.query.RequestLogQuery;
import com.gzw.debit.dal.mapper.ext.RequestLogExtMapper;
import com.gzw.debit.core.manager.RequestLogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manager for RequestLog.
 */

@Primary
@Component
public class RequestLogManagerImpl implements RequestLogManager{

    
    @Autowired
    protected RequestLogExtMapper requestLogExtMapper;
    /**
     * query count by query condition.
     */
    @Override
    public int countByQuery(RequestLogQuery query){
        return requestLogExtMapper.countByQuery(query);
    }

    /**
     * delete by query condition.
     */
    @Override
    public int deleteByQuery(RequestLogQuery query){
        return requestLogExtMapper.deleteByQuery(query);
    }

    /**
     * delete by primary key.
     */
    @Override
    public int deleteByPrimaryKey(RequestLogDO record){
        return requestLogExtMapper.deleteByPrimaryKey(record);
    }

    /**
     * insert selective.
     */
    @Override
    public long insertSelective(RequestLogDO record){
        return requestLogExtMapper.insertSelective(record);
    }

    /**
     * select by query condition.
     */
    @Override
    public List<RequestLogDO> selectByQuery(RequestLogQuery query){
        return requestLogExtMapper.selectByQuery(query);
    }

    /**
     * select by query condition with page.
    */
    @Override
    public PageResult<RequestLogDO> selectByQueryWithPage(RequestLogQuery query) {
        PageResult<RequestLogDO> result = new PageResult<RequestLogDO>();
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
    public RequestLogDO selectByPrimaryKey(Long id){
        return requestLogExtMapper.selectByPrimaryKey(id);
    }

    /**
     * update by query condition selective.
     */
    @Override
    public int updateByQuerySelective( RequestLogDO record,  RequestLogQuery query){
        return requestLogExtMapper.updateByQuerySelective(record,  query);
    }

    /**
     * update by query condition.
     */
    @Override
    public int updateByQuery( RequestLogDO record,  RequestLogQuery query){

        return requestLogExtMapper.updateByQuery(record, query);
    }

    /**
     * update by primary key selective.
     */
    @Override
    public int updateByPrimaryKeySelective(RequestLogDO record){
        return requestLogExtMapper.updateByPrimaryKeySelective(record);
    }
}