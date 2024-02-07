package org.tests;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.pages.LandingPage;

public class CreBraPageTests extends BaseTest {

    CreBraPageTests() {
        super("https://thebrandplace.com/creative-branding/");
    }

    @Test
    public void visualTest() {
        LandingPage.acceptCookies();
        Eyes eyes = new Eyes();
        eyes.setConfiguration(eyeConfig);
        eyes.open(webDriver, "Brand Place",
                "Creative Branding Visual test",
                new RectangleSize(1500, 1000));
        eyes.check(Target.window());
        eyes.closeAsync();
        webDriver.manage().deleteAllCookies();
    }

    @Test
    public void test() {
    }
}