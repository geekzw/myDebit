package com.gzw.debit.dal.mapper;

import com.gzw.debit.dal.model.ProductLogDO;
import com.gzw.debit.dal.query.ProductLogQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * MyBatis Mapper for ProductLog.
 */
 @Mapper
 @Repository
public interface ProductLogMapper {
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
    int insertSelective(ProductLogDO record);

    /**
     * select by query condition.
     */
    List<ProductLogDO> selectByQuery(ProductLogQuery query);

    /**
     * select by primary key.
     */
    ProductLogDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(@Param("record") ProductLogDO record, @Param("query") ProductLogQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(@Param("record") ProductLogDO record, @Param("query") ProductLogQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(ProductLogDO record);
}