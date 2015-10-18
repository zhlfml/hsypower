package com.hsypower.epct.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas on 2015/10/18.
 */
public class HttpClient {

    public static String get(String uri, Map<String, String> params) {
        boolean noMark = uri.indexOf('?') == -1;
        StringBuffer sb = new StringBuffer(uri);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (StringUtils.isNotEmpty(entry.getValue())) {
                sb.append("&")
                        .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue());
            }
        }
        if (noMark) {
            int pos = uri.length();
            sb.replace(pos, pos + 1, "?");
        }
        HttpGet httpGet = new HttpGet(sb.toString());

        return execute(httpGet);
    }

    public static String post(String uri, Map<String, String> params) {
        HttpPost httpPost = new HttpPost(uri);
        List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (StringUtils.isNotEmpty(entry.getValue())) {
                postData.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        HttpEntity reqEntity = new UrlEncodedFormEntity(postData, Charset.defaultCharset());
        httpPost.setEntity(reqEntity);

        return execute(httpPost);
    }

    /**
     *
     *
     * @param uri 上传路径
     * @param file 文件
     * @return 响应内容
     */
    public static String upload(String uri, File file) {
        HttpPost httpPost = new HttpPost(uri);
        FileBody fileBody = new FileBody(file);
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("file", fileBody)
                .build();
        httpPost.setEntity(reqEntity);

        return execute(httpPost);
    }

    /**
     * 通过文件流上传，服务器端获取不到文件名
     *
     * @param uri 上传路径
     * @param inputStream 文件流
     * @return 响应内容
     */
    public static String upload(String uri, InputStream inputStream) {
        HttpPost httpPost = new HttpPost(uri);
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addBinaryBody("file", inputStream)
                .build();
        httpPost.setEntity(reqEntity);

        return execute(httpPost);
    }

    public static String execute(HttpUriRequest request) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode() ||
                    HttpStatus.SC_NOT_MODIFIED == response.getStatusLine().getStatusCode()) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    return EntityUtils.toString(resEntity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // ignore
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        String responseText = null;
//        Map<String, String> loginParams = new HashMap<String, String>();
//        loginParams.put("name", "admin");
//        loginParams.put("password", "password");
//        responseText = HttpClient.post("http://localhost:8080/hsypower/admin/login", loginParams);
//        System.out.println(responseText);
//
//        Map<String, String> channelParams = new HashMap<String, String>();
//        channelParams.put("channelId", "4");
//        responseText = HttpClient.get("http://localhost:8080/hsypower/admin/channel/modify?a=b", channelParams);

        File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\829BJDH633AN.jpg");
        responseText = HttpClient.upload("http://localhost:8080/file/fileupload", FileUtils.openInputStream(file));
//        responseText = HttpClient.upload("http://localhost:8080/file/fileupload", file);

        System.out.println(responseText);
    }
}
