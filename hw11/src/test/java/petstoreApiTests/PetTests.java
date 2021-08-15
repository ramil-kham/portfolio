package petstoreApiTests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Epic("Petstore")
@Story("Petstore pet tests")
@Feature("Pet tests")
public class PetTests {

    Pet pet = new Pet(159,
            new Category(1, "Dogs"),
            "Rex",
            new String[] {"https://cdnimg.rg.ru/img/content/177/25/24/iStock-1026229726_d_850.jpg"},
            new Tag[] {new Tag(5, "5")},
            PetStatus.AVAILABLE
    );

    @Test
    @Order(1)
    public void createNewPetTest() {
        Response response = RestAssured
                .given()
                .log().all()
                .body(pet)
                .contentType(ContentType.JSON)
                .post("https://petstore.swagger.io/v2/pet/");
        System.out.println(response.statusCode());
        System.out.println(response.asString());
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertFalse(response.getBody().asString().isEmpty());
    }

    @Test
    @Order(2)
    void getById() {
        Response response = RestAssured
                .given()
                .log().all()
                .get("http://petstore.swagger.io/v2/pet/159");
        System.out.println(response.asString());
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
    }

    @Test
    @Order(3)
    void updateByIdTest() {
        Pet expectedPet = new Pet(
                159,
                new Category(1, "Dogs"),
                "Tuzik",
                new String[] {"https://dogdiary.ru/wp-content/uploads/2018/07/shhenok-zolotistogo-retrivera.jpg"},
                new Tag[] {new Tag(6, "6")},
                PetStatus.SOLD
        );
        Response response = RestAssured
                .given()
                .log().all()
                .body(expectedPet)
                .contentType(ContentType.JSON)
                .post("https://petstore.swagger.io/v2/pet/");

        System.out.println(response.asString());
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertFalse(response.getBody().asString().isEmpty());
    }
    @Test
    @Order(4)
    void updateByIdTest1() {
        pet.setId(159);
        pet.setName("Sharik");
        pet.setStatus(PetStatus.SOLD);
        Response response = RestAssured
                .given()
                .log().all()
                .body(pet)
                .contentType(ContentType.JSON)
                .put("https://petstore.swagger.io/v2/pet/159");
        System.out.println(response.asString());
    }

    @Test
    @Order(5)
    void deletePetTest() {
        RestAssured.given()
                .delete("http://petstore.swagger.io/v2/pet/159");
        Response expectedPet = RestAssured
                .given()
                .log().all()
                .get("http://petstore.swagger.io/v2/pet/159");
        System.out.println(expectedPet.asString());
        Assertions.assertEquals(404, expectedPet.getStatusCode());
    }
}
