package com.gzw.debit.dal.mapper.ext;

import com.gzw.debit.dal.mapper.RequestLogMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * MyBatis Ext Mapper for RequestLog.
 */

@Mapper
@Repository
public interface RequestLogExtMapper extends RequestLogMapper {

}