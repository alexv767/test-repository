package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class myLitecartGrid {
    private RemoteWebDriver driver;
    private WebDriverWait wait;
    private int bp;

    @Before
    public void start() throws Exception {
        URL rurl;
        DesiredCapabilities caps;

        //driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        // driver = new FirefoxDriver();

        // WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());

        //driver = new RemoteWebDriver(new URL("http://192.168.1.72:4444/wd/hub"), DesiredCapabilities.firefox());

//        try {
//            rurl = new URL("http://192.168.1.72:4444/wd/hub");
//        } catch (Exception e){
//            return null;
//        }

        rurl = new URL("http://192.168.56.102:4444/wd/hub");
        caps = DesiredCapabilities.firefox();
        caps.setPlatform(Platform.LINUX);
        //caps.setBrowserName("");

        driver = new RemoteWebDriver( rurl, caps);

//URL
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


        //wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void myFirstTest() {

        //driver.get("http://selenium2.ru/");


        bp = 1; // just for breakpoint :)






    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
