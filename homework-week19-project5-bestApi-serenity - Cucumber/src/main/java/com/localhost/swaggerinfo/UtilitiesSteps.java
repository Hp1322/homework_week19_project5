package com.localhost.swaggerinfo;

import com.localhost.constants.EndPoints;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UtilitiesSteps {

    @Step("Getting healthcheck information")
    public HashMap<?, ?> getHealthCheck() {
        HashMap<?, ?> healthMap = SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_HEALTHCHECK)
                .then()
                .statusCode(200)
                .extract().path("");
        return healthMap;
    }

    @Step("Getting version information")
    public HashMap<?, ?> getVersion() {
        HashMap<?, ?> versionMap = SerenityRest.given().log().all().
                when()
                .get(EndPoints.GET_ALL_VERSION)
                .then()
                .statusCode(200)
                .extract().path("");
        return versionMap;
    }
}
