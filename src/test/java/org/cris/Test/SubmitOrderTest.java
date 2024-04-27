package org.cris.Test;

import org.apache.commons.io.FileUtils;
import org.cris.PageObjectModel.*;
import org.cris.TestComponents.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    //krisx@gmail.com
    // M123456#n

    //arwon@gmail.com
    // S589624fg$

    public String prodcutName = "ADIDAS ORIGINAL";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {



        ProductCatalog productCatalog = landingPage.loginAplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalog.getProdcutList();
        productCatalog.addProductTocart(input.get("product"));
        CartPage cartPage = productCatalog.goToCardPage();

        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToChexkout();
        checkOutPage.SelectCountry("Bra");
        ConfirmationPage confirmationPage = checkOutPage.selectBtnPlaceOrder();
        String confirmMessage = confirmationPage.VerifyConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest(){
        ProductCatalog productCatalog = landingPage.loginAplication("krisx@gmail.com", "M123456#n");
        OrderPage orderPage = productCatalog.goToOrderPage();
        Boolean macth = orderPage.VerifyOrderDisplay(prodcutName);
        Assert.assertTrue(macth);
    }




    @DataProvider
    public Object[] getData() throws IOException {
       /* HashMap<Object,Object> map = new HashMap();
        map.put("email", "krisx@gmail.com");
        map.put("password", "M123456#n");
        map.put("product", "ADIDAS ORIGINAL");

        HashMap<Object,Object> map1 = new HashMap();
        map1.put("email", "arwon@gmail.com");
        map1.put("password", "S589624fg$");
        map1.put("product", "IPHONE 13 PRO");*/

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//org//cris//data//PurshaseOrder.Json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }

}
