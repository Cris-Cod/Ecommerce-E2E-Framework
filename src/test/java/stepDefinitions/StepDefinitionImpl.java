package stepDefinitions;

import io.cucumber.java.en.*;
import org.cris.PageObjectModel.*;
import org.cris.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public  ProductCatalog productCatalog;
    ConfirmationPage confirmationPage;

    @Given("Given I landed on Ecommerce Page")
    public void given_i_landed_on_ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }
    @Given("I landed on Ecommerce Page")
    public void i_landed_on_ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }
    @Given("^Logged in with usrrname (.+) and password (.+)$")
    public void logged_in_with_usrrname_password(String username, String password) {
        productCatalog = landingPage.loginAplication(username, password);
    }
    @When("^I add product (.+) from cart$")
    public void i_add_product_from_cart(String productName) throws InterruptedException {

        List<WebElement> products = productCatalog.getProdcutList();
        productCatalog.addProductTocart(productName);
    }
    @When("^checkout (.+) and submit ther order$")
    public void checkout_submit_ther_order(String productName) {
        CartPage cartPage = productCatalog.goToCardPage();

        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToChexkout();
        checkOutPage.SelectCountry("Bra");
        confirmationPage = checkOutPage.selectBtnPlaceOrder();
    }
    @Then("{string} message is displayed on confirmationPage")
    public void message_is_displayed_on_confirmation_page(String string) {
        String confirmMessage = confirmationPage.VerifyConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed")
    public void message_is_displayed(String string) {
        Assert.assertEquals(string ,landingPage.getErrorMessage());;
        driver.close();
    }


}
