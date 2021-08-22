package apacheTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pojo.ResponseBodyPojo;
import pojo.UserPojo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ApacheHttpUserTests {
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
    @RepeatedTest(3)
    void check200StatusCodeInGetRequestUser()  {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://petstore.swagger.io/v2/user/user1");
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        UserPojo userPojo = (UserPojo)  getObject(httpResponse, UserPojo.class);
        System.out.println(userPojo);
        Assertions.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    void check200StatusCodeInPostRequestUser() throws UnsupportedEncodingException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://petstore.swagger.io/v2/user/");
        httpPost.setHeader("Content-Type", "application/json");
        String json = "{\n" +
                "  \"id\": 123,\n" +
                "  \"username\": \"user1\",\n" +
                "  \"firstName\": \"123\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"userStatus\": 0\n" +
                "}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        ResponseBodyPojo responseBodyPojo = (ResponseBodyPojo) getObject(httpResponse, ResponseBodyPojo.class);
        System.out.println(responseBodyPojo);
        Assertions.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    void check200StatusCodeInPutRequestUser() throws UnsupportedEncodingException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("https://petstore.swagger.io/v2/user/user1");
        httpPut.setHeader("Content-Type", "application/json");
        String json = "{\n" +
                "  \"id\": 456,\n" +
                "  \"username\": \"user1\",\n" +
                "  \"firstName\": \"456\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"userStatus\": 0\n" +
                "}";
        StringEntity entity = new StringEntity(json);
        httpPut.setEntity(entity);
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPut);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        ResponseBodyPojo responseBodyPojo = (ResponseBodyPojo) getObject(httpResponse, ResponseBodyPojo.class);
        System.out.println(responseBodyPojo);
        Assertions.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    @RepeatedTest(3)
    void check200StatusCodeInDeleteRequestUser()  {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete("https://petstore.swagger.io/v2/user/string");
        httpDelete.setHeader("Content-Type", "application/json");
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpDelete);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        ResponseBodyPojo responseBodyPojo = (ResponseBodyPojo) getObject(httpResponse, ResponseBodyPojo.class);
        System.out.println(responseBodyPojo);
        Assertions.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }
}
