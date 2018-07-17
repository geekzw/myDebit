package com.gzw.debit.core.manager;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.IpRecordDO;
import com.gzw.debit.dal.query.IpRecordQuery;

import java.util.List;


/**
 * Manager for IpRecord.
 */
public interface IpRecordManager {
    /**
     * query count by query condition.
     */
    int countByQuery(IpRecordQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(IpRecordQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(IpRecordDO record);

    /**
     * insert selective.
     */
    long insertSelective(IpRecordDO record);

    /**
     * select by query condition.
     */
    List<IpRecordDO> selectByQuery(IpRecordQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<IpRecordDO> selectByQueryWithPage(IpRecordQuery query);

    /**
     * select by primary key.
     */
    IpRecordDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(IpRecordDO record,  IpRecordQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(IpRecordDO record, IpRecordQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(IpRecordDO record);
}