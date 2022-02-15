package com.bestnastos.apicases;


import com.bestnastos.Pet;
import com.bestnastos.apiobjects.PetApiObject;
import com.bestnastos.base.BaseAPITest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

@Feature("Feature 'Update Pet'")
public class TestCreateUpdatePet extends BaseAPITest {

    @Story("Story 'Update'")
    @Test(enabled = true)
    public void testUpdatePet() {

        System.out.println("\n=== TEST START ===\n");

        ExtractableResponse<Response> extractable = new PetApiObject()
                .update(new Pet()
                        .withCategory(1, "category")
                        .withId(2)
                        .withName("Kitty")
                        .withPhotoUrls("/url")
                        .withTag(3, "tag"))
                .then()
                .assertThat()
                .spec(responseSpecificationOK())
                .extract();

        System.out.println("\n=== TEST end ===\n");
    }
}
