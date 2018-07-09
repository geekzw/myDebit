package com.gzw.debit.dal.mapper;

import com.gzw.debit.dal.model.BuryDO;
import com.gzw.debit.dal.query.BuryQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * MyBatis Mapper for Bury.
 */
 @Mapper
 @Repository
public interface BuryMapper {
    /**
     * query count by query condition.
     */
    int countByQuery(BuryQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(BuryQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(BuryDO record);

    /**
     * insert selective.
     */
    int insertSelective(BuryDO record);

    /**
     * select by query condition.
     */
    List<BuryDO> selectByQuery(BuryQuery query);

    /**
     * select by primary key.
     */
    BuryDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(@Param("record") BuryDO record, @Param("query") BuryQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(@Param("record") BuryDO record, @Param("query") BuryQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(BuryDO record);
}