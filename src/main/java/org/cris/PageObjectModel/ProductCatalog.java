package org.cris.PageObjectModel;

import org.cris.AbstracComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponents {

    WebDriver driver;

    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By prodcutBy = By.cssSelector(".mb-3");
    By addTocart =  By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProdcutList(){
        waitForElementToAppear(prodcutBy);
        return products;
    }

    public WebElement getProductsByName(String productName){
        WebElement prod = getProdcutList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst().orElse(null);
        return prod;
    }

    public void addProductTocart(String productName) throws InterruptedException {
        WebElement prod = getProductsByName(productName);
        prod.findElement(addTocart).click();
        waitForElementToAppear(toastMessage);
        waitForElementDissappear();
    }


}