package com.gzw.debit.core.manager;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.DayAliveDO;
import com.gzw.debit.dal.query.DayAliveQuery;

import java.util.List;


/**
 * Manager for DayAlive.
 */
public interface DayAliveManager {
    /**
     * query count by query condition.
     */
    int countByQuery(DayAliveQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(DayAliveQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(DayAliveDO record);

    /**
     * insert selective.
     */
    long insertSelective(DayAliveDO record);

    /**
     * select by query condition.
     */
    List<DayAliveDO> selectByQuery(DayAliveQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<DayAliveDO> selectByQueryWithPage(DayAliveQuery query);

    /**
     * select by primary key.
     */
    DayAliveDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(DayAliveDO record,  DayAliveQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(DayAliveDO record, DayAliveQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(DayAliveDO record);
}