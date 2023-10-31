package weare.ui.pagemodels;

import api.controllers.UserController;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.models.UserData;

public class BasePage {


    protected String url;
    public UserActions actions = new UserActions();
    protected WebDriver driver = actions.getDriver();
    public static UserModel userModel;
    public static UserData userData;

    public BasePage(String urlKey, boolean register) {
        if (register) {
            this.url = Utils.getConfigPropertyByKey(urlKey);
            navigateToPage();
        } else nonRegister(urlKey);
    }

    public void nonRegister(String urlKey) {
        userData = new UserData();
        UserController userController = new UserController();
        userModel = userController.createUser(userData.username, userData.password, userData.email, false);
        this.url = Utils.getConfigPropertyByKey(urlKey);
        navigateToPage();
        assertPageNavigated();
        driver.manage().addCookie(userModel.cookie);
    }

    public void navigateToPage() {
        driver.get(url);
    }

    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains(url),
                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + url);
    }
}
