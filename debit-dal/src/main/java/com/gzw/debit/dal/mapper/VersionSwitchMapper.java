package com.gzw.debit.dal.mapper;

import com.gzw.debit.dal.model.VersionSwitchDO;
import com.gzw.debit.dal.query.VersionSwitchQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * MyBatis Mapper for VersionSwitch.
 */
 @Mapper
 @Repository
public interface VersionSwitchMapper {
    /**
     * query count by query condition.
     */
    int countByQuery(VersionSwitchQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(VersionSwitchQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(VersionSwitchDO record);

    /**
     * insert selective.
     */
    int insertSelective(VersionSwitchDO record);

    /**
     * select by query condition.
     */
    List<VersionSwitchDO> selectByQuery(VersionSwitchQuery query);

    /**
     * select by primary key.
     */
    VersionSwitchDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(@Param("record") VersionSwitchDO record, @Param("query") VersionSwitchQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(@Param("record") VersionSwitchDO record, @Param("query") VersionSwitchQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(VersionSwitchDO record);
}