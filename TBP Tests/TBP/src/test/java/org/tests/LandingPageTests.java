package org.tests;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.pages.CookiePolicyPage;
import org.pages.LandingPage;
import org.pages.PrivacyPolicyPage;
import org.utils.Actions;

public class LandingPageTests extends BaseTest {

    LandingPageTests() {
        super(LandingPage.URL);
    }

    @Test
    public void visualTest() {
        webDriver.findElement(By.xpath(LandingPage.COOKIE_ACCEPT)).click();
        Eyes eyes = new Eyes();
        eyes.setApiKey(System.getenv("AppliAPI"));
        eyes.open(webDriver, "Brand Place",
                "Landing Page Visual test",
                new RectangleSize(1500, 1000));
        eyes.check(Target.window());
        eyes.closeAsync();
        webDriver.manage().deleteAllCookies();
    }

    @Test
    @DisplayName("Main logo displayed correctly")
    public void mainLogoDisplayed() {
        Assertions.assertTrue(webDriver.findElement(By.xpath(LandingPage.LOGO)).isDisplayed());
        Assertions.assertTrue(webDriver.findElement(By.xpath(LandingPage.LOGO)).isEnabled());
        Assertions.assertTrue(Actions.isClickable(webDriver.findElement(By.xpath(LandingPage.LOGO))));
    }

    @Test
    @DisplayName("Cookie pop-up is displayed correctly")
    public void cookiePopupDisplayed() {

        Assertions.assertTrue(webDriver.findElement(By.xpath(LandingPage.COOKIE_POPUP)).isDisplayed());
        Assertions.assertTrue(webDriver.findElement(By.xpath(LandingPage.COOKIE_POPUP)).isEnabled());
        Assertions.assertTrue(Actions.isClickable(webDriver.findElement(By.xpath(LandingPage.COOKIE_ACCEPT))));
    }

    @Test
    @DisplayName("Cookie policy link functioning")
    public void cookiePolicyLinkNavigatingToPolicyPageSuccessfully() {
        Assertions.assertTrue(Actions.isClickable(webDriver.findElement(By.xpath(LandingPage.COOKIE_POLICY))));

        webDriver.findElement(By.xpath(LandingPage.COOKIE_POLICY)).click();

        Assertions.assertEquals(webDriver.getCurrentUrl(), CookiePolicyPage.URL, "Unexpected URL navigated");
        Assertions.assertTrue(webDriver.findElement(By.xpath(CookiePolicyPage.TITLE_PATH)).isDisplayed());
        Assertions.assertEquals(webDriver.findElement(By.xpath(CookiePolicyPage.TITLE_PATH)).getText(),
                CookiePolicyPage.TITLE_TEXT, "Cookie policy title not displayed correctly");
    }

    @Test
    @DisplayName("Privacy policy link functioning")
    public void privacyPolicyLinkNavigatingToPolicyPageSuccessfully() {
        Assertions.assertTrue(Actions.isClickable(webDriver.findElement(By.xpath(LandingPage.PRIVACY_POLICY))));

        webDriver.findElement(By.xpath(LandingPage.PRIVACY_POLICY)).click();

        Assertions.assertEquals(webDriver.getCurrentUrl(), PrivacyPolicyPage.URL, "Unexpected URL navigated");
        Assertions.assertTrue(webDriver.findElement(By.xpath(PrivacyPolicyPage.TITLE_PATH)).isDisplayed());
        Assertions.assertEquals(webDriver.findElement(By.xpath(PrivacyPolicyPage.TITLE_PATH)).getText(),
                PrivacyPolicyPage.TITLE_TEXT, "Cookie policy title not displayed correctly");
    }

    @Test
    @DisplayName("Cookie accept button accepts cookies and closes dialog")
    public void cookieAcceptClosesDialogSuccessfully() {
        webDriver.manage().deleteAllCookies();
        Assertions.assertTrue(Actions.isClickable(webDriver.findElement(By.xpath(LandingPage.COOKIE_ACCEPT))));

        webDriver.findElement(By.xpath(LandingPage.COOKIE_ACCEPT)).click();

        Assertions.assertFalse(webDriver.findElement(By.xpath(LandingPage.COOKIE_POPUP)).isDisplayed());
        Assertions.assertFalse(webDriver.findElement(By.xpath(LandingPage.COOKIE_ACCEPT)).isDisplayed());
        Assertions.assertTrue(Actions.isNotClickable(webDriver.findElement(By.xpath(LandingPage.COOKIE_ACCEPT))));

        webDriver.manage().deleteAllCookies();
    }
}
