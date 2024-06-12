package com.example.runner;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@pet")
@RunWith(SerenityRunner.class)
public class CucumberTestSuite {}
