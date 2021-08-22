package petstoreApiTests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static petstoreApiTests.TestHelper.runJsonSchemaFactory;


public class PetTests {

    @Test
    void petPost() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 100);
        jsonObject.addProperty("category", 2);
        jsonObject.addProperty("category", "Dogs");
        jsonObject.addProperty("name", "Tuzik");
        jsonObject.addProperty("photoUrls", "https://petslike.net/media/cache/sylius_blog_item_image/35/6c/d35c891e94aeda634264c402088f.jpeg");
        jsonObject.addProperty("tags", 12);
        jsonObject.addProperty("tags", "string");
        jsonObject.addProperty("status", "available");
        Response response = RestAssured.given().contentType("application/json")
                .log().all().body(jsonObject.toString()).post("https://petstore.swagger.io/v2/pet");
        response.then().body(matchesJsonSchemaInClasspath("petSchema.json").using(runJsonSchemaFactory()));
        Assertions.assertEquals(200, response.statusCode());
    }
    @Test
    void validatePetGet() {
        Response response = RestAssured.get("https://petstore.swagger.io/v2/pet/1");
        response.then().body(matchesJsonSchemaInClasspath("petSchema.json").using(runJsonSchemaFactory()));
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.asString());
    }
}

