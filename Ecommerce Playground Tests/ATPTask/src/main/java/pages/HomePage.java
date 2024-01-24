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
        utils.waitForElementClickable(
                By.xpath("//div[contains(@class, 'shop-by-category')]"));
        utils.driver.findElement(
                By.xpath("//div[contains(@class, 'shop-by-category')]")).click();
        utils.waitForElementClickable(
                By.xpath("//nav[contains(@class, 'navbar-light')]" +
                        "//ul[@class='navbar-nav vertical']/li[@class='nav-item']" + String.format("[%d]", i)));
        utils.waitForElementClickable(
                By.xpath("//nav[contains(@class, 'navbar-light')]" +
                        "//ul[@class='navbar-nav vertical']/li[@class='nav-item']" + String.format("[%d]", i)));
        while (title.isEmpty()) {
            title = utils.driver.findElement(By.xpath(
                    "//li[@class='nav-item' and ancestor::div[contains(@class, 'active')]]"
                            + String.format("[%d]", i) +
                            "/descendant::span[@class='title']")).getText();
        }
        LOGGER.info(String.format("Selecting category: %s", title));
        utils.driver.findElement(
                By.xpath("//nav[contains(@class, 'navbar-light')]" +
                        "//ul[@class='navbar-nav vertical']/li[@class='nav-item']" + String.format("[%d]", i))).click();
    }

    public void selectCategoryFromMegaMenu(String selector) {
        utils.waitForElementClickable(By.xpath("//li[contains(@class, 'mega-menu')]"));
        utils.actions.moveToElement(
                utils.driver.findElement(By.xpath("//li[contains(@class, 'mega-menu')]/a[@role='button']"))).perform();
        utils.waitForElementClickable(By.xpath("//a[@title=" + String.format("'%s']", selector)));
        title = selector;
        LOGGER.info(String.format("Selecting category: %s", selector));
        utils.driver.findElement(By.xpath("//a[@title=" + String.format("'%s']", selector))).click();
    }

    public String selectItemFromMainCarousel(int i) {
        utils.waitForElementClickable
                (By.xpath(
                        "//div[contains(@class, 'entry-design') and descendant::div[@class='carousel-item active']]"));
        utils.driver.findElement(
                By.xpath(
                        "//li[@data-slide-to="
                                + String.format("'%d'", i)
                                + "and ancestor::div[contains(@class, 'entry-design')]]")).click();
        utils.waitForElementClickable(
                By.xpath(
                        "//div[contains(@class, 'entry-design') and descendant::div[@class='carousel-item active']]"));
        String target = utils.driver.findElement(
                        By.xpath("//img[contains(@class, 'd-block') and ancestor::div[@class='carousel-item active']]"))
                .getAttribute("alt").toLowerCase();
        utils.driver.findElement(
                By.xpath("//div[contains(@class, 'entry-design') and descendant::div[contains(@class, 'active')]]")).click();
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
        result = utils.driver.findElement(
                By.xpath("//div[contains(@class, 'content-extra')]//a")).getText().toLowerCase();
        altResult = utils.driver.findElement(
                By.xpath("//div[contains(@class, 'content-title')]/h1[@class='h3']")).getText().toLowerCase();

        Assertions.assertTrue(target.contains(result) || target.contains(altResult),
                "Product displayed does not correspond to carousel image");
    }
}