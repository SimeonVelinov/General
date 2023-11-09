import org.junit.jupiter.api.AfterAll;
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

        selectCategoryFromMegaMenu(selector);

        assertCategoryDisplayed();
    }

    @ParameterizedTest
    @DisplayName("Main carousel - Displayed items lead to expected page")
    @ValueSource(ints = {0, 1, 2})
    public void mainCarouselNavigateSuccessfully(int i) {

        String target = selectItemFromMainCarousel(i);

        assertItemFromMainCarouselNavigated(target);
    }
}