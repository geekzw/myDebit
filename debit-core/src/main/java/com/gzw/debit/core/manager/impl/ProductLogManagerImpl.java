package com.gzw.debit.core.manager.impl;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.ProductLogDO;
import com.gzw.debit.dal.query.ProductLogQuery;
import com.gzw.debit.dal.mapper.ext.ProductLogExtMapper;
import com.gzw.debit.core.manager.ProductLogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manager for ProductLog.
 */

@Primary
@Component
public class ProductLogManagerImpl implements ProductLogManager{

    
    @Autowired
    protected ProductLogExtMapper productLogExtMapper;
    /**
     * query count by query condition.
     */
    @Override
    public int countByQuery(ProductLogQuery query){
        return productLogExtMapper.countByQuery(query);
    }

    /**
     * delete by query condition.
     */
    @Override
    public int deleteByQuery(ProductLogQuery query){
        return productLogExtMapper.deleteByQuery(query);
    }

    /**
     * delete by primary key.
     */
    @Override
    public int deleteByPrimaryKey(ProductLogDO record){
        return productLogExtMapper.deleteByPrimaryKey(record);
    }

    /**
     * insert selective.
     */
    @Override
    public long insertSelective(ProductLogDO record){
        return productLogExtMapper.insertSelective(record);
    }

    /**
     * select by query condition.
     */
    @Override
    public List<ProductLogDO> selectByQuery(ProductLogQuery query){
        return productLogExtMapper.selectByQuery(query);
    }

    /**
     * select by query condition with page.
    */
    @Override
    public PageResult<ProductLogDO> selectByQueryWithPage(ProductLogQuery query) {
        PageResult<ProductLogDO> result = new PageResult<ProductLogDO>();
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
    public ProductLogDO selectByPrimaryKey(Long id){
        return productLogExtMapper.selectByPrimaryKey(id);
    }

    /**
     * update by query condition selective.
     */
    @Override
    public int updateByQuerySelective( ProductLogDO record,  ProductLogQuery query){
        return productLogExtMapper.updateByQuerySelective(record,  query);
    }

    /**
     * update by query condition.
     */
    @Override
    public int updateByQuery( ProductLogDO record,  ProductLogQuery query){

        return productLogExtMapper.updateByQuery(record, query);
    }

    /**
     * update by primary key selective.
     */
    @Override
    public int updateByPrimaryKeySelective(ProductLogDO record){
        return productLogExtMapper.updateByPrimaryKeySelective(record);
    }
}