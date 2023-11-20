package com.pageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utility.AndroidActions;

import java.util.List;

public class CartPage extends AndroidActions {
    AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private WebElement productName;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement cartTitle;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPrice;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsAndServicesPopUp;
    @AndroidFindBy(id = "android:id/button1")
    private WebElement closeTermsAndServicesPopUp;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement completePurchase;


    public String verifyProductName() {
        return productName.getText();
    }

    public double getProductsSum() {
        int countProducts = productPrice.size();
        double sum = 0;
        for (int i = 0; i < countProducts; i++) {
            String amount = productPrice.get(i).getText();
            //$160.97, $120.0
            Double individualPrice = getFormattedTotalSum(amount);
            sum = sum + individualPrice;

        }
        return sum;
    }

    public Double getTotalAmountDisplayed() {
        return getFormattedTotalSum(totalAmount.getText());
    }

    public void verifyTermsAndServices() {

        termsAndServicesPopUp.isDisplayed();
        longPressAction(termsAndServicesPopUp);
        closeTermsAndServicesPopUp.click();
    }

    public void selectCheckbox() {
        checkBox.click();
    }

    public void checkout() {
        completePurchase.click();
    }


}
