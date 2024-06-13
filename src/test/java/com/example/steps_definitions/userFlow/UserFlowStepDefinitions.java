package com.example.steps_definitions.userFlow;

import com.example.pages.api.Pet;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.*;
//import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UserFlowStepDefinitions {

    public Response response;
    public Map<Object, Object> responseMap;
    public static int pet_id;
    public Pet requestPet;

    /**************************************************************
     * The test expecting to create successfully a user on the page
     *************************************************************/
    @Given("User create a user on the page")
    public void userCreateAUserOnThePage() {
        File file = new File("src/test/resources/requestFile/createUser.json");
        response =
                given().
                        contentType(ContentType.JSON).
                        accept("application/json").
                        body(file).
                        when().
                        post("/user");

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                log().all().
                body(
                        "message", is("5555"),
                        "code", is(200)).
                body(matchesJsonSchemaInClasspath("responseSchema/createUserSchema.json"));

    }

    /******************************
     * Get created user credentials
     * GET METHOD
     *****************************/

    @When("User get created user credentials")
    public void userGetCreatedUserCredentials() {
        given().
                accept("application/json").
                when().
                get("/user/dan_greaker").
                then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                log().all().
                body("id", is(5555),
                        "username", is("dan_greaker"),
                        "email", is("dan.greaker@gmail.com"));

    }

    /**************************************
     * Update the created user(dan_greaker)
     * PUT METHOD
     ********************************/
    @Then("User update the created user")
    public void userUpdateTheCreatedUser() {
        given().
                contentType(ContentType.JSON).
                accept("application/json").
                body(new File("src/test/resources/requestFile/updateUser.json")).
                when().
                put("/user/dan_greaker").
                then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                log().all().
                body("message", is("4444"),
                        "code", is(200)).log().all();

    }

    /******************************
     * Get updated user (john_doey)
     * GET METHOD
     *****************************/
    @Then("User get updated user")
    public void userGetUpdatedUser() {
        given().
                accept("application/json").
                when().
                get("/user/john_doey").
                then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                log().all().
                body("id", is(4444),
                        "username", is("john_doey"),
                        "email", is("john_doey@gmail.com"));

    }

    /***********************************
     * Delete the updated user(john_doey)
     * DELETE METHOD
     **********************************/
    @Then("User delete the updated user")
    public void userDeleteTheUpdatedUser() {
        given().
                contentType(ContentType.JSON).
                accept("application/json").
                when().
                delete("/user/john_doey").
                then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                log().all().
                body("message", is("john_doey"),
                        "code", is(200)).log().all();

    }

    /*****************************************
     * Delete the first created user(dan_greaker)
     * DELETE METHOD
     ****************************************/
    @Then("User delete first created user")
    public void userDeleteFirstCreatedUser() {
        given().
                contentType(ContentType.JSON).
                accept("application/json").
                when().
                delete("/user/dan_greaker").
                then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("message", is("dan_greaker"),
                        "code", is(200)).log().all();

    }

    /*******************************
     * Get deleted user (john_doey)
     * Validated user already deleted.
     * Thr test suppose to get error message
     * GET METHOD
     *****************************/
    @Then("User get deleted user")
    public void userGetDeletedUser() {
        given().
                accept("application/json").
                when().
                get("/user/john_doey").
                then().
                assertThat().
                statusCode(404).
                contentType(ContentType.JSON).
                log().all().
                body("code", is(1),
                        "message", is("User not found"));

    }

    /*******************************
     * Get deleted user (dan_graker)
     * Validated user already deleted.
     * Thr test suppose to get error message
     * GET METHOD
     *****************************/
    @Then("User check user is deleted")
    public void userCheckUserIsDeleted() {
        given().
                accept("application/json").
                when().
                get("/user/dan_greaker").
                then().assertThat().
                statusCode(404).
                contentType(ContentType.JSON).
                log().all().
                body("code", is(1),
                        "message", is("User not found"));

    }



















   
}
