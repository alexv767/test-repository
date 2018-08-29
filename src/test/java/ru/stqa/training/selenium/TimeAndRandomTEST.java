package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import java.time.Clock;
import java.time.Instant;
import java.util.Calendar;
import java.util.Random;

public class TimeAndRandomTEST {
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
//
//        driver.findElement(By.name("q")).sendKeys("webdriver");
//        driver.findElement(By.name("q")).click();
//
//        i = 1; // just for breakpoint :)


        int bp;

        int min=1, max = 1999999999;

        // 1) create a java calendar instance
        Calendar calendar = Calendar.getInstance();

        // 2) get a java.util.Date from the calendar instance.
//    this date will represent the current instant, or "now".
        java.util.Date now = calendar.getTime();

        // 3) a java current time (now) instance
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        Clock clock = Clock.systemUTC();
        //String str = (String) clock.instant();
        Instant instant = clock.instant();

        Long instant1 = clock.millis();

        // Инициализируем генератор
        Long sss = System.currentTimeMillis();
        Random rnd = new Random(System.currentTimeMillis());
        // Получаем случайное число в диапазоне от min до max (включительно)
        int number = min + rnd.nextInt(max - min + 1);

        // Функция rnd.nextInt(limit) возвращает число от нуля (включительно) до limit (не включая limit).


        bp = 1;

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
