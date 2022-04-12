package com.bestnastos.worker;

import com.bestnastos.PetBuilder;
import com.bestnastos.apiobjects.PetApiObject;
import io.qameta.allure.Step;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static com.bestnastos.base.BaseTest.responseSpecJsonOK;
import static com.bestnastos.base.BaseTest.responseSpecOK;

public class PetWorker extends Worker implements CRUD {

    private PetBuilder petBuilder;
    private PetApiObject petApiObject = new PetApiObject();
    private Pet pet;

    public PetWorker(PetBuilder petBuilder){
        this.petBuilder = petBuilder;
    }

    @Override
    @Step("PET WORKER - Create Pet")//todo pass id
    public void create() {
        ExtractableResponse<Response> extractableResponseCreate = petApiObject
                .create(petBuilder)
                .then()
                .assertThat()
                .spec(responseSpecJsonOK())
                .extract();
        super.id = extractableResponseCreate.path("id");
    }

    @Override
    @Step("PET WORKER - Delete Pet")//todo pass id
    public void delete() {
        if (super.id > 0){
            new PetApiObject()
                    .delete(id)
                    .then()
                    .assertThat()
                    .spec(responseSpecOK())
                    .extract();
        }
    }

    @Override
    @Step("PET WORKER")
    public void update() {
        throw new IllegalStateException("Unsupported action: update");
    }

    @Override
    @Step("PET WORKER")
    public void read() {
        throw new IllegalStateException("Unsupported action: read");
    }
}
