package com.bestnastos.apicases;

import com.bestnastos.Pet;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class TestWithoutArchitecture {

    @Test
    public void testGet(){

        RestAssured.given()
                .queryParams("status", "sold")
                .get("https://petstore.swagger.io/v2/pet/findByStatus")
                .prettyPeek()

                .then()
                .assertThat()
                .spec(new ResponseSpecBuilder()
                        .expectStatusCode(HttpStatus.SC_OK)
                        .build())
                .body("[0].status", equalTo("sold"));

//        Response response = RestAssured.given()
//                .request(Method.GET, "https://petstore.swagger.io/v2/pet/findByStatus");
//        System.out.println(response.getBody().asString());
    }

    @Test
    public void testPut(){ // same for post

        Map<String, Object> mapPayload = new Pet()
                .withCategory(5, "name")
                .withName("name").getPayload();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(mapPayload)
                .put("https://petstore.swagger.io/v2/pet")
                .prettyPeek()

                .then()
                .assertThat()
                .statusCode(200)
                .body("category.id", equalTo(5));

    }

    @Test
    public void testDelete(){

        RestAssured.given()
                // use pathParam instead of queryParam when you want to append smth to URI instead of placeholder {}
                .pathParam("petId", 5)
                .delete("https://petstore.swagger.io/v2/pet/{petId}")
                .prettyPeek()

                .then()
                .assertThat()
                .statusCode(200);
    }
}
