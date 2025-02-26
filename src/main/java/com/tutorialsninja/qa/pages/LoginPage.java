package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
    WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Objects
    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[text()='Warning: No match for E-Mail Address and/or Password.']")
    private WebElement noMatchEmailPassword;

    //Methods
    public AccountPage sendingLoginData(String email, String password)
    {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();

        return new AccountPage(driver);
    }

    public String retrievingNoMatchEmailPasswordWarningText()
    {
        return noMatchEmailPassword.getText();
    }
}