package com.example.steps_definitions.ddtPet;

import com.example.steps_definitions.hooks.Hooks;
import io.cucumber.java.en.Given;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class DDTPetStepDefinitions  {

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
