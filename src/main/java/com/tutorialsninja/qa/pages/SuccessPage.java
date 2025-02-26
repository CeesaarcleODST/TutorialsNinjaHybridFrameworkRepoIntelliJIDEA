package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessPage
{
    WebDriver driver;

    public SuccessPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Object
    @FindBy(xpath = "//h1[text()]")
    private WebElement yourAccountBeenCreatedMessage;

    //Method
    public String retrievingYourAccountHasBeenCreatedMessageText()
    {
        return yourAccountBeenCreatedMessage.getText();
    }
}