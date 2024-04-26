package org.cris.TestComponents;

import org.cris.PageObjectModel.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;

    public WebDriver initalzeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//org//cris//resources/GlobalData.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;

    }

    public LandingPage launchApplication() throws IOException {
        driver = initalzeDriver();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }
}
