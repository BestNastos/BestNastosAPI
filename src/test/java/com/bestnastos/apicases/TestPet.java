package com.bestnastos.apicases;

import com.bestnastos.apiobjects.PetApiObject;
import com.bestnastos.base.BaseAPI;
import com.bestnastos.constants.PetStatuses;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

@Feature("feature annotation")
public class TestPet extends BaseAPI {

    @Story("story annotation")
    @Test(alwaysRun = true)
    public void testPet(){
        PetApiObject pet = new PetApiObject();
        System.out.println("=== TEST START ===\n");
        pet
                .findByStatus(PetStatuses.sold)
                .then()
                .assertThat()
                .spec(responseSpecificationOK());
//        Object str = JsonPath.with(response.asString()).get("[0].name");
        System.out.println("=== TEST END ===" );

    }
}
