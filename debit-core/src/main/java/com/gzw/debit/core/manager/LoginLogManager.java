package com.gzw.debit.core.manager;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.LoginLogDO;
import com.gzw.debit.dal.query.LoginLogQuery;

import java.util.List;


/**
 * Manager for LoginLog.
 */
public interface LoginLogManager {
    /**
     * query count by query condition.
     */
    int countByQuery(LoginLogQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(LoginLogQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(LoginLogDO record);

    /**
     * insert selective.
     */
    long insertSelective(LoginLogDO record);

    /**
     * select by query condition.
     */
    List<LoginLogDO> selectByQuery(LoginLogQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<LoginLogDO> selectByQueryWithPage(LoginLogQuery query);

    /**
     * select by primary key.
     */
    LoginLogDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(LoginLogDO record,  LoginLogQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(LoginLogDO record, LoginLogQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(LoginLogDO record);
}