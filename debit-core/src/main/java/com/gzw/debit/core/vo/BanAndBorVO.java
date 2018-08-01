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

//    private static final long serialVersionUID = 1113799434508676095L;

    private List<BannerVO> bannerVOList;

    private List<BorrowListVO> borrowVOList;
}
