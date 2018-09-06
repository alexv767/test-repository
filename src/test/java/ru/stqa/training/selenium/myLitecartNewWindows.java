package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

//import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.*;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
// import static org.openqa.selenium.PageLoadStrategy.EAGER;

public class myLitecartNewWindows {
    private WebDriver driver;
    private WebDriverWait wait;
    private String browserName, browserVersion;

    @Before
    public void start() {
        //driver = new ChromeDriver();
        ////driver = new InternetExplorerDriver();   // the only 1 window handler !!!
        //driver = new FirefoxDriver();

        FirefoxOptions options = new FirefoxOptions().setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new FirefoxDriver(options);

////        InternetExplorerOptions optionsIE = new InternetExplorerOptions().setPageLoadStrategy(PageLoadStrategy.EAGER);
////        driver = new InternetExplorerDriver(optionsIE);

          // EAGER, NONE, NORMAL (EAGER doesn't work ?)
//        ChromeOptions optionsChr = new ChromeOptions().setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        driver = new ChromeDriver(optionsChr);

        wait = new WebDriverWait(driver, 10);

        // driver.manage().window().maximize();

        // Browser :
//        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
//        browserName = caps.getBrowserName();
//        browserVersion = caps.getVersion();

    }

    @Test
    public void myFirstTest() {
        String handleMain, handleNew="";
        List <WebElement> linksExt;
        WebElement linkArg;
        int bp, i, j;
        Set<String> handlesAll;

        driver.get("http://localhost/litecart/admin/");

        driver.findElement(By.name("username")).sendKeys("admin");      //LOGIN
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);

        // *********************************    COUNTRIES Page :        *********************************

        wait.until(presenceOfElementLocated(By.cssSelector("a[href *= countries]")));
        driver.findElement(By.cssSelector("div#box-apps-menu-wrapper a[href $= \"doc=countries\"]")).click();     // Countries

        wait.until(presenceOfElementLocated(By.cssSelector("a[href $= AR]")));
        linkArg = driver.findElement(By.cssSelector("table.dataTable tr.row a[href $= AR][title = Edit]"));
        linkArg.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h1[style ^= \"margin-top\"]"), "Edit Country"));
        linksExt = driver.findElements(By.cssSelector( "i[class = \"fa fa-external-link\"]"));
        int num = linksExt.size();

        handleMain = driver.getWindowHandle(); // current window
        handleNew = handleMain;

        for (i=0; i < num; i++) {

            handlesAll = driver.getWindowHandles();   // ALL handles
            int bef = handlesAll.size();
            int aft = 0;

            linksExt = driver.findElements(By.cssSelector( "i[class = \"fa fa-external-link\"]"));
            linksExt.get(i).click();

            //handleNew = wait.until(anyWindowOtherThan(handlesAll));

            do {
                handlesAll = driver.getWindowHandles();   //  wait new window
                aft = handlesAll.size();
            }while (aft <= bef);

            for ( String s : handlesAll ){    // cause only 2 handles exist. else method is required.
                if ( ! (handleMain.equals(s)) ) {
                    handleNew = s;
                    break;
                }
            }

            // for visualisation only :
//            try {
//                Thread.sleep(1000 * 1);
//            } catch (InterruptedException ex) { ; }

            driver.switchTo().window(handleNew);
            driver.close();                         // close new window

            driver.switchTo().window(handleMain);    // go to main window

        }

        bp = 1; // just for breakpoint

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    String anyWindowOtherThan( Set <String> existingWindows){

;
return "";
    }

}
