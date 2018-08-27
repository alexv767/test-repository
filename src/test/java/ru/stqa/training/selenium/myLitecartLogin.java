package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class myLitecartLogin {
    private WebDriver driver;
    private WebDriverWait wait;
    private int i;

    @Before
    public void start() {
        //driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        driver = new FirefoxDriver();

        //FirefoxOptions options = new FirefoxOptions().setLegacy(true);
        ////FirefoxOptions options = new FirefoxOptions();
        //WebDriver driver = new FirefoxDriver(options);

        //FirefoxOptions options = new FirefoxOptions();
        //options.setBinary(new FirefoxBinary(new File("c:\\Program Files (x86)\\Nightly\\firefox.exe")));
        //options.setBinary(new FirefoxBinary(new File("C:\\Users\\A\\AppData\\Local\\Mozilla Firefox45ESR\\firefox.exe")));
        ////options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly63\\firefox.exe")));
        // C:\Users\A\AppData\Local\Mozilla Firefox45ESR
        // C:\Program Files\Firefox Nightly63
        ////driver = new FirefoxDriver(options);


        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void myFirstTest() {

        driver.get("http://localhost/litecart/admin/");

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        i = 1; // just for breakpoint :)






    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
