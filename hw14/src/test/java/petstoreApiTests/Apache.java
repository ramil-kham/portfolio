package petstoreApiTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import httpBean.HttpBeanPojo;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Apache {
    ObjectMapper objectMapper = new ObjectMapper();
    private Object getObject(HttpResponse httpResponse, Class clazz) {
        try {
            return objectMapper.readValue(httpResponse.getEntity().getContent(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    void check200StatusCodeAndHeadersHttpBeanGetRequest() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://httpbin.org/get");
        httpGet.addHeader("Accept", "application/json");
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        HttpBeanPojo httpBeanPojo = (HttpBeanPojo) getObject(httpResponse,HttpBeanPojo.class);
        System.out.println(httpBeanPojo);
        Assertions.assertEquals(200,httpResponse.getStatusLine().getStatusCode());
//        Assertions.assertEquals("httpbin.org", httpBeanPojo.);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
        softly.assertThat(httpBeanPojo.getHeaders().getAccept()).isEqualTo("application/json");
        softly.assertThat(httpBeanPojo.getHeaders().getHost()).isEqualTo("httpbin.org");
        softly.assertAll();
    }
    @Test
    void checkFormInHttpBeanPostRequest() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://httpbin.org/post");
        httpPost.addHeader("Accept", "application/json");
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("name", "value"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        HttpBeanPojo httpBeanPojo = (HttpBeanPojo) getObject(httpResponse,HttpBeanPojo.class);
        Assertions.assertEquals("{name=value}", httpBeanPojo.getArgs().get("form").toString());
        System.out.println(httpBeanPojo);
    }
    @Test
    void checkJsonInHttpBeanPostRequestWithBody() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://httpbin.org/post");

        try {
            StringEntity stringEntity = new StringEntity("{\"name\":\"value\"}");
            httpPost.setEntity(stringEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        HttpBeanPojo httpBeanPojo = (HttpBeanPojo) getObject(httpResponse,HttpBeanPojo.class);
        Assertions.assertEquals("{name=value}", httpBeanPojo.getArgs().get("json").toString());
    }
    @Test
    public void check200InHttpBeanRequest() {
        String login = "login";
        HttpGet httpGet = new HttpGet("http://httpbin.org/basic-auth/" + login + "/password");
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(login, "password"));
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
        HttpResponse httpResponse = null;


    }
}
