package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.pages.SuccessPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterTest extends Base
{
    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    SuccessPage successPage;

    @BeforeMethod
    public void setup()
    {
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
    }

    @Test(priority = 1)
    public void verifyRegisteringWithMandatoryFields()
    {
        homePage = new HomePage(driver);
        homePage.clickOnMyAccountDropMenu();
        registerPage = homePage.clickOnRegisterOption();

        successPage = registerPage.sendingRegisterData(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"), dataProp.getProperty("genericPassword"));

        Assert.assertTrue(successPage.retrievingYourAccountHasBeenCreatedMessageText().contains("Your Account Has Been Created!"), "'Your Account Has Been Created!' message is not showing.");
    }

    @Test(priority = 2)
    public void verifyRegisteringProvidingAllFields()
    {
        homePage = new HomePage(driver);
        homePage.clickOnMyAccountDropMenu();
        registerPage = homePage.clickOnRegisterOption();

        successPage = registerPage.sendingRegisterData(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"), dataProp.getProperty("genericPassword"));

        Assert.assertTrue(successPage.retrievingYourAccountHasBeenCreatedMessageText().contains("Your Account Has Been Created!"), "'Your Account Has Been Created!' message is not showing.");
    }

    @DataProvider(name = "validCredentialsSupplier")
    public Object[][] supplyTestData()
    {
        Object[][] data = Utilities.getTestDataFromExcel("Login");
        return data;
    }

    @Test(priority = 3, dataProvider = "validCredentialsSupplier")
    public void verifyRegisteringWithDuplicateEmail(String email, String mnah)
    {
        homePage = new HomePage(driver);
        homePage.clickOnMyAccountDropMenu();
        registerPage = homePage.clickOnRegisterOption();

        registerPage.sendingRegisterData(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), email, dataProp.getProperty("telephone"), dataProp.getProperty("genericPassword"));

        Assert.assertTrue(registerPage.emailAlreadyRegisteredWarningText().contains("Warning: E-Mail Address is already registered!"), "'Warning: E-Mail Address is already registered!' warning is not showing.");
    }

    @Test(priority = 4)
    public void verifyRegisteringWithoutFillingAnyDetail()
    {
        homePage = new HomePage(driver);
        homePage.clickOnMyAccountDropMenu();
        registerPage = homePage.clickOnRegisterOption();

        registerPage.clickOnContinueButton();

        Assert.assertTrue(registerPage.retrievingStatusMessages());
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}