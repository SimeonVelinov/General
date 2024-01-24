package org.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Actions {
    static WebDriver driver;

    public static void setDriver(WebDriver in) {
        driver = in;
    }

    public static boolean isClickable(WebElement el) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(el));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNotClickable(WebElement el) {
        try {
            el.click();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
