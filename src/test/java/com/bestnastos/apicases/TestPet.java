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

@Feature("feature annotation")
public class TestPet extends BaseAPITest {

    @Story("story annotation")
    @Test(alwaysRun = true)
    public void testPet(){
        PetApiObject pet = new PetApiObject();
        System.out.println("=== TEST START ===\n");
        ExtractableResponse<Response> extractable = pet
                .findByStatus(PetStatuses.sold)
                .then()
                .assertThat()
                .spec(responseSpecificationOK())
                .extract();

        List<String> categories = extractable.path("category.name");
        assertThat("List of pet categories should not be empty", !categories.isEmpty());
        List<String> ids = extractable.path("id");
        assertThat("List of ids should not be empty", !ids.isEmpty());
//todo soft assert
        System.out.println("=== TEST merge conflict ===" );

    }
}
