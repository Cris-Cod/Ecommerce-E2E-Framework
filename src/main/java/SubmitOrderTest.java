import org.cris.PageObjectModel.LandingPage;
import org.cris.PageObjectModel.ProductCatalog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {

    //krisx@gmail.com
    // M123456#n

    public static void main(String[] args) {

        String prodcutName = "ADIDAS ORIGINAL";

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        landingPage.loginAplication("krisx@gmail.com", "M123456#n");

        ProductCatalog productCatalog = new ProductCatalog(driver);
        List<WebElement> products = productCatalog.getProdcutList();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

       //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

       WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(prodcutName))
               .findFirst().orElse(null);
       prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        Boolean match = cartProducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(prodcutName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "Bra").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));

        driver.findElement(By.cssSelector(".ta-item:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector(".btnn")).click();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        driver.close();
    }



}
