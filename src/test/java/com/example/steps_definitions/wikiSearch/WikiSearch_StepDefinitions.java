package com.example.steps_definitions.wikiSearch;


import com.example.pages.uı.WikiSearchPage;
import com.example.steps_definitions.base.BaseTests;
import com.example.utilities.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;

public class WikiSearch_StepDefinitions extends BaseTests {

    @Steps
    WikiSearchPage wikiSearchPage;


    @Given("User is on Wikipedia home page")
    public void user_is_on_wikipedia_home_page() {
        wikiSearchPage.getDriver().get(ConfigReader.getProperty("webdriver.base.wikipedia.url"));
    }

    @When("User types {string} in the wiki search box")
    public void user_types_in_the_wiki_search_box(String string) {
        wikiSearchPage.searchBox.click();
        wikiSearchPage.searchBox.sendKeys(string);
    }

    @And("User clicks wiki search button")
    public void user_clicks_wiki_search_button() {
        wikiSearchPage.clickBtn.click();
    }

    @Then("User sees {string} is in the wiki title")
    public void user_sees_is_in_the_wiki_title(String title) {
        String expectedTitle = title;
        String actualTitle = wikiSearchPage.getDriver().getTitle();
        softAssertionsThread.get().assertThat(actualTitle.contains(expectedTitle)).isTrue();
    }

    @Then("User sees {string} is in the main header")
    public void user_sees_steve_jobs_is_in_the_main_header(String header) {
        String expectedHeader = header;
        String actualFirstHeader = wikiSearchPage.firstHeader.getText();
        softAssertionsThread.get().assertThat(expectedHeader).isEqualTo(actualFirstHeader);
    }

    @Then("User sees {string} is in the image header")
    public void user_sees_is_in_the_image_header(String imageHeader) {
        String expectedImageHeader = imageHeader;
        String actualImageHeader = wikiSearchPage.imageHeader.getTextContent();
        softAssertionsThread.get().assertThat(expectedImageHeader).isEqualTo(actualImageHeader);
    }


}
