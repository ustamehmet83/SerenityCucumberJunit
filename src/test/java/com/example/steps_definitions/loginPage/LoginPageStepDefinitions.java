package com.example.steps_definitions.loginPage;

import com.example.pages.uı.DashboardPage;
import com.example.pages.uı.LoginPage;
import com.example.steps_definitions.base.BaseTests;
import com.example.utilities.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;


public class LoginPageStepDefinitions extends BaseTests {
    @Steps
    LoginPage loginPage;
    @Steps
    DashboardPage dashPage;


    @Given("User is on Home page")
    public void openApplication() {
        loginPage.getDriver().get(ConfigReader.getProperty("webdriver.base.url"));
        System.out.println("Page is opened");
    }

    @When("User enters username as {string}")
    public void enterUsername(String userName) {
        System.out.println("Enter Username");
        loginPage.username.sendKeys(userName);
    }

    @When("User enters password as {string}")
    public void enterPassword(String passWord) {
        loginPage.password.sendKeys(passWord);
        loginPage.submitButton.click();

    }

    @Then("User should be able to login successfully")
    public void clickOnLoginButton() {
        String dashboardTitle = dashPage.dashboardText.getTextContent();
        softAssertionsThread.get().assertThat(dashboardTitle.contains("Dashboard")).isTrue();
    }

    @Then("User should be able to see error message {string}")
    public void unsucessfulLogin(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.errorMessage.getText();
        softAssertionsThread.get().assertThat(expectedErrorMessage).isEqualTo(actualErrorMessage);
    }


}
