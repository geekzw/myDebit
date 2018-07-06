package com.gzw.debit.dal.mapper;

import com.gzw.debit.dal.model.DownUrlDO;
import com.gzw.debit.dal.query.DownUrlQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * MyBatis Mapper for DownUrl.
 */
 @Mapper
 @Repository
public interface DownUrlMapper {
    /**
     * query count by query condition.
     */
    int countByQuery(DownUrlQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(DownUrlQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(DownUrlDO record);

    /**
     * insert selective.
     */
    int insertSelective(DownUrlDO record);

    /**
     * select by query condition.
     */
    List<DownUrlDO> selectByQuery(DownUrlQuery query);

    /**
     * select by primary key.
     */
    DownUrlDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(@Param("record") DownUrlDO record, @Param("query") DownUrlQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(@Param("record") DownUrlDO record, @Param("query") DownUrlQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(DownUrlDO record);
}