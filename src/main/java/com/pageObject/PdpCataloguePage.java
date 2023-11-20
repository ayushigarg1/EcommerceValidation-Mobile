package com.pageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utility.AndroidActions;

import java.util.List;

public class PdpCataloguePage extends AndroidActions {
    AndroidDriver driver;

    public PdpCataloguePage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement productPageTitle;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private List<WebElement> productCount;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'ADD TO CART']")
    private List<WebElement> addToCartByIndex;

    @AndroidFindBy(xpath = "com.androidsample.generalstore:id/productName")
    private WebElement addSpecficProduct;


    public String verifyProductPageTitle() {
        return productPageTitle.getText();
    }

    public void addItemToCartByIndex(int index) {
        addToCartByIndex.get(index).click();
    }

    public void addItemByName(String productName) {
        scrollToText(productName);
        int count = productCount.size();
        for (int i = 0; i < count; i++) {

            String getProduct = productCount.get(i).getText();
            if (getProduct.equalsIgnoreCase("Jordan Lift Off")) {
                addToCartByIndex.get(i).click();
            }
        }
    }

    public void addToCart() {
        cartButton.click();
    }


}
