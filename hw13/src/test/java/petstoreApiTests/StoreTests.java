package petstoreApiTests;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static petstoreApiTests.TestHelper.runJsonSchemaFactory;

public class StoreTests {

    @Test
    void storePost() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 5);
        jsonObject.addProperty("petId", 5);
        jsonObject.addProperty("quantity", 5);
        jsonObject.addProperty("shipdate", "2021-07-31T01:24:41.409+0000");
        jsonObject.addProperty("status", "available");
        jsonObject.addProperty("complete", true);
        Response response = RestAssured.given().contentType("application/json")
                .log().all().body(jsonObject.toString()).post("https://petstore.swagger.io/v2/store/order/");
        response.then().body(matchesJsonSchemaInClasspath("storeSchema.json").using(runJsonSchemaFactory()));
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void validateStoreGet() {
        Response response = RestAssured.get("https://petstore.swagger.io/v2/store/order/5");
        response.then().body(matchesJsonSchemaInClasspath("storeSchema.json").using(runJsonSchemaFactory()));
        Assertions.assertEquals(200, response.statusCode());
    }
}
