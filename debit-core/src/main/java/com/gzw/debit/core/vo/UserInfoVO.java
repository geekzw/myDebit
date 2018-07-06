package com.gzw.debit.core.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/7/6
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class UserInfoVO implements Serializable{

    private String username;

    private Content content;


    @Data
    public static class Content implements Serializable{

        private Integer totalVisitCount;

        private Integer androidVisit;

        private Integer iosVisit;

        private Integer h5Visit;

        private Integer downAndroidCount;

        private Integer downIosCount;

        private Integer totalDown;

        private Integer androidDetailCount;

        private Integer iosDetailCount;

        private Integer totalDetailCount;
    }

}
