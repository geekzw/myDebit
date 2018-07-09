package com.gzw.debit.core.manager.impl;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.LoginLogDO;
import com.gzw.debit.dal.query.LoginLogQuery;
import com.gzw.debit.dal.mapper.ext.LoginLogExtMapper;
import com.gzw.debit.core.manager.LoginLogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manager for LoginLog.
 */

@Primary
@Component
public class LoginLogManagerImpl implements LoginLogManager{

    
    @Autowired
    protected LoginLogExtMapper loginLogExtMapper;
    /**
     * query count by query condition.
     */
    @Override
    public int countByQuery(LoginLogQuery query){
        return loginLogExtMapper.countByQuery(query);
    }

    /**
     * delete by query condition.
     */
    @Override
    public int deleteByQuery(LoginLogQuery query){
        return loginLogExtMapper.deleteByQuery(query);
    }

    /**
     * delete by primary key.
     */
    @Override
    public int deleteByPrimaryKey(LoginLogDO record){
        return loginLogExtMapper.deleteByPrimaryKey(record);
    }

    /**
     * insert selective.
     */
    @Override
    public long insertSelective(LoginLogDO record){
        return loginLogExtMapper.insertSelective(record);
    }

    /**
     * select by query condition.
     */
    @Override
    public List<LoginLogDO> selectByQuery(LoginLogQuery query){
        return loginLogExtMapper.selectByQuery(query);
    }

    /**
     * select by query condition with page.
    */
    @Override
    public PageResult<LoginLogDO> selectByQueryWithPage(LoginLogQuery query) {
        PageResult<LoginLogDO> result = new PageResult<LoginLogDO>();
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
    public LoginLogDO selectByPrimaryKey(Long id){
        return loginLogExtMapper.selectByPrimaryKey(id);
    }

    /**
     * update by query condition selective.
     */
    @Override
    public int updateByQuerySelective( LoginLogDO record,  LoginLogQuery query){
        return loginLogExtMapper.updateByQuerySelective(record,  query);
    }

    /**
     * update by query condition.
     */
    @Override
    public int updateByQuery( LoginLogDO record,  LoginLogQuery query){

        return loginLogExtMapper.updateByQuery(record, query);
    }

    /**
     * update by primary key selective.
     */
    @Override
    public int updateByPrimaryKeySelective(LoginLogDO record){
        return loginLogExtMapper.updateByPrimaryKeySelective(record);
    }
}