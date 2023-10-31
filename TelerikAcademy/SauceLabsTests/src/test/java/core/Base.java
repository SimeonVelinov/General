package core;

import data.BrowserTypes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import saucedemotests.ProductToCartTests;

import java.time.Duration;

import static data.ConstantsForSwagLabs.*;
import static data.StatusMessages.END_OF_TESTING_MESSAGE;

public class Base {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static double price;

    @BeforeAll
    public static void classSetup() {
        driver = BrowserTypes.WebDriver.startBrowser(BrowserTypes.FIREFOX);
        assert driver != null;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @BeforeEach
    public void testSetup() {
        driver.get(TARGET_URL);
        login_Method("standard_user", "secret_sauce");
    }

    @AfterEach
    protected void resetState() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='reset_sidebar_link']"))));
        driver.findElement(By.xpath("//*[@id='reset_sidebar_link']")).click();
        ProductToCartTests.price = 0;
    }

    @AfterAll
    public static void teardown() {
        driver.close();
        System.out.println(ANSI_YELLOW + END_OF_TESTING_MESSAGE + ANSI_RESET);
    }

    protected static void login_Method(String usr, String pass) {
        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys(usr);
        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys(pass);
        WebElement loginButton = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        loginButton.click();
        WebElement pageLogo = driver.findElement(By.xpath("//div[@class='app_logo']"));
        wait.until(ExpectedConditions.visibilityOf(pageLogo));
        System.out.println(ANSI_GREEN + "Login successful" + ANSI_RESET);
    }
}
