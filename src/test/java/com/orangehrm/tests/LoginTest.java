package com.orangehrm.tests;

import com.aventstack.extentreports.Status;
import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.DataUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Saucedemo Application")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", description = "Test login functionality with valid and invalid credentials")
    @Story("User attempts to login with different sets of credentials")
    @Description("This test verifies the login functionality of the saucedemo application by using a data-driven approach.")
    @Severity(SeverityLevel.BLOCKER)
    public void testLogin(String username, String password, String expected) {
        try {
            // Log test data
            test.get().info("Test Data - Username: " + username + ", Expected: " + expected);
            
            // Perform login
            LoginPage loginPage = new LoginPage(getDriver());
            test.get().log(Status.INFO, "Performing login with username: " + username);
            
            DashboardPage dashboardPage = loginPage.login(username, password);
            boolean actual = dashboardPage.hasCartIcon();
            
            // Log the actual result
            test.get().info("Login result - Has cart icon: " + actual);
            
            // Assert and log the result
            Assert.assertEquals(String.valueOf(actual), expected);
            test.get().pass("Test passed successfully");
            
        } catch (AssertionError e) {
            test.get().fail("Test failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            test.get().fail("Test encountered an error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        String csvFilePath = DataUtils.getTestResourcePath("testdata/login_test_data.csv");
        return DataUtils.readCsvData(csvFilePath);
    }
}
