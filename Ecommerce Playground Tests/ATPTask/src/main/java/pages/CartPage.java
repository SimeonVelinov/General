package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CartPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String targetPage = "https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart";

    public CartPage() {
        super(targetPage);
    }

    public void addItemToCart() {
        HomePage homePage = new HomePage();
        utils.waitForElementClickable(By.id("mz-product-listing-image-37213259-0-0"));
        utils.driver.findElement(By.id("mz-product-listing-image-37213259-0-0")).click();
        utils.waitForElementClickable(By.id("entry_216842"));
        utils.driver.findElement(By.id("entry_216842")).click();
        utils.waitForElementClickable
                (By.xpath("//span[contains(@class, 'cart-item-total') and text()='1']"));
    }

    public void removeItemFromCart() {
        LOGGER.info("Removing item from cart");
        utils.driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
        utils.waitForElementPresent(By.xpath("//a[@class='btn btn-primary']"));
        utils.assertElementNotPresent(By.xpath("//button[@class='btn btn-danger']"));
    }

    public void updateItemQuantity(String i) {
        utils.waitForElementClickable(By.xpath("//input[@value='1']"));
        utils.driver.findElement(By.xpath("//input[@value='1']")).click();
        utils.typeValueInField(i, By.xpath("//input[@value='1']"));
        LOGGER.info(String.format("Updating quantity of item in cart; new value: %s", i));
        utils.waitForElementClickable
                (By.xpath("//button[@type='submit' and ancestor::div[@class='input-group-append']]"));
        utils.driver.findElement
                (By.xpath("//button[@type='submit' and ancestor::div[@class='input-group-append']]")).click();
    }

    public void proceedToCheckout() {
        utils.waitForElementClickable(By.xpath("//a[contains(@class, 'btn-primary') and text()='Checkout']"));
        utils.driver.findElement(By.xpath("//a[contains(@class, 'btn-primary') and text()='Checkout']")).click();
    }

    public void assertItemAddedToCart() {
        utils.assertElementNotPresent(By.xpath("//span[contains(@class, 'cart-item-total') and text()='0']"));
        utils.assertElementPresent(By.xpath("//span[contains(@class, 'cart-item-total') and text()='1']"));
        utils.assertElementPresent(By.xpath("//a[contains(@class, 'btn-primary') and text()='Checkout']"));
    }

    public void assertItemQuantityUpdated(String value) {
        try {
            if (Integer.parseInt(value) > 0) {
                utils.assertElementPresent(By.xpath("//div[contains(@class, 'alert-success')]"));
                removeItemFromCart();
            }
        } catch (Exception e) {
            utils.assertElementNotPresent
                    (By.xpath("//button[@type='submit' and ancestor::div[@class='input-group-append']]"));
        }
    }

    public void assertItemRemovedFromCart() {
        utils.assertElementNotPresent(By.xpath("//span[contains(@class, 'cart-item-total') and text()='1']"));
        utils.assertElementPresent(By.xpath("//span[contains(@class, 'cart-item-total') and text()='0']"));
    }

    public void checkoutPageNavigated() {
        utils.assertElementPresent(By.xpath("//li[@class='breadcrumb-item active' and text()='Checkout']"));
    }
}
