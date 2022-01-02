package com.bestnastos.apiobjects;

import com.bestnastos.base.BaseAPITest;
import com.bestnastos.constants.PetStatuses;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import java.util.HashMap;

public class PetApiObject extends BaseAPITest {

    private final String UPLOAD_IMAGE = "/pet/{petId}/uploadImage";
    private final String ADD_OR_UPDATE_PET = "/pet";
    private final String GET_PET = "/pet/findByStatus";

    public void uploadImage(){

    }

    public void add(){

    }
    /*
    {
"id": 0,
"category": {
"id": 0,
"name": "string"
},
"name": "doggie",
"photoUrls": [
"string"
],
"tags": [
{
  "id": 0,
  "name": "string"
}
],
"status": "available"
}
     */
    public Response update(Object pet){//create class Pet
        return RestAssured
                .given(requestSpecification())
                .log()
                .parameters()
                .body(pet, ObjectMapperType.GSON)//gson or json?
                .contentType(ContentType.JSON)
//                .header("Content-Type", "application/json")
                .put(ADD_OR_UPDATE_PET)
                .prettyPeek();

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
