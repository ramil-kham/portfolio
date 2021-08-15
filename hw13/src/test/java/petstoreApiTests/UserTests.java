package petstoreApiTests;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import static petstoreApiTests.TestHelper.runJsonSchemaFactory;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserTests {
    @Test
    void userPost() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", new Random().nextLong());
        jsonObject.addProperty("username", "Ivanushka");
        jsonObject.addProperty("firstName", "Ivan");
        jsonObject.addProperty("lastName", "Petrov");
        jsonObject.addProperty("email", "petrov@test.tt");
        jsonObject.addProperty("password", "qwerty");
        jsonObject.addProperty("phone", "+79090909090");
        jsonObject.addProperty("userStatus", 1);
        Response response = RestAssured.given().contentType("application/json")
                .body(jsonObject.toString()).post("https://petstore.swagger.io/v2/user");
        response.then().body(matchesJsonSchemaInClasspath("userSchema.json").using(runJsonSchemaFactory()));
        System.out.println(response.statusCode());
        Assertions.assertEquals(200, response.statusCode());
    }
    @Test
    void validateUserGet() {
        Response response = RestAssured.get("https://petstore.swagger.io/v2/user/Ivanushka");
        response.then().body(matchesJsonSchemaInClasspath("userSchema.json").using(runJsonSchemaFactory()));
        Assertions.assertEquals(200, response.statusCode());
    }
}
