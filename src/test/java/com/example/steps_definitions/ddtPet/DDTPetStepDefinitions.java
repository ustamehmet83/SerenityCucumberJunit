package com.example.steps_definitions.ddtPet;

import com.example.steps_definitions.hooks.Hooks;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class DDTPetStepDefinitions  {
    @ParameterizedTest
    @CsvSource({"777,hamham",
            "222,domdom",
            "333,dombily",
            "444,karabas",
            "555,kral",
            "666,kontes"})
    public void getMultiplePet(int id, String expectedName) {

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
