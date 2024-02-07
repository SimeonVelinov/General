package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utils.Actions;

public class LandingPage {
    public static final String URL = "https://thebrandplace.com/";
    public static final String LOGO = "//a/img[@src='https://thebrandplace.com/wp-content/uploads/2022/11/thebrandplace_logo_new-1.png']";
    public static final String COOKIE_POPUP = "//div[contains(@class, 'optin')]";
    public static final String COOKIE_ACCEPT = "//div[contains(@class, 'optin')]/descendant::button[contains(@class, 'cmplz-accept')]";
    public static final String COOKIE_POLICY = "//a[@class='cmplz-link cookie-statement']";
    public static final String PRIVACY_POLICY = "//a[@class='cmplz-link privacy-statement']";
    public static final String COMPANY_NAME = "//input[@name='mf-company']";
    public static final String CLIENT_NAME = "//input[@name='mf-name']";
    public static final String EMAIL_FIELD = "//input[@name='mf-email']";
    public static final String PHONE_NO = "//input[@name='mf-telephone']";
    public static final String MESSAGE = "//input[@name='mf-textarea']";
    public static final String ACCEPT_TERMS = "//div[@class='mf-checkbox-option']";
    public static final String SEND_REQUEST = "//button[@type='submit']";

    public void acceptCookies(WebDriver webDriver) {
        if (Actions.isClickable(webDriver.findElement(By.xpath(COOKIE_ACCEPT))))
            webDriver.findElement(By.xpath(COOKIE_ACCEPT)).click();
    }

    public void fillInRequestForm(String compName, String clientName, String email, String phone, String message) {
        WebDriver webDriver = Actions.getDriver();
        webDriver.findElement(By.xpath(COMPANY_NAME)).sendKeys(compName);
        webDriver.findElement(By.xpath(CLIENT_NAME)).sendKeys(clientName);
        webDriver.findElement(By.xpath(EMAIL_FIELD)).sendKeys(email);
        webDriver.findElement(By.xpath(PHONE_NO)).sendKeys(phone);
        webDriver.findElement(By.xpath(MESSAGE)).sendKeys(message);
        webDriver.findElement(By.xpath(ACCEPT_TERMS)).click();
        webDriver.findElement(By.xpath(SEND_REQUEST)).click();
    }
}
