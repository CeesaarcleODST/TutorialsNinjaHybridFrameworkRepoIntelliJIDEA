package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
    WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Objects
    @FindBy(linkText = "My Account")
    private WebElement myAccountDropMenu;

    @FindBy(linkText = "Login")
    private WebElement loginOption;

    @FindBy(linkText = "Register")
    private WebElement registerOption;

    @FindBy(name = "search")
    private WebElement searchBox;

    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement searchButton;

    //Methods
    public void clickOnMyAccountDropMenu()
    {
        myAccountDropMenu.click();
    }

    public LoginPage clickOnLoginOption()
    {
        loginOption.click();
        return new LoginPage(driver);
    }

    public RegisterPage clickOnRegisterOption()
    {
        registerOption.click();
        return new RegisterPage(driver);
    }

    public void searchingAProduct(String product)
    {
        searchBox.sendKeys(product);
    }

    public SearchPage clickOnSearchButton()
    {
        searchButton.click();
        return new SearchPage(driver);
    }
}