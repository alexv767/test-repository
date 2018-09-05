package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class myLitecartBasket {
    private WebDriver driver;
    private WebDriverWait wait;
    private String browserName, browserVersion;
    private Capabilities caps;

    @Before
    public void start() {
        driver = new ChromeDriver();  // name = "chrome"
        //driver = new InternetExplorerDriver();    // name = "internet explorer"
        //driver = new FirefoxDriver();   // name = "firefox"

        wait = new WebDriverWait(driver, 10);

        // Browser :
        caps = ((RemoteWebDriver) driver).getCapabilities();
        browserName = caps.getBrowserName();
        browserVersion = caps.getVersion();
    }

    @Test
    public void myFirstTest() {
        int bp, tRows;
        Integer basketQuantity, iq;
        String quantity, basketQStr;
        WebElement firstProd, basketQuant, dataTable, title, btnR;
        List<WebElement> selSize;
        Select selectItem;

        driver.get("http://localhost/litecart/");  // Open Shop page
        wait.until(presenceOfElementLocated(By.cssSelector("li[class ^= product]")));


        //                                      ADD three products to BASKET :

        for (iq = 1; iq < 4; iq++) {    // 3 products add to basket

            firstProd = driver.findElements(By.cssSelector("li[class ^= product]")).get(0); // 1st product
            firstProd.click();          // open first product

            wait.until(presenceOfElementLocated(By.cssSelector("h1.title")));
            title = driver.findElement(By.cssSelector("div.middle h1[class=title]")); // check TITLE is refreshed

            // Select Size Select :   for Yellow Duck only
            selSize = driver.findElements(By.cssSelector("select[name = \"options[Size]\"]"));
            if (selSize.size() > 0) {          // "size" need to be selected for Yellow Duck only
                selectItem = new Select(selSize.get(0));
                selectItem.selectByValue("Large");
            }

            // Add to cart :
            driver.findElement(By.cssSelector("button[name = add_cart_product]")).click();

            // Wait Quantity added :
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.quantity"), iq.toString()));

            firstProd = driver.findElements(By.cssSelector("li[class ^= product]")).get(0); // 1st product

            driver.findElement(By.cssSelector("img[title=\"My Store\"]")).click();    // refresh products page

            wait.until(ExpectedConditions.stalenessOf(firstProd));
            wait.until(presenceOfElementLocated(By.cssSelector("li[class ^= product]")));

        }   // end "For"


        //                                      DELETE ALL products from BASKET :

        // go to Basket :
        driver.findElement(By.cssSelector("a.link[href $= checkout]")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("table[class = \"dataTable rounded-corners\"]")));

        // get number of rows in Summary table :
        tRows = driver.findElements(By.cssSelector("tbody td.item")).size();

        for (iq = tRows; iq > 0; iq--) {    // N products from basket

            dataTable = driver.findElement(By.cssSelector("table[class = \"dataTable rounded-corners\"]")); // data Table

            // wait btn Remove visibility :
            wait.until(ExpectedConditions.visibilityOf(btnR = driver.findElement(By.cssSelector("button[name=remove_cart_item]"))));
            btnR.click();        // remove product button

            // check table is refreshed :
            if (iq > 1) {
                //wait.until(ExpectedConditions.visibilityOf(dataTable));
                //dataTable.click();
                wait.until(ExpectedConditions.stalenessOf(dataTable));
                wait.until(presenceOfElementLocated(By.cssSelector("table[class = \"dataTable rounded-corners\"]")));
                wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tbody td.item"), iq-1)); // check rows number - just in case
            } else {
                //wait.until(presenceOfElementLocated(By.cssSelector("div#checkout-cart-wrapper em")));
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#checkout-cart-wrapper em"), "There are no items in your cart."));
            }

        }   // end "For"

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
