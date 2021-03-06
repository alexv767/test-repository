package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class myLitecartProductPropertyTEST {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        //driver = new ChromeDriver();
        driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();

        wait = new WebDriverWait(driver, 20);

/*
    //make sure have correct import statements - I had to add these
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

WebDriver driver = new FirefoxDriver();

Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
String browserName = caps.getBrowserName();
String browserVersion = caps.getVersion();
System.out.println(browserName+" "+browserVersion);


  */
    }

    @Test
    public void myFirstTest() {
        List <WebElement> ducksLinks, duckLabels, content1;
        WebElement ducksLink, content4, duckMain, regularPriceMain, campaignPriceMain, prodPageRegPrice, prodPageCampPrice;
        String duckMainName, regPriceMainText, campPriceMainText, regPriceColor, regPriceLineThrough, campaignPriceColor;
        String regPriceSize, campaignPriceSize, campaignPriceBold, prodPageNameText, prodPageRegPriceText, prodPageCampPriceText;
        String regPriceSizeStr, campaignPriceSizeStr, prodPageRegPriceSizeStr, prodPageCampaignPriceSizeStr;
        String prodPageRegPriceColor, prodPageRegPriceLineThrough, prodPageRegPriceSize, prodPageCampaignPriceColor, prodPageCampaignPriceBold, prodPageCampaignPriceSize;
        String[] stringColor;
        int bp,intColorR, intColorG, intColorB;

        driver.get("http://localhost/litecart/");

        content1 = driver.findElements(By.cssSelector("div#box-campaigns.box div.content"));
        //duckMain = content1.get(0);
        duckMain = content1.get(0).findElement(By.cssSelector("a.link"));
        String lnk = duckMain.getAttribute("href");
        driver.get(lnk);
        //duckMain.click();
        //duckMain.click();

        duckMainName = duckMain.findElement(By.cssSelector(".name")).getAttribute("textContent");

        // ********************************     MAIN PAGE :       ********************************

        // regular and campaign prices (main page) :
        regularPriceMain = duckMain.findElement(By.cssSelector(".regular-price"));
        regPriceMainText = regularPriceMain.getText();
        campaignPriceMain = duckMain.findElement(By.cssSelector(".campaign-price"));
        campPriceMainText = campaignPriceMain.getText();

        // get prices attributes (main page) :
        regPriceColor = regularPriceMain.getCssValue("color");  // gray
        regPriceLineThrough = regularPriceMain.getCssValue("text-decoration-line");   // "line-through"

        regPriceSize = regularPriceMain.getCssValue("font-size"); // smaller

        campaignPriceColor = campaignPriceMain.getCssValue("color"); // red
        campaignPriceBold = campaignPriceMain.getCssValue("font-weight"); // bold = "700"
        campaignPriceSize = campaignPriceMain.getCssValue("font-size"); // larger

        // Д) акционная цена крупнее, чем обычная
        regPriceSizeStr = regPriceSize.substring(0, regPriceSize.length()-2);                           // truncate "px" suffix
        campaignPriceSizeStr = campaignPriceSize.substring(0, campaignPriceSize.length()-2);
        assertTrue(Float.valueOf(regPriceSizeStr) < Float.valueOf(campaignPriceSizeStr));

        // main page Regular price color :       ( need to be designed as a Method a bit later )
        stringColor = regPriceColor.substring(5, regPriceColor.length()-1).split(", ");  //    "102, 102, 102, 1"    Gray
        intColorR = Integer.parseInt(stringColor[0]);
        intColorG = Integer.parseInt(stringColor[1]);
        intColorB = Integer.parseInt(stringColor[2]);

        // В) обычная цена зачёркнутая и серая
        ////assertTrue(regPriceLineThrough.equals("line-through"));                                           // "" in IE only
        assertTrue((intColorR == intColorG) && (intColorG == intColorB) && (intColorR > 0) );   // rgba in IE and Chrome but rgb in FF

        // main page Campaign price color :
        stringColor = campaignPriceColor.substring(5, campaignPriceColor.length()-1).split(", ");  //    "204, 0, 0, 1"   Red
        intColorR = Integer.parseInt(stringColor[0]);
        intColorG = Integer.parseInt(stringColor[1]);
        intColorB = Integer.parseInt(stringColor[2]);

        // Г) акционная жирная и красная
        assertTrue(Integer.parseInt(campaignPriceBold) >= 700);                                // bold = "700"
        assertTrue(intColorR > 0 && intColorG == 0 && intColorB == 0);      //    "204, 0, 0, 1"   Red

        bp = 1; // just for breakpoint


        // ******************************** GO TO PRODUCT PAGE NOW :     ********************************
        //duckMain = driver.findElement(By.cssSelector("div#box-campaigns.box a.link"));
        duckMain = driver.findElement(By.cssSelector("div#box-campaigns.box img.image"));
        //duckMain = content1.get(0);

        duckMain.click();
        //wait.until()
        prodPageNameText = driver.findElement(By.cssSelector("h1.title")).getText();

        // А) на главной странице и на странице товара совпадает текст названия товара ( compare product names )
        assertTrue(prodPageNameText.equals(duckMainName));

        prodPageRegPrice = driver.findElement(By.cssSelector("div.box .regular-price"));
        prodPageRegPriceText = prodPageRegPrice.getText();
        prodPageCampPrice = driver.findElement(By.cssSelector("div.box .campaign-price"));
        prodPageCampPriceText = prodPageCampPrice.getText();

        // Б) на главной странице и на странице товара совпадают цены (обычная и акционная) ( compare regular and campaign prices )
        assertTrue(prodPageRegPriceText.equals(regPriceMainText));
        assertTrue(prodPageCampPriceText.equals(campPriceMainText));

        // get prices attributes (product page) :
        prodPageRegPriceColor = prodPageRegPrice.getCssValue("color");  // gray
        prodPageRegPriceLineThrough = prodPageRegPrice.getCssValue("text-decoration-line");   // "line-through"
        prodPageRegPriceSize = prodPageRegPrice.getCssValue("font-size"); // smaller

        prodPageCampaignPriceColor = prodPageCampPrice.getCssValue("color"); // red
        prodPageCampaignPriceBold = prodPageCampPrice.getCssValue("font-weight"); // bold = "700"
        prodPageCampaignPriceSize = prodPageCampPrice.getCssValue("font-size"); // larger

        // Д) акционная цена крупнее, чем обычная
        prodPageRegPriceSizeStr = prodPageRegPriceSize.substring(0, prodPageRegPriceSize.length()-2);                   // truncate "px" suffix
        prodPageCampaignPriceSizeStr = prodPageCampaignPriceSize.substring(0, prodPageCampaignPriceSize.length()-2);
        assertTrue(Float.valueOf(prodPageRegPriceSizeStr) < Float.valueOf(prodPageCampaignPriceSizeStr));

        // product page Regular price color :
        stringColor = prodPageRegPriceColor.substring(5, prodPageRegPriceColor.length()-1).split(", ");  //    "102, 102, 102, 1"    Gray
        intColorR = Integer.parseInt(stringColor[0]);
        intColorG = Integer.parseInt(stringColor[1]);
        intColorB = Integer.parseInt(stringColor[2]);

        // В) обычная цена зачёркнутая и серая
        ////assertTrue(prodPageRegPriceLineThrough.equals("line-through"));
        assertTrue((intColorR == intColorG) && (intColorG == intColorB) && (intColorR > 0) );

        // product page Campaign price color :
        stringColor = prodPageCampaignPriceColor.substring(5, prodPageCampaignPriceColor.length()-1).split(", ");  //    "204, 0, 0, 1"   Red
        intColorR = Integer.parseInt(stringColor[0]);
        intColorG = Integer.parseInt(stringColor[1]);
        intColorB = Integer.parseInt(stringColor[2]);

        // Г) акционная жирная и красная
        assertTrue(Integer.parseInt(prodPageCampaignPriceBold) >= 700);                        // bold = "700"
        assertTrue(intColorR > 0 && intColorG == 0 && intColorB == 0);      //    "204, 0, 0, 1"   Red


        bp = 1; // just for breakpoint


    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
