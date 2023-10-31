package data;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static data.ConstantsForSwagLabs.ANSI_GREEN;
import static data.ConstantsForSwagLabs.ANSI_RESET;


public enum BrowserTypes {
    CHROME,
    CHROME_HEADLESS,
    FIREFOX,
    FIREFOX_HEADLESS,
    EDGE,
    EDGE_HEADLESS;

    public static class WebDriver {
        public static RemoteWebDriver startBrowser(BrowserTypes browserType) {
            switch (browserType) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    return new ChromeDriver(chromeOptions);
                case CHROME_HEADLESS:
                    ChromeOptions chromeHdOptions = new ChromeOptions();
                    chromeHdOptions.addArguments("--headless=new");
                    System.out.println(ANSI_GREEN + "Chrome running in headless mode" + ANSI_RESET);
                    return new ChromeDriver(chromeHdOptions);
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    return new EdgeDriver(edgeOptions);
                case EDGE_HEADLESS:
                    EdgeOptions edgeHdOptions = new EdgeOptions();
                    edgeHdOptions.addArguments("--headless=new");
                    System.out.println(ANSI_GREEN + "Edge running in headless mode" + ANSI_RESET);
                    return new EdgeDriver(edgeHdOptions);
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    return new FirefoxDriver(firefoxOptions);
                case FIREFOX_HEADLESS:
                    FirefoxOptions firefoxHdOptions = new FirefoxOptions();
                    firefoxHdOptions.addArguments("--headless");
                    System.out.println(ANSI_GREEN + "Firefox running in headless mode" + ANSI_RESET);
                    return new FirefoxDriver(firefoxHdOptions);
            }
            return null;
        }
    }
}
