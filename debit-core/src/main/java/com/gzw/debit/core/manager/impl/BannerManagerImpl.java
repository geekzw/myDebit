package com.gzw.debit.core.manager.impl;

import com.gzw.debit.dal.model.BannerDO;
import com.gzw.debit.dal.query.BannerQuery;
import com.gzw.debit.dal.mapper.ext.BannerExtMapper;
import com.gzw.debit.core.manager.BannerManager;

import com.gzw.debit.dal.querybase.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Manager for Banner.
 */

@Primary
@Component
public class BannerManagerImpl implements BannerManager{

    
    @Autowired
    protected BannerExtMapper bannerExtMapper;
    /**
     * query count by query condition.
     */
    @Override
    public int countByQuery(BannerQuery query){
        return bannerExtMapper.countByQuery(query);
    }

    /**
     * delete by query condition.
     */
    @Override
    public int deleteByQuery(BannerQuery query){
        return bannerExtMapper.deleteByQuery(query);
    }

    /**
     * delete by primary key.
     */
    @Override
    public int deleteByPrimaryKey(BannerDO record){
        return bannerExtMapper.deleteByPrimaryKey(record);
    }

    /**
     * insert selective.
     */
    @Override
    public long insertSelective(BannerDO record){
        return bannerExtMapper.insertSelective(record);
    }

    /**
     * select by query condition.
     */
    @Override
    public List<BannerDO> selectByQuery(BannerQuery query){
        return bannerExtMapper.selectByQuery(query);
    }

    /**
     * select by query condition with page.
    */
    @Override
    public PageResult<BannerDO> selectByQueryWithPage(BannerQuery query) {
        PageResult<BannerDO> result = new PageResult<BannerDO>();
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
    public BannerDO selectByPrimaryKey(Long id){
        return bannerExtMapper.selectByPrimaryKey(id);
    }

    /**
     * update by query condition selective.
     */
    @Override
    public int updateByQuerySelective( BannerDO record,  BannerQuery query){
        return bannerExtMapper.updateByQuerySelective(record,  query);
    }

    /**
     * update by query condition.
     */
    @Override
    public int updateByQuery( BannerDO record,  BannerQuery query){

        return bannerExtMapper.updateByQuery(record, query);
    }

    /**
     * update by primary key selective.
     */
    @Override
    public int updateByPrimaryKeySelective(BannerDO record){
        return bannerExtMapper.updateByPrimaryKeySelective(record);
    }
}