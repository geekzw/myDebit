package com.gzw.debit.dal.mapper.ext;

import com.gzw.debit.dal.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * MyBatis Ext Mapper for User.
 */

@Mapper
@Repository
public interface UserExtMapper extends UserMapper {

}