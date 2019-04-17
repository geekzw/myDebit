package com.gzw.debit.web.controller;

import com.gzw.debit.core.ao.HtmlReplace;
import com.gzw.debit.core.ao.TestAO;
import com.gzw.debit.core.form.base.BaseResponse;
import com.gzw.debit.core.manager.UserManager;
import com.gzw.debit.core.utils.HttpsClientUtils;
import com.gzw.debit.dal.model.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    private String myUrlAPI = "http://";

    @Autowired
    private TestAO testAO;
    @Autowired
    private UserManager userManager;
    @Autowired
    private HtmlReplace htmlReplace;

    @GetMapping("/testRedis.json")
    public BaseResponse<String> testRedis(){
        return testAO.testRedis();
    }

    @GetMapping("/testTransaction.json")
    @Transactional
    public BaseResponse<Boolean> testTransaction(){

        UserDO userDO = new UserDO();
        userDO.setUsername("gujian");
        userDO.setPassword("123");
        userManager.insertSelective(userDO);
        testException();
        return BaseResponse.create();
    }

    private void testException() {

        String string = null;
        string.equals("");
    }

    @GetMapping("/html.json")
    public String getHtml(){
        /**
         * 替换改成数组
         * /user/verification_code/sendmobilecode.html  -> myapi?url=转码(/user/verification_code/sendmobilecode.html)
         * /mobile/public/doregister.html               -> myapi?url=转码(/mobile/public/doregister.html)
         *
         * */
//        return htmlReplace.htmlUrlReplace("http://ybqbzc.newhopeai.com/?channel=yuebang","/mobile/public/doregister.html",myUrlAPI+"?url=xxxx");
        String result = htmlReplace.htmlUrlReplace("http://ybqbzc.newhopeai.com/?channel=yuebang","<head>","<head><base href=\"http://ybqbzc.newhopeai.com\" />");
        String srouce = "/mobile/public/doregister.html";
        return result.replaceAll(srouce,"/normalPost?url="+srouce);
    }

    @PostMapping("/normalPost")
    public String nPost(HttpServletRequest req){
        // 配置文件， 每个网站需要储存哪些字段
        try{
            String code = req.getParameter("code");
            String mobile = req.getParameter("mobile");


        }catch(Exception e){
            e.printStackTrace();
        }

        String[] arr = req.getQueryString().split("=");
        String url = null;
        if(arr.length > 1){ url = arr[1]; }
        if (url == null){ return ""; }
        try{
            HttpPost post = new HttpPost();
            HttpClient httpClient = null;
            HttpPost httpPost = new HttpPost(url);
            try {
                httpClient = HttpsClientUtils.createClient();
                // 设置头信息
                if (!CollectionUtils.isEmpty(headers)) {
                    headers.forEach((key, value) -> httpPost.addHeader(key, value));
                }
                // 设置请求参数
                if (!CollectionUtils.isEmpty(params)) {
                    List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
                    params.forEach((key, value) -> pairs.add(new BasicNameValuePair(key, value)));
                    httpPost.setEntity(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
                }
                // 设置实体 优先级高
                if (StringUtils.isNotBlank(body)) {
                    StringEntity entity = new StringEntity(body, Consts.UTF_8);
                    entity.setContentType(org.apache.http.entity.ContentType.APPLICATION_JSON.getMimeType());
                    entity.setContentEncoding(Consts.UTF_8.toString());
                    httpPost.setEntity(entity);
                }

                HttpResponse httpResponse = httpClient.execute(httpPost);
                String resultContent = new HttpsClientUtils.Utf8ResponseHandler().handleResponse(
                        httpResponse);
                return resultContent;
            } catch (Exception e) {
                LOGGER.error("doPost err", e);
                throw new RuntimeException();
            }
        }catch (Exception e) {

        }

        return "";
    }

}
