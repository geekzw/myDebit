package com.gzw.debit.core.ao.impl;

import com.gzw.debit.core.ao.AppMainAO;
import com.gzw.debit.core.entry.Const;
import com.gzw.debit.core.enums.ErrorEnum;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.BorDetailForm;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.BannerManager;
import com.gzw.debit.core.manager.BorrowManager;
import com.gzw.debit.core.vo.BanAndBorVO;
import com.gzw.debit.core.vo.BannerVO;
import com.gzw.debit.core.vo.BorrowListVO;
import com.gzw.debit.core.vo.BorrowVO;
import com.gzw.debit.dal.model.BannerDO;
import com.gzw.debit.dal.model.BorrowDO;
import com.gzw.debit.dal.query.BannerQuery;
import com.gzw.debit.dal.query.BorrowQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
@Service
public class AppMainAOImpl implements AppMainAO {

    private static Logger logger = LoggerFactory.getLogger(AppMainAOImpl.class);

    @Autowired
    private BannerManager bannerManager;
    @Autowired
    private BorrowManager borrowManager;

    @Override
    @Cacheable(value = "MainData",key = "1")
    public BaseResponse<BanAndBorVO> getMainData() {
        BanAndBorVO banAndBorVO = new BanAndBorVO();
        BannerQuery bannerQuery = new BannerQuery();
        bannerQuery.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode());
        List<BannerDO> bannerDOS = bannerManager.selectByQuery(bannerQuery);
        banAndBorVO.setBannerVOList(BannerVO.createListByDO(bannerDOS));

        BorrowQuery borrowQuery = new BorrowQuery();
        borrowQuery.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode());
        List<BorrowDO> borrowDOS = borrowManager.selectByQuery(borrowQuery);
        banAndBorVO.setBorrowVOList(BorrowListVO.createListByDO(borrowDOS));
        return BaseResponse.create(banAndBorVO);
    }

    @Override
    @Cacheable(value = "BorrowDetail",key = "#form.id")
    public BaseResponse<BorrowVO> getBorrowDetail(BorDetailForm form) {
        if(form.getId() == null){
            return BaseResponse.create(Const.PARAMS_ERROR,"id不能为空");
        }
        BorrowQuery borrowQuery = new BorrowQuery();
        borrowQuery.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode())
                .andIdEqualTo(form.getId());
        logger.info("查询数据库");
        BorrowDO borrowDO = borrowManager.selectByPrimaryKey(form.getId());
        if(borrowDO == null || borrowDO.getStatus() == StatusEnum.DELETE_STATUS.getCode()){
            return BaseResponse.create(Const.LOGIC_ERROR,"找不到对应信息");
        }
        BorrowVO borrowVO = new BorrowVO();
        BeanUtils.copyProperties(borrowDO,borrowVO);
        return BaseResponse.create(borrowVO);
    }
}
