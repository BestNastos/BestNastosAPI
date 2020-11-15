package com.bestnastos.apicases;

import com.bestnastos.apiobjects.PetApiObject;
import com.bestnastos.base.BaseAPI;
import com.bestnastos.constants.PetStatuses;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestPet extends BaseAPI {

    @Test(alwaysRun = true)
    public void testPet(){
        PetApiObject pet = new PetApiObject();
        Response response = pet.findByStatus(PetStatuses.available.toString());
        Object str = JsonPath.with(response.asString()).get("[0].name");
        System.out.println("LOOK HERE: " + str);

    }
}
