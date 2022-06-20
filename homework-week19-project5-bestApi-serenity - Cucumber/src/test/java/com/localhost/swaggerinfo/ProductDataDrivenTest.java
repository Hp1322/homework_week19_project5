package com.localhost.swaggerinfo;

import com.localhost.testbase.TestBase;
import com.localhost.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

//importstatic org.hamcrest.Matchers.hasValue;

public class ProductDataDrivenTest extends TestBase {
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


    @Steps
    ProductSteps productSteps;

    @Title("Data driven test for adding product to application")
    @Test
    public void createMultipleProducts() {

        ValidatableResponse response = productSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productID = response.log().all().extract().path("id");
        System.out.println(productID);

    }

    @Title("Verify if the product was added to the stock")
    @Test
    public void verifyProductAdded() {
        HashMap<String, Object> productMap = productSteps.getproductInfoByID(productID);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println("product map : " + productMap);

    }

    @Title("Update the product information and verify updated information")
    @Test
    public void updateProductInformation() {
        name = name + "_updated";
        productSteps.updateProduct(productID, name, type, price, shipping, upc, description, manufacturer, model, url, image).log().all().statusCode(200);
        HashMap<String, Object> productMap = productSteps.getproductInfoByID(productID);
        Assert.assertThat(productMap, hasValue(name));
    }

    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void deleteProductAndVerify() {
        productSteps.deleteProduct(productID).statusCode(200);
        productSteps.getProductById(productID).statusCode(404);
    }
    @Title("Getting all product of application")
    @Test
    public void getAllProducts() {
        productSteps.getAllProduct().statusCode(200);
    }
}
