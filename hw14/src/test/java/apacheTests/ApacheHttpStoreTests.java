package apacheTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.client.methods.HttpDelete;
import pojo.ResponseBodyPojo;
import pojo.StorePojo;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ApacheHttpStoreTests {
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
    void check200StatusCodeInGetRequestStore()  {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        int id = RandomUtils.nextInt(0, 10);
        HttpGet httpGet = new HttpGet("https://petstore.swagger.io/v2/store/order/" + id);
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        StorePojo storePojo = (StorePojo) getObject(httpResponse, StorePojo.class);
        System.out.println(storePojo);
        Assertions.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
    }

    @Test
    void check2000StatusCodeInPostRequestStore() throws UnsupportedEncodingException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://petstore.swagger.io/v2/store/order/");
        httpPost.setHeader("Content-Type", "application/json");
        String json = "{\n" +
                "    \"id\": 1,\n" +
                "    \"petId\": 102030,\n" +
                "    \"quantity\": 0,\n" +
                "    \"shipDate\": \"2021-08-22T12:42:19.320+0000\",\n" +
                "    \"status\": \"placed\",\n" +
                "    \"complete\": true\n" +
                "}";
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        StorePojo storePojo = (StorePojo) getObject(httpResponse, StorePojo.class);
        System.out.println(storePojo);
        Assertions.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
    }

    @Test
    @RepeatedTest(10)
    void check200StatusCodeInDeleteRequestStore()  {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        int id = RandomUtils.nextInt(0, 10);
        HttpDelete httpDelete = new HttpDelete("https://petstore.swagger.io/v2/store/order/" + id);
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
