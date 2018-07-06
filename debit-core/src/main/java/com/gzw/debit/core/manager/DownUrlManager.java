package com.gzw.debit.core.manager;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.DownUrlDO;
import com.gzw.debit.dal.query.DownUrlQuery;

import java.util.List;


/**
 * Manager for DownUrl.
 */
public interface DownUrlManager {
    /**
     * query count by query condition.
     */
    int countByQuery(DownUrlQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(DownUrlQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(DownUrlDO record);

    /**
     * insert selective.
     */
    long insertSelective(DownUrlDO record);

    /**
     * select by query condition.
     */
    List<DownUrlDO> selectByQuery(DownUrlQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<DownUrlDO> selectByQueryWithPage(DownUrlQuery query);

    /**
     * select by primary key.
     */
    DownUrlDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(DownUrlDO record,  DownUrlQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(DownUrlDO record, DownUrlQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(DownUrlDO record);
}