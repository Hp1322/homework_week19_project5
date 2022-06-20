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
public class ServiceDataDrivenTest extends TestBase {
    static String name = "Swag" + TestUtils.getRandomValue();
    static int serviceID;

    @Steps
    ServicesSteps servicesSteps;

    @Title("This will create a new Service")
    @Test
    public void createMultipleService() {

        ValidatableResponse response = servicesSteps.createService(name);
        response.log().all().statusCode(201);
        serviceID = response.log().all().extract().path("id");
        System.out.println(serviceID);

    }

    @Title("Verify if the service was added to the stock")
    @Test
    public void verifyServiceAdded() {
        HashMap<String, Object> serviceMap = servicesSteps.getserviceInfoByID(serviceID);
        Assert.assertThat(serviceMap, hasValue(name));
        System.out.println(serviceMap);

    }

    @Title("Update the service information and verify updated services")
    @Test
    public void updateServiceInformation() {
        name = name + "_updated";
        servicesSteps.updateService(serviceID, name);
        HashMap<String, Object> productMap = servicesSteps.getserviceInfoByID(serviceID);
        Assert.assertThat(productMap, hasValue(name));
    }

    @Title("Delete the service and verify if the service is deleted!")
    @Test
    public void deleteServiceAndVerify() {
        servicesSteps.deleteService(serviceID).statusCode(200);
        servicesSteps.getProductById(serviceID).statusCode(404);
    }

}
