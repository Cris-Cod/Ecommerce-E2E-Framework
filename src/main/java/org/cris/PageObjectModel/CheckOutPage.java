package org.cris.PageObjectModel;

import org.cris.AbstracComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckOutPage extends AbstractComponents {

    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement selectCountryList;

    @FindBy(css = ".ta-item:nth-of-type(1)")
    WebElement selectOneCountry;

    @FindBy(css = ".action__submit")
    WebElement btnPlaceOrder;

    By listCountry = By.cssSelector(".ta-item");

    public void SelectCountry(String country){
        Actions a = new Actions(driver);
        a.sendKeys(selectCountryList, country).build().perform();
        waitForElementToAppear(listCountry);
        selectOneCountry.click();
    }

    public ConfirmationPage selectBtnPlaceOrder(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", btnPlaceOrder);
        //btnPlaceOrder.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }

}
