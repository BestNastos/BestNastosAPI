package com.bestnastos.apiobjects;

import com.bestnastos.base.BaseAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;

public class PetApiObject extends BaseAPI {

    private final String UPLOAD_IMAGE = "/pet/{petId}/uploadImage";
    private final String ADD_UPDATE_PET = "/pet";
    private final String GET_PET = "/pet/findByStatus";

    public void uploadImage(){

    }

    public void add(){

    }

    public void update(){

    }

    public Response findByStatus(String statuses){
        HashMap<String, Object> params = new HashMap<>();
        params.put("status", statuses);
        return RestAssured
                .given(requestSpecification())
                .log().parameters()
                .queryParams(params)
                .get("https://petstore.swagger.io/v2/pet/findByStatus")//try passing parameters here
                .prettyPeek();
    }

}
