package ru.stqa.training.selenium;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarLog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

public class myLitecartProxy {
    private WebDriver driver;
    private BrowserMobProxy proxy;

    @Before
    public void start() {
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();

        //ChromeOptions options = new ChromeOptions();
        //FirefoxOptions options = new FirefoxOptions();

        // start the proxy
        proxy = new BrowserMobProxyServer();
        proxy.start(0);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        // start the browser up
        driver = new ChromeDriver(capabilities);
        int bp = 1; // just for breakpoint

        //options.setCapability(CapabilityType.LOGGING_PREFS, log);
        //options.setProxy((java.lang.reflect.Proxy) proxy);
        //driver = new ChromeDriver(options);
        //driver = new FirefoxDriver(options);
    }

    @Test
    public void myFirstTest() {
        int bp;
        File file = new File("C:\\Temp\\harlog.txt");


        // create a new HAR with the label "yahoo.com"
        //proxy.newHar("yahoo.com");
        proxy.newHar();

        // open yahoo.com
        //driver.get("http://yahoo.com");
        //driver.get("http://selenium2.ru/");
        driver.navigate().to("http://selenium2.ru/");

        // get the HAR data
        Har har = proxy.getHar();
        //Har har = proxy.endHar();

        bp = 1; // just for breakpoint

        har.getLog().getEntries().forEach(l -> System.out.println(l.getResponse().getStatus() + " #################### " + l.getRequest().getUrl()));

        try{
            har.writeTo(file);
        } catch (Exception e){
            ;
        }


        bp = 1; // just for breakpoint

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
