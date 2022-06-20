package com.localhost.swaggerinfo;

import com.localhost.constants.EndPoints;
import com.localhost.model.ProductsPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductSteps {

    @Step("Creating product with name : {0}, type: {1}, price : {2}, shipping : {3}, upc : {4}, description : {5}, manufacturer : {6}, model : {7}, url : {8} and image: {9}")
    public ValidatableResponse createProduct(String name, String type, int price, int shipping, String upc,
                                             String description, String manufacturer, String model,
                                             String url, String image) {
        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(productsPojo)
                .when()
                .post(EndPoints.CREATE_PRODUCT_BY_ID)
                .then();
    }
    @Step("Getting the product information with ID : {0}")
    public HashMap<String, Object> getproductInfoByID(int productID){
        HashMap<String, Object> productMap = SerenityRest.given().log().all().
                when()
                .pathParam("productID", productID)
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");
        return productMap;
    }
    @Step("Updating product with productId : {0} ,name : {1}, type: {2}, price : {3}, shipping : {4}, upc : {5}, description : {6}, manufacturer : {7}, model : {8}, url : {9} and image: {10}")
    public ValidatableResponse updateProduct(int productID,String name, String type, int price, int shipping, String upc,
                                             String description, String manufacturer, String model,
                                             String url, String image) {
        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productID", productID)
                .body(productsPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }
    @Step("Deleting product information with productID : {0}")
    public ValidatableResponse deleteProduct(int productID){
        return SerenityRest.given().log().all()
                .pathParam("productID", productID)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }
    @Step("Getting product information with productID: {0}")
    public ValidatableResponse getProductById(int productID){
        return SerenityRest.given().log().all()
                .pathParam("productID", productID)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }
    @Step("Getting all product from application")
    public ValidatableResponse getAllProduct(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_PRODUCT)
                .then();

    }

}
