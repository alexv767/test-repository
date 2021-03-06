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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstClass {
    private WebDriver driver;
    private WebDriverWait wait;
    private int i;

    @Before
    public void start() {
        //driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();

        FirefoxOptions options = new FirefoxOptions().setLegacy(true);
        //WebDriver driver = new FirefoxDriver(options);

        //FirefoxOptions options = new FirefoxOptions();
        //options.setBinary(new FirefoxBinary(new File("c:\\Program Files (x86)\\Nightly\\firefox.exe")));
        options.setBinary(new FirefoxBinary(new File("C:\\Users\\A\\AppData\\Local\\Mozilla Firefox45ESR\\firefox.exe")));
        // C:\Users\A\AppData\Local\Mozilla Firefox45ESR
        driver = new FirefoxDriver(options);

        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, false);
        //driver = new FirefoxDriver(caps);

        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void myFirstTest() {

        //WebDriver driver = new RemoteWebDriver("http://localhost:9515", DesiredCapabilities.chrome());

        driver.get("http://www.google.com");

        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("q")).click();

        i = 1; // just for breakpoint :)
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
