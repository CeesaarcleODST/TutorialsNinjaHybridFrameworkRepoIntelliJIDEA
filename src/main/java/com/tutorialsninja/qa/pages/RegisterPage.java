package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage
{
    WebDriver driver;

    public RegisterPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Objects
    @FindBy(name = "firstname")
    private WebElement firstNameField;

    @FindBy(name = "lastname")
    private WebElement lastNameField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "telephone")
    private WebElement telephoneField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "confirm")
    private WebElement confirmField;

    @FindBy(name = "newsletter")
    private WebElement newsletterCheckCircle;

    @FindBy(name = "agree")
    private WebElement privacyPolicyCheckBox;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//div[text()='Warning: E-Mail Address is already registered!']")
    private WebElement emailAlreadyRegisteredWarning;

    @FindBy(xpath = "//div[text()='Warning: You must agree to the Privacy Policy!']")
    private WebElement privacyPolicyWarning;

    @FindBy(xpath = "//div[text()='First Name must be between 1 and 32 characters!']")
    private WebElement firstNameError;

    @FindBy(xpath = "//div[text()='Last Name must be between 1 and 32 characters!']")
    private WebElement lastNameError;

    @FindBy(xpath = "//div[text()='E-Mail Address does not appear to be valid!']")
    private WebElement emailError;

    @FindBy(xpath = "//div[text()='Telephone must be between 3 and 32 characters!']")
    private WebElement telephoneError;

    @FindBy(xpath = "//div[text()='Password must be between 4 and 20 characters!']")
    private WebElement passwordError;

    //Methods
    public SuccessPage sendingRegisterData(String firstname, String lastname, String email, String telephone, String password)
    {
        firstNameField.sendKeys(firstname);
        lastNameField.sendKeys(lastname);
        emailField.sendKeys(email);
        telephoneField.sendKeys(telephone);
        passwordField.sendKeys(password);
        confirmField.sendKeys(password);
        newsletterCheckCircle.click();
        privacyPolicyCheckBox.click();
        continueButton.click();

        return new SuccessPage(driver);
    }

    public void clickOnContinueButton()
    {
        continueButton.click();
    }

    public String emailAlreadyRegisteredWarningText()
    {
        return emailAlreadyRegisteredWarning.getText();
    }

    public boolean retrievingStatusMessages()
    {
        return privacyPolicyWarning.isDisplayed() &&
                firstNameError.isDisplayed() &&
                lastNameError.isDisplayed() &&
                emailError.isDisplayed() &&
                telephoneError.isDisplayed() &&
                passwordError.isDisplayed();
    }
}
