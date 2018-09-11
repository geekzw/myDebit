package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BorrowDO implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private java.math.BigDecimal monthyRate;

    /**
     * 逻辑状态。1有效。0无效。
     * debit_borrow.status
     */
    private Integer status;

    /**
     * 创建时间
     * debit_borrow.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * debit_borrow.gmt_modified
     */
    private LocalDateTime gmtModified;

    /**
     * 列表点击次数
     * debit_borrow.list_click_count
     */
    private Integer listClickCount;

    /**
     * 详情点击次数
     * debit_borrow.detail_click_count
     */
    private Integer detailClickCount;

}