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

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class MyLitecartMainPage {

    public void open(WebDriver driver, WebDriverWait wait){
        // Open MAIN PAGE
        driver.get("http://localhost/litecart/");  // Open Shop page  -  MAIN PAGE
        wait.until(presenceOfElementLocated(By.cssSelector("li[class ^= product]")));
    }

    public void refresh(WebDriver driver, WebDriverWait wait) {
        WebElement firstProd;
        firstProd = driver.findElements(By.cssSelector("li[class ^= product]")).get(0); // 1st product

        driver.findElement(By.cssSelector("img[title=\"My Store\"]")).click();    // refresh products page

        wait.until(ExpectedConditions.stalenessOf(firstProd));   // wait for staleness of first product (addet to basket)
        wait.until(presenceOfElementLocated(By.cssSelector("li[class ^= product]")));

    }

}
