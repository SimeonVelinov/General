package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String targetPage = "https://ecommerce-playground.lambdatest.io/index.php?route=checkout/checkout";
    public CheckoutPage() {
        super(targetPage);
    }

    public void fillAccountRegistration() {
        utils.driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys();
        utils.driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys();
        utils.driver.findElement(By.xpath("//input[@name='email']")).sendKeys();
        utils.driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys();
        utils.driver.findElement(By.xpath("//input[@name='password']")).sendKeys();
        utils.driver.findElement(By.xpath("//input[@name='confirm']")).sendKeys();
        utils.driver.findElement(By.xpath("//input[@name='address-1']")).sendKeys();
        utils.driver.findElement(By.xpath("//input[@name='city']")).sendKeys();
        utils.driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys();
    }
}
