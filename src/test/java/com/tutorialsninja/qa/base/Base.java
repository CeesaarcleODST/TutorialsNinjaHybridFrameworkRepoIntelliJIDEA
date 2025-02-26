package com.tutorialsninja.qa.base;

import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Base
{
    WebDriver driver;
    public Properties prop, dataProp;

    public Base()
    {
        prop = new Properties();
        File propFile = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/config/config.properties");

        try {
            FileInputStream fis = new FileInputStream(propFile);
            prop.load(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        dataProp = new Properties();
        File dataPropFile = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/testdata/testdata.properties");

        try {
            FileInputStream fis2 = new FileInputStream(dataPropFile);
            dataProp.load(fis2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public WebDriver initializeBrowserAndOpenApplicationURL( String browserName)
    {
        if(browserName.equalsIgnoreCase("Chrome"))
            driver = new ChromeDriver();
        if (browserName.equalsIgnoreCase("Firefox"))
            driver = new FirefoxDriver();
        if (browserName.equalsIgnoreCase("Safari"))
            driver = new SafariDriver();
        if(browserName.equalsIgnoreCase("Edge"))
            driver = new EdgeDriver();

        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));

        return driver;
    }
}