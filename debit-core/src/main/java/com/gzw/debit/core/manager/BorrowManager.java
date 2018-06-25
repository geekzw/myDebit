package com.gzw.debit.core.manager;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.BorrowDO;
import com.gzw.debit.dal.query.BorrowQuery;

import java.util.List;


/**
 * Manager for Borrow.
 */
public interface BorrowManager {
    /**
     * query count by query condition.
     */
    int countByQuery(BorrowQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(BorrowQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(BorrowDO record);

    /**
     * insert selective.
     */
    long insertSelective(BorrowDO record);

    /**
     * select by query condition.
     */
    List<BorrowDO> selectByQuery(BorrowQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<BorrowDO> selectByQueryWithPage(BorrowQuery query);

    /**
     * select by primary key.
     */
    BorrowDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(BorrowDO record,  BorrowQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(BorrowDO record, BorrowQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(BorrowDO record);
}