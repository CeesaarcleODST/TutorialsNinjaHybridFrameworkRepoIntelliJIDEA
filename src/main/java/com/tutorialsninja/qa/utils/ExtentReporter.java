package com.tutorialsninja.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ExtentReporter
{
    public static ExtentReports generateExtentReport()
    {
        ExtentReports extentReports = new ExtentReports();
        File extentReportFile = new File(System.getProperty("user.dir") + "/test-output/ExtentReport/extentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Tutorials Ninja Test Suite");
        sparkReporter.config().setDocumentTitle("TN Test Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        Properties prop = new Properties();
        File propFile = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/config/config.properties");

        try {
            FileInputStream fis = new FileInputStream(propFile);
            prop.load(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        extentReports.setSystemInfo("Application URL", prop.getProperty("url"));
        extentReports.setSystemInfo("Browser Name", prop.getProperty("browserName"));
        extentReports.setSystemInfo("Email Address", prop.getProperty("email"));
        extentReports.setSystemInfo("Password", prop.getProperty("password"));
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("Username", System.getProperty("user.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.attachReporter(sparkReporter); //Me faltaba el sparkReporter.

        return extentReports;
    }
}