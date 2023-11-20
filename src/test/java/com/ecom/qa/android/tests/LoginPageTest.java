package com.ecom.qa.android.tests;


import com.pageObject.CartPage;
import com.pageObject.PdpCataloguePage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginPageTest extends BaseTest {

    @Test(priority = 1, description = "Fill information in the form")

    public void fillForm() {
        formPage.setName("Ayushi Garg");
        formPage.setGender("Female");
        formPage.setCountry("Argentina");
        formPage.submitForm();
    }

    @Test(priority = 2)
    public void addToCart() {
        PdpCataloguePage pdpCataloguePage = new PdpCataloguePage(driver);
        String productPageTitle = pdpCataloguePage.verifyProductPageTitle();
        Assert.assertEquals(productPageTitle, "Products");
        pdpCataloguePage.addItemToCartByIndex(0);
        pdpCataloguePage.addItemByName("Jordan Lift Off");
        pdpCataloguePage.addToCart();

    }

    @Test(priority = 3)
    public void cartPage() {
        CartPage cartPage = new CartPage(driver);
        String productName = cartPage.verifyProductName();
        Assert.assertEquals(productName, "Air Jordan 4 Retro");
        double productSum = cartPage.getProductsSum();
        double displayFormattedSum = cartPage.getTotalAmountDisplayed();
        Assert.assertEquals(productSum, displayFormattedSum);
        cartPage.selectCheckbox();
        cartPage.verifyTermsAndServices();
        cartPage.checkout();

    }

}
