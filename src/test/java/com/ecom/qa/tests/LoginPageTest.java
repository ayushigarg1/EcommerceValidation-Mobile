package com.ecom.qa.tests;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LoginPageTest extends BaseTest {

    @BeforeMethod

    public void fillForm() {
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ayushi Garg");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

    }

    @Test

    public void verifyPdpPage() throws InterruptedException {
        Thread.sleep(3000);
        String verifyProductTest = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();
        Assert.assertEquals(verifyProductTest, "Products");
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).isDisplayed();
        String productIsVisible = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan Lift Off\"));")).getText();
        Assert.assertEquals(productIsVisible, "Jordan Lift Off");

    }

    @Test

    public void addToCart() throws InterruptedException {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan Lift Off\"));")).getText();
        List<WebElement> productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
        int count = productCount.size();
        for (int i = 0; i < count; i++) {

            String getProduct = productCount.get(i).getText();
            if (getProduct.equalsIgnoreCase("Jordan Lift Off")) {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

        Thread.sleep(3000);
        String verifyCartProduct = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(verifyCartProduct, "Jordan Lift Off");
        //select checkbox
        driver.findElement(By.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

    }


}
