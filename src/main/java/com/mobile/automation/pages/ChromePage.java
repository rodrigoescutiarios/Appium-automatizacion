package com.mobile.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ChromePage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.EditText")
    private WebElement searchInput;

    public ChromePage(AppiumDriver driver) {
        super(driver);
    }

    public void searchFor(String text) throws InterruptedException {
        waitForElement(searchInput);
        searchInput.sendKeys(text);
    }
}