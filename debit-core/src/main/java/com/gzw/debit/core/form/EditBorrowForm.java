package com.gzw.debit.core.form;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * auth:gujian
 * time:2018/7/14
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class EditBorrowForm implements Serializable{

    /**
     * borrow_id
     * debit_borrow.id
     */
    private Long id;

    /**
     * borrow跳转链接
     * debit_borrow.url
     */
    private String url;

    /**
     * borrow图片链接
     * debit_borrow.image
     */
    private String image;

    /**
     *
     * debit_borrow.borrow_order
     */
    private Integer borrowOrder;

    /**
     * 产品名称
     * debit_borrow.product_name
     */
    private String productName;

    /**
     * 产品介绍
     * debit_borrow.product_detail
     */
    private String productDetail;

    /**
     * 贷款介绍
     * debit_borrow.debit_desc
     */
    private String debitDesc;

    /**
     * 所需材料
     * debit_borrow.need_data
     */
    private String needData;

    /**
     * 申请资格
     * debit_borrow.qualification
     */
    private String qualification;

    /**
     * 放贷人数
     * debit_borrow.people_number
     */
    private Integer peopleNumber;

    /**
     * 放款速度
     * debit_borrow.fast_time
     */
    private String fastTime;

    /**
     * 放款金额
     * debit_borrow.debit_money
     */
    private String debitMoney;

    /**
     * 最短借款时间
     * debit_borrow.debit_time
     */
    private String debitTime;

    /**
     * 利率
     * debit_borrow.monthy_rate
     */
    private String monthyRate;



}
