package com.bootx.util.wechat;

import com.bootx.entity.App;
import com.bootx.service.RedisService;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author black
 */
public final class WechatUtils {
    /**
     * PoolingHttpClientConnectionManager
     */
    private static final PoolingHttpClientConnectionManager HTTP_CLIENT_CONNECTION_MANAGER;

    /**
     * CloseableHttpClient
     */
    private static final CloseableHttpClient HTTP_CLIENT;

    @Resource
    private RedisService redisService;

    public static WechatUtils wechatUtils;

    @PostConstruct
    public void init() {
        wechatUtils = this;
        wechatUtils.redisService = this.redisService;
    }

    static {
        HTTP_CLIENT_CONNECTION_MANAGER = new PoolingHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build());
        HTTP_CLIENT_CONNECTION_MANAGER.setDefaultMaxPerRoute(100);
        HTTP_CLIENT_CONNECTION_MANAGER.setMaxTotal(200);
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(60000).setConnectTimeout(60000).setSocketTimeout(60000).build();
        HTTP_CLIENT = HttpClientBuilder.create().setConnectionManager(HTTP_CLIENT_CONNECTION_MANAGER).setDefaultRequestConfig(requestConfig).build();
    }


    /**
     * 不可实例化
     */
    private WechatUtils() {
    }

    public static AccessToken getAccessToken(App app) {
        Assert.notNull(app, "[Assertion failed] - app is required; it must not be null");
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        url = url.replace("APPID", app.getAppId()).replace("APPSECRET", app.getAppSecret());

        String result = WebUtils.get(url, null);
        System.out.println(app.getAppName()+":====================获取token");
        System.out.println(result);
        return JsonUtils.toObject(result, AccessToken.class);
    }


    public static String postJson(String url, Map<String,Object> parameterMap){
        Assert.hasText(url,"");
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url + (StringUtils.contains(url, "?") ? "&" : "?") + "access_token="+parameterMap.get("accessToken"));
            if (!parameterMap.isEmpty()) {
                StringEntity params = new StringEntity(JsonUtils.toJson(parameterMap),"utf-8");
                params.setContentEncoding("UTF-8");
                params.setContentType("application/json");
                httpPost.setEntity(params);
            }
            try (CloseableHttpResponse httpResponse = HTTP_CLIENT.execute(httpPost)) {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    result = EntityUtils.toString(httpEntity);
                    EntityUtils.consume(httpEntity);
                }
            }
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }
    public static String get(String url, Map<String, Object> parameterMap) {
        Assert.hasText(url,"");

        String result = null;
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("access_token",parameterMap.get("accessToken")+""));
            for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                String name = entry.getKey();
                String value = ConvertUtils.convert(entry.getValue());
                if (StringUtils.isNotEmpty(name)) {
                    nameValuePairs.add(new BasicNameValuePair(name, value));
                }
            }

            HttpGet httpGet = new HttpGet(url + (StringUtils.contains(url, "?") ? "&" : "?") + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")));
            CloseableHttpResponse httpResponse = HTTP_CLIENT.execute(httpGet);
            try {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    result = EntityUtils.toString(httpEntity);
                    EntityUtils.consume(httpEntity);
                }
            } finally {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                }
            }
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        System.out.println(result);
        return result;
    }
}