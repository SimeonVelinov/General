package org.pages;

public class LandingPage {
    public static final String url = "https://thebrandplace.com/";
    public static final String logoXpath = "//a/img[@src='https://thebrandplace.com/wp-content/uploads/2022/11/thebrandplace_logo_new-1.png']";
    public static final String cookieXpath = "//div[contains(@class, 'optin')]";
    public static final String cookieAcceptXpath = "//div[contains(@class, 'optin')]/descendant::button[contains(@class, 'cmplz-accept')]";
    public static final String cookiePolicyXpath = "//a[@class='cmplz-link cookie-statement']";
    public static final String privacyPolicyXpath = "//a[@class='cmplz-link privacy-statement']";
}
