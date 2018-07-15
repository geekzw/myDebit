package com.gzw.debit.dal.mapper;

import com.gzw.debit.dal.model.MerchantDO;
import com.gzw.debit.dal.query.MerchantQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * MyBatis Mapper for Merchant.
 */
 @Mapper
 @Repository
public interface MerchantMapper {
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
    int insertSelective(MerchantDO record);

    /**
     * select by query condition.
     */
    List<MerchantDO> selectByQuery(MerchantQuery query);

    /**
     * select by primary key.
     */
    MerchantDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(@Param("record") MerchantDO record, @Param("query") MerchantQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(@Param("record") MerchantDO record, @Param("query") MerchantQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(MerchantDO record);


}