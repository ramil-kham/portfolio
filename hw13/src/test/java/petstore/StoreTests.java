package petstore;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.lang.module.ResolutionException;

public class StoreTests {

    public JsonSchemaFactory runJsonSchemaFactory() {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder()
                .setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();
        return jsonSchemaFactory;
    }

    @Test
    void storePost() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 5);
        jsonObject.addProperty("petId", 5);
        jsonObject.addProperty("quantity", 5);
        jsonObject.addProperty("shipfate", "2021-07-31T01:24:41.409+0000");
        jsonObject.addProperty("status", "available");
        jsonObject.addProperty("complete", true);

        Response response = RestAssured.given().log().all().body(jsonObject.toString()).post("https://petstore.swagger.io/v2/store/order\n");
        response.then().body(matchesJsonSchemaInClasspath("storeSchema.json").using(runJsonSchemaFactory()));
        System.out.println(response.statusCode());

    }

    @Test
    void storePostTests() {
        Response response = RestAssured.post("https://petstore.swagger.io/v2/store/order");
        System.out.println(response.statusCode());
        response.then().body(matchesJsonSchemaInClasspath("storeSchema.json").using(runJsonSchemaFactory()));
    }
}
