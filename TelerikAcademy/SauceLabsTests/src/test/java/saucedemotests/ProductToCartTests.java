package saucedemotests;

import core.Base;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class ProductToCartTests extends Base {

    private static final List<String> items = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt");

    @Test
    public void item_Added_to_Cart_When_Add_to_CartButton() {
        for (String item : items) {
            find_Item_to_Add(item);
        }
        WebElement cart = driver.findElement(By.xpath("//div[@id='shopping_cart_container']"));
        Assertions.assertEquals(items.size(), Integer.parseInt(cart.getText()));
        cart.click();
        var itemNames = driver.findElements(By.className("inventory_item_name"));
        for (int i = 0; i < itemNames.size(); i++) {
            Assertions.assertTrue(itemNames.get(i).getText().contains(items.get(i)), "Incorrect item added to shopping cart");
            System.out.printf("Item %s successfully added to cart\n", items.get(i));
        }
    }

    @Test
    public void order_Form_Displayed_When_Checkout() {
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();
        driver.findElement(By.xpath("//button[@data-test='checkout']")).click();
        Assertions.assertTrue(driver.findElement(By.id("checkout_info_container")).isDisplayed(), "Checkout page not loaded");
        System.out.println("Order form navigated successfully");
    }

    @Test
    public void orderCompleted_when_addProduct_and_checkout_withConfirm() {
        for (String item : items) {
            find_Item_to_Add(item);
        }
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
        fill_User_Data();
        driver.findElement(By.xpath("//input[@data-test='continue']")).click();
        String temp = driver.findElement(By.className("summary_total_label")).getText().replace("Total: $", "");
        Assertions.assertEquals((double) Math.round((price += price * 0.08) * 100) / 100, Double.parseDouble(temp));
        driver.findElement(By.xpath("//button[@data-test='finish']")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//h2[@class='complete-header']")).getText().contains("Thank you for your order!"));
        Assertions.assertTrue(driver.findElement(By.xpath("//button[@data-test='back-to-products']")).isEnabled());
        driver.findElement(By.xpath("//button[@data-test='back-to-products']")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']")).isEnabled());
        System.out.println("Order completed successfully");
    }

    private void find_Item_to_Add(String itemName) {
        WebElement item = driver.findElement(By.xpath(String.format("//div[@class='inventory_item' and descendant::div[text()='%s']]", itemName)));
        item.findElement(By.className("btn_inventory")).click();
        String temp = item.findElement(By.className("inventory_item_price")).getText().replace("$", "");
        price += Double.parseDouble(temp);
    }

    private void fill_User_Data() {
        WebElement firstName = driver.findElement(By.xpath("//input[@data-test='firstName']"));
        WebElement lastName = driver.findElement(By.xpath("//input[@data-test='lastName']"));
        WebElement postCode = driver.findElement(By.xpath("//input[@data-test='postalCode']"));
        firstName.sendKeys("FirstName");
        lastName.sendKeys("LastName");
        postCode.sendKeys("9000");
    }
}
