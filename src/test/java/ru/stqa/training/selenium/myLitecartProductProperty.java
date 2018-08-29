package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class myLitecartProductProperty {
    private WebDriver driver;
    private WebDriverWait wait;
    private int IE = 0;
    private String browserName, browserVersion;
    private Capabilities caps;

    @Before
    public void start() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();

        //make sure have correct import statements - I had to add these
        //WebDriver driver = new FirefoxDriver();
        caps = ((RemoteWebDriver) driver).getCapabilities();
        browserName = caps.getBrowserName();
        browserVersion = caps.getVersion();
        //System.out.println(browserName+" "+browserVersion);

        wait = new WebDriverWait(driver, 20);

    }

    @Test
    public void myFirstTest() {
        List <WebElement> ducksLinks, duckLabels, content1;
        WebElement ducksLink, content4, duckMain, regularPriceMain, campaignPriceMain, prodPageRegPrice, prodPageCampPrice;
        String duckMainName, regPriceMainText, campPriceMainText, regPriceColor, regPriceLineThrough, campaignPriceColor;
        String regPriceSize, campaignPriceSize, campaignPriceBold, prodPageNameText, prodPageRegPriceText, prodPageCampPriceText;
        String regPriceSizeStr, campaignPriceSizeStr, prodPageRegPriceSizeStr, prodPageCampaignPriceSizeStr;
        String prodPageRegPriceColor, prodPageRegPriceLineThrough, prodPageRegPriceSize, prodPageCampaignPriceColor, prodPageCampaignPriceBold, prodPageCampaignPriceSize;
        String regPriceColor1, campaignPriceColor1, prodPageRegPriceColor1, prodPageCampaignPriceColor1;
        String[] stringColor;
        int bp,intColorR, intColorG, intColorB, index;

        driver.get("http://localhost/litecart/");

        content1 = driver.findElements(By.cssSelector("div#box-campaigns.box a.link"));
        duckMain = content1.get(0);

        duckMainName = duckMain.findElement(By.cssSelector(".name")).getAttribute("textContent");

        // ********************************     MAIN PAGE :       ********************************

        // regular and campaign prices (main page) :
        regularPriceMain = duckMain.findElement(By.cssSelector(".regular-price"));
        regPriceMainText = regularPriceMain.getText();
        campaignPriceMain = duckMain.findElement(By.cssSelector(".campaign-price"));
        campPriceMainText = campaignPriceMain.getText();

        // get prices attributes (main page) :
        regPriceColor1 = regularPriceMain.getCssValue("color");  // gray
        index = regPriceColor1.indexOf("(");
        regPriceColor = regPriceColor1.substring(index+1, regPriceColor1.length()-1);

        regPriceLineThrough = regularPriceMain.getCssValue("text-decoration-line");   // "line-through"
        if (browserName.equals("internet explorer")) regPriceLineThrough = regularPriceMain.getCssValue("text-decoration");  // for IE only

        regPriceSize = regularPriceMain.getCssValue("font-size"); // smaller

        campaignPriceColor1 = campaignPriceMain.getCssValue("color"); // red
        index = campaignPriceColor1.indexOf("(");
        campaignPriceColor = campaignPriceColor1.substring(index+1, campaignPriceColor1.length()-1);

        campaignPriceBold = campaignPriceMain.getCssValue("font-weight"); // bold = "700" or "900" for IE
        campaignPriceSize = campaignPriceMain.getCssValue("font-size"); // larger

        // Д) акционная цена крупнее, чем обычная
        regPriceSizeStr = regPriceSize.substring(0, regPriceSize.length()-2);                   // truncate "px" suffix
        campaignPriceSizeStr = campaignPriceSize.substring(0, campaignPriceSize.length()-2);
        assertTrue(Float.valueOf(regPriceSizeStr) < Float.valueOf(campaignPriceSizeStr));

        // main page Regular price color :       ( need to be designed as a Method a bit later )
        stringColor = regPriceColor.split(", ");  // "102, 102, 102, 1" Gray
        intColorR = Integer.parseInt(stringColor[0]);
        intColorG = Integer.parseInt(stringColor[1]);
        intColorB = Integer.parseInt(stringColor[2]);

        // В) обычная цена зачёркнутая и серая
        ////assertTrue(regPriceLineThrough.equals("line-through"));                                    // "" in IE only
        assertTrue((intColorR == intColorG) && (intColorG == intColorB) && (intColorR > 0) ); // rgba in IE and Chrome but rgb in FF

        // main page Campaign price color :
        stringColor = campaignPriceColor.split(", ");  //    "204, 0, 0, 1"   Red
        intColorR = Integer.parseInt(stringColor[0]);
        intColorG = Integer.parseInt(stringColor[1]);
        intColorB = Integer.parseInt(stringColor[2]);

        // Г) акционная жирная и красная
        assertTrue(Integer.parseInt(campaignPriceBold) >= 700);                                // bold = "700" or "900" for IE
        assertTrue(intColorR > 0 && intColorG == 0 && intColorB == 0);      //    "204, 0, 0, 1"   Red

        bp = 1; // just for breakpoint


        // ******************************** GO TO PRODUCT PAGE NOW :     ********************************


        //duckMain.click(); doesn't work for IE (by me)
        content1 = driver.findElements(By.cssSelector("div#box-campaigns.box div.content"));
        duckMain = content1.get(0).findElement(By.cssSelector("a.link"));
        String lnk = duckMain.getAttribute("href");
        driver.get(lnk);


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
        prodPageRegPriceColor1 = prodPageRegPrice.getCssValue("color");  // gray
        index = campaignPriceColor1.indexOf("(");
        prodPageRegPriceColor = prodPageRegPriceColor1.substring(index+1, prodPageRegPriceColor1.length()-1);

        prodPageRegPriceLineThrough = prodPageRegPrice.getCssValue("text-decoration-line");   // "line-through"
        if (browserName.equals("internet explorer")) prodPageRegPriceLineThrough = prodPageRegPrice.getCssValue("text-decoration");  // for IE only

        prodPageRegPriceSize = prodPageRegPrice.getCssValue("font-size"); // smaller

        prodPageCampaignPriceColor1 = prodPageCampPrice.getCssValue("color"); // red
        index = campaignPriceColor1.indexOf("(");
        prodPageCampaignPriceColor = prodPageCampaignPriceColor1.substring(index+1, prodPageCampaignPriceColor1.length()-1);

        prodPageCampaignPriceBold = prodPageCampPrice.getCssValue("font-weight"); // bold = "700" or "900"  for IE
        prodPageCampaignPriceSize = prodPageCampPrice.getCssValue("font-size"); // larger

        // Д) акционная цена крупнее, чем обычная
        prodPageRegPriceSizeStr = prodPageRegPriceSize.substring(0, prodPageRegPriceSize.length()-2);   // truncate "px" suffix
        prodPageCampaignPriceSizeStr = prodPageCampaignPriceSize.substring(0, prodPageCampaignPriceSize.length()-2);
        assertTrue(Float.valueOf(prodPageRegPriceSizeStr) < Float.valueOf(prodPageCampaignPriceSizeStr));

        // product page Regular price color :
        stringColor = prodPageRegPriceColor.split(", ");  //    "102, 102, 102, 1"    Gray
        intColorR = Integer.parseInt(stringColor[0]);
        intColorG = Integer.parseInt(stringColor[1]);
        intColorB = Integer.parseInt(stringColor[2]);

        // В) обычная цена зачёркнутая и серая
        ////assertTrue(prodPageRegPriceLineThrough.equals("line-through"));
        assertTrue((intColorR == intColorG) && (intColorG == intColorB) && (intColorR > 0) );

        // product page Campaign price color :
        stringColor = prodPageCampaignPriceColor.split(", ");  //    "204, 0, 0, 1"   Red
        intColorR = Integer.parseInt(stringColor[0]);
        intColorG = Integer.parseInt(stringColor[1]);
        intColorB = Integer.parseInt(stringColor[2]);

        // Г) акционная жирная и красная
        //assertTrue(prodPageCampaignPriceBold.equals("700"));                        // bold = "700"
        assertTrue(Integer.parseInt(prodPageCampaignPriceBold) >= 700);     // bold = "700" or "900" for IE
        assertTrue(intColorR > 0 && intColorG == 0 && intColorB == 0);      //    "204, 0, 0, 1"   Red


        bp = 1; // just for breakpoint

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
