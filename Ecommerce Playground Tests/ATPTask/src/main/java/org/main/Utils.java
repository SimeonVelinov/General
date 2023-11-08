package org.main;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
    public WebDriver driver = getWebDriver();
    public Actions actions = new Actions(driver);
    private static final int timeout = 10;
    private static String target;

    public WebDriver getWebDriver() {
        return WebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
    }
    public static void quitDriver() {
        WebDriverManager.CustomWebDriverManagerEnum.INSTANCE.quitDriver();
    }

    public void assertElementPresent(By target) {
        Assertions.assertNotNull(driver.findElement(target),
                String.format("Target element not found on page %s", getTarget()));
    }

    public void assertElementNotPresent(By target) {
        boolean test = true;
        try {
            Assertions.assertNull(driver.findElement(target));
        } catch (Exception e) {
            test = false;
        }
        Assertions.assertFalse(test, String.format("Element present on page when not expected %s", getTarget()));
    }

    public void clickElement(By target) {
        driver.findElement(target).click();
    }

    public void hoverElement(By target) {
        actions.moveToElement(driver.findElement(target)).perform();
    }

    public void typeValueInField(String value, By target) {
        WebElement element = driver.findElement(target);
        element.clear();
        element.sendKeys(value);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void waitForElementVisible(By target) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(target));
        } catch (Exception e) {
            Assertions.fail(String.format("Element not visible %s", getTarget()));
        }
    }

    public void waitForElementPresent(By target) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(target));
        } catch (Exception e) {
            Assertions.fail(String.format("Element not present %s", getTarget()));
        }
    }

    public void waitForElementClickable(By target) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(target));
        } catch (Exception e) {
            Assertions.fail(String.format("Element not clickable %s", getTarget()));
        }
    }

    public static By xPath(String locator) {
        setTarget(locator);
        return By.xpath(getTarget());
    }

    public static By id(String locator) {
        setTarget(locator);
        return By.id(getTarget());
    }

    public static By cssSelector(String locator) {
        setTarget(locator);
        return By.cssSelector(getTarget());
    }

    public static By className(String locator) {
        setTarget(locator);
        return By.className(getTarget());
    }

    public static By name(String locator) {
        setTarget(locator);
        return By.name(getTarget());
    }

    public static By linkText(String locator) {
        setTarget(locator);
        return By.linkText(getTarget());
    }

    public static By partialLinkText(String locator) {
        setTarget(locator);
        return By.partialLinkText(getTarget());
    }

    public static By tagName(String locator) {
        setTarget(locator);
        return By.tagName(getTarget());
    }

    public static void setTarget(String target) {
        Utils.target = target;
    }

    static String getTarget() {
        return target;
    }
}