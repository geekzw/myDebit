package com.gzw.debit.core.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class BanAndBorVO implements Serializable{

    private List<BannerVO> bannerVOList;

    private List<BorrowListVO> borrowVOList;
}
