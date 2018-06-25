package com.gzw.debit.core.manager;

import com.gzw.debit.dal.model.BannerDO;
import com.gzw.debit.dal.query.BannerQuery;
import com.gzw.debit.dal.querybase.PageResult;

import java.util.List;


/**
 * Manager for Banner.
 */
public interface BannerManager {
    /**
     * query count by query condition.
     */
    int countByQuery(BannerQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(BannerQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(BannerDO record);

    /**
     * insert selective.
     */
    long insertSelective(BannerDO record);

    /**
     * select by query condition.
     */
    List<BannerDO> selectByQuery(BannerQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<BannerDO> selectByQueryWithPage(BannerQuery query);

    /**
     * select by primary key.
     */
    BannerDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(BannerDO record,  BannerQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(BannerDO record, BannerQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(BannerDO record);
}