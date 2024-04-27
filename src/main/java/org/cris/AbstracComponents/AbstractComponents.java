package org.cris.AbstracComponents;

import org.cris.PageObjectModel.CartPage;
import org.cris.PageObjectModel.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;
    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[routerlink='/dashboard/cart']")
    WebElement cartHeader;

    @FindBy(css = "button[routerlink='/dashboard/myorders']")
    WebElement btnOrder;

    public void waitForElementToAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementDissappear() throws InterruptedException {
        Thread.sleep(1000);
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        //wait.until(ExpectedConditions.invisibilityOf(element));

    }

    public CartPage goToCardPage(){
       cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrderPage(){
        btnOrder.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }

    public void waitForWebElementToAppear(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

}
