package com.gzw.debit.core.ao.impl;

import com.gzw.debit.core.ao.AppMainAO;
import com.gzw.debit.core.enums.StatusEnum;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.BannerManager;
import com.gzw.debit.core.manager.BorrowManager;
import com.gzw.debit.core.vo.BanAndBorVO;
import com.gzw.debit.core.vo.BannerVO;
import com.gzw.debit.core.vo.BorrowVO;
import com.gzw.debit.dal.model.BannerDO;
import com.gzw.debit.dal.model.BorrowDO;
import com.gzw.debit.dal.query.BannerQuery;
import com.gzw.debit.dal.query.BorrowQuery;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BannerManager bannerManager;
    @Autowired
    private BorrowManager borrowManager;

    @Override
    public BaseResponse<BanAndBorVO> getMainData() {
        BanAndBorVO banAndBorVO = new BanAndBorVO();
        BannerQuery bannerQuery = new BannerQuery();
        bannerQuery.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode());
        List<BannerDO> bannerDOS = bannerManager.selectByQuery(bannerQuery);
        banAndBorVO.setBannerVOList(BannerVO.createListByDO(bannerDOS));

        BorrowQuery borrowQuery = new BorrowQuery();
        borrowQuery.createCriteria().andStatusEqualTo(StatusEnum.NORMAL_STATUS.getCode());
        List<BorrowDO> borrowDOS = borrowManager.selectByQuery(borrowQuery);
        banAndBorVO.setBorrowVOList(BorrowVO.createListByDO(borrowDOS));
        return BaseResponse.create(banAndBorVO);
    }
}
