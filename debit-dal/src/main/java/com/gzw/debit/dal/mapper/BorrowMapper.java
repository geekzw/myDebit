package com.gzw.debit.dal.mapper;

import com.gzw.debit.dal.model.BorrowDO;
import com.gzw.debit.dal.query.BorrowQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * MyBatis Mapper for Borrow.
 */
 @Mapper
 @Repository
public interface BorrowMapper {
    /**
     * query count by query condition.
     */
    int countByQuery(BorrowQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(BorrowQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(BorrowDO record);

    /**
     * insert selective.
     */
    int insertSelective(BorrowDO record);

    /**
     * select by query condition.
     */
    List<BorrowDO> selectByQuery(BorrowQuery query);

    /**
     * select by primary key.
     */
    BorrowDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(@Param("record") BorrowDO record, @Param("query") BorrowQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(@Param("record") BorrowDO record, @Param("query") BorrowQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(BorrowDO record);
}