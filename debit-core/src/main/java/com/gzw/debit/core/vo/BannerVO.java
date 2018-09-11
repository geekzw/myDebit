package com.gzw.debit.core.vo;

import com.gzw.debit.dal.model.BannerDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

/**
 * auth:gujian
 * time:2018/6/25
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class BannerVO implements Serializable{

//    private static final long serialVersionUID = 1113799434508676094L;

    /**
     * banner_id
     */
    private Long id;

    /**
     * banner跳转链接
     */
    private String url;

    /**
     * banner图片链接
     */
    private String image;

    /**
     * banner序号
     */
    private Integer bannerOrder;

    /**
     * 点击次数
     */
    private Integer clickCount;


    public static List<BannerVO> createListByDO(List<BannerDO> bannerDOS){
        if(CollectionUtils.isEmpty(bannerDOS)){
            return new ArrayList();
        }
        List<BannerVO> bannerVOS = new ArrayList<>();
        for(BannerDO bannerDO: bannerDOS){
            BannerVO bannerVO = new BannerVO();
            BeanUtils.copyProperties(bannerDO,bannerVO);
            bannerVOS.add(bannerVO);
        }
        Collections.sort(bannerVOS, (o1,o2)->{
            if(o1.bannerOrder == null && o2.bannerOrder == null){
                return 0;
            }
            if(o1.bannerOrder == null){
                return -1;
            }
            if(o2.bannerOrder == null){
                return 1;
            }
            if(o1.bannerOrder == o2.bannerOrder){
                return 0;
            }
            if(o1.bannerOrder > o2.bannerOrder){
                return 1;
            }
            return -1;
        });
        return bannerVOS;

    }
}
