package com.gzw.debit.dal.mapper;

import com.gzw.debit.dal.model.BannerDO;
import com.gzw.debit.dal.query.BannerQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * MyBatis Mapper for Banner.
 */
 @Mapper
 @Repository
public interface BannerMapper {
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
    int insertSelective(BannerDO record);

    /**
     * select by query condition.
     */
    List<BannerDO> selectByQuery(BannerQuery query);

    /**
     * select by primary key.
     */
    BannerDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(@Param("record") BannerDO record, @Param("query") BannerQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(@Param("record") BannerDO record, @Param("query") BannerQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(BannerDO record);
}