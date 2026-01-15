package com.orangehrm.listeners;

import com.aventstack.extentreports.Status;
import com.orangehrm.base.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseTest implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        String testName = getTestMethodName(iTestResult);
        System.out.println("I am in onTestStart method " + testName + " start");
        test.set(extent.createTest(testName));
        test.get().log(Status.INFO, "Starting test: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String testName = getTestMethodName(iTestResult);
        System.out.println("I am in onTestSuccess method " + testName + " succeed");
        if (test.get() != null) {
            test.get().log(Status.PASS, "Test passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String testName = getTestMethodName(iTestResult);
        System.out.println("I am in onTestFailure method " + testName + " failed");
        
        // Take screenshot for Allure report
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        if (driver != null) {
            System.out.println("Screenshot captured for test case: " + testName);
            saveScreenshotPNG(driver);
            saveTextLog(testName + " failed and screenshot taken!");
        }
        
        // Log failure in Extent Report
        if (test.get() != null) {
            test.get().log(Status.FAIL, "Test Failed: " + iTestResult.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String testName = getTestMethodName(iTestResult);
        System.out.println("I am in onTestSkipped method " + testName + " skipped");
        if (test.get() != null) {
            test.get().log(Status.SKIP, "Test Skipped: " + iTestResult.getThrowable());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        String testName = getTestMethodName(iTestResult);
        System.out.println("Test failed but it is in defined success ratio " + testName);
        if (test.get() != null) {
            test.get().log(Status.WARNING, "Test failed but within success percentage: " + iTestResult.getThrowable());
        }
    }
}
