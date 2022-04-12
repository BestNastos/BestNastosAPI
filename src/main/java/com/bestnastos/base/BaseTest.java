package com.bestnastos.base;

import com.bestnastos.worker.User;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static org.hamcrest.Matchers.lessThan;

public class BaseTest {

    protected final static String BASE_URI = "https://petstore.swagger.io/v2";
    protected User user = new User();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(){

    }

    @Step("After Class Cleanup in BaseAPITest")
    @AfterClass(alwaysRun = true)
    public void afterClassCleanup() {
        user.setClassLevel(false);//todo class vs method level
        user.workUnit.doCleanup();
    }

    @Step("After Method Cleanup in BaseAPITest")
    @AfterMethod(alwaysRun = true)
    public void afterMethodCleanup() {
        user.deleteUsedWorkers();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
    }

    public static ResponseSpecification responseSpecOK() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(10000L))
                .build();
    }

    public static ResponseSpecification responseSpecJsonOK() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(10000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
//                .addQueryParam("requestNumber", ++requestNumber)
                .setBaseUri(BASE_URI)
                .build()
                .filter(new AllureRestAssured());
    }
    //delete comment
}
