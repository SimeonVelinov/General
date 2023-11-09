package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;


public class HomePage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String targetPage = "https://ecommerce-playground.lambdatest.io/";
    private String title = "";

    public HomePage() {
        super(targetPage);
    }

    public void selectCategoryFromMenu(int i) {
        utils.waitForElementClickable
                (By.xpath("//a[@class='icon-left both text-reset' and //a[@aria-label='Shop by Category']]"));
        utils.driver.findElement
                (By.xpath("//a[@class='icon-left both text-reset' and //a[@aria-label='Shop by Category']]")).click();
        utils.waitForElementClickable
                (By.xpath(String.format("//li[@class='nav-item' and ancestor::div[contains(@class, 'active')]][%d]", i)));
        utils.waitForElementClickable
                (By.xpath(String.format("//li[@class='nav-item' and ancestor::div[contains(@class, 'active')]][%d]", i)));
        while (title.isEmpty()) {
            title = utils.driver.findElement(By.xpath
                    (String.format("//li[@class='nav-item' and ancestor::div[contains(@class, 'active')]][%d]" +
                            "/descendant::span[@class='title']", i))).getText();
        }
        LOGGER.info(String.format("Selecting category: %s", title));
        utils.driver.findElement
                (By.xpath(String.format("//li[@class='nav-item' and ancestor::div[contains(@class, 'active')]][%d]", i))).click();
    }

    public void selectCategoryFromMegaMenu(String selector) {
        utils.waitForElementClickable(By.xpath("//li[contains(@class, 'mega-menu')]"));
        utils.actions.moveToElement
                (utils.driver.findElement(By.xpath("//li[contains(@class, 'mega-menu')]"))).perform();
        utils.waitForElementClickable(By.xpath(String.format("//a[@title='%s']", selector)));
        title = selector;
        LOGGER.info(String.format("Selecting category: %s", selector));
        utils.driver.findElement(By.xpath(String.format("//a[@title='%s']", selector))).click();
    }

    public String selectItemFromMainCarousel(int i) {
        utils.waitForElementClickable
                (By.xpath
                        ("//div[contains(@class, 'entry-design') and descendant::div[@class='carousel-item active']]"));
        utils.driver.findElement
                (By.xpath(String.format
                        ("//li[@data-slide-to='%d' and ancestor::div[contains(@class, 'entry-design')]]", i))).click();
        utils.waitForElementClickable
                (By.xpath
                        ("//div[contains(@class, 'entry-design') and descendant::div[@class='carousel-item active']]"));
        String target = utils.driver.findElement
                        (By.xpath("//img[contains(@class, 'd-block') and ancestor::div[@class='carousel-item active']]"))
                .getAttribute("alt").toLowerCase();
        utils.driver.findElement
                (By.xpath("//div[contains(@class, 'entry-design') and descendant::div[contains(@class, 'active')]]")).click();
        return target;
    }

    public void assertCategoryDisplayed() {
        try {
            String result = utils.driver.findElement(By.xpath("//li[@class='breadcrumb-item active']")).getText();
            Assertions.assertTrue(title.contains(result), "Category navigated not corresponding to menu option");
        } catch (Exception e) {
            Assertions.fail(String.format("Expected category %s not navigated", title));
        }
    }

    public void assertItemFromMainCarouselNavigated(String target) {
        String result;
        String altResult;
        result = utils.driver.findElement(By.xpath("//a[ancestor::div[@id='entry_216826']]")).getText().toLowerCase();
        altResult = utils.driver.findElement
                (By.xpath("//h1[@class='h3' and ancestor::div[@id='entry_216816']]")).getText().toLowerCase();

        Assertions.assertTrue(target.contains(result) || target.contains(altResult),
                "Product displayed does not correspond to carousel image");
    }
}