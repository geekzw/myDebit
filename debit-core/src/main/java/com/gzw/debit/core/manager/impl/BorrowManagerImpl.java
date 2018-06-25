package com.gzw.debit.core.manager.impl;

//import com.subaru.common.query.PageResult;
import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.BorrowDO;
import com.gzw.debit.dal.query.BorrowQuery;
import com.gzw.debit.dal.mapper.ext.BorrowExtMapper;
import com.gzw.debit.core.manager.BorrowManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manager for Borrow.
 */

@Primary
@Component
public class BorrowManagerImpl implements BorrowManager{

    
    @Autowired
    protected BorrowExtMapper borrowExtMapper;
    /**
     * query count by query condition.
     */
    @Override
    public int countByQuery(BorrowQuery query){
        return borrowExtMapper.countByQuery(query);
    }

    /**
     * delete by query condition.
     */
    @Override
    public int deleteByQuery(BorrowQuery query){
        return borrowExtMapper.deleteByQuery(query);
    }

    /**
     * delete by primary key.
     */
    @Override
    public int deleteByPrimaryKey(BorrowDO record){
        return borrowExtMapper.deleteByPrimaryKey(record);
    }

    /**
     * insert selective.
     */
    @Override
    public long insertSelective(BorrowDO record){
        return borrowExtMapper.insertSelective(record);
    }

    /**
     * select by query condition.
     */
    @Override
    public List<BorrowDO> selectByQuery(BorrowQuery query){
        return borrowExtMapper.selectByQuery(query);
    }

    /**
     * select by query condition with page.
    */
    @Override
    public PageResult<BorrowDO> selectByQueryWithPage(BorrowQuery query) {
        PageResult<BorrowDO> result = new PageResult<BorrowDO>();
        result.setPageSize(query.getPageSize());
        result.setPageNo(query.getPageNo());
        result.setTotalCount(this.countByQuery(query));
        result.setResult(this.selectByQuery(query));
        return result;
    }

    /**
     * select by primary key.
     */
    @Override
    public BorrowDO selectByPrimaryKey(Long id){
        return borrowExtMapper.selectByPrimaryKey(id);
    }

    /**
     * update by query condition selective.
     */
    @Override
    public int updateByQuerySelective( BorrowDO record,  BorrowQuery query){
        return borrowExtMapper.updateByQuerySelective(record,  query);
    }

    /**
     * update by query condition.
     */
    @Override
    public int updateByQuery( BorrowDO record,  BorrowQuery query){

        return borrowExtMapper.updateByQuery(record, query);
    }

    /**
     * update by primary key selective.
     */
    @Override
    public int updateByPrimaryKeySelective(BorrowDO record){
        return borrowExtMapper.updateByPrimaryKeySelective(record);
    }
}