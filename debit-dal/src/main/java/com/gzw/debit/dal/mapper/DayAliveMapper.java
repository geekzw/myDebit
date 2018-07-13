package com.gzw.debit.dal.mapper;

import com.gzw.debit.dal.model.DayAliveDO;
import com.gzw.debit.dal.query.DayAliveQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * MyBatis Mapper for DayAlive.
 */
 @Mapper
 @Repository
public interface DayAliveMapper {
    /**
     * query count by query condition.
     */
    int countByQuery(DayAliveQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(DayAliveQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(DayAliveDO record);

    /**
     * insert selective.
     */
    int insertSelective(DayAliveDO record);

    /**
     * select by query condition.
     */
    List<DayAliveDO> selectByQuery(DayAliveQuery query);

    /**
     * select by primary key.
     */
    DayAliveDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(@Param("record") DayAliveDO record, @Param("query") DayAliveQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(@Param("record") DayAliveDO record, @Param("query") DayAliveQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(DayAliveDO record);
}