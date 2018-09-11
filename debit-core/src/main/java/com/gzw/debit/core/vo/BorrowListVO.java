package com.gzw.debit.core.vo;

import com.gzw.debit.dal.model.BorrowDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class BorrowListVO implements Serializable{

//    private static final long serialVersionUID = 1113799434508676093L;

    /**
     * borrow_id
     */
    private Long id;


    /**
     * borrow图片链接
     */
    private String image;

    /**
     * borrow序号
     */
    private Integer borrowOrder;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品介绍
     */
    private String productDetail;
    /**
     * 跳转url
     */
    private String url;

    /**
     * 放贷人数
     */
    private Integer peopleNumber;

    /**
     * 列表点击次数
     */
    private Integer listClickCount;

    /**
     * 详情点击次数
     */
    private Integer detailClickCount;



    private java.math.BigDecimal monthyRate;

    public static List<BorrowListVO> createListByDO(List<BorrowDO> borrowDOS){
        if(CollectionUtils.isEmpty(borrowDOS)){
            return new ArrayList();
        }
        List<BorrowListVO> borrowVOS = new ArrayList<>();
        for(BorrowDO bannerDO: borrowDOS){
            BorrowListVO borrowVO = new BorrowListVO();
            BeanUtils.copyProperties(bannerDO,borrowVO);
            borrowVOS.add(borrowVO);
        }
        Collections.sort(borrowVOS, (o1, o2)->{
            if(o1.borrowOrder == null || o2.borrowOrder == null){
                return 0;
            }
            if(o1.borrowOrder >= o2.borrowOrder){
                return 1;
            }
            return -1;
        });
        return borrowVOS;

    }
}
