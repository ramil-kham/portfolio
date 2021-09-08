package dbTests;

import DBO.TestUser;
import client.CustomerDBClient;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DataBaseTests {
    private static CustomerDBClient dbClient;

    @BeforeAll
    public static void createCustomers() {
        dbClient = new CustomerDBClient();
        dbClient.saveCustomer(new TestUser("email888", "name444", "s", "sr"));
        dbClient.saveCustomer(new TestUser("email4444", "name3334", "scz", "vxcx"));
    }

    @AfterAll
    public static void clearDB() {
       // dbClient.deleteAllCustomers();
    }

    @Test
    void getCustomersTest() {
        List<TestUser> actualCustomers = dbClient.findAllCustomers();
        TestUser[] expectedCustomers = RestAssured.get("http://localhost:3000/customers/").as(TestUser[].class);
        Assertions.assertEquals(expectedCustomers[0], actualCustomers.get(0));
        Assertions.assertEquals(expectedCustomers.length, actualCustomers.size());
    }

    @Test
    void getCustomerByIdTest() {
        List<TestUser> allCustomers = dbClient.findAllCustomers();
        int id = allCustomers.get(RandomUtils.nextInt(0, allCustomers.size() - 1)).getId();
        TestUser actualCustomer = dbClient.findCustomerById(id);
        TestUser expectedCustomer = RestAssured.get("http://localhost:3000/customers/" + id).as(TestUser.class);
        Assertions.assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    void createCustomerTest() {
        TestUser expectedCustomer = RestAssured.given().contentType(ContentType.JSON).body(generateCustomerBody()).post("http://localhost:3000/customers/").as(TestUser.class);
        TestUser actualCustomer = dbClient.findCustomerById(expectedCustomer.getId());
        Assertions.assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    void editCustomerTest() {
        List<TestUser> allCustomers = dbClient.findAllCustomers();
        int id = allCustomers.get(RandomUtils.nextInt(0, allCustomers.size() - 1)).getId();
        TestUser customerBeforeRequest = dbClient.findCustomerById(id);
        TestUser expectedCustomer = RestAssured.given().contentType(ContentType.JSON).body(generateCustomerBody()).put("http://localhost:3000/customers/" + id).as(TestUser.class);
        TestUser customerAfterRequest = dbClient.findCustomerById(id);
        Assertions.assertEquals(expectedCustomer.getId(), customerBeforeRequest.getId());
        Assertions.assertEquals(expectedCustomer, customerAfterRequest);
    }

    @Test
    void deleteCustomerTest() {
        List<TestUser> allCustomers = dbClient.findAllCustomers();
        int id = allCustomers.get(RandomUtils.nextInt(0, allCustomers.size() - 1)).getId();
        Response expectedCustomer = RestAssured.delete("http://localhost:3000/customers/" + id);
        TestUser customerAfterRequest = dbClient.findCustomerById(id);
        Assertions.assertEquals("Customer was deleted successfully!", expectedCustomer.getBody().jsonPath().getString("message"));
        Assertions.assertNull(customerAfterRequest);
    }

    @Test
    void checkUpdateHibernateMethod() {
        TestUser testCustomerBefore = new TestUser("testEmail", "testName", "3e", "34");
        dbClient.saveCustomer(testCustomerBefore);
        List<TestUser> allCustomers = dbClient.findAllCustomers();
        TestUser savedCustomer = allCustomers.get(allCustomers.size() - 1);
        TestUser testCustomerAfter = new TestUser( savedCustomer.getId(), "testEmail2", "testName2", "434", "34");
        dbClient.updateCustomer(testCustomerAfter);
        Assertions.assertEquals(testCustomerAfter, dbClient.findCustomerById(savedCustomer.getId()));
    }

    @Test
    void checkUpdateHibernateMethodWithIndex() {
        TestUser testCustomerBefore = new TestUser("testEmail", "testName", "rff", "dscs");
        dbClient.saveCustomer(testCustomerBefore);
        TestUser testCustomerAfter = new TestUser(dbClient.findIndexCustomer(), "testEmail2", "testName2", "ewf", "wef");
        dbClient.updateCustomer(testCustomerAfter);
        Assertions.assertEquals(testCustomerAfter, dbClient.findCustomerById(dbClient.findIndexCustomer()));
    }

    @Test
    void checkDeleteHibernateMethod() {
        TestUser testCustomerBefore = new TestUser("testEmail", "testName", "458", "wefs");
        dbClient.saveCustomer(testCustomerBefore);
        List<TestUser> allCustomers = dbClient.findAllCustomers();
        TestUser savedCustomer = allCustomers.get(allCustomers.size() - 1);
        dbClient.deleteCustomer(savedCustomer);
        Assertions.assertNull(dbClient.findCustomerById(savedCustomer.getId()));
    }

    private String generateCustomerBody() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", RandomStringUtils.randomAlphanumeric(4) + "@test.tt");
        jsonObject.addProperty("date", RandomStringUtils.randomAlphabetic(5));
        jsonObject.addProperty("city", RandomStringUtils.randomAlphabetic(5));
        jsonObject.addProperty("phone", RandomStringUtils.randomAlphabetic(5));
        return jsonObject.toString();
    }
}
