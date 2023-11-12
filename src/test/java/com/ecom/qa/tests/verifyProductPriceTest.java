package com.ecom.qa.tests;

import com.beust.ah.A;
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

public class verifyProductPriceTest extends BaseTest {
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

    public void addToCart() {
        List<WebElement> addProducts = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart"));
        addProducts.get(0).click();
        addProducts.get(1).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        List<WebElement> productNames = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
        String firstProduct = productNames.get(0).getText();
        Assert.assertEquals(firstProduct, "Air Jordan 4 Retro");
        String secondProduct = productNames.get(1).getText();
        Assert.assertEquals(secondProduct, "Air Jordan 1 Mid SE");

        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        int countProducts = productPrices.size();
        double sum = 0;
        for (int i = 0; i < countProducts; i++) {
            String amount = productPrices.get(i).getText();
            //$160.97, $120.0
            Double individualPrice = getFormattedTotalSum(amount);
            sum = sum + individualPrice;

        }
        //verify total amount
        String totalAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Double displaySum = getFormattedTotalSum(totalAmount);
        Assert.assertEquals(sum, displaySum);
    }


}
