package com.gzw.debit.core.manager;

import com.gzw.debit.dal.querybase.PageResult;
import com.gzw.debit.dal.model.AnalyzeRuleDO;
import com.gzw.debit.dal.query.AnalyzeRuleQuery;

import java.util.List;


/**
 * Manager for AnalyzeRule.
 */
public interface AnalyzeRuleManager {
    /**
     * query count by query condition.
     */
    int countByQuery(AnalyzeRuleQuery query);

    /**
     * delete by query condition.
     */
    int deleteByQuery(AnalyzeRuleQuery query);

    /**
     * delete by primary key.
     */
    int deleteByPrimaryKey(AnalyzeRuleDO record);

    /**
     * insert selective.
     */
    long insertSelective(AnalyzeRuleDO record);

    /**
     * select by query condition.
     */
    List<AnalyzeRuleDO> selectByQuery(AnalyzeRuleQuery query);


    /**
     * select by query condition with page.
     */
    PageResult<AnalyzeRuleDO> selectByQueryWithPage(AnalyzeRuleQuery query);

    /**
     * select by primary key.
     */
    AnalyzeRuleDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(AnalyzeRuleDO record,  AnalyzeRuleQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(AnalyzeRuleDO record, AnalyzeRuleQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(AnalyzeRuleDO record);
}