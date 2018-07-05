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
public class BorrowVO implements Serializable{

    /**
     * borrow_id
     */
    private Long id;

    /**
     * borrow跳转链接
     */
    private String url;

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
     * 贷款介绍
     */
    private String debitDesc;

    /**
     * 所需材料
     */
    private String needData;

    /**
     * 申请资格
     */
    private String qualification;

    /**
     * 放贷人数
     */
    private Integer peopleNumber;

    /**
     * 放款速度
     */
    private String fastTime;

    /**
     * 放款金额
     */
    private String debitMoney;

    /**
     * 最短借款时间
     */
    private String debitTime;

    /**
     * 利率
     */
    private java.math.BigDecimal monthyRate;

}
