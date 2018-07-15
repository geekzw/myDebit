package com.gzw.debit.dal.mapper.ext;

import com.gzw.debit.dal.mapper.DayAliveMapper;
import com.gzw.debit.dal.model.ext.StrIntKeyValue;
import com.gzw.debit.dal.query.ext.AliveDataQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MyBatis Ext Mapper for DayAlive.
 */

@Mapper
@Repository
public interface DayAliveExtMapper extends DayAliveMapper {

    List<StrIntKeyValue> getAliveData(AliveDataQuery query);

}