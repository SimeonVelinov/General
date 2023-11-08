package pages;

import org.junit.jupiter.api.Assertions;
import org.main.Utils;

public class BasePage {
    protected String url;
    protected Utils utils = new Utils();

    public BasePage(String url) {
        this.url = url;
        navigateToPage();
    }

    public void navigateToPage() {
        utils.getWebDriver().get(url);
        assertPageNavigated();
    }

    public void assertPageNavigated() {
        String current = utils.driver.getCurrentUrl();
        Assertions.assertTrue(current.contains(url),
                "Target page not navigated successfully");
    }
}
