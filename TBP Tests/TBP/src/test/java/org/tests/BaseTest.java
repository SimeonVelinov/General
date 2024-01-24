package org.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.openqa.selenium.WebDriver;
import org.utils.Actions;
import org.utils.WebDriverFactory;

import java.util.Properties;


public class BaseTest {
    static Properties p = new Properties();
    static String targetUrl = "notSet";
    public static WebDriver webDriver;

    BaseTest(String url) {
        p.setProperty("browser", "chrome");
        if (webDriver == null)
            webDriver = WebDriverFactory.createWebDriver(p);
        targetUrl = url;
        webDriver.get(targetUrl);
        Actions.setDriver(webDriver);
    }

    @DisabledIfEnvironmentVariable(named = "targetUrl", matches = "notSet", disabledReason = "Not needed before URL set")
    @AfterAll
    public static void cleanup() {
        webDriver.quit();
        webDriver = null;
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "targetUrl", matches = "notSet", disabledReason = "Not needed before URL set")
    @DisplayName("Successful page navigation check")
    public void targetPageNavigatedSuccess() {
        Assertions.assertEquals(webDriver.getCurrentUrl(), targetUrl, "Unexpected URL navigated");
    }
}
