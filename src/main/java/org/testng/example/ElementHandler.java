package org.testng.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ElementHandler {

    protected WebDriver driver;
    protected long configWaitTimeout = 0L;
    protected long configVerifyInterval = 0L;
    Logger logger = LoggerFactory.getLogger(ElementHandler.class);

    public ElementHandler(WebDriver driver) {
        this.driver = driver;
        setupConfigValue();
    }

    private void setupConfigValue() {
        configWaitTimeout = 5000;
        configVerifyInterval = 500;
    }

    public String getText(String selector) {
        String text = null;
        try {
            WebElement textElement = findElement(selector);
            if (textElement != null) {
                text = textElement.getText();
            }
        } catch (WebDriverException e) {
			e.printStackTrace();
        }
        return text;
    }

    public WebElement findElement(String selector) {
        return findElementWithDifferentLocators(selector);
    }

    private WebElement findElementWithDifferentLocators(String selector) {
        WebElement element = null;
        String[] locatorWithValue = stringSplit(selector);
        if (locatorWithValue.length > 1) {
            if(locatorWithValue[0].equalsIgnoreCase("xpath")) {
                element = findElement(By.xpath(locatorWithValue[1]), configWaitTimeout);
            } else {
                logger.info("Please select/give the locator");
            }
        } else {
            logger.info("locator/selector input is not in proper format, please pass in this format in loctors.properties file, identifername=loctor=locatorvalue");
        }
        return element;
    }

    private String[] stringSplit(String selector) {
        return selector.split("=", 2);
    }

    public WebElement findElement(By by, long timeout) {
        WebElement ele;
        long startTime = System.currentTimeMillis();
        long stopTime = startTime + timeout;
        while (System.currentTimeMillis() < stopTime) {
            ele = driver.findElement(by);
            if (ele != null) {
                break;
            }
            if (configVerifyInterval > 0L) {
                sleep(Math.min(timeout, configWaitTimeout));
            }
        }
        ele = driver.findElement(by);

        return ele;
    }

    public void sleep(long ms) {
        if (ms > 0L) {
            try {
                String time = TimeUnit.MILLISECONDS.toString();
                logger.info("Sleeping for {} {}.",ms , time);
                TimeUnit.MILLISECONDS.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();  // set interrupt flag
                logger.info("Caught InterruptedException ", e);
            }
        }
    }
}
