package com.orangehrm.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.orangehrm.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setUp(Method method) throws IOException {
        // Create test in Extent Report
        String testName = method.getName();
        test.set(extent.createTest(testName));
        
        // Initialize WebDriver
        Properties prop = new Properties();
        FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
        prop.load(ip);
        String url = prop.getProperty("url");

        driver.set(WebDriverFactory.getDriver());
        getDriver().get(url);
        
        // Log test start
        test.get().log(Status.INFO, "Test started: " + testName);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Log test status in Extent Report
        if (result.getStatus() == ITestResult.FAILURE) {
            test.get().fail("Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.get().skip("Test Skipped: " + result.getThrowable());
        } else {
            test.get().pass("Test Passed");
        }
        
        // Close browser and clean up
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
    
    @AfterSuite
    public void afterSuite() {
        if (extent != null) {
            extent.flush();
        }
    }
}
