import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.main.Utils;
import pages.HomePage;

public class HomePageTests extends HomePage {
    @AfterAll
    public static void cleanup() {
        Utils.quitDriver();
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16})
    @DisplayName("Shop by category - Categories lead to expected page")
    public void categoriesInCategoryMenuNavigateSuccessfully(int i) {
        expandCategoryMenu();
        utils.waitForElementClickable
                (Utils.xPath(String.format("//li[@class='nav-item' and ancestor::div[contains(@class, 'active')]][%d]", i)));
        selectCategoryFromMenu(i);

        assertCategoryDisplayed();
    }

    @ParameterizedTest
    @DisplayName("Mega Menu - Categories lead to expected page")
    @ValueSource(strings = {"Apple", "HTC", "LG", "Nokia", "Samsung", "Xiomi", "Apple Macbook", "Asus", "HP",
            "Lenovo", "Headphones", "Memory Card", "Mobile cases", "Power bank", "Screenguards", "Smart Watch",
            "Smart band", "Apple Ipad", "Desktop", "Hard disk", "Mouse & Keyboard", "Pen Drive", "Printer",
            "Bluetooth Speaker", "DTH", "Home Audio", "Home Theatre", "SoundBar"})
    public void megaMenuNavigateSuccessfully(String selector) {
        utils.waitForElementClickable(Utils.xPath("//li[contains(@class, 'mega-menu')]"));
        expandMegaMenu();
        utils.waitForElementClickable(Utils.xPath(String.format("//a[@title='%s']", selector)));
        selectCategoryFromMegaMenu(selector);

        assertCategoryDisplayed();
    }

    @ParameterizedTest
    @DisplayName("Main carousel - Displayed items lead to expected page")
    @ValueSource(ints = {0, 1, 2})
    public void mainCarouselNavigateSuccessfully(int i) {
        utils.waitForElementClickable
                (Utils.xPath
                        ("//div[contains(@class, 'entry-design') and descendant::div[@class='carousel-item active']]"));
        utils.clickElement
                (Utils.xPath(String.format
                        ("//li[@data-slide-to='%d' and ancestor::div[contains(@class, 'entry-design')]]", i)));
        utils.waitForElementClickable
                (Utils.xPath
                        ("//div[contains(@class, 'entry-design') and descendant::div[@class='carousel-item active']]"));
        String target = utils.driver.findElement
                        (Utils.xPath("//img[contains(@class, 'd-block') and ancestor::div[@class='carousel-item active']]"))
                .getAttribute("alt").toLowerCase();
        utils.clickElement
                (Utils.xPath("//div[contains(@class, 'entry-design') and descendant::div[contains(@class, 'active')]]"));
        String result;
        String altResult;
        result = utils.driver.findElement(Utils.xPath("//a[ancestor::div[@id='entry_216826']]")).getText().toLowerCase();
        altResult = utils.driver.findElement
                (Utils.xPath("//h1[@class='h3' and ancestor::div[@id='entry_216816']]")).getText().toLowerCase();

        Assertions.assertTrue(target.contains(result) || target.contains(altResult),
                "Product displayed does not correspond to carousel image");
    }
}