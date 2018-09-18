package ru.stqa.training.selenium.NewBasketTestStructure;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyLitecartApplication {
    private WebDriver driver;
    private WebDriverWait wait;
    private MyLitecartMainPage mainPage;
    private MyLitecartProductPage productPage;
    private MyLitecartBasketPage basketPage;

    public MyLitecartApplication(){
        driver = new ChromeDriver();  // name = "chrome"
        wait = new WebDriverWait(driver, 10);

        MyLitecartMainPage mainPage = new MyLitecartMainPage();  // new main page
        MyLitecartProductPage productPage = new MyLitecartProductPage();  // new product page
        MyLitecartBasketPage basketPage = new MyLitecartBasketPage();  // new basket page

    }

    public void addToBasket(Integer iq){
        mainPage.open(driver, wait);

        //    ADD three products to BASKET :
        for (iq = 1; iq < 4; iq++) {    // 3 products add to basket
            productPage.open(driver, wait); //

            productPage.addToBasket(driver, wait, iq); // product - addToBasket

            mainPage.refresh(driver, wait); // MAIN PAGE - Refresh
        }   // end "For"
    }

    public void clearBasket(){
        basketPage.removeAll(driver, wait); //
    }

    public void endApp(){
        driver.quit();
        driver = null;
    }

}
