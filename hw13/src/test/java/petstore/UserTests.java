package petstore;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserTests {
    public JsonSchemaFactory runJsonSchemaFactory() {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder()
                .setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();
        return jsonSchemaFactory;
    }

    @Test
    void userPost() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 2);
        jsonObject.addProperty("username", "Ivanushka");
        jsonObject.addProperty("firstname", "Ivan");
        jsonObject.addProperty("lastname", "Petrov");
        jsonObject.addProperty("email", "petrov@test.tt");
        jsonObject.addProperty("password", "qwerty");
        jsonObject.addProperty("phone", "+79090909090");
        jsonObject.addProperty("userStatus", 1);
        Response response = RestAssured.given().log().all().body(jsonObject.toString()).post("https://petstore.swagger.io/v2/user");
//        response.then().body(matchesJsonSchemaInClasspath("userSchema.json").using(runJsonSchemaFactory()));
//        System.out.println(response.asString());
        System.out.println(response.statusCode());
    }

    @Test
    void validateUserGet() {
        Response response = RestAssured.get("https://petstore.swagger.io/v2/user/12345");
        System.out.println(response.statusCode());
//        response.then().body(matchesJsonSchemaInClasspath("userSchema.json").using(runJsonSchemaFactory()));
    }
}
