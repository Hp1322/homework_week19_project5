package com.localhost.cucumber.steps;

import com.localhost.swaggerinfo.ServicesSteps;
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

public class ServiceStepdefs {

    static String name = "Swag" + TestUtils.getRandomValue();
    static int serviceID;
    static ValidatableResponse response;

    @Steps
    ServicesSteps servicesSteps;

    @Given("^I am on homepage of application of service$")
    public void iAmOnHomepageOfApplicationOfService() {
    }

    @When("^I send Get request to list endpoint of service$")
    public void iSendGetRequestToListEndpointOfService() {
        response = servicesSteps.getAllServices();
    }

    @Then("^I must get back a valid status code (\\d+) of service$")
    public void iMustGetBackAValidStatusCodeOfService(int code) {
        response.statusCode(code);
        response.assertThat().statusCode(code);
    }

    @When("^I send Post request to list endpoint of service$")
    public void iSendPostRequestToListEndpointOfService() {
        response = servicesSteps.createService(name);
        response.log().all().statusCode(201);
        serviceID = response.log().all().extract().path("id");
        System.out.println(serviceID);
    }

    @When("^I send Put request to list endpoint of service$")
    public void iSendPutRequestToListEndpointOfService() {
        name = name + "_updated";
        response = servicesSteps.updateService(serviceID, name);
        HashMap<String, Object> productMap = servicesSteps.getserviceInfoByID(serviceID);
        Assert.assertThat(productMap, hasValue(name));
    }

    @When("^I send Delete request to list endpoint of service$")
    public void iSendDeleteRequestToListEndpointOfService() {
        response = servicesSteps.deleteService(serviceID);
        response.assertThat().statusCode(200);
    }

    @And("^I validate if service is deleted$")
    public void iValidateIfServiceIsDeleted() {
        response = servicesSteps.getProductById(serviceID);
        response.assertThat().statusCode(404);
    }
}
