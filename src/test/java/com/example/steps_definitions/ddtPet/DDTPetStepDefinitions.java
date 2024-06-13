package com.example.steps_definitions.ddtPet;

import com.example.steps_definitions.base.BaseTests;
import com.example.steps_definitions.hooks.Hooks;
import io.cucumber.java.en.Given;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class DDTPetStepDefinitions extends BaseTests {

    @Given("User get multiple pet with {int} and {string}")
    public void userGetMultiplePetWithCsvSource(int id, String expectedName) {
        given().log().all().
                accept("application/json").
                when().
                get("/pet/" + id).
                then().assertThat().
                statusCode(200).
                body("id", is(id),
                        "name", equalTo(expectedName),
                        "status", equalTo("available")).
                log().all();

    }



}
