package com.gzw.debit.core.manager;

import com.gzw.debit.dal.model.MerchantDO;
import com.gzw.debit.dal.model.ext.StrIntKeyValue;
import com.gzw.debit.dal.query.MerchantQuery;
import com.gzw.debit.dal.query.ext.AnalyzeQuery;
import com.gzw.debit.dal.querybase.PageResult;

import java.util.List;


/**
 * Manager for Merchant.
 */
public interface MerchantManager {
    /**
     * query count by query condition.
     */
    int countByQuery(MerchantQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(MerchantQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(MerchantDO record);

    /**
     * insert selective.
     */
    long insertSelective(MerchantDO record);

    /**
     * select by query condition.
     */
    List<MerchantDO> selectByQuery(MerchantQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<MerchantDO> selectByQueryWithPage(MerchantQuery query);

    /**
     * select by primary key.
     */
    MerchantDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(MerchantDO record,  MerchantQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(MerchantDO record, MerchantQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(MerchantDO record);

    Integer getAnalyzeInfo(AnalyzeQuery query);

    List<StrIntKeyValue> getMerchantRegisterData();
}