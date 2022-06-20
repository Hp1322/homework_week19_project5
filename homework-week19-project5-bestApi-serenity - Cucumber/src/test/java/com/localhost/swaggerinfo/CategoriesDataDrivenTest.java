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
public class CategoriesDataDrivenTest extends TestBase {
    static String name = "Swag" + TestUtils.getRandomValue();
    static String id = "12" + TestUtils.getRandomValue();
    static String categoryID;

    @Steps
    CategoriesSteps categoriesSteps;

    @Title("This will create a new category")
    @Test
    public void createMultipleCategory() {

        ValidatableResponse response = categoriesSteps.createCategory(name,id);
        response.log().all().statusCode(201);
        categoryID = response.log().all().extract().path("id");
        System.out.println(categoryID);

    }

    @Title("Verify if the category was added to the stock")
    @Test
    public void verifyCategoryAdded() {
        HashMap<String, Object> categoryMap = categoriesSteps.getCategoryInfoByID(categoryID);
        Assert.assertThat(categoryMap, hasValue(name));
        System.out.println(categoryMap);

    }

    @Title("Update the category information and verify updated categories")
    @Test
    public void updateCategoryInformation() {
        name = name + "_updated";
        categoriesSteps.updateCategory(categoryID, name, id);
        HashMap<String, Object> categoryMap = categoriesSteps.getCategoryInfoByID(categoryID);
        Assert.assertThat(categoryMap, hasValue(name));
    }

    @Title("Delete the category and verify if the category is deleted!")
    @Test
    public void deleteCategoryAndVerify() {
        categoriesSteps.deleteCategory(categoryID).statusCode(200);
        categoriesSteps.getCategoryById(categoryID).statusCode(404);
    }

}
