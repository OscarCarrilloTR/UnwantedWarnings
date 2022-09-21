package org.testng.example;

import org.openqa.selenium.WebElement;

public class WebDriverFactory {

    private WebDriverFactory() {

    }

    public static Boolean isDisplayed(final WebElement locator)
    {
        try
        {
            return locator.isDisplayed();
        }
        catch (Exception ex)
        {
            return false;
        }

    }
}
