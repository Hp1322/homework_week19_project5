package com.localhost.swaggerinfo;

import com.localhost.constants.EndPoints;
import com.localhost.model.ServicesPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ServicesSteps {

    @Step("Creating service with name : {0}")
    public ValidatableResponse createService(String name){
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(servicesPojo)
                .when()
                .post(EndPoints.CREATE_SERVICE_BY_ID)
                .then();
    }
    @Step("Getting the product information with ID : {0}")
    public HashMap<String, Object> getserviceInfoByID(int serviceID){
        HashMap<String, Object> serviceMap = SerenityRest.given().log().all().
                when()
                .pathParam("serviceID", serviceID)
                .get(EndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");
        return serviceMap;
    }
    @Step("Updating the service information with serviceID : {0} and name : {1}")
    public ValidatableResponse updateService(int serviceID, String name){
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("serviceID", serviceID)
                .body(servicesPojo)
                .when()
                .put(EndPoints.UPDATE_SERVICE_BY_ID)
                .then();
    }
    @Step("Deleting the service information with serviceID : {0}")
    public ValidatableResponse deleteService(int serviceID){
        return SerenityRest.given().log().all()
                .pathParam("serviceID", serviceID)
                .when()
                .delete(EndPoints.DELETE_SERVICE_BY_ID)
                .then();
    }
    @Step("Getting service information with serviceID: {0}")
    public ValidatableResponse getProductById(int serviceID) {
        return SerenityRest.given().log().all()
                .pathParam("serviceID", serviceID)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then();
    }
    @Step("Getting all service from application")
    public ValidatableResponse getAllServices(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_SERVICE)
                .then();

    }
}
