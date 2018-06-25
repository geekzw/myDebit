package com.gzw.debit.core.manager;

import com.gzw.debit.dal.model.UserDO;
import com.gzw.debit.dal.query.UserQuery;
import com.gzw.debit.dal.querybase.PageResult;

import java.util.List;


/**
 * Manager for User.
 */
public interface UserManager {
    /**
     * query count by query condition.
     */
    int countByQuery(UserQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(UserQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(UserDO record);

    /**
     * insert selective.
     */
    long insertSelective(UserDO record);

    /**
     * select by query condition.
     */
    List<UserDO> selectByQuery(UserQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<UserDO> selectByQueryWithPage(UserQuery query);

    /**
     * select by primary key.
     */
    UserDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(UserDO record,  UserQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(UserDO record, UserQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(UserDO record);
}