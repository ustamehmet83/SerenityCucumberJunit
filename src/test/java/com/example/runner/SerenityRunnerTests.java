package com.example.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber.json",
                //"html:target/cucumber-reports.html",
                "html:target/default-html-reports",
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber"
        },
        features = "src/test/resources/features",
        glue = "com.example.steps_definitions",
        dryRun = false,
        tags = "@wiki",
        publish=true

)
public class SerenityRunnerTests {
}
