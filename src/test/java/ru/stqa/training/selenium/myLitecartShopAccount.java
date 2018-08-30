package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class myLitecartShopAccount {
    private WebDriver driver;
    private WebDriverWait wait;
    private int IE = 0;
    private String browserName, browserVersion;
    private Capabilities caps;

    @Before
    public void start() {
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver = new InternetExplorerDriver();

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        List <WebElement> ducksLinks;
        WebElement content1, submitRtn, firstName, lastName, address1, postCode, city, country, loginBtn, inputCountry;
        WebElement logoutBtn, email, phone, desiredPswd, confirmedPswd, state, countrySel, stateSel, emailInput;
        String linkText, randStr, mailStr;
        Integer randi;
        int bp, min=1, max = 1999999999;

        // OPen Shop page :
        driver.get("http://localhost/litecart/");

        // Random string for email :
        Random rnd = new Random(System.currentTimeMillis());    // Инициализируем Random
        randi = (min + rnd.nextInt(max - min + 1));   // случ. число от min до max (включит.)
        randStr = randi.toString();

        // Go to NEW ACCOUNT page :
        content1 = driver.findElement(By.cssSelector("a[href $= create_account]"));
        linkText = content1.getText();
        content1.click();

        //                              Add data to required fields :
        // Find fields :
        firstName = driver.findElement(By.cssSelector("input[name = firstname]"));
        lastName = driver.findElement(By.cssSelector("input[name = lastname]"));
        address1 = driver.findElement(By.cssSelector("input[name = address1]"));
        postCode = driver.findElement(By.cssSelector("input[name = postcode]"));
        city = driver.findElement(By.cssSelector("input[name = city]"));

        //country = driver.findElement(By.cssSelector("div.content span.selection span [id ^= select2-country_code]"));
        countrySel = driver.findElement(By.cssSelector("span[id ^= select2-country_code]"));
        countrySel.click();

        //inputCountry = driver.findElement(By.cssSelector("span[class ^= select2-container]"));
        inputCountry = driver.findElement(By.cssSelector("input.select2-search__field"));
        inputCountry.sendKeys("United States");
        inputCountry.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.content option[value = OK]")));
        stateSel = driver.findElement(By.cssSelector("select[name = zone_code]"));
        state = driver.findElement(By.cssSelector("option[value = OK]"));
        state.click();

        email = driver.findElement(By.cssSelector("input[name = email]"));
        phone = driver.findElement(By.cssSelector("input[name = phone]"));
        desiredPswd = driver.findElement(By.cssSelector("input[name = password]"));
        confirmedPswd = driver.findElement(By.cssSelector("input[name = confirmed_password]"));

        //  Add data :
        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        address1.sendKeys("Main Street, 10");
        postCode.sendKeys("12345");
        city.sendKeys("Seattle");

        mailStr = "mail_" + randStr + "@mail.us";
        email.sendKeys(mailStr); // random value

        phone.sendKeys("+71234567890");

        desiredPswd.sendKeys("user");
        confirmedPswd.sendKeys("user");

        // 'Create account' button :
        submitRtn = driver.findElement(By.cssSelector("button[type = submit]"));
        submitRtn.click();

        bp = 1; // just for breakpoint

        // LOGOUT :
        logoutBtn = driver.findElement(By.cssSelector("a[href $= logout]"));
        logoutBtn.click();

        bp = 1; // just for breakpoint

        // LOGIN as John Doe :
        emailInput = driver.findElement(By.cssSelector("input[name = email]"));
        emailInput.sendKeys(mailStr);

        emailInput = driver.findElement(By.cssSelector("input[name = password]"));
        emailInput.sendKeys("user");

        loginBtn = driver.findElement(By.cssSelector("button[name = login]"));
        loginBtn.click();

        bp = 1; // just for breakpoint

        // LOGOUT again :
        logoutBtn = driver.findElement(By.cssSelector("a[href $= logout]"));
        logoutBtn.click();

        bp = 1; // just for breakpoint

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
