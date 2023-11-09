import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.main.Utils;
import org.openqa.selenium.By;
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
        navigateToPage();

        assertItemAddedToCart();
        removeItemFromCart();
    }

    @Test
    @DisplayName("Remove an existing item from the cart")
    public void removeItemFromCartSuccessfully() {
        removeItemFromCart();

        assertItemRemovedFromCart();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "5", "30495", "not a number", "should remove", "0"})
    @DisplayName("Update item quantity for existing item")
    public void updateItemQuantitySuccessfully(String value) {
        updateItemQuantity(value);

        assertItemQuantityUpdated(value);
    }

    @Test
    @DisplayName("Proceed to checkout")
    public void proceedToCheckoutWithItemSuccessfully() {
        proceedToCheckout();

        checkoutPageNavigated();
    }
}