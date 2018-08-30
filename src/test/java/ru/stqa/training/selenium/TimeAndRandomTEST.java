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
        driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();

        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void myFirstTest() {
        int bp;
        int min=1, max = 1999999999;

        driver.get("http://www.google.com");

        // 1) create a java calendar instance
        ///Calendar calendar = Calendar.getInstance();

        // 2) get a java.util.Date from the calendar instance.
//    this date will represent the current instant, or "now".
        ///java.util.Date now = calendar.getTime();

        // 3) a java current time (now) instance
        ///java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        ///Clock clock = Clock.systemUTC();
        //String str = (String) clock.instant();
        ///Instant instant = clock.instant();

        ///Long instant1 = clock.millis();

        //int min=1, max = 1999999999;
        // Инициализируем генератор
        Long sss = System.currentTimeMillis();
        Random rnd = new Random(System.currentTimeMillis());
        // Получаем случайное число в диапазоне от min до max (включительно)
        Integer randi = (min + rnd.nextInt(max - min + 1));
        String randStr = randi.toString();
        // Функция rnd.nextInt(limit) возвращает число от нуля (включительно) до limit (не включая limit).


        bp = 1;

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
