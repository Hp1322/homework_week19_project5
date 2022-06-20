package com.localhost.swaggerinfo;

import com.localhost.constants.EndPoints;
import com.localhost.model.CategoriesPojo;
import com.localhost.model.ServicesPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class CategoriesSteps {
    @Step("Creating category with name : {0} and id : {1}")
    public ValidatableResponse createCategory(String name, String id){
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(categoriesPojo)
                .when()
                .post(EndPoints.CREATE_CATEGORY_BY_ID)
                .then();
    }
    @Step("Getting the category information with ID : {0}")
    public HashMap<String, Object> getCategoryInfoByID(String categoryID){
        HashMap<String, Object> categoryMap = SerenityRest.given().log().all().
                when()
                .pathParam("categoryID", categoryID)
                .get(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");
        return categoryMap;
    }
    @Step("Updating the category information with serviceID : {0}, name : {1} and id : {1}")
    public ValidatableResponse updateCategory(String categoryID, String name, String id){
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("categoryID", categoryID)
                .body(categoriesPojo)
                .when()
                .put(EndPoints.UPDATE_CATEGORY_BY_ID)
                .then();
    }
    @Step("Deleting the category information with serviceID : {0}")
    public ValidatableResponse deleteCategory(String categoryID){
        return SerenityRest.given().log().all()
                .pathParam("categoryID", categoryID)
                .when()
                .delete(EndPoints.DELETE_CATEGORY_BY_ID)
                .then();
    }
    @Step("Getting category information with serviceID: {0}")
    public ValidatableResponse getCategoryById(String categoryID) {
        return SerenityRest.given().log().all()
                .pathParam("categoryID", categoryID)
                .when()
                .get(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then();
    }
    @Step("Getting all categories from application")
    public ValidatableResponse getAllCategory(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_CATEGORY)
                .then();

    }
}
