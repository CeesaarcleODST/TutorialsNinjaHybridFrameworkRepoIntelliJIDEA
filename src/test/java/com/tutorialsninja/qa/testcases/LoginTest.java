package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends Base
{
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;

    @BeforeMethod
    public void setup()
    {
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
    }

    @DataProvider(name = "validCredentialsSupplier")
    public Object[][] supplyTestData()
    {
        Object[][] data = Utilities.getTestDataFromExcel("Login");
        return data;
    }

    @Test(priority = 1, dataProvider = "validCredentialsSupplier")
    public void verifyLoginWithValidCredentials(String email, String password)
    {
        homePage = new HomePage(driver);
        homePage.clickOnMyAccountDropMenu();
        loginPage = homePage.clickOnLoginOption();

        accountPage = loginPage.sendingLoginData(email, password);

        Assert.assertTrue(accountPage.retrievingEditYourAccountInformationOptionText().contains("Edit your account information"), "'Edit your account information' option is not showing.");
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials()
    {
        homePage = new HomePage(driver);
        homePage.clickOnMyAccountDropMenu();
        loginPage = homePage.clickOnLoginOption();

        loginPage.sendingLoginData(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("genericPassword"));

        Assert.assertTrue(loginPage.retrievingNoMatchEmailPasswordWarningText().contains("Warning: No match for E-Mail Address and/or Password."), "'Warning: No match for E-Mail Address and/or Password.' message is not showing.");
    }

    @Test(priority = 3, dataProvider = "validCredentialsSupplier")
    public void verifyLoginWithValidEmailAndInvalidPassword(String email, String mnah)
    {
        homePage = new HomePage(driver);
        homePage.clickOnMyAccountDropMenu();
        loginPage = homePage.clickOnLoginOption();

        loginPage.sendingLoginData(email, dataProp.getProperty("genericPassword"));

        Assert.assertTrue(loginPage.retrievingNoMatchEmailPasswordWarningText().contains("Warning: No match for E-Mail Address and/or Password."), "'Warning: No match for E-Mail Address and/or Password.' message is not showing.");
    }

    @Test(priority = 4, dataProvider = "validCredentialsSupplier")
    public void verifyLoginWithInvalidEmailAndValidPassword(String mnah, String password)
    {
        homePage = new HomePage(driver);
        homePage.clickOnMyAccountDropMenu();
        loginPage = homePage.clickOnLoginOption();

        loginPage.sendingLoginData(Utilities.generateEmailWithTimeStamp(), password);

        Assert.assertTrue(loginPage.retrievingNoMatchEmailPasswordWarningText().contains("Warning: No match for E-Mail Address and/or Password."), "'Warning: No match for E-Mail Address and/or Password.' message is not showing.");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}