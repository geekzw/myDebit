package com.gzw.debit.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BannerDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * banner_id
     * debit_banner.id
     */
    private Long id;

    /**
     * banner跳转链接
     * debit_banner.url
     */
    private String url;

    /**
     * banner图片链接
     * debit_banner.image
     */
    private String image;

    /**
     * 
     * debit_banner.banner_order
     */
    private Integer bannerOrder;

    /**
     * 逻辑状态。1有效。0无效。
     * debit_banner.status
     */
    private Integer status;

    /**
     * 创建时间
     * debit_banner.gmt_create
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次的修改时间
     * debit_banner.gmt_modified
     */
    private LocalDateTime gmtModified;

    /**
     * 产品名称
     * debit_banner.product_name
     */
    private String productName;

    /**
     * 点击次数
     * debit_banner.click_count
     */
    private Integer clickCount;

}