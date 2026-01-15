package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;

    private By cartIcon = By.className("shopping_cart_link");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check for cart icon")
    public boolean hasCartIcon() {
        return driver.findElements(cartIcon).size() > 0;
    }
}
