package com.mobile.automation.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class DriverManager {
    private static AppiumDriver driver;
    private static Properties properties;

    public static AppiumDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    private static void initializeDriver() {
        try {
            loadProperties();

            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName(properties.getProperty("platform.name"));
            options.setDeviceName(properties.getProperty("device.name"));
            options.setAutomationName(properties.getProperty("automation.name"));
            options.setAppPackage(properties.getProperty("app.package"));
            options.setAppActivity(properties.getProperty("app.activity"));
            options.setNoReset(true);

            String appiumUrl = properties.getProperty("appium.server.url");
            driver = new AndroidDriver(new URL(appiumUrl), options);

            int implicitWait = Integer.parseInt(properties.getProperty("implicit.wait"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        }
    }

    private static void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static Properties getProperties() {
        if (properties == null) {
            loadProperties();
        }
        return properties;
    }
}

#playwright
