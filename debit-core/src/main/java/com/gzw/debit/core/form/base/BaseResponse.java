package com.gzw.debit.core.form.base;

import com.gzw.debit.core.enums.ErrorEnum;
import lombok.Data;

/**
 * auth:gujian
 * time:2018/6/24
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class BaseResponse<T> {

    private int code;

    private String desc;

    private T data;

    public static BaseResponse create(ErrorEnum errorEnum){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.code = errorEnum.getCode();
        baseResponse.desc = errorEnum.getDesc();
        return baseResponse;
    }

    public static<T> BaseResponse<T> create(T data){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ErrorEnum.SUCCESS.getCode());
        baseResponse.setDesc(ErrorEnum.SUCCESS.getDesc());
        baseResponse.data = data;
        return baseResponse;
    }

    public static BaseResponse create(int code,String desc){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.code = code;
        baseResponse.desc = desc;
        return baseResponse;
    }

    public static BaseResponse create(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ErrorEnum.SUCCESS.getCode());
        baseResponse.setDesc(ErrorEnum.SUCCESS.getDesc());
        return baseResponse;
    }
}
