package com.bestnastos.apicases;


import com.bestnastos.Pet;
import com.bestnastos.apiobjects.PetApiObject;
import com.bestnastos.base.BaseAPITest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.MatcherAssert.assertThat;

@Feature("Feature 'Update Pet'")
public class TestCreateUpdatePet extends BaseAPITest {

    @Story("Story 'Create and Update'")
    @Test(enabled = true)
    public void testCRUDPet() {

        System.out.println("\n=== TEST START ===\n");

        String urlForCreate = "/urlForCreate";
        String urlForUpdate = "/urlForUpdate";
        int randomNum = ThreadLocalRandom.current().nextInt(500, 100000);

        ExtractableResponse<Response> extractableResponseCreate = new PetApiObject()
                .create(new Pet()
                        .withCategory(randomNum, randomNum+"1")
                        .withName(randomNum+"1")
                        .withPhotoUrls(urlForCreate)
                        .withTag(randomNum, randomNum+"1"))
                .then()
                .assertThat()
                .spec(responseSpecJsonOK())
                .extract();

        Long id = extractableResponseCreate.path("id");
        String urlAfterCreate = extractableResponseCreate.path("photoUrls[0]");
        assertThat("Url should be successfully updated", urlAfterCreate.equals(urlForCreate));

        ExtractableResponse<Response> extractableResponseUpdate = new PetApiObject()
                .update(new Pet()
                        .withId(id)
                        .withPhotoUrls(urlForUpdate))
                .then()
                .assertThat()
                .spec(responseSpecJsonOK())
                .extract();

        String urlAfterUpdate = extractableResponseUpdate.path("photoUrls[0]");
        assertThat("Url should be successfully updated", urlAfterUpdate.equals(urlForUpdate));

       new PetApiObject()
                .delete(id)
                .then()
                .assertThat()
                .spec(responseSpecOK())
                .extract();

        System.out.println("\n=== TEST end ===\n");
    }
}
