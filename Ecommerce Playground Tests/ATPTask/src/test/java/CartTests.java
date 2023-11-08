import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.main.Utils;
import pages.CartPage;

public class CartTests extends CartPage {
    @BeforeEach
    public void setup(TestInfo testInfo) {
        if (testInfo.getTags().contains("IgnoreSetup"))
            return;
        addItemToCart();
        navigateToPage();
    }

    @AfterAll
    public static void cleanup() {
        Utils.quitDriver();
    }

    @Test
    @Tag("IgnoreSetup")
    @DisplayName("Add a product to cart")
    public void addItemToCartSuccessfully() {
        addItemToCart();

        utils.assertElementNotPresent(Utils.xPath("//span[contains(@class, 'cart-item-total') and text()='0']"));
        utils.assertElementPresent(Utils.xPath("//span[contains(@class, 'cart-item-total') and text()='1']"));
        navigateToPage();
        utils.assertElementPresent(Utils.xPath("//a[contains(@class, 'btn-primary') and text()='Checkout']"));

        removeItemFromCart();
    }

    @Test
    @DisplayName("Remove an existing item from the cart")
    public void removeItemFromCartSuccessfully() {
        removeItemFromCart();

        utils.assertElementNotPresent(Utils.xPath("//span[contains(@class, 'cart-item-total') and text()='1']"));
        utils.assertElementPresent(Utils.xPath("//span[contains(@class, 'cart-item-total') and text()='0']"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "5", "30495", "not a number", "should remove", "0"})
    @DisplayName("Update item quantity for existing item")
    public void updateItemQuantitySuccessfully(String value) {
        updateItemQuantity(value);

        try {
            if (Integer.parseInt(value) > 0) {
                utils.assertElementPresent(Utils.xPath("//div[contains(@class, 'alert-success')]"));
                removeItemFromCart();
            }
        } catch (Exception e) {
            utils.assertElementNotPresent
                    (Utils.xPath("//button[@type='submit' and ancestor::div[@class='input-group-append']]"));
        }
    }

    @Test
    @DisplayName("Proceed to checkout")
    public void proceedToCheckoutWithItemSuccessfully() {
        utils.waitForElementClickable(Utils.xPath("//a[contains(@class, 'btn-primary') and text()='Checkout']"));
        utils.clickElement(Utils.xPath("//a[contains(@class, 'btn-primary') and text()='Checkout']"));

        utils.assertElementPresent(Utils.xPath("//li[@class='breadcrumb-item active' and text()='Checkout']"));
    }
}