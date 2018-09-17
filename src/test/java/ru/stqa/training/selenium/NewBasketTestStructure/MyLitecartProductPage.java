package ru.stqa.training.selenium.NewBasketTestStructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class MyLitecartProductPage {
    public void open(WebDriver driver, WebDriverWait wait){
        WebElement firstProd;

        // Open Product Page :
        firstProd = driver.findElements(By.cssSelector("li[class ^= product]")).get(0); // 1st product

        // OPEN :
        firstProd.click();          // open first product

        wait.until(presenceOfElementLocated(By.cssSelector("h1.title")));

        //title = driver.findElement(By.cssSelector("div.middle h1[class=title]")); // check TITLE is refreshed (for DEBUG only)
    }

    public void addToBasket (WebDriver driver, WebDriverWait wait, Integer iq) {
        Select selectItem;
        List<WebElement> selSize;

        // Select "Size Select" :   for Yellow Duck only
        selSize = driver.findElements(By.cssSelector("select[name = \"options[Size]\"]"));
        if (selSize.size() > 0) {          // "size" need to be selected for Yellow Duck only
            selectItem = new Select(selSize.get(0));
            selectItem.selectByValue("Large");
        }

        // PRODUCT PAGE - Add to cart :
        driver.findElement(By.cssSelector("button[name = add_cart_product]")).click();

        // Wait Quantity added :
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.quantity"), iq.toString()));
        // RETURN : number of products in cart. ???

    }

}
