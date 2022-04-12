package com.bestnastos.apicases;

import com.bestnastos.apiobjects.PetApiObject;
import com.bestnastos.base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Feature("feature annotation")
public class TestGetPet extends BaseTest {
    
    @Story("story annotation")
    @Test(enabled = true)
    public void testPetWithExtractableResponse() {

        System.out.println("\n=== TEST START ===\n");
        
        ExtractableResponse<Response> extractable = new PetApiObject()
                .getByStatus(com.bestnastos.constants.PetStatus.sold)
                .then()
                .assertThat()
                .spec(responseSpecJsonOK())
                .extract();
        
        List<String> categories = extractable.path("category.name");
        assertThat("List of pet categories should not be empty", categories.isEmpty()); //this assertion should fail for test purposes
        List<String> ids = extractable.path("id");
        assertThat("List of ids should not be empty", !ids.isEmpty());
        
        System.out.println("\n=== TEST end ===\n");
        /*

    when().
            get("/lotto/{id}", 5).
    then().
            statusCode(200).
            body("lotto.lottoId", equalTo(5),
                 "lotto.winners.winnerId", hasItems(23, 54));n
         */
    }
    
    @Test(enabled = true)
    public void testPetWithMatcher() {
    
        PetApiObject pets = new PetApiObject();
    
        System.out.println("\n=== TEST START #1 ===\n");
        pets
                .getByStatus(com.bestnastos.constants.PetStatus.available)
                .then()
                .body("[0].status", equalTo(com.bestnastos.constants.PetStatus.available.toString()));
        System.out.println("\n=== TEST END ===\n");
    
        
        
        System.out.println("\n=== TEST START #2 ===\n");
        pets
                .getByStatus(com.bestnastos.constants.PetStatus.sold)
                .then()
                .body("[0].status", equalTo(com.bestnastos.constants.PetStatus.available.toString()));//this assertion should fail for test purposes
        System.out.println("\n=== TEST END ===\n");
        
    }
    
    @Test(enabled = true, groups = {"test"})
    public void testDumbWayToSoftAssert() {
        
        System.out.println("\n=== TEST START ===\n");
        new PetApiObject()
                .getByStatus(com.bestnastos.constants.PetStatus.available)
                .then()
                .body("[0].status", equalTo(com.bestnastos.constants.PetStatus.sold.toString()),//this assertion should fail for test purposes
                        "[1].status", equalTo(com.bestnastos.constants.PetStatus.available.toString()),
                        "[2].status", equalTo(com.bestnastos.constants.PetStatus.sold.toString()));//this assertion should fail for test purposes
    
        System.out.println("\n=== TEST END ===\n");

        
    }
}
