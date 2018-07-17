package com.gzw.debit.dal.mapper;

import com.gzw.debit.dal.model.IpRecordDO;
import com.gzw.debit.dal.query.IpRecordQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * MyBatis Mapper for IpRecord.
 */
 @Mapper
 @Repository
public interface IpRecordMapper {
    /**
     * query count by query condition.
     */
    int countByQuery(IpRecordQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(IpRecordQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(IpRecordDO record);

    /**
     * insert selective.
     */
    int insertSelective(IpRecordDO record);

    /**
     * select by query condition.
     */
    List<IpRecordDO> selectByQuery(IpRecordQuery query);

    /**
     * select by primary key.
     */
    IpRecordDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(@Param("record") IpRecordDO record, @Param("query") IpRecordQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(@Param("record") IpRecordDO record, @Param("query") IpRecordQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(IpRecordDO record);
}