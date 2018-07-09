package com.gzw.debit.core.form.base;

import com.gzw.debit.core.enums.ErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * auth:gujian
 * time:2018/6/24
 * email:gujian@maihaoche.com
 * describe:
 */
@Data
public class BaseResponse<T> implements Serializable{

    private int code;

    private String desc;

    private boolean isSuccess;

    private T data;

    private Integer pageSize;

    private Integer pageNo;

    private Integer totalCount;

    public static BaseResponse create(ErrorEnum errorEnum){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.code = errorEnum.getCode();
        baseResponse.desc = errorEnum.getDesc();
        baseResponse.isSuccess = false;
        return baseResponse;
    }

    public static<T> BaseResponse<T> create(T data){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ErrorEnum.SUCCESS.getCode());
        baseResponse.setDesc(ErrorEnum.SUCCESS.getDesc());
        baseResponse.isSuccess = true;
        baseResponse.data = data;
        return baseResponse;
    }

    public static BaseResponse create(int code,String desc){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.code = code;
        baseResponse.desc = desc;
        baseResponse.isSuccess = false;
        return baseResponse;
    }

    public static BaseResponse create(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ErrorEnum.SUCCESS.getCode());
        baseResponse.setDesc(ErrorEnum.SUCCESS.getDesc());
        baseResponse.isSuccess = true;
        return baseResponse;
    }
}
