package org.tests;

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
        super(LandingPage.url);
    }

    @Test
    @DisplayName("Main logo displayed correctly")
    public void mainLogoDisplayed() {
        Assertions.assertTrue(webDriver.findElement(By.xpath(LandingPage.logoXpath)).isDisplayed());
        Assertions.assertTrue(webDriver.findElement(By.xpath(LandingPage.logoXpath)).isEnabled());
        Assertions.assertTrue(Actions.isClickable(webDriver.findElement(By.xpath(LandingPage.logoXpath))));
    }

    @Test
    @DisplayName("Cookie pop-up is displayed correctly")
    public void cookiePopupDisplayed() {
        Assertions.assertTrue(webDriver.findElement(By.xpath(LandingPage.cookieXpath)).isDisplayed());
        Assertions.assertTrue(webDriver.findElement(By.xpath(LandingPage.cookieXpath)).isEnabled());
        Assertions.assertTrue(Actions.isClickable(webDriver.findElement(By.xpath(LandingPage.cookieAcceptXpath))));
    }

    @Test
    @DisplayName("Cookie policy link functioning")
    public void cookiePolicyLinkNavigatingToPolicyPageSuccessfully() {
        Assertions.assertTrue(Actions.isClickable(webDriver.findElement(By.xpath(LandingPage.cookiePolicyXpath))));

        webDriver.findElement(By.xpath(LandingPage.cookiePolicyXpath)).click();

        Assertions.assertEquals(webDriver.getCurrentUrl(), CookiePolicyPage.url, "Unexpected URL navigated");
        Assertions.assertTrue(webDriver.findElement(By.xpath(CookiePolicyPage.titleXpath)).isDisplayed());
        Assertions.assertEquals(webDriver.findElement(By.xpath(CookiePolicyPage.titleXpath)).getText(),
                CookiePolicyPage.titleText, "Cookie policy title not displayed correctly");
    }

    @Test
    @DisplayName("Privacy policy link functioning")
    public void privacyPolicyLinkNavigatingToPolicyPageSuccessfully() {
        Assertions.assertTrue(Actions.isClickable(webDriver.findElement(By.xpath(LandingPage.privacyPolicyXpath))));

        webDriver.findElement(By.xpath(LandingPage.privacyPolicyXpath)).click();

        Assertions.assertEquals(webDriver.getCurrentUrl(), PrivacyPolicyPage.url, "Unexpected URL navigated");
        Assertions.assertTrue(webDriver.findElement(By.xpath(PrivacyPolicyPage.titleXpath)).isDisplayed());
        Assertions.assertEquals(webDriver.findElement(By.xpath(PrivacyPolicyPage.titleXpath)).getText(),
                PrivacyPolicyPage.titleText, "Cookie policy title not displayed correctly");
    }

    @Test
    @DisplayName("Cookie accept button accepts cookies and closes dialog")
    public void cookieAcceptClosesDialogSuccessfully() {
        Assertions.assertTrue(Actions.isClickable(webDriver.findElement(By.xpath(LandingPage.cookieAcceptXpath))));

        webDriver.findElement(By.xpath(LandingPage.cookieAcceptXpath)).click();

        Assertions.assertFalse(webDriver.findElement(By.xpath(LandingPage.cookieXpath)).isDisplayed());
        Assertions.assertFalse(webDriver.findElement(By.xpath(LandingPage.cookieAcceptXpath)).isDisplayed());
        Assertions.assertTrue(Actions.isNotClickable(webDriver.findElement(By.xpath(LandingPage.cookieAcceptXpath))));

        webDriver.manage().deleteAllCookies();
    }
}
