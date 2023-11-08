package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.main.Utils;


public class HomePage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String targetPage = "https://ecommerce-playground.lambdatest.io/";
    private String title;

    public HomePage() {
        super(targetPage);
    }

    public void expandCategoryMenu() {
        utils.waitForElementClickable
                (Utils.xPath("//a[@class='icon-left both text-reset' and //a[@aria-label='Shop by Category']]"));
        utils.clickElement
                (Utils.xPath("//a[@class='icon-left both text-reset' and //a[@aria-label='Shop by Category']]"));
    }
    public void expandMegaMenu() {
        utils.waitForElementClickable(Utils.xPath("//li[contains(@class, 'mega-menu')]"));
        utils.hoverElement(Utils.xPath("//li[contains(@class, 'mega-menu')]"));
    }
    public void selectCategoryFromMenu(int i) {
        utils.waitForElementClickable
                (Utils.xPath(String.format("//li[@class='nav-item' and ancestor::div[contains(@class, 'active')]][%d]", i)));
        while (title == null) {
            title = utils.driver.findElement(Utils.xPath
                    (String.format("//li[@class='nav-item' and ancestor::div[contains(@class, 'active')]][%d]" +
                            "/descendant::span[@class='title']", i))).getText();
        }
        LOGGER.info(String.format("Selecting category: %s", title));
        utils.clickElement
                (Utils.xPath(String.format("//li[@class='nav-item' and ancestor::div[contains(@class, 'active')]][%d]", i)));
    }
    public void selectCategoryFromMegaMenu(String selector) {
        title = selector;
        LOGGER.info(String.format("Selecting category: %s", selector));
        utils.clickElement(Utils.xPath(String.format("//a[@title='%s']", selector)));
    }

    public void assertCategoryDisplayed() {
        try {
            String result = utils.driver.findElement(Utils.xPath("//li[@class='breadcrumb-item active']")).getText();
            Assertions.assertTrue(title.contains(result), "Category navigated not corresponding to menu option");
        } catch (Exception e) {
            Assertions.fail(String.format("Expected category %s not navigated", title));
        }
    }
}
