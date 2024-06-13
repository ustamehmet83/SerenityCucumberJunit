package com.example.steps_definitions.hooks;


import com.example.pages.api.Pet;
import com.example.steps_definitions.base.BaseTests;
import com.example.utilities.BrowserUtils;
import com.example.utilities.ConfigReader;
import com.example.utilities.DBUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.core.di.SerenityInfrastructure;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

public class Hooks  {

    public static String osName;

    @Before
    public void setUp(Scenario scenario) throws Exception {
        RestAssured.baseURI = ConfigReader.getProperty("baseURI");

        BaseTests.softAssertionsThread.set(new SoftAssertions());
        SerenityInfrastructure.getEnvironmentVariables();
        System.out.println(scenario.getName() + " started");
        //create connection
        //DBUtils.createConnection();
        //DBUtils.sslContext();

    }

    @After
    public void teardownForSs(Scenario scenario) throws Exception {
        System.out.println(scenario.getName() + " finished");
        //ScreenRecorderUtil.stopRecord();
        DBUtils.destroy();
       BrowserUtils.getThreadAssert();
    }



}







