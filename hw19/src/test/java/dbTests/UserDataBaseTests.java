package dbTests;

import DBO.User;
import client.UserDBClient;
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

public class UserDataBaseTests {

    private static UserDBClient dbClient;

    @BeforeAll
    public static void createUsers() {
        dbClient = new UserDBClient();
        dbClient.saveUser(new User(1,"Ivan", "22.07.1980", "Ufa", "+79638527410"));
        dbClient.saveUser(new User(2, "Andrey", "01.04.2001", "Moscow", "+79518472360"));
    }

    @AfterAll
    public static void clearDB() {
       // dbClient.deleteAllUsers();
    }

    @Test
    void getUsersTest() {
        List<User> actualUsers = dbClient.findAllUsers();
        User[] expectedUsers = RestAssured.get("http://localhost:3000/users/").as(User[].class);
        Assertions.assertEquals(expectedUsers[0], actualUsers.get(0));
        Assertions.assertEquals(expectedUsers.length, actualUsers.size());
    }

    @Test
    void getUserByIdTest() {
        List<User> allUsers = dbClient.findAllUsers();
        int id = allUsers.get(RandomUtils.nextInt(0, allUsers.size() - 1)).getId();
        User actualUser = dbClient.findUserById(id);
        User expectedUser = RestAssured.get("http://localhost:3000/users/" + id).as(User.class);
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void createUserTest() {
        User expectedUser = RestAssured.given().contentType(ContentType.JSON).body(generateUserBody()).post("http://localhost:3000/users/").as(User.class);
        User actualUser = dbClient.findUserById(expectedUser.getId());
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void editUserTest() {
        List<User> allUsers = dbClient.findAllUsers();
        int id = allUsers.get(RandomUtils.nextInt(0, allUsers.size() - 1)).getId();
        User userBeforeRequest = dbClient.findUserById(id);
        User expectedUser = RestAssured.given().contentType(ContentType.JSON).body(generateUserBody()).put("http://localhost:3000/users/" + id).as(User.class);
        User UserAfterRequest = dbClient.findUserById(id);
        Assertions.assertEquals(expectedUser.getId(), userBeforeRequest.getId());
        Assertions.assertEquals(expectedUser, UserAfterRequest);
    }

    @Test
    void deleteUserTest() {
        List<User> allUsers = dbClient.findAllUsers();
        int id = allUsers.get(RandomUtils.nextInt(0, allUsers.size() - 1)).getId();
        Response expectedUser = RestAssured.delete("http://localhost:3000/users/" + id);
        User UserAfterRequest = dbClient.findUserById(id);
        Assertions.assertEquals("User was deleted successfully!", expectedUser.getBody().jsonPath().getString("message"));
        Assertions.assertNull(UserAfterRequest);
    }

    @Test
    void checkUpdateHibernateMethod() {
        User testUserBefore = new User("testName", "testDate", "testCity", "testPhone");
        dbClient.saveUser(testUserBefore);
        List<User> allUsers = dbClient.findAllUsers();
        User savedUser = allUsers.get(allUsers.size() - 1);
        User testUserAfter = new User( savedUser.getId(), "testName2", "testDate2", "testCity2", "testPhone2");
        dbClient.updateUser(testUserAfter);
        Assertions.assertEquals(testUserAfter, dbClient.findUserById(savedUser.getId()));
    }

    @Test
    void checkDeleteHibernateMethod() {
        User testUserBefore = new User("testName3", "testDate3", "testCity3","testPhone3");
        dbClient.saveUser(testUserBefore);
        List<User> allUsers = dbClient.findAllUsers();
        User savedUser = allUsers.get(allUsers.size() - 1);
        dbClient.deleteUser(savedUser);
        Assertions.assertNull(dbClient.findUserById(savedUser.getId()));
    }

    private String generateUserBody() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", RandomStringUtils.randomAlphabetic(5));
        jsonObject.addProperty("date", RandomStringUtils.randomAlphanumeric(6));
        jsonObject.addProperty("city", RandomStringUtils.randomAlphabetic(6));
        jsonObject.addProperty("phone", RandomStringUtils.randomAlphanumeric(6));
        return jsonObject.toString();
    }
}
