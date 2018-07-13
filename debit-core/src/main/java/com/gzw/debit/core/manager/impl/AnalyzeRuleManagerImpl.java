package com.gzw.debit.core.manager.impl;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.AnalyzeRuleDO;
import com.gzw.debit.dal.query.AnalyzeRuleQuery;
import com.gzw.debit.dal.mapper.ext.AnalyzeRuleExtMapper;
import com.gzw.debit.core.manager.AnalyzeRuleManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manager for AnalyzeRule.
 */

@Primary
@Component
public class AnalyzeRuleManagerImpl implements AnalyzeRuleManager{

    
    @Autowired
    protected AnalyzeRuleExtMapper analyzeRuleExtMapper;
    /**
     * query count by query condition.
     */
    @Override
    public int countByQuery(AnalyzeRuleQuery query){
        return analyzeRuleExtMapper.countByQuery(query);
    }

    /**
     * delete by query condition.
     */
    @Override
    public int deleteByQuery(AnalyzeRuleQuery query){
        return analyzeRuleExtMapper.deleteByQuery(query);
    }

    /**
     * delete by primary key.
     */
    @Override
    public int deleteByPrimaryKey(AnalyzeRuleDO record){
        return analyzeRuleExtMapper.deleteByPrimaryKey(record);
    }

    /**
     * insert selective.
     */
    @Override
    public long insertSelective(AnalyzeRuleDO record){
        return analyzeRuleExtMapper.insertSelective(record);
    }

    /**
     * select by query condition.
     */
    @Override
    public List<AnalyzeRuleDO> selectByQuery(AnalyzeRuleQuery query){
        return analyzeRuleExtMapper.selectByQuery(query);
    }

    /**
     * select by query condition with page.
    */
    @Override
    public PageResult<AnalyzeRuleDO> selectByQueryWithPage(AnalyzeRuleQuery query) {
        PageResult<AnalyzeRuleDO> result = new PageResult<AnalyzeRuleDO>();
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
    public AnalyzeRuleDO selectByPrimaryKey(Long id){
        return analyzeRuleExtMapper.selectByPrimaryKey(id);
    }

    /**
     * update by query condition selective.
     */
    @Override
    public int updateByQuerySelective( AnalyzeRuleDO record,  AnalyzeRuleQuery query){
        return analyzeRuleExtMapper.updateByQuerySelective(record,  query);
    }

    /**
     * update by query condition.
     */
    @Override
    public int updateByQuery( AnalyzeRuleDO record,  AnalyzeRuleQuery query){

        return analyzeRuleExtMapper.updateByQuery(record, query);
    }

    /**
     * update by primary key selective.
     */
    @Override
    public int updateByPrimaryKeySelective(AnalyzeRuleDO record){
        return analyzeRuleExtMapper.updateByPrimaryKeySelective(record);
    }
}