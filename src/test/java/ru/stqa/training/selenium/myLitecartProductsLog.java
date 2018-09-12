package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Level;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class myLitecartProductsLog {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();

        ChromeOptions options = new ChromeOptions();
        //FirefoxOptions options = new FirefoxOptions();

        LoggingPreferences log = new LoggingPreferences();
        log.enable(LogType.BROWSER, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, log);
        driver = new ChromeDriver(options);
        //driver = new FirefoxDriver(options);

        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void myFirstTest() {
        List  <LogEntry> logsBr, logsDr, logsCl;
        List <WebElement> rows, prods;
//        WebElement ;
        int bp;


        driver.get("http://localhost/litecart/admin/");

        driver.findElement(By.name("username")).sendKeys("admin");      //LOGIN
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // Products :

        //driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        rows = driver.findElements(By.cssSelector("tr.row"));   // all rows

        int n = rows.size();

        for ( int j=0; j<n; j++ ){

            // product rows :
            prods = rows.get(j).findElements(By.cssSelector("a[href *= 'doc=edit_product&category_id=1']"));

            if (prods.size() > 0) {
                prods.get(0).click();     // open first link in pair (at row)

                wait.until(presenceOfElementLocated(By.cssSelector("form[method=post]")));

                System.out.println(driver.manage().logs().getAvailableLogTypes());

                //driver.manage().logs().get("browser").filter(Level.ALL);

                logsBr = driver.manage().logs().get("browser").getAll();
                logsDr = driver.manage().logs().get("driver").getAll();
                logsCl = driver.manage().logs().get("client").getAll();

                for (LogEntry l : logsBr) {
                    System.out.println(l);
                }

                bp = 1; // just for breakpoint

                driver.navigate().back();
                wait.until(presenceOfElementLocated(By.cssSelector("tr.row")));
                rows = driver.findElements(By.cssSelector("tr.row"));
            }
        }

        bp = 1; // just for breakpoint

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
