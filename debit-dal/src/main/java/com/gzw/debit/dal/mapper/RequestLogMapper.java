package com.gzw.debit.dal.mapper;

import com.gzw.debit.dal.model.RequestLogDO;
import com.gzw.debit.dal.query.RequestLogQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * MyBatis Mapper for RequestLog.
 */
 @Mapper
 @Repository
public interface RequestLogMapper {
    /**
     * query count by query condition.
     */
    int countByQuery(RequestLogQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(RequestLogQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(RequestLogDO record);

    /**
     * insert selective.
     */
    int insertSelective(RequestLogDO record);

    /**
     * select by query condition.
     */
    List<RequestLogDO> selectByQuery(RequestLogQuery query);

    /**
     * select by primary key.
     */
    RequestLogDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(@Param("record") RequestLogDO record, @Param("query") RequestLogQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(@Param("record") RequestLogDO record, @Param("query") RequestLogQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(RequestLogDO record);
}