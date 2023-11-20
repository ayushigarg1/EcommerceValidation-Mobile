package com.ecom.qa.android.tests;

import com.google.common.collect.ImmutableMap;
import com.pageObject.FormPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;

    @BeforeClass
    public void configureAppium() throws MalformedURLException {
        //start server
        service = new AppiumServiceBuilder().withAppiumJS(new File("//Users//ayushi//.nvm//versions//node//v21.1.0//lib//node_modules//appium//build//lib//main.js")).withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("AyushiEmulator");
        options.setApp("//Users//ayushi//Projects//EcommerceValidation//src//main//java//utility//General-Store.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        formPage = new FormPage(driver);
    }

    public Double getFormattedTotalSum(String amount) {
        Double individualPrice = Double.parseDouble(amount.substring(1));
        return individualPrice;

    }

    public void longPressAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
    }

   /* @AfterTest
    public void tearDown() {
        driver.quit();
        service.stop();
    }
*/
}


