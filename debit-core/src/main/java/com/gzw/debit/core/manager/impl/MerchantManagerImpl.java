package com.gzw.debit.core.manager.impl;

import com.gzw.debit.core.manager.MerchantManager;
import com.gzw.debit.dal.mapper.ext.MerchantExtMapper;
import com.gzw.debit.dal.model.MerchantDO;
import com.gzw.debit.dal.model.ext.StrIntKeyValue;
import com.gzw.debit.dal.query.MerchantQuery;
import com.gzw.debit.dal.query.ext.AnalyzeQuery;
import com.gzw.debit.dal.querybase.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manager for Merchant.
 */

@Primary
@Component
public class MerchantManagerImpl implements MerchantManager{

    
    @Autowired
    protected MerchantExtMapper merchantExtMapper;
    /**
     * query count by query condition.
     */
    @Override
    public int countByQuery(MerchantQuery query){
        return merchantExtMapper.countByQuery(query);
    }

    /**
     * delete by query condition.
     */
    @Override
    public int deleteByQuery(MerchantQuery query){
        return merchantExtMapper.deleteByQuery(query);
    }

    /**
     * delete by primary key.
     */
    @Override
    public int deleteByPrimaryKey(MerchantDO record){
        return merchantExtMapper.deleteByPrimaryKey(record);
    }

    /**
     * insert selective.
     */
    @Override
    public long insertSelective(MerchantDO record){
        return merchantExtMapper.insertSelective(record);
    }

    /**
     * select by query condition.
     */
    @Override
    public List<MerchantDO> selectByQuery(MerchantQuery query){
        return merchantExtMapper.selectByQuery(query);
    }

    /**
     * select by query condition with page.
    */
    @Override
    public PageResult<MerchantDO> selectByQueryWithPage(MerchantQuery query) {
        PageResult<MerchantDO> result = new PageResult<MerchantDO>();
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
    public MerchantDO selectByPrimaryKey(Long id){
        return merchantExtMapper.selectByPrimaryKey(id);
    }

    /**
     * update by query condition selective.
     */
    @Override
    public int updateByQuerySelective( MerchantDO record,  MerchantQuery query){
        return merchantExtMapper.updateByQuerySelective(record,  query);
    }

    /**
     * update by query condition.
     */
    @Override
    public int updateByQuery( MerchantDO record,  MerchantQuery query){

        return merchantExtMapper.updateByQuery(record, query);
    }

    /**
     * update by primary key selective.
     */
    @Override
    public int updateByPrimaryKeySelective(MerchantDO record){
        return merchantExtMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer getAnalyzeInfo(AnalyzeQuery query) {
        return merchantExtMapper.getAnalyzeInfo(query);
    }

    @Override
    public List<StrIntKeyValue> getMerchantRegisterData() {
        return merchantExtMapper.getMerchantRegisterData();
    }
}