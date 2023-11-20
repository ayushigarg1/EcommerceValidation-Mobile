package com.pageObject;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utility.AndroidActions;

public class FormPage extends AndroidActions {
    AndroidDriver driver;

    public FormPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleGender;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement maleGender;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement letsShop;
    @AndroidFindBy(id = "android:id/text1")
    private WebElement countryDropDown;
    @AndroidFindBy(xpath = " //android.widget.TextView[@text='Argentina']")
    private WebElement verifyCountryName;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;


    public void setName(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender) {
        if (gender.contains("Female")) {
            femaleGender.click();
        } else {
            maleGender.click();
        }
    }

    public void setCountry(String countryName) {
        countryDropDown.click();
        scrollToText(countryName);
        verifyCountryName.click();
    }

    public void submitForm() {
        shopButton.click();
    }

}
