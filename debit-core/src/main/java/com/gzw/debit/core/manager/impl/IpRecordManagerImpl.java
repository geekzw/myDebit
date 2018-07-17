package com.gzw.debit.core.manager.impl;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.IpRecordDO;
import com.gzw.debit.dal.query.IpRecordQuery;
import com.gzw.debit.dal.mapper.ext.IpRecordExtMapper;
import com.gzw.debit.core.manager.IpRecordManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manager for IpRecord.
 */

@Primary
@Component
public class IpRecordManagerImpl implements IpRecordManager{

    
    @Autowired
    protected IpRecordExtMapper ipRecordExtMapper;
    /**
     * query count by query condition.
     */
    @Override
    public int countByQuery(IpRecordQuery query){
        return ipRecordExtMapper.countByQuery(query);
    }

    /**
     * delete by query condition.
     */
    @Override
    public int deleteByQuery(IpRecordQuery query){
        return ipRecordExtMapper.deleteByQuery(query);
    }

    /**
     * delete by primary key.
     */
    @Override
    public int deleteByPrimaryKey(IpRecordDO record){
        return ipRecordExtMapper.deleteByPrimaryKey(record);
    }

    /**
     * insert selective.
     */
    @Override
    public long insertSelective(IpRecordDO record){
        return ipRecordExtMapper.insertSelective(record);
    }

    /**
     * select by query condition.
     */
    @Override
    public List<IpRecordDO> selectByQuery(IpRecordQuery query){
        return ipRecordExtMapper.selectByQuery(query);
    }

    /**
     * select by query condition with page.
    */
    @Override
    public PageResult<IpRecordDO> selectByQueryWithPage(IpRecordQuery query) {
        PageResult<IpRecordDO> result = new PageResult<IpRecordDO>();
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
    public IpRecordDO selectByPrimaryKey(Long id){
        return ipRecordExtMapper.selectByPrimaryKey(id);
    }

    /**
     * update by query condition selective.
     */
    @Override
    public int updateByQuerySelective( IpRecordDO record,  IpRecordQuery query){
        return ipRecordExtMapper.updateByQuerySelective(record,  query);
    }

    /**
     * update by query condition.
     */
    @Override
    public int updateByQuery( IpRecordDO record,  IpRecordQuery query){

        return ipRecordExtMapper.updateByQuery(record, query);
    }

    /**
     * update by primary key selective.
     */
    @Override
    public int updateByPrimaryKeySelective(IpRecordDO record){
        return ipRecordExtMapper.updateByPrimaryKeySelective(record);
    }
}