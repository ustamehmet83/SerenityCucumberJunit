package com.example.steps_definitions;

import com.example.pages.StepDashboardPage;
import com.example.pages.StepLoginPage;
import com.example.utilities.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class LoginPageStepDefinitions {

    @Managed
    WebDriver driver;
    @Steps
    StepLoginPage loginPage;

    @Steps
    StepDashboardPage dashPage;

    @Given("User is on Home page")
    public void openApplication() {
        driver.get(ConfigReader.getProperty("webdriver.base.url"));
        System.out.println("Page is opened");
    }

    @When("User enters username as {string}")
    public void enterUsername(String userName) {
        System.out.println("Enter Username");
        loginPage.username.sendKeys(userName+ Keys.ENTER);
    }

    @When("User enters password as {string}")
    public void enterPassword(String passWord) {
        loginPage.password.sendKeys(passWord+Keys.ENTER);
        loginPage.submitButton.click();

    }

    @Then("User should be able to login successfully")
    public void clickOnLoginButton() {
        String dashboardTitle = dashPage.dashboardText.getTextContent();
        Assert.assertTrue(dashboardTitle.contains("Dashboard"));
    }

    @Then("User should be able to see error message {string}")
    public void unsucessfulLogin(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.errorMessage.getText();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
