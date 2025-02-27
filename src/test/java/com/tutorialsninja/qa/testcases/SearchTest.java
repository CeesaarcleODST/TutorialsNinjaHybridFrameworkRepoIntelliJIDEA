package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//Updated comment.

public class SearchTest extends Base
{
    public WebDriver driver;
    HomePage homePage;
    SearchPage searchPage;

    @BeforeMethod
    public void setup()
    {
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
    }

    @Test(priority = 1)
    public void verifySearchingWithValidProduct()
    {
        homePage = new HomePage(driver);
        homePage.searchingAProduct(dataProp.getProperty("validProduct"));
        searchPage = homePage.clickOnSearchButton();

        Assert.assertTrue(searchPage.retrievingProductText().contains("HP LP3065"), "'HP LP3065' is not displayed.");
    }

    @Test(priority = 2)
    public void verifySearchingWithInvalidProduct()
    {
        homePage = new HomePage(driver);
        homePage.searchingAProduct(dataProp.getProperty("invalidProduct"));
        searchPage = homePage.clickOnSearchButton();

        Assert.assertTrue(searchPage.retrievingNoProductMessageText().contains("There is no product that matches the search criteria. ERROR"), "'There is no product that matches the search criteria.' message is not showing.");
    }

    @Test(priority = 3, dependsOnMethods = "verifySearchingWithInvalidProduct")
    public void verifySearchingWithoutProduct()
    {
        homePage = new HomePage(driver);
        searchPage = homePage.clickOnSearchButton();

        Assert.assertTrue(searchPage.retrievingNoProductMessageText().contains("There is no product that matches the search criteria."), "'There is no product that matches the search criteria.' message is not showing.");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}