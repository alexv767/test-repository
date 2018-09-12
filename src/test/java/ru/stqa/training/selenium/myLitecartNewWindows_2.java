package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

//import org.openqa.selenium.remote.RemoteWebDriver;
// import static org.openqa.selenium.PageLoadStrategy.EAGER;

public class myLitecartNewWindows_2 {
    private WebDriver driver;
    //private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setPlatform(Platform.WINDOWS);
        capability.setCapability("build", "JUnit - Sample");
        driver = new RemoteWebDriver(
                new URL("https://alexandervelikzh1:67ryqey5kvCdoDdMs9Lz@hub-cloud.browserstack.com/wd/hub"),
                capability
        );
    }

    @Test
    public void testSimple() throws Exception {
        driver.get("http://www.SELENIUM2.RU");
        System.out.println("Page title is: " + driver.getTitle());
        //WebElement element = driver.findElement(By.name("q"));
        //element.sendKeys("BrowserStack");
        //element.submit();

                    try {
                Thread.sleep(1000 * 1);
            } catch (InterruptedException ex) { ; }


    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
