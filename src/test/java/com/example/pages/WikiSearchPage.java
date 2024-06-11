package com.example.pages;


import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class WikiSearchPage extends PageObject {

    @FindBy (id="input")
    public WebElementFacade searchBox;

    @FindBy(xpath = "//i[.='Search']")
    public WebElementFacade clickBtn;

    @FindBy (id="firstHeading")
    public WebElementFacade firstHeader;

    @FindBy(xpath = "//div[.='Steven Paul Jobs']")
    public WebElementFacade imageHeader;
}
