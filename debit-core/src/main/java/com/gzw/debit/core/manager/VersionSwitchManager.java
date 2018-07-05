package com.gzw.debit.core.manager;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.VersionSwitchDO;
import com.gzw.debit.dal.query.VersionSwitchQuery;

import java.util.List;


/**
 * Manager for VersionSwitch.
 */
public interface VersionSwitchManager {
    /**
     * query count by query condition.
     */
    int countByQuery(VersionSwitchQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(VersionSwitchQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(VersionSwitchDO record);

    /**
     * insert selective.
     */
    long insertSelective(VersionSwitchDO record);

    /**
     * select by query condition.
     */
    List<VersionSwitchDO> selectByQuery(VersionSwitchQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<VersionSwitchDO> selectByQueryWithPage(VersionSwitchQuery query);

    /**
     * select by primary key.
     */
    VersionSwitchDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(VersionSwitchDO record,  VersionSwitchQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(VersionSwitchDO record, VersionSwitchQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(VersionSwitchDO record);
}