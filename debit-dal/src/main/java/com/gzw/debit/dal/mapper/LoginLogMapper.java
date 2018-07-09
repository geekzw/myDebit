package com.gzw.debit.dal.mapper;

import com.gzw.debit.dal.model.LoginLogDO;
import com.gzw.debit.dal.query.LoginLogQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * MyBatis Mapper for LoginLog.
 */
 @Mapper
 @Repository
public interface LoginLogMapper {
    /**
     * query count by query condition.
     */
    int countByQuery(LoginLogQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(LoginLogQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(LoginLogDO record);

    /**
     * insert selective.
     */
    int insertSelective(LoginLogDO record);

    /**
     * select by query condition.
     */
    List<LoginLogDO> selectByQuery(LoginLogQuery query);

    /**
     * select by primary key.
     */
    LoginLogDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(@Param("record") LoginLogDO record, @Param("query") LoginLogQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(@Param("record") LoginLogDO record, @Param("query") LoginLogQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(LoginLogDO record);
}