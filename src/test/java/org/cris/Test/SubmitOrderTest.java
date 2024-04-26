package org.cris.Test;

import org.cris.PageObjectModel.*;
import org.cris.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    //krisx@gmail.com
    // M123456#n

    @Test
    public void submitOrder() throws IOException {

        String prodcutName = "ADIDAS ORIGINAL";

        LandingPage landingPage = launchApplication();
        ProductCatalog productCatalog = landingPage.loginAplication("krisx@gmail.com", "M123456#n");

        List<WebElement> products = productCatalog.getProdcutList();
        productCatalog.addProductTocart(prodcutName);
        CartPage cartPage = productCatalog.goToCardPage();

        Boolean match = cartPage.VerifyProductDisplay(prodcutName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToChexkout();
        checkOutPage.SelectCountry("Bra");
        ConfirmationPage confirmationPage = checkOutPage.selectBtnPlaceOrder();
        String confirmMessage = confirmationPage.VerifyConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();
    }




}
