package org.tests;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.openqa.selenium.WebDriver;
import org.utils.Actions;
import org.utils.WebDriverFactory;

import java.util.Properties;


public class BaseTest {
    static Properties p = new Properties();
    static String targetUrl = "notSet";
    static BatchInfo batchInfo = new BatchInfo("Brand Place Tests");

    public static WebDriver webDriver;
    public static Configuration eyeConfig;
    public static EyesRunner testRunner = new VisualGridRunner(new RunnerOptions().testConcurrency(8));


    BaseTest(String url) {
        p.setProperty("browser", "chrome");
        if (webDriver == null)
            webDriver = WebDriverFactory.createWebDriver(p);
        targetUrl = url;
        webDriver.get(targetUrl);
        Actions.setDriver(webDriver);
        eyeConfig = new Configuration();
        eyeConfig.setBatch(batchInfo);
        eyeConfig.addBrowser(1920, 1080, BrowserType.CHROME);
        eyeConfig.addBrowser(1920, 1080, BrowserType.FIREFOX);
        eyeConfig.addBrowser(1920, 1080, BrowserType.EDGE_CHROMIUM);
        eyeConfig.addDeviceEmulation(DeviceName.Galaxy_S20, ScreenOrientation.PORTRAIT);
        eyeConfig.addDeviceEmulation(DeviceName.Galaxy_S20, ScreenOrientation.LANDSCAPE);
        eyeConfig.addDeviceEmulation(DeviceName.iPhone_11_Pro_Max, ScreenOrientation.PORTRAIT);
        eyeConfig.addDeviceEmulation(DeviceName.iPhone_11_Pro_Max, ScreenOrientation.LANDSCAPE);
        eyeConfig.addDeviceEmulation(DeviceName.iPad_Air_2, ScreenOrientation.LANDSCAPE);
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