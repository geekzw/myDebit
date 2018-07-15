package com.gzw.debit.core.form;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/7/14
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class EditBannerForm implements Serializable{
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
     * 产品名称
     * debit_banner.product_name
     */
    private String productName;

}
