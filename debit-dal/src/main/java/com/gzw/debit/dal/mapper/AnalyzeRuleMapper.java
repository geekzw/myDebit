package com.gzw.debit.dal.mapper;

import com.gzw.debit.dal.model.AnalyzeRuleDO;
import com.gzw.debit.dal.query.AnalyzeRuleQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * MyBatis Mapper for AnalyzeRule.
 */
 @Mapper
 @Repository
public interface AnalyzeRuleMapper {
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
    int insertSelective(AnalyzeRuleDO record);

    /**
     * select by query condition.
     */
    List<AnalyzeRuleDO> selectByQuery(AnalyzeRuleQuery query);

    /**
     * select by primary key.
     */
    AnalyzeRuleDO selectByPrimaryKey(Long id);

    /**
     * update by query condition selective.
     */
    int updateByQuerySelective(@Param("record") AnalyzeRuleDO record, @Param("query") AnalyzeRuleQuery query);

    /**
     * update by query condition.
     */
    int updateByQuery(@Param("record") AnalyzeRuleDO record, @Param("query") AnalyzeRuleQuery query);

    /**
     * update by primary key selective.
     */
    int updateByPrimaryKeySelective(AnalyzeRuleDO record);
}