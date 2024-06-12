package com.example.pages.uı;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class StepDashboardPage extends PageObject {

    @FindBy(xpath = "//h6[.='Dashboard']")
    public WebElementFacade dashboardText;


}
