package com.orangehrm.tests;

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
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(username, password);
        boolean actual = dashboardPage.hasCartIcon();
        Assert.assertEquals(String.valueOf(actual), expected);
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        String csvFilePath = DataUtils.getTestResourcePath("testdata/login_test_data.csv");
        return DataUtils.readCsvData(csvFilePath);
    }
}
