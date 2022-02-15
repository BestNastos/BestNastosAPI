package com.bestnastos.apicases;


import com.bestnastos.Pet;
import com.bestnastos.apiobjects.PetApiObject;
import com.bestnastos.base.BaseAPITest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

@Feature("Feature 'Update Pet'")
public class TestCreateUpdatePet extends BaseAPITest {

    @Story("Story 'Create and Update'")
    @Test(enabled = true)
    public void testUpdatePet() {

        System.out.println("\n=== TEST START ===\n");

        String urlForCreate = "/urlForCreate";
        String urlForUpdate = "/urlForUpdate";

        ExtractableResponse<Response> extractableResponseCreate = new PetApiObject()
                .create(new Pet()
                        .withCategory(1, "category")
                        .withName("Kitty")
                        .withPhotoUrls(urlForCreate)
                        .withTag(3, "tag"))
                .then()
                .assertThat()
                .spec(responseSpecificationOK())
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
                .spec(responseSpecificationOK())
                .extract();

        String urlAfterUpdate = extractableResponseUpdate.path("photoUrls[0]");
        assertThat("Url should be successfully updated", urlAfterUpdate.equals(urlForUpdate));

        System.out.println("\n=== TEST end ===\n");
    }
}
