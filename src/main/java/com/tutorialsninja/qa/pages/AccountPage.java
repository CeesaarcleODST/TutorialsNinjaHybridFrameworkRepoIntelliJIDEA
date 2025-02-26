package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage
{
    WebDriver driver;

    public AccountPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Object
    @FindBy(xpath = "//a[text()='Edit your account information']")
    private WebElement editYourAccountInformationOption;

    //Method
    public String retrievingEditYourAccountInformationOptionText()
    {
        return editYourAccountInformationOption.getText();
    }
}
