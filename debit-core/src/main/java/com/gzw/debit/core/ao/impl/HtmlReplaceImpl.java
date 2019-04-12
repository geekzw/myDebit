package com.gzw.debit.core.ao.impl;

import com.gzw.debit.core.ao.HtmlReplace;
import com.gzw.debit.core.utils.HttpsClientUtils;
import org.springframework.stereotype.Service;

/**
 *   
 *  * <p>  </p>
 *   
 *  * @author: gujian（gujian@maihaoche.com）
 *  * @date: 2019-04-12 10:20
 *
 * @since V1.0
 *  
 */
@Service
public class HtmlReplaceImpl implements HtmlReplace {


    @Override
    public String htmlUrlReplace(String address, String oriUrl, String newUrl) {
        String oriHtml = HttpsClientUtils.getHtml(address);
        if(null == oriHtml){
            return "error";
        }

        String s = oriHtml.replaceAll(oriUrl, newUrl);

        return s;
    }
}
