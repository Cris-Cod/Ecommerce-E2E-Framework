package org.cris.PageObjectModel;

import org.cris.AbstracComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement pass;

    @FindBy(id = "login")
    WebElement btnLogin;


    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalog loginAplication(String email, String password){
        userEmail.sendKeys(email);
        pass.sendKeys(password);
        btnLogin.click();
        ProductCatalog productCatalog = new ProductCatalog(driver);
        return productCatalog;
    }

    public String getErrorMessage(){
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();

    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}
