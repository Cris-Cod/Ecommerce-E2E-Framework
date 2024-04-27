package org.cris.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.cris.PageObjectModel.CartPage;
import org.cris.PageObjectModel.CheckOutPage;
import org.cris.PageObjectModel.ConfirmationPage;
import org.cris.PageObjectModel.ProductCatalog;
import org.cris.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

    //krisx@gmail.com
    // M123456#n

    @Test(groups = {"ErrorHandling"})
    public void LoginErrorValidation() throws IOException, InterruptedException {

        String prodcutName = "ADIDAS ORIGINAL";


        landingPage.loginAplication("krisx@gmail.com", "M123456#n155");
        Assert.assertEquals("Incorrect email password." ,landingPage.getErrorMessage());;

    }

    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {

        String prodcutName = "ADIDAS ORIGINAL";
        String wrongProdct = "ADIDAS";

        ProductCatalog productCatalog = landingPage.loginAplication("krisx@gmail.com", "M123456#n");

        List<WebElement> products = productCatalog.getProdcutList();
        productCatalog.addProductTocart(prodcutName);
        CartPage cartPage = productCatalog.goToCardPage();

        Boolean match = cartPage.VerifyProductDisplay(wrongProdct);
        Assert.assertFalse(match);
    }




}
