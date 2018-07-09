package com.gzw.debit.core.manager;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.ProductLogDO;
import com.gzw.debit.dal.query.ProductLogQuery;

import java.util.List;


/**
 * Manager for ProductLog.
 */
public interface ProductLogManager {
    /**
     * query count by query condition.
     */
    int countByQuery(ProductLogQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(ProductLogQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(ProductLogDO record);

    /**
     * insert selective.
     */
    long insertSelective(ProductLogDO record);

    /**
     * select by query condition.
     */
    List<ProductLogDO> selectByQuery(ProductLogQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<ProductLogDO> selectByQueryWithPage(ProductLogQuery query);

    /**
     * select by primary key.
     */
    ProductLogDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(ProductLogDO record,  ProductLogQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(ProductLogDO record, ProductLogQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(ProductLogDO record);
}