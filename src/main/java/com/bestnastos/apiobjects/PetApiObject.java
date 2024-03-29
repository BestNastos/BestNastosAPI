package com.bestnastos.apiobjects;

import com.bestnastos.Pet;
import com.bestnastos.base.BaseAPITest;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import java.util.HashMap;

public class PetApiObject extends BaseAPITest {

    private final String UPLOAD_IMAGE = "/pet/{petId}/uploadImage";
    private final String ADD_OR_UPDATE_PET = "/pet";
    private final String GET_PET = "/pet/findByStatus";
    private final String DELETE_PET = "/pet/{petId}";

    @Step("Create Pet")
    public Response create(Pet pet){
        return RestAssured
                .given(requestSpecification())
                .log()
                .parameters()
                .body(pet.getPayload(), ObjectMapperType.GSON)
                .contentType(ContentType.JSON)
                .put(ADD_OR_UPDATE_PET)
                .prettyPeek();

    }

    @Step("Update Pet")
    public Response update(Pet pet){
        return RestAssured
                .given(requestSpecification())
                .log()
                .parameters()
                .body(pet.getPayload(), ObjectMapperType.GSON)
                .contentType(ContentType.JSON)
                .put(ADD_OR_UPDATE_PET)
                .prettyPeek();

    }

    @Step("Get Pet by Status")
    public Response getByStatus(com.bestnastos.constants.PetStatus status){
        HashMap<String, Object> params = new HashMap<>();
        params.put("status", status.toString());
        return RestAssured
                .given(requestSpecification())
                .log()
                .parameters()
                .queryParams(params)
                .get(GET_PET)
                .prettyPeek();
    }

    @Step("Delete Pet")
    public Response delete(Long id){
        return RestAssured
                .given(requestSpecification())
                .log()
                .parameters()
                .pathParam("petId", id)
                .delete(DELETE_PET)
                .prettyPeek();
    }

}
