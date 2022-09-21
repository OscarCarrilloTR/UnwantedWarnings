package org.testng.example;

import org.openqa.selenium.WebDriver;

public class LoginPage {

    protected ElementHandler elementhandler;

    public LoginPage(WebDriver driver, String productUrl) throws IllegalArgumentException {
        elementhandler=new ElementHandler(driver);
        try {
            driver.get(productUrl);

        } catch (Exception e) {
            // If automatically logged in, then log off & reload URL
            e.printStackTrace();
        }

    }

    public boolean isClientIDLinkDisplayed() {
        boolean linkDisplayed = false;
        try {
            String selector = "xpath=//a[4]";  // here need to put a valid xpath to see if something was find in the webpage or not
            if (Boolean.TRUE.equals(WebDriverFactory.isDisplayed(elementhandler.findElement(selector)))) {
                String linkText = elementhandler.getText(selector).trim();
                linkDisplayed = linkText.equals("Some Text we need to be equals")
                        || linkText.equals("Another text we need to be equals");
            }
        } catch (Exception exc) {
			exc.printStackTrace();
        }
        return linkDisplayed;
    }
}
