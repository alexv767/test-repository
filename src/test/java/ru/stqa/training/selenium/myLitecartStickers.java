package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class myLitecartStickers {
    private WebDriver driver;
    private WebDriverWait wait;
    private int bp, i, k, n;
    private int j, m;
    private WebElement menuitem1;
    List <WebElement> menuitems2, ducksLinks, content3, duckLabels;
    private WebElement menuitem2;
    private String menutext;

    private WebElement leftPane, ducksLink, content1, content2, content4;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void myFirstTest() {

        driver.get("http://localhost/litecart/");

        //leftPane = driver.findElement(By.className("left"));
        //ducksLink = leftPane.findElement(By.cssSelector("a[href='http://localhost/litecart/en/rubber-ducks-c-1/']"));
        //ducksLink.click();

        bp = 1; // just for breakpoint

        //content2 = driver.findElement(By.className("products"));
        //ducksLinks = content2.findElements(By.className("link"));

        content3 = driver.findElements(By.className("products"));
        n = content3.size();
        for(j=0; j<n; j++) {                // 3 areas:  Most populars;  Campaigns;  Latest products
            content4 = content3.get(j);
            ducksLinks = content4.findElements(By.className("link"));
            m = ducksLinks.size();

            for(i=0; i<m; i++){                     // all Ducks links  in area
                ducksLink = ducksLinks.get(i);
                duckLabels = ducksLink.findElements(By.cssSelector(".sticker"));
                assertTrue(duckLabels.size() == 1);     // the only 1 sticker !

                bp = 1; // just for breakpoint

            }
            bp = 1; // just for breakpoint

        }

        bp = 1; // just for breakpoint



    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
