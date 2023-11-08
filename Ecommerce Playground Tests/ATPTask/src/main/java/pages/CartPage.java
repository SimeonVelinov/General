package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.main.Utils;

public class CartPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String targetPage = "https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart";

    public CartPage() {
        super(targetPage);
    }

    public void addItemToCart() {
        HomePage homePage = new HomePage();
        utils.waitForElementClickable(Utils.id("mz-product-listing-image-37213259-0-0"));
        utils.clickElement(Utils.id("mz-product-listing-image-37213259-0-0"));
        utils.waitForElementClickable(Utils.id("entry_216842"));
        utils.clickElement(Utils.id("entry_216842"));
        utils.waitForElementClickable(Utils.xPath("//span[contains(@class, 'cart-item-total') and text()='1']"));
    }

    public void removeItemFromCart() {
        LOGGER.info("Removing item from cart");
        utils.clickElement(Utils.xPath("//button[@class='btn btn-danger']"));
        utils.waitForElementPresent(Utils.xPath("//a[@class='btn btn-primary']"));
        utils.assertElementNotPresent(Utils.xPath("//button[@class='btn btn-danger']"));
    }

    public void updateItemQuantity(String i) {
        utils.waitForElementClickable(Utils.xPath("//input[@value='1']"));
        utils.clickElement(Utils.xPath("//input[@value='1']"));
        utils.typeValueInField(i, Utils.xPath("//input[@value='1']"));
        LOGGER.info(String.format("Updating quantity of item in cart; new value: %s", i));
        utils.waitForElementClickable(Utils.xPath("//button[@type='submit' and ancestor::div[@class='input-group-append']]"));
        utils.clickElement(Utils.xPath("//button[@type='submit' and ancestor::div[@class='input-group-append']]"));
    }
}
