package com.gzw.debit.core.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *   
 *  * <p>  </p>
 *   
 *  * @author: gujian（gujian@maihaoche.com）
 *  * @date: 2019-04-12 09:25
 *
 * @since V1.0
 *  
 */
public class TestUtil {


    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.createDefault();//创建httpclient对象
        String URL = "http://ybqbzc.newhopeai.com/?channel=yuebang";//设置URL
        HttpGet httpGet = new HttpGet(URL);//创建httpget请求
        httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding","gzip,deflate,sdch");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpGet.setHeader("Connection","keep-alive");
//        httpGet.setHeader("Host", "blog.csdn.net");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");

        try {
            HttpResponse response = httpClient.execute(httpGet);//使用httpclient执行请求
            int returnCode = response.getStatusLine().getStatusCode();//获取返回状态码
            if (returnCode == 200) {//200表示成功访问
                String html = EntityUtils.toString(response.getEntity(), "gbk");//提取源码
                System.out.println(html);//输出
            } else {
                System.out.println("error code: " + returnCode);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
