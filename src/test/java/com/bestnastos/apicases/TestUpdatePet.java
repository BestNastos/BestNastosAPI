package com.bestnastos.apicases;


import com.bestnastos.apiobjects.PetApiObject;
import com.bestnastos.base.BaseAPITest;
import com.bestnastos.constants.PetStatuses;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@Feature("Feature 'Update Pet'")
public class TestUpdatePet extends BaseAPITest {

    @Story("Story 'Update'")
    @Test(enabled = true)
    public void testUpdatePet() {

        System.out.println("\n=== TEST START ===\n");

        ExtractableResponse<Response> extractable = new PetApiObject()
                .update(PetStatuses.sold)
                .then()
                .assertThat()
                .spec(responseSpecificationOK())
                .extract();

        List<String> categories = extractable.path("category.name");
        assertThat("List of pet categories should not be empty", categories.isEmpty()); //this assertion should fail for test purposes
        List<String> ids = extractable.path("id");
        assertThat("List of ids should not be empty", !ids.isEmpty());

        System.out.println("\n=== TEST end ===\n");
    }
}
