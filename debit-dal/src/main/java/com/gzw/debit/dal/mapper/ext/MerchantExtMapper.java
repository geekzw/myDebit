package com.gzw.debit.dal.mapper.ext;

import com.gzw.debit.dal.mapper.MerchantMapper;
import com.gzw.debit.dal.query.ext.AnalyzeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * MyBatis Ext Mapper for Merchant.
 */

@Mapper
@Repository
public interface MerchantExtMapper extends MerchantMapper {

    Integer getAnalyzeInfo(AnalyzeQuery query);

}