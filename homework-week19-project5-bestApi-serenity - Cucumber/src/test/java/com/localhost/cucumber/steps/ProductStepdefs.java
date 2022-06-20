package com.localhost.cucumber.steps;

import com.localhost.swaggerinfo.ProductSteps;
import com.localhost.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class ProductStepdefs {

    static String name = "Ram" + TestUtils.getRandomValue();
    static String type = "Male" + TestUtils.getRandomValue();
    static int price = 540;
    static int shipping = 12121;
    static String upc = "abc";
    static String description = "xyz";
    static String manufacturer = "pqr";
    static String model = "Tesla" + TestUtils.getRandomValue();
    static String url = "https://www.google.com/";
    static String image = "http://img.bbyprivate.com/images/products/1276/127687_sa.jpg";
    static int productID;
    static ValidatableResponse response;

    @Steps
    ProductSteps productSteps;

    @Given("^I am on homepage of application of product$")
    public void iAmOnHomepageOfApplicationOfProduct() {
    }

    @When("^I send Get request to list endpoint of product$")
    public void iSendGetRequestToListEndpointOfProduct() {
        response = productSteps.getAllProduct();
    }


    @Then("^I must get back a valid status code (\\d+) of product$")
    public void iMustGetBackAValidStatusCode(int code) {
        response.statusCode(code);
        response.assertThat().statusCode(code);

    }

    @When("^I send Post request to list endpoint of product$")
    public void iSendPostRequestToListEndpointOfProduct() {
        response = productSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productID = response.log().all().extract().path("id");
        System.out.println(productID);
    }

    @When("^I send Put request to list endpoint of product$")
    public void iSendPutRequestToListEndpointOfProduct() {
        name = name + "_updated";
        response = productSteps.updateProduct(productID, name, type, price, shipping, upc, description, manufacturer, model, url, image).log().all().statusCode(200);
        HashMap<String, Object> productMap = productSteps.getproductInfoByID(productID);
        Assert.assertThat(productMap, hasValue(name));
    }

    @When("^I send Delete request to list endpoint of product$")
    public void iSendDeleteRequestToListEndpointOfProduct() {
        response = productSteps.deleteProduct(productID);
        response.assertThat().statusCode(200);
    }

    @And("^I validate if product is deleted$")
    public void iValidateIfProductIsDeleted() {
        response = productSteps.getProductById(productID);
        response.assertThat().statusCode(404);
    }
}
