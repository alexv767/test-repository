package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstClass {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void myFirstTest() {

        //WebDriver driver = new RemoteWebDriver("http://localhost:9515", DesiredCapabilities.chrome());

        driver.get("http://www.google.com");

        driver.findElement(By.name("q")).sendKeys("webdriver");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
