package com.example.steps_definitions.hooks;


import com.example.steps_definitions.base.BaseTests;
import com.example.utilities.BrowserUtils;
import com.example.utilities.DBUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.core.di.SerenityInfrastructure;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import org.assertj.core.api.SoftAssertions;

public class Hooks  {

    public static String osName;

    @Before
    public void setUp(Scenario scenario) throws Exception {

        BaseTests.softAssertionsThread.set(new SoftAssertions());
        SerenityInfrastructure.getEnvironmentVariables();


        //ScreenRecorderUtil.startRecording();
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







