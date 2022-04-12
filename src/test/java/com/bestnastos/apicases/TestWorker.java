package com.bestnastos.apicases;

import com.bestnastos.PetBuilder;
import com.bestnastos.base.BaseTest;
import org.testng.annotations.Test;

public class TestWorker extends BaseTest {

    @Test(alwaysRun = true)
    public void testWorker(){

        user
                .withPet()
                .createPet(new PetBuilder()
                        .withName("TestName"))
                .completeWith()
                .doSetup();
    }

}
