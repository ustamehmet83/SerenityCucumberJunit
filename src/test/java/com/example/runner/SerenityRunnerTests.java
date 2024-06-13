package com.example.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {
                "pretty", "html:target/serenity-reports/serenity-html-report",
                "json:target/serenity-reports/cucumber_report.json",
                "rerun:target/serenity-reports/rerun.txt"
        },
        features = "src/test/resources/features",
        glue = "com.example.steps_definitions",
        dryRun = false,
        tags = "@DDTPet",
        publish=true

)
public class SerenityRunnerTests {
}
