package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class myLitecartMenu {
    private WebDriver driver;
    private WebDriverWait wait;
    private int i, k, n;
    private int j, m;
    private WebElement menuitem1;
    List <WebElement> menuitems2;
    private WebElement menuitem2;
    private String menutext;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void myFirstTest() {

        driver.get("http://localhost/litecart/admin/");

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        WebElement menu1 = driver.findElement(By.className("list-vertical"));

        List <WebElement> menuitems1 = menu1.findElements(By.id("app-"));

        k = menuitems1.size();

        //for(WebElement menuitem1 : menuitems1){
        for(j=0; j<k; j++){


            menu1 = driver.findElement(By.className("list-vertical"));
            menuitems1 = menu1.findElements(By.id("app-"));
            menuitem1 = menuitems1.get(j);

            //menutext = menuitem1.getText();

            menuitem1.click();

            assertTrue(driver.findElements(By.tagName("h1")).size()!= 0);

            //assertTrue(isElementPresent(By.ByTagName("h1")));  driver.findElements(By.xpath(XPath)).size()!= 0;

            //wait.until(titleIs(menutext));

            menu1 = driver.findElement(By.className("list-vertical"));
            menuitems1 = menu1.findElements(By.id("app-"));
            menuitem1 = menuitems1.get(j);

            menuitems2 = menuitem1.findElements(By.tagName("li"));
            n = menuitems2.size();

            i = 1; // just for breakpoint

            for(m=1; m<n; m++){                     // from 2-nd submenu item cause 1-st item is opened already
                menu1 = driver.findElement(By.className("list-vertical"));
                menuitems1 = menu1.findElements(By.id("app-"));
                menuitem1 = menuitems1.get(j);
                menuitems2 = menuitem1.findElements(By.tagName("li"));
                menuitem2 = menuitems2.get(m);

                menuitem2.click();

                assertTrue(driver.findElements(By.tagName("h1")).size()!= 0);

                i = 1; // just for breakpoint
            }


            i = 1; // just for breakpoint
        }

        i = 1; // just for breakpoint
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
