package com.localhost.cucumber.steps;

import com.localhost.swaggerinfo.CategoriesSteps;
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

public class CategoryStepdefs {

    static String name = "Swag" + TestUtils.getRandomValue();
    static String id = "12" + TestUtils.getRandomValue();
    static String categoryID;

    static ValidatableResponse response;

    @Steps
    CategoriesSteps categoriesSteps;

    @Given("^I am on homepage of application of category$")
    public void iAmOnHomepageOfApplicationOfCategory() {

    }

    @When("^I send Get request to list endpoint of category$")
    public void iSendGetRequestToListEndpointOfCategory() {
        response = categoriesSteps.getAllCategory();
    }

    @Then("^I must get back a valid status code (\\d+) of category$")
    public void iMustGetBackAValidStatusCodeOfCategory(int code) {
            response.statusCode(code);

    }

    @When("^I send Post request to list endpoint of category$")
    public void iSendPostRequestToListEndpointOfCategory() {
       response = categoriesSteps.createCategory(name,id);
        response.log().all().statusCode(201);
        categoryID = response.log().all().extract().path("id");
        System.out.println(categoryID);
    }

    @When("^I send Put request to list endpoint of category$")
    public void iSendPutRequestToListEndpointOfCategory() {
        name = name + "_updated";
        response = categoriesSteps.updateCategory(categoryID, name, id);
        HashMap<String, Object> categoryMap = categoriesSteps.getCategoryInfoByID(categoryID);
        Assert.assertThat(categoryMap, hasValue(name));
    }

    @When("^I send Delete request to list endpoint of category$")
    public void iSendDeleteRequestToListEndpointOfCategory() {
        response = categoriesSteps.deleteCategory(categoryID);
        response.assertThat().statusCode(200);
    }

    @And("^I validate if category is deleted$")
    public void iValidateIfCategoryIsDeleted() {
        response = categoriesSteps.getCategoryById(categoryID);
        response.assertThat().statusCode(404);
    }
}
