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

    By prodcutBy = By.cssSelector(".mb-3");

    public List<WebElement> getProdcutList(){
        waitForElementToAppear(prodcutBy);
        return products;
    }


}