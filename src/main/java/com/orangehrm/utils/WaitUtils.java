package com.orangehrm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

/**
 * Utility class for common WebDriver wait operations.
 */
public class WaitUtils {
    
    private static final int DEFAULT_TIMEOUT;
    
    static {
        Properties prop = new Properties();
        try (InputStream input = WaitUtils.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in the classpath");
            }
            prop.load(input);
            DEFAULT_TIMEOUT = Integer.parseInt(prop.getProperty("default.timeout", "10"));
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties", e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid default.timeout value in config.properties", e);
        }
    }
    
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public WaitUtils(WebDriver driver, int timeoutInSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    // Wait for element to be visible
    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable
    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait for element to be clickable (WebElement version)
    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait for element to be present in DOM
    public WebElement waitForElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for all elements to be visible
    public List<WebElement> waitForAllElementsVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // Wait for element to be invisible
    public boolean waitForElementInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Wait for text to be present in element
    public boolean waitForTextToBePresentInElement(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    // Wait for alert to be present
    public void waitForAlertToBePresent() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    // Wait for frame to be available and switch to it
    public void waitForFrameAndSwitchToIt(By frameLocator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    // Wait for page title to contain specific text
    public boolean waitForTitleToContain(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }

    // Wait for page title to be exactly as expected
    public boolean waitForTitleToBe(String title) {
        return wait.until(ExpectedConditions.titleIs(title));
    }

    // Wait for URL to contain specific text
    public boolean waitForUrlToContain(String fraction) {
        return wait.until(ExpectedConditions.urlContains(fraction));
    }

    // Wait for URL to be exactly as expected
    public boolean waitForUrlToBe(String url) {
        return wait.until(ExpectedConditions.urlToBe(url));
    }

    // Wait for element to be selected
    public boolean waitForElementToBeSelected(By locator) {
        return wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    // Wait for element to be selected (WebElement version)
    public boolean waitForElementToBeSelected(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    // Static method to pause execution for a specified time (in milliseconds)
    public static void staticWait(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted", e);
        }
    }
}
