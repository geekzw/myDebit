package com.gzw.debit.core.utils;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/6/26
 * email:gujian@maihaoche.com
 * describe:
 */
public class SmsCodeUtil {

    public static String createRandomVcode(){
        //验证码
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
//        CodeEntry codeEntry = new CodeEntry("code",vcode);
        return vcode;
    }

    public static class CodeEntry implements Serializable{
        private String name;
        private String code;

        public CodeEntry(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

}
