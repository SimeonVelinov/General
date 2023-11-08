package org.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverManager {
    static final BrowserType browserType = BrowserType.CHROME;

    public enum CustomWebDriverManagerEnum {
        INSTANCE;
        private WebDriver driver = getDriver();
        public static final Logger LOGGER = LogManager.getRootLogger();

        public void quitDriver() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }

        public WebDriver getDriver() {
            if (driver == null) {
                driver = startBrowser();
            }
            return driver;
        }

        public WebDriver startBrowser() {
            switch (browserType) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("start-maximized");
                    return new ChromeDriver(chromeOptions);
                case CHROME_HEADLESS:
                    ChromeOptions chromeHdOptions = new ChromeOptions();
                    chromeHdOptions.addArguments("--headless=new", "start-maximized");
                    LOGGER.info("Chrome running in headless mode");
                    return new ChromeDriver(chromeHdOptions);
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("start-maximized");
                    return new EdgeDriver(edgeOptions);
                case EDGE_HEADLESS:
                    EdgeOptions edgeHdOptions = new EdgeOptions();
                    edgeHdOptions.addArguments("--headless=new");
                    LOGGER.info("Edge running in headless mode");
                    return new EdgeDriver(edgeHdOptions);
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("start-maximized");
                    return new FirefoxDriver(firefoxOptions);
                case FIREFOX_HEADLESS:
                    FirefoxOptions firefoxHdOptions = new FirefoxOptions();
                    firefoxHdOptions.addArguments("--headless");
                    LOGGER.info("Firefox running in headless mode");
                    return new FirefoxDriver(firefoxHdOptions);
            }
            return null;
        }
    }
}