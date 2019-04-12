package com.gzw.debit.core.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Created by alex on 16/10/11.
 */
public class HttpsClientUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpsClientUtils.class);

    private static ConnectionKeepAliveStrategy collectionStrategy = new ConnectionKeepAliveStrategy() {
        @Override
        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
            // 解析http响应中的Keep-Alive头消息
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement header = it.nextElement();
                String param = header.getName();
                String value = header.getValue();
                // 如果制定了超时时间，则连接的存活时间为timeout的值
                if (value != null && "timeout".equalsIgnoreCase(param)) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Value format error.", e);
                    }
                }
            }
            // 否则默认存活30秒
            return Long.valueOf(30) * 1000;
        }
    };

    public static HttpClient createClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
        ConnectionSocketFactory plainConnectionSocketFactory = new PlainConnectionSocketFactory();
        registryBuilder.register("http", plainConnectionSocketFactory);

        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        //信任任何链接
        TrustStrategy anyTrustStrategy = new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        };

        SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, anyTrustStrategy).build();
        LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        registryBuilder.register("https", sslSF);

        Registry<ConnectionSocketFactory> registry = registryBuilder.build();
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(registry);
        clientConnectionManager.setMaxTotal(2);
        clientConnectionManager.setDefaultMaxPerRoute(20);

        return HttpClientBuilder.create().setConnectionManager(clientConnectionManager).build();
    }


    public static Map get(String url) {

        try {
            HttpGet httpGet = new HttpGet(url);
            HttpClient client = createClient();

            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(2000).setConnectTimeout(2000).build();
            httpGet.setConfig(requestConfig);

            HttpResponse response = client.execute(httpGet);

            if (response.getStatusLine().getStatusCode() != 200) {

                LOGGER.error("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + url);

                throw new Error("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + url);
            }

            HttpEntity entity = response.getEntity();
            String resultStr = EntityUtils.toString(entity, "utf-8");

            Gson gson = new Gson();
            return gson.fromJson(resultStr, HashMap.class);

        } catch (Exception ex) {
            LOGGER.error("get err", ex);
            throw new Error(ex);
        }

    }

    public static String getStr(String url) {

        try {
            HttpGet httpGet = new HttpGet(url);
            HttpClient client = createClient();

            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(2000).setConnectTimeout(2000).build();
            httpGet.setConfig(requestConfig);

            HttpResponse response = client.execute(httpGet);

            if (response.getStatusLine().getStatusCode() != 200) {

                LOGGER.error("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + url);

                throw new Error("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + url);
            }

            HttpEntity entity = response.getEntity();
            String resultStr = EntityUtils.toString(entity, "utf-8");

            return resultStr;

        } catch (Exception ex) {
            LOGGER.error("get err", ex);
            throw new Error(ex);
        }

    }

    public static String getHtml(String url){
        HttpClient httpClient = HttpClients.createDefault();//创建httpclient对象
        String URL = url;//设置URL
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
                return html;
            } else {
                System.out.println("error code: " + returnCode);
                return null;
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map post(String url, String contentType, Object data) {
        try {
            HttpPost httpPost = new HttpPost(url);
            HttpClient client = createClient();

            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(2000).setConnectTimeout(2000).build();
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-Type", contentType);


            StringEntity requestEntity = new StringEntity(JSON.toJSONString(data), "utf-8");

            httpPost.setEntity(requestEntity);

            HttpResponse response = client.execute(httpPost);

            if (response.getStatusLine().getStatusCode() != 200) {

                LOGGER.error("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + url);

                throw new Error("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + url);
            }

            HttpEntity entity = response.getEntity();
            String resultStr = EntityUtils.toString(entity, "utf-8");
            Gson gson = new Gson();
            return gson.fromJson(resultStr, HashMap.class);

        } catch (Exception ex) {
            LOGGER.error("post err", ex);
            throw new Error(ex);
        }
    }

    /**
     * @param url     请求url
     * @param headers 请求头
     * @param queries 请求参数
     * @return
     */
    public static String doGet(String url, Map<String, String> headers, Map<String, String> queries) {
        try {
            StringBuilder sb = new StringBuilder(url);
            if (queries != null && queries.keySet().size() > 0) {
                boolean firstFlag = true;
                Iterator iterator = queries.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                    if (firstFlag) {
                        sb.append("?")
                                .append((String) entry.getKey())
                                .append("=")
                                .append((String) entry.getValue());
                        firstFlag = false;
                    } else {
                        sb.append("&")
                                .append((String) entry.getKey())
                                .append("=")
                                .append((String) entry.getValue());
                    }
                }
            }
            HttpGet httpGet = new HttpGet(sb.toString());
            if (headers!=null && headers.size() > 0) {
                Iterator<Map.Entry<String, String>> headerIterator = headers.entrySet().iterator();
//                Iterator<String> i = keys.iterator();
                while (headerIterator.hasNext()) {
                    Map.Entry<String, String> headerEntry = headerIterator.next();
                    httpGet.addHeader(headerEntry.getKey(), headerEntry.getValue());
                }
            }
            HttpClient httpclient = createClient();
            HttpResponse response = httpclient.execute(httpGet);
            String resultContent = new Utf8ResponseHandler().handleResponse(
                    response);
            return resultContent;
        } catch (Exception e) {
            LOGGER.error("doGet error", e);
            throw new RuntimeException();
        }
    }

    /**
     * @param url     请求url
     * @param headers 请求头
     * @param params  请求参数 form适用
     * @param body    请求体 json适用
     * @return
     */
    public static String doPost(String url, Map<String, String> headers,
                                Map<String, String> params, String body) {

        HttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        try {
            httpClient = createClient();
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
            String resultContent = new Utf8ResponseHandler().handleResponse(
                    httpResponse);
            return resultContent;
        } catch (Exception e) {
            LOGGER.error("doPost err", e);
            throw new RuntimeException();
        }
    }

    /**
     * utf-8编码
     */
    static class Utf8ResponseHandler implements ResponseHandler<String> {
        @Override
        public String handleResponse(final HttpResponse response)
                throws IOException {
            final StatusLine statusLine = response.getStatusLine();
            final HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                EntityUtils.consume(entity);
                throw new HttpResponseException(statusLine.getStatusCode(),
                        statusLine.getReasonPhrase());
            }
            return entity == null ? null : EntityUtils
                    .toString(entity, Consts.UTF_8.toString());
        }

    }
}
