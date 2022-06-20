package com.localhost.cucumber.steps;

import com.localhost.swaggerinfo.UtilitiesSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;

public class UtilityStepdefs {

    @Steps
    UtilitiesSteps utilitiesSteps;
    static ValidatableResponse response;

    @Given("^I am on homepage of application of utility$")
    public void iAmOnHomepageOfApplicationOfUtility() {
    }

    @When("^I send Get request to list endpoint of heathCheck$")
    public void iSendGetRequestToListEndpointOfHeathCheck() {
        HashMap<?, ?> healthMap = utilitiesSteps.getHealthCheck();
        Assert.assertThat(healthMap, hasKey("uptime"));
    }



    @When("^I send Get request to list endpoint of version$")
    public void iSendGetRequestToListEndpointOfVersion() {
        HashMap<?, ?> versionMap = utilitiesSteps.getVersion();
        Assert.assertThat(versionMap, hasValue("1.1.0"));
        System.out.println(versionMap);
    }
}
