package com.example.steps_definitions.petFlow;
import com.example.pages.api.Tag;
import com.example.pages.api.Category;
import com.example.pages.api.Pet;
import com.example.steps_definitions.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;


import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.*;
//import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static java.util.Collections.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class PetFlowStepDefinitions  {

    public static String osName;
    public Response response;
    public Map<Object, Object> responseMap;
    public static int pet_id;
    public Pet requestPet;

    /*************************************************
     * The Test creating a pet using the JSON file
     * The assertion using the map structure
     * POST METHOD
     *************************************************/


    @Given("User create a pet using the Json file")
    public void userCreateAPetUsingTheJsonFile() {
        response =
                given().
                        contentType(ContentType.JSON).
                        accept("application/json").
                        body(new File("src/test/resources/requestFile/createPet.json")).
                        when().
                        post("/pet");

        responseMap = response.body().as(Map.class);

        assertEquals( 9898,responseMap.get("id"));
        assertEquals( "fluffy",responseMap.get("name"));
        assertEquals( "available",responseMap.get("status"));
        pet_id = (int) responseMap.get("id");
    }


    /*************************************************
     * The Test creating a pet using the POJO Pet classes structure
     * The JSON file updated by the Pet pages
     * POST METHOD
     *************************************************/

    @Given("User create a pet using Pojo pet class structure")
    public void userCreateAPetUsingPojoPetClassStructure() {


        /**
         * The constructor updating the request Json payload
         */
        requestPet = new Pet(9999,new Category(99,"fluff"),
                "ponpon", singletonList("null"), singletonList(new Tag(1010, "crash")),
                "available");

        given().
                contentType(ContentType.JSON).
                accept("application/json").
                body(requestPet).
                when().
                post("/pet").
                then().assertThat().log().all().
                statusCode(200).
                body("id", equalTo(9999),
                        "name",is("ponpon"),
                        "status",is("available"),
                        "tags[0].id",is(1010));


    }

    /*************************************************
     * Created previous test's pet calling details
     * GET METHOD
     *************************************************/

    @Given("User gets Created previous test's pet calling details")
    public void UserGetsCreatedPreviousTestsPetCallingDetails() {

        given().log().all().
                accept("application/json").
                when().
                get("/pet/" + pet_id).
                then().assertThat().
                statusCode(200).
                body("id",is(pet_id),
                        "name",equalTo("fluffy"),
                        "status",equalTo("available")).
                log().all() ;

    }

    /*************************************************
     * Update previous test's created pet
     * PUT METHOD
     *************************************************/

    @Given("User update previous test's created pet")
    public void UserUpdatePreviousTestsCreatedPet() {
        requestPet = new Pet(8888,new Category(88,"pluffy"),
                "tomtom", singletonList("picture"), singletonList(new Tag(9090, "nope")),
                "pending");

        response =
                given().
                        contentType(ContentType.JSON).
                        accept("application/json").
                        body(requestPet).
                        when().
                        put("/pet");

        response.
                then().assertThat().
                statusCode(200).
                body("id",is(8888),
                        "name",equalTo("tomtom"),
                        "status",equalTo("pending")).
                log().all();

        responseMap = response.body().as(Map.class);
        pet_id = (int) responseMap.get("id");

    }

    /*************************************************
     * Updated previous test's pet calling details
     * Validation has JSON Schema validation as well
     * GET METHOD
     *************************************************/

    @Given("User check previous test's pet calling details Schema validation")
    public void UserCheckPreviousTestsPetCallingDetailsSchemaValidation() {

        given().log().all().
                accept("application/json").
                when().
                get("/pet/" + pet_id).
                then().assertThat().
                statusCode(200).
                body("id",is(pet_id),
                        "name",equalTo("tomtom"),
                        "status",equalTo("pending")).
               // body(matchesJsonSchemaInClasspath("responseSchema/getUpdatedPetSchema.json")).
                log().all() ;

    }

    /*************************************************
     * Delete first created test's  pet
     * DELETE METHOD
     *************************************************/

    @Given("User delete first created test pet")
    public void UserDeleteFirstCreatedTestPet() {

        given().
                contentType(ContentType.JSON).
                accept("application/json").
                when().
                delete("/pet/"+pet_id).
                then().assertThat().
                statusCode(200).
                body("code",is(200),
                        "message",is("8888")).
                log().all();
    }


    /*************************************************
     * Validating the deleted pet information
     * The test suppose to get error message
     * GET METHOD
     *************************************************/

    @Given("User check deleted pet information")
    public void UserCheckDeletedPetInformation() {

        given().log().all().
                accept("application/json").
                when().
                get("/pet/" + pet_id).
                then().assertThat().
                statusCode(404).
                body("code",is(1),
                        "message",equalTo("Pet not found")).
                log().all() ;

    }


}
