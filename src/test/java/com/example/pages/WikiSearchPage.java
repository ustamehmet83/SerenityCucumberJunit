package com.example.pages;


import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
@DefaultUrl("https://www.wikipedia.org/")
public class WikiSearchPage extends PageObject {

    @FindBy (id="searchInput")
    public WebElementFacade searchBox;

    @FindBy(xpath = "//i[.='Search']")
    public WebElementFacade clickBtn;

    @FindBy (id="firstHeading")
    public WebElementFacade firstHeader;

    @FindBy(xpath = "//table[@class='infobox biography vcard']//th[@class='infobox-above']/div")
    public WebElementFacade imageHeader;
}
