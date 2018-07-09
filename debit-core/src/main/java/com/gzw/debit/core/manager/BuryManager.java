package com.gzw.debit.core.manager;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.BuryDO;
import com.gzw.debit.dal.query.BuryQuery;

import java.util.List;


/**
 * Manager for Bury.
 */
public interface BuryManager {
    /**
     * query count by query condition.
     */
    int countByQuery(BuryQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(BuryQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(BuryDO record);

    /**
     * insert selective.
     */
    long insertSelective(BuryDO record);

    /**
     * select by query condition.
     */
    List<BuryDO> selectByQuery(BuryQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<BuryDO> selectByQueryWithPage(BuryQuery query);

    /**
     * select by primary key.
     */
    BuryDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(BuryDO record,  BuryQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(BuryDO record, BuryQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(BuryDO record);
}