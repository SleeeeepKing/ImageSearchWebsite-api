package com.cytech.imagesearchwebsiteapi.utils.api;

import com.cytech.imagesearchwebsiteapi.utils.api.domain.Response;
import com.cytech.imagesearchwebsiteapi.utils.api.domain.RequestBody;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class ApiCaller {
    public Response callGet(String url, RequestBody request) {
        try {
            // 创建HttpClient实例
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 创建URI构建器，并添加查询参数
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameter(request.getKey(), request.getValue());

            // 创建HttpGet请求对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());

            // 发送GET请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            // 获取响应实体
            String responseBody = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("Response body: " + responseBody);

            // 关闭HttpClient
            httpClient.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public Response callPost(String url, RequestBody request) {
        try {
            // 创建HttpClient实例
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 创建HttpPost请求对象
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            httpPost.addHeader("Content-Type", "application/json");

            // 设置请求体
            String requestBody = "{\""+request.getKey()+"\":\""+request.getValue()+"\"}";
            StringEntity requestEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
            httpPost.setEntity(requestEntity);

            // 发送POST请求
            HttpResponse httpResponse = httpClient.execute(httpPost);

            // 获取响应实体
            HttpEntity responseEntity = httpResponse.getEntity();

            // 解析响应内容
            if (responseEntity != null) {
                String responseBody = EntityUtils.toString(responseEntity);
                System.out.println("Response body: " + responseBody);
            }

            // 关闭HttpClient
            httpClient.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }
}
