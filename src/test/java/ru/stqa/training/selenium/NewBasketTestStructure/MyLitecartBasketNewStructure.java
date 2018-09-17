package ru.stqa.training.selenium.NewBasketTestStructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyLitecartBasketNewStructure {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();  // name = "chrome"
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        int bp;
        Integer iq;

        MyLitecartMainPage mainPage = new MyLitecartMainPage();  // new main page
        MyLitecartProductPage productPage = new MyLitecartProductPage();  // new product page
        MyLitecartBasketPage basketPage = new MyLitecartBasketPage();  // new basket page

        mainPage.open(driver, wait);

        //    ADD three products to BASKET :
        for (iq = 1; iq < 4; iq++) {    // 3 products add to basket

            productPage.open(driver, wait); //

            productPage.addToBasket(driver, wait, iq); // product - addToBasket

            mainPage.refresh(driver, wait); // MAIN PAGE - Refresh

        }   // end "For"

        bp = 1; // for breakpoint only

        //                                      DELETE ALL products from BASKET :
        basketPage.removeAll(driver, wait); //

        bp = 1; // for breakpoint only
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
