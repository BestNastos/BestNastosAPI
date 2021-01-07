package com.bestnastos.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static org.hamcrest.Matchers.lessThan;

public class BaseAPITest {

    protected final static String BASE_URI = "https://petstore.swagger.io/v2";

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(){

    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){

    }

    public static ResponseSpecification responseSpecificationOK() {
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
                .build();
    }

        /*
    public APIResponse createShiftByShiftTemplate(Map<String, Object> dataMap) throws KronosCoreAPIException {
        setGenericParameter();
        params.setHeader(DYNATRACE_HEADER, DYNATRACE_HEADER_BASE_VALUE + "createShiftByShiftTemplate");
        String payload = build(dataMap).getRequest().toString();
        RequestSpecification requestSpec = given().cookie(driver.getCookies()).accept(ContentType.JSON)
                .headers(params.getHeader()).contentType(ContentType.JSON).when().body(payload)
                .pathParameters(params.getPath()).queryParameters(params.getQuery());
        Response response = requestSpec.post(BACKEND_SERVER + CREATE_BY_TEMPLATE);
        return new APIResponse(response);
    }*/
}
