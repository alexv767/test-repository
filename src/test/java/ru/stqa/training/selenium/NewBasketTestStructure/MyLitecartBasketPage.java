package ru.stqa.training.selenium.NewBasketTestStructure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MyLitecartBasketPage {

    public void removeAll(WebDriver driver, WebDriverWait wait) {
        int tRows, iq;
        WebElement dataTable, btnR;
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
}
