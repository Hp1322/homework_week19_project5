package com.localhost.swaggerinfo;

import com.localhost.testbase.TestBase;
import com.localhost.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreDataDrivenTest extends TestBase {
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

    @Steps
    StoreSteps storeSteps;

    @Title("This will create a new store")
    @Test
    public void createMultipleStore(){
        HashMap<Object,Object> services = new HashMap<>();
        services.put("storeId", "8");
        services.put("serviceId", "9");

        ValidatableResponse response = storeSteps.createStore(name,type,address,address2,city,state,zip,lat,lng,hours,services);
        response.log().all().statusCode(201);
        storeID = response.log().all().extract().path("id");
        System.out.println(storeID);

    }
    @Title("Verify if the store was added to the stock")
    @Test
    public void verifyStoreAdded() {
        HashMap<String, Object> storeMap = storeSteps.getstoreInfoByID(storeID);
        Assert.assertThat(storeMap, hasValue(name));
        System.out.println(storeMap);

    }

    @Title("Update the store information and verify updated information")
    @Test
    public void updateStoreInformation() {
        HashMap<Object,Object> services = new HashMap<>();
        services.put("storeId", "8");
        services.put("serviceId", "9");

        name = name + "_updated";
        storeSteps.updateStore(storeID, name,type,address,address2,city,state,zip,lat,lng,hours,services).log().all().statusCode(200);
        HashMap<String, Object> storeMap = storeSteps.getstoreInfoByID(storeID);
        Assert.assertThat(storeMap, hasValue(name));
    }

    @Title("Delete the store and verify if the store is deleted!")
    @Test
    public void deleteStoreAndVerify() {
        storeSteps.deleteStore(storeID).statusCode(200);
        storeSteps.getStoreById(storeID).statusCode(404);
    }


}
