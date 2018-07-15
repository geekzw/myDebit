package com.gzw.debit.dal.mapper.ext;

import com.gzw.debit.dal.mapper.UserMapper;
import com.gzw.debit.dal.model.ext.StrIntKeyValue;
import com.gzw.debit.dal.model.ext.UserDOExt;
import com.gzw.debit.dal.query.ext.AliveDataQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MyBatis Ext Mapper for User.
 */

@Mapper
@Repository
public interface UserExtMapper extends UserMapper {

    List<UserDOExt> getUserDOExt();

    List<StrIntKeyValue> getRegisterData(AliveDataQuery query);

}