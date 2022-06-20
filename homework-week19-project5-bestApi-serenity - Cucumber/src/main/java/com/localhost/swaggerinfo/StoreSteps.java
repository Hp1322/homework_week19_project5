package com.localhost.swaggerinfo;

import com.localhost.constants.EndPoints;
import com.localhost.model.StoresPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoreSteps {
    @Step("Creating store with name : {0}, type: {1}, address : {2}, address2 : {3}, city : {4}, state : {5}, zip : {6}, lat : {7}, lng : {8}, hours: {9} and services {10}")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city,
                                             String state, String zip, int lat,
                                             int lng, String hours, HashMap<Object, Object> services) {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        storesPojo.setServices(services);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(storesPojo)
                .when()
                .post(EndPoints.CREATE_STORE_BY_ID)
                .then();
    }
    @Step("Getting the store information with ID : {0}")
    public HashMap<String, Object> getstoreInfoByID(int storeID){
        HashMap<String, Object> storeMap = SerenityRest.given().log().all().
                when()
                .pathParam("storeID", storeID)
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");
        return storeMap;
    }
    @Step("Creating store with storeID : {0}, name : {1}, type: {2}, address : {3}, address2 : {4}, city : {5}, state : {6}, zip : {7}, lat : {8}, lng : {9}, hours: {10} and services {11}")
    public ValidatableResponse updateStore(int storeID,String name, String type, String address, String address2, String city,
                                           String state, String zip, int lat,
                                           int lng, String hours, HashMap<Object, Object> services) {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        storesPojo.setServices(services);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("storeID", storeID)
                .body(storesPojo)
                .when()
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then();
    }
    @Step("Deleting store information with productID : {0}")
    public ValidatableResponse deleteStore(int storeID){
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeID)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }
    @Step("Getting store information with productID: {0}")
    public ValidatableResponse getStoreById(int storeID){
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeID)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }
    @Step("Getting all store from application")
    public ValidatableResponse getAllStore(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STORE)
                .then();

    }
}
