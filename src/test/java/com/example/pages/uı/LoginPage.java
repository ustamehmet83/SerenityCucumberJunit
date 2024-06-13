package com.example.pages.uı;


import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {

    @FindBy(name = "username")
    public WebElementFacade username;

    @FindBy(name = "password")
    public WebElementFacade password;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElementFacade submitButton;

    @FindBy(xpath = "//p[.='Invalid credentials']")
    public WebElementFacade errorMessage;


}
