package com.localhost.cucumber.steps;

import com.localhost.swaggerinfo.StoreSteps;
import com.localhost.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class StoreStepdefs {

    static String name = "Ram" + TestUtils.getRandomValue();
    static String type = "Don" + TestUtils.getRandomValue();
    static String address = "1205 10th Street";
    static String address2 = "";
    static String city = "Rom";
    static String state = "DN";
    static String zip = "10101";
    static int lat = 44;
    static int lng = 90;
    static String hours = "Mon: 10-9; Tue: 10-9";
    static int storeID;
    static ValidatableResponse response;
    SoftAssertions softAssert = new SoftAssertions();
    @Steps
    StoreSteps storeSteps;

    @Given("^I am on homepage of application of store$")
    public void iAmOnHomepageOfApplicationOfStore() {
    }

    @When("^I send Get request to list endpoint of store$")
    public void iSendGetRequestToListEndpointOfStore() {
        response = storeSteps.getAllStore();
    }

    @Then("^I must get back a valid status code (\\d+) of store$")
    public void iMustGetBackAValidStatusCodeOfStore(int code) {
        response.statusCode(code);
        response.assertThat().statusCode(code);
    }

    @When("^I send Post request to list endpoint of store$")
    public void iSendPostRequestToListEndpointOfStore() {
        HashMap<Object,Object> services = new HashMap<>();
        services.put("storeId", "8");
        services.put("serviceId", "9");

        response = storeSteps.createStore(name,type,address,address2,city,state,zip,lat,lng,hours,services);
        response.log().all().statusCode(201);
        storeID = response.log().all().extract().path("id");
        System.out.println(storeID);
    }

    @When("^I send Put request to list endpoint of store$")
    public void iSendPutRequestToListEndpointOfStore() {
        HashMap<Object,Object> services = new HashMap<>();
        services.put("storeId", "8");
        services.put("serviceId", "9");

        name = name + "_updated";
        response = storeSteps.updateStore(storeID, name,type,address,address2,city,state,zip,lat,lng,hours,services).log().all().statusCode(200);
        HashMap<String, Object> storeMap = storeSteps.getstoreInfoByID(storeID);
        Assert.assertThat(storeMap, hasValue(name));
    }

    @When("^I send Delete request to list endpoint of store$")
    public void iSendDeleteRequestToListEndpointOfStore() {
        response = storeSteps.deleteStore(storeID);
        response.assertThat().statusCode(200);
    }

    @And("^I validate if store is deleted$")
    public void iValidateIfStoreIsDeleted() {
        response = storeSteps.getStoreById(storeID);
        response.assertThat().statusCode(404);
    }

    @Then("^Verify the if the total is equal to 1561$")
    public void verifyTheIfTheTotalIsEqualTo() {
//        softAssert.assertThat("total").isEqualTo(1561);
        response = storeSteps.getAllStore();
      response.body("total", equalTo(1568));
//        softAssert.assertAll();
    }

    @And("^Verify the if the stores of limit is equal to 10$")
    public void verifyTheIfTheStoresOfLimitIsEqualTo() {
        response.body("limit", equalTo(10));
    }

    @And("^Check the single Name in the Array list \\(Inver Grove Heights\\)$")
    public void checkTheSingleNameInTheArrayListInverGroveHeights() {
        response.body("data.name", hasItem("Inver Grove Heights"));
    }

    @And("^Check the multiple Names in the ArrayList \\(Roseville, Burnsville, Maplewood\\)$")
    public void checkTheMultipleNamesInTheArrayListRosevilleBurnsvilleMaplewood() {
        response.body("data.name",hasItem("Roseville"))
                .body("data.name", hasItem("Burnsville"))
                .body("data.name", hasItem("Maplewood"));
    }

    @And("^Verify the storied=(\\d+) inside storeservices of the third store of second services$")
    public void verifyTheStoriedInsideStoreservicesOfTheThirdStoreOfSecondServices(int expected) {
       int actual = response.extract().path("data[2].services[1].storeservices.storeId");
        Assert.assertEquals(expected, actual);//check
    }

    @And("^Check hash map values createdAt inside storeservices map where store name = Roseville$")
    public void checkHashMapValuesCreatedAtInsideStoreservicesMapWhereStoreNameRoseville() {
        response.body("data[1].services.storeservices.createdAt", hasItem("2016-11-17T17:57:09.417Z"));
    }

    @And("^Verify the state = MN of forth store$")
    public void verifyTheStateMNOfForthStore() {
        response.body("data[3].state", equalTo("MN"));
    }

    @And("^Verify the store name = \"([^\"]*)\" of nineth store$")
    public void verifyTheStoreNameOfNinethStore(String expected) {
        response = storeSteps.getAllStore();
        String storeName = response.extract().path("data[8].name");
        Assert.assertEquals(expected, storeName);
    }

    @And("^Verify the storeId = 11 for the sixth store$")
    public void verifyTheStoreIdForTheThStore() {
        response = storeSteps.getAllStore();
        List<Integer> storeIDSixthStore = response.extract().path("data[5].services.storeservices.storeId");
        for (int ids : storeIDSixthStore) {
            Assert.assertEquals(11, ids);
        }
    }

    @And("^Verify the serviceId = 4 for the seventh store of forth service$")
    public void verifyTheServiceIdForTheThStoreOfForthService() {
        response.body("data[6].services[3].storeservices.serviceId", equalTo(4));
    }


}
