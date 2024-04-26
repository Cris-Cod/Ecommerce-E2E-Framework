package org.cris.PageObjectModel;

import org.cris.AbstracComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".totalRow button")
    WebElement checkOutElemt;

    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProducts;


    public boolean VerifyProductDisplay(String productName){
        Boolean match = cartProducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckOutPage goToChexkout(){
        checkOutElemt.click();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;
    }


}