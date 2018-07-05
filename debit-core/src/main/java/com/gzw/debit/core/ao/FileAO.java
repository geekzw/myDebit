package com.gzw.debit.core.ao;

import com.gzw.debit.core.form.DownLoadForm;
import com.gzw.debit.core.form.base.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * auth:gujian
 * time:2018/7/5
 * email:gujian@maihaoche.com
 * describe:
 */
public interface FileAO {

    BaseResponse<String> downLoadAndroidFile(DownLoadForm form, HttpServletRequest request, HttpServletResponse response);
}
