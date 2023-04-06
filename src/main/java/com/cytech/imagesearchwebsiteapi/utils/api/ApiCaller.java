package com.cytech.imagesearchwebsiteapi.utils.api;

import com.cytech.imagesearchwebsiteapi.utils.api.domain.Response;
import com.cytech.imagesearchwebsiteapi.utils.api.domain.RequestBody;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class ApiCaller {
    public Response callGet(String url, RequestBody request) {
        Response response = null;
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
            response = new Response(httpResponse.getStatusLine().getStatusCode(), responseBody);
            System.out.println("Response body: " + response);

            // 关闭HttpClient
            httpClient.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return response;
    }

    public Response callPost(String url, RequestBody request) {
        Response response = new Response();
        try {
            // 创建HttpClient实例
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 创建HttpPost请求对象
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            httpPost.addHeader("Content-Type", "application/json");

            // 设置请求体
            String requestBody = "{\"" + request.getKey() + "\":\"" + request.getValue() + "\"}";
            StringEntity requestEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
            httpPost.setEntity(requestEntity);

            // 发送POST请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            // 获取响应实体
            HttpEntity responseEntity = httpResponse.getEntity();

            // 解析响应内容
            if (responseEntity != null) {
                InputStream inputStream = responseEntity.getContent();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) > -1) {
                    byteArrayOutputStream.write(buffer, 0, len);
                }
                byteArrayOutputStream.flush();
                String responseBody = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
                response.setCode(statusCode);
                response.setData(responseBody);
                System.out.println("Response body: " + responseBody);
            }
            // 关闭HttpClient
            httpClient.close();

        } catch (Exception e) {
            System.out.println("ClientProtocolException: " + e.getMessage());
        }
        return response;
    }
}
