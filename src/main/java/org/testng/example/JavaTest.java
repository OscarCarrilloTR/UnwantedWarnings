package org.testng.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class JavaTest {

    WebDriver driver;

    @AfterMethod
    public void after() {
        driver.quit();
    }

    @Test
    public void exampleLogin() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        SoftAssert softas = new SoftAssert();

        String url = "https://www.google.com/";

        LoginPage loginpage = new LoginPage(driver, url);

        boolean clientIDLinkFound = loginpage.isClientIDLinkDisplayed();
        softas.assertTrue(clientIDLinkFound,"Assign Client ID link found");

        if(clientIDLinkFound) {
            // The same assertion
            softas.assertTrue(clientIDLinkFound,"Assign Client ID link found");
        }

        softas.assertAll();

    }
}
