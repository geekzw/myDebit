package com.gzw.debit.core.ao;

/**
 *   
 *  * <p>  </p>
 *   
 *  * @author: gujian（gujian@maihaoche.com）
 *  * @date: 2019-04-12 10:19
 *
 * @since V1.0
 *  
 */
public interface HtmlReplace {

    /**
     * 把网页中特定url替换为自己的url
     * @param address
     * @param oriUrl
     * @param newUrl
     * @return
     */
    String htmlUrlReplace(String address,String oriUrl,String newUrl);
}
