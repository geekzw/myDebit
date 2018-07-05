package com.gzw.debit.core.manager;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.RequestLogDO;
import com.gzw.debit.dal.query.RequestLogQuery;

import java.util.List;


/**
 * Manager for RequestLog.
 */
public interface RequestLogManager {
    /**
     * query count by query condition.
     */
    int countByQuery(RequestLogQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(RequestLogQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(RequestLogDO record);

    /**
     * insert selective.
     */
    long insertSelective(RequestLogDO record);

    /**
     * select by query condition.
     */
    List<RequestLogDO> selectByQuery(RequestLogQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<RequestLogDO> selectByQueryWithPage(RequestLogQuery query);

    /**
     * select by primary key.
     */
    RequestLogDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(RequestLogDO record,  RequestLogQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(RequestLogDO record, RequestLogQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(RequestLogDO record);
}