package com.example.steps_definitions;


import com.example.pages.StepDashboardPage;
import com.example.pages.StepLoginPage;
import com.example.pages.WikiSearchPage;
import com.example.utilities.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WikiSearch_StepDefinitions {



    @Managed
    WebDriver driver;
    @Steps
    WikiSearchPage wikiSearchPage;


    @Given("User is on Wikipedia home page")
    public void user_is_on_wikipedia_home_page() {
        driver.get(ConfigReader.getProperty("webdriver.base.gogle.url"));
    }

    @When("User types {string} in the wiki search box")
    public void user_types_in_the_wiki_search_box(String string) {
        wikiSearchPage.searchBox.getShadowRoot().findElement(By.xpath("//input[@id='input']"));
        wikiSearchPage.searchBox.sendKeys(string);
    }

    @And("User clicks wiki search button")
    public void user_clicks_wiki_search_button() {
        wikiSearchPage.clickBtn.click();
    }

    @Then("User sees {string} is in the wiki title")
    public void user_sees_is_in_the_wiki_title(String string) {
        String expectedTitle = string + " - Vikipedi";
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Title is not as expected", actualTitle, expectedTitle);
    }

    @Then("User sees {string} is in the main header")
    public void user_sees_steve_jobs_is_in_the_main_header(String string) {
        String expectedHeader = string;
        String actualFirstHeader = wikiSearchPage.firstHeader.getText();
        Assert.assertEquals("Title is not as expected", expectedHeader, actualFirstHeader);
    }

    @Then("User sees {string} is in the image header")
    public void user_sees_is_in_the_image_header(String string) {
        String expectedImageHeader = string;
        String actualImageHeader = wikiSearchPage.imageHeader.getText();
        Assert.assertEquals("Title is not as expected", expectedImageHeader, actualImageHeader);
    }


}
