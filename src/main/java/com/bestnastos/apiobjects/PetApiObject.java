package com.bestnastos.apiobjects;

import com.bestnastos.base.BaseAPI;
import com.bestnastos.constants.PetStatuses;
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

    public Response findByStatus(PetStatuses status){
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

}
