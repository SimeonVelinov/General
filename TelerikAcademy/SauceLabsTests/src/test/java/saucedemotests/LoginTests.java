package saucedemotests;

import core.Base;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static data.ConstantsForSwagLabs.TARGET_URL;

public class LoginTests extends Base {
    @BeforeEach
    public void testSetup() {
        driver.get(TARGET_URL);
    }

    @Test
    public void login_Successful_When_Valid_Data() {
        login_Method("standard_user", "secret_sauce");
        System.out.println("Logged in");
    }
}
