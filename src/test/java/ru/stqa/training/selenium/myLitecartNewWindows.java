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
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
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

        // EAGER, NONE, NORMAL (EAGER doesn't work ?)
//        ChromeOptions optionsChr = new ChromeOptions().setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        driver = new ChromeDriver(optionsChr);

       //  IE  DOESN'T work with windows handles correctly :
// //       InternetExplorerOptions optionsIE = new InternetExplorerOptions().setPageLoadStrategy(PageLoadStrategy.NORMAL);
//  //      driver = new InternetExplorerDriver(optionsIE);


        wait = new WebDriverWait(driver, 25);

        driver.manage().window().maximize();

        // Browser :
//        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
//        browserName = caps.getBrowserName();
//        browserVersion = caps.getVersion();

    }

    @Test
    public void myFirstTest() {
        String handleMain, handleNew="", extLink;
        List <WebElement> linksExt;
        WebElement linkArg;
        int bp, i, j, bef, aft;
        Set<String> handlesAll;

        driver.get("http://localhost/litecart/admin/");

        driver.findElement(By.name("username")).sendKeys("admin");      //LOGIN
        driver.findElement(By.name("password")).sendKeys("admin");
        wait.until(elementToBeClickable(By.name("login")));
        driver.findElement(By.name("login")).click();

        // *********************************    COUNTRIES Page :        *********************************

        wait.until(presenceOfElementLocated(By.cssSelector("a[href *= countries]")));
        driver.get(driver.findElement(By.cssSelector("div#box-apps-menu-wrapper a[href $= \"doc=countries\"]")).getAttribute("href"));

        //wait.until(presenceOfElementLocated(By.cssSelector("a[href $= AR]")));
        wait.until(elementToBeClickable(By.cssSelector("table.dataTable tr.row a[href $= AR][title = Edit]")));
        linkArg = driver.findElement(By.cssSelector("a[title = Edit][href $= AR]"));
        WebElement link2 =  linkArg.findElement(By.cssSelector("i.fa"));  // fa fa-pencil
        wait.until(elementToBeClickable(link2));
        //driver.get(linkArg.getAttribute("href"));
        link2.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h1[style ^= \"margin-top\"]"), "Edit Country"));
        linksExt = driver.findElements(By.cssSelector( "a[target='_blank'] i[class='fa fa-external-link']"));
        //linksExt = driver.findElements(By.xpath("//i[@class='fa fa-external-link']/.."));
        int num = linksExt.size();

        handleMain = driver.getWindowHandle(); // current window
        handleNew = handleMain;

        for (i=0; i < num; i++) {       // by all external links

            handlesAll = driver.getWindowHandles();   // ALL windows handles
            bef = handlesAll.size();
            aft = 0;

            //linksExt = driver.findElements(By.xpath("//i[@class='fa fa-external-link']/.."));  // links external
            //((JavascriptExecutor)driver).executeScript("window.open();");

            // open new window by clicking Link :
            linksExt = driver.findElements(By.cssSelector("a[target='_blank'] i[class='fa fa-external-link']"));
            linksExt.get(i).click();

            int kk = 1;
            do {
                handlesAll = driver.getWindowHandles();         // wait for new window !
                aft = handlesAll.size();

                kk++;
                if (kk > 1000) break;

            } while (aft <= bef);

            bp = 1; // just for breakpoint  TESTING IE - driver.getWindowHandles() - resume : DOESN'T WORK.   (IE 32 driver tested in Windows 10 64 bit)
            handlesAll = driver.getWindowHandles();   // opening here new window by manual click doesn't affect number of windows handles in IE only.
            bp = 1; // just for breakpoint

            for (String s : handlesAll) {    // cause only 2 handles exist. else method is required.
                if (!(handleMain.equals(s))) {
                    handleNew = s;
                    break;
                }
            }

            // for visualisation only :
//            try {
//                Thread.sleep(1000 * 1);
//            } catch (InterruptedException ex) { ; }

            driver.switchTo().window(handleNew);

            //wait.until(presenceOfElementLocated(By.cssSelector("html body")));  // check element on page just in case
            // Check text on pages (Change to cycle with string array later) :
            if (i == 0)
                wait.until(textToBePresentInElementLocated(By.cssSelector("html body div h1"), "ISO 3166-1 alpha-2"));  // check element
            if (i == 1)
                wait.until(textToBePresentInElementLocated(By.cssSelector("html body div h1"), "ISO 3166-1 alpha-3"));  // check element
            if (i == 2)
                wait.until(textToBePresentInElementLocated(By.cssSelector("html body div h1"), "Regular expression"));  // check element
            if (i == 3){    // works but too long in Chrome
                wait.until(presenceOfElementLocated(By.cssSelector("#contact-us-button > div > a > span")));  // check element
                //wait.until(textToBePresentInElementLocated(By.cssSelector("#contact-us-button > div > a > span"), "Contact us"));  // check element
            }  // #contact-us-button > div > a > span
            if(i==4)
                wait.until(textToBePresentInElementLocated(By.cssSelector("html body div h1"), "Regular expression"));  // check element

            if(i==5)
                wait.until(textToBePresentInElementLocated(By.cssSelector("html body div h1"), "List of countries and capitals with currency and language"));  // check element
            if(i==6)
                wait.until(textToBePresentInElementLocated(By.cssSelector("html body div h1"), "List of country calling codes"));  // check element

            driver.close();                         // close new window

            driver.switchTo().window(handleMain);    // go to main window

        }

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
