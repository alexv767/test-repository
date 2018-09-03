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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class myLitecartNewProduct {
    private WebDriver driver;
    private WebDriverWait wait;
    private String browserName, browserVersion;
    private Capabilities caps;

    public void setDatepicker(WebDriver driver, String cssSelector, String date) {
        new WebDriverWait(driver, 30000).until(
                (WebDriver d) -> d.findElement(By.cssSelector(cssSelector)).isDisplayed());
        JavascriptExecutor.class.cast(driver).executeScript(
                String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));

    }

    @Before
    public void start() {
        driver = new ChromeDriver();  // name = "chrome"
        //driver = new InternetExplorerDriver();    // name = "internet explorer"
        //driver = new FirefoxDriver();   // name = "firefox"

        wait = new WebDriverWait(driver, 20);

        // Browser :
        caps = ((RemoteWebDriver) driver).getCapabilities();
        browserName = caps.getBrowserName();
        browserVersion = caps.getVersion();
    }

    @Test
    public void myFirstTest() {
        int bp, i, j, superDuckPresented;
        String headTitleStr, catItemStr;
        WebElement datePick, saveFile, inputName, inputCode, inpGender, inpSold, inpSold2, inpQuantity, inputStatus, btnSave, inpKeyWds;
        WebElement shDescr, fullDescr, headTitle, metaDescr, pPrice, usdPrice, usdPriceTax, eurPrice, eurPriceTax, catItem;
        List<WebElement> catRows, catItems;
        Select inpSelect, pPriceCC;

        driver.get("http://localhost/litecart/admin/");

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("ul.list-vertical a[href $= catalog]")).click();  //  "Catalog"  menu item

        driver.findElement(By.cssSelector("td#content a[href $= edit_product]")).click();   // "Add product" btn/link

        //                                     GENERAL TAB :

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#tab-general")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#tab-general input[name = date_valid_from]")));

        datePick = driver.findElement(By.cssSelector("div#tab-general input[name = date_valid_from]"));

        bp = 1; // just for breakpoint

        // Date From :
        // On my comp : for IE  yyyy-mm-dd ;   for Chrome : дд-мм-гггг;      for FF :  дд-мм-гггг .
        if (browserName.equals("chrome")) datePick.sendKeys("28-12-2018");  //Chrome
        if (browserName.equals("internet explorer")) datePick.sendKeys("2018-12-28");  //IE
        if (browserName.equals("firefox")) datePick.sendKeys("2018-12-28");  //FireFox

        // Date To :
        datePick = driver.findElement(By.cssSelector("div.content input[name = date_valid_to]"));
        if (browserName.equals("chrome")) datePick.sendKeys("28-12-2019");  //Chrome
        if (browserName.equals("internet explorer")) datePick.sendKeys("2019-12-28");  //IE
        if (browserName.equals("firefox")) datePick.sendKeys("2019-12-28");  //FireFox

        // Status - set to Enabled :
        inputStatus = driver.findElement(By.cssSelector("input[value = '1']"));
        inputStatus.click();

        // Name :
        inputName = driver.findElement(By.cssSelector("input[name ^= name]"));
        inputName.sendKeys("Super Duck");

        // Code :
        inputCode = driver.findElement(By.cssSelector("input[name ^= code]"));
        inputCode.sendKeys("777");

        // Gender - set to Female :
        inpGender = driver.findElement(By.cssSelector("input[value = '1-2']"));
        inpGender.click();

        // Quantity :
        inpQuantity = driver.findElement(By.cssSelector("input[name = quantity]"));
        inpQuantity.sendKeys("1");

        // Sold Out Status :
        inpSold = driver.findElement(By.cssSelector("td select[name = sold_out_status_id]"));
        inpSold.sendKeys("Temporary sold out");;

        // Upload Image :
        saveFile = driver.findElement(By.cssSelector("input[type = file]"));

        File resourceDirectory = new File("src/test/resources");
        String absPath = resourceDirectory.getAbsolutePath();
        absPath = absPath + "\\super-duck-small.JPG";
        saveFile.sendKeys(absPath);


        //                                      INFORMATION TAB :

        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.tabs a[href $= tab-information]")));
        driver.findElement(By.cssSelector("div.tabs a[href $= tab-information]")).click();   // "Info" Tab
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#tab-information")));


        // Manufacturer :
        inpSelect = new Select(driver.findElement(By.cssSelector("select[name ^= manufacturer]")));
        inpSelect.selectByIndex(1);;

        // Keywords :
        inpKeyWds = driver.findElement(By.cssSelector("input[name = keywords]"));
        inpKeyWds.sendKeys("some, key, word");

        // Short Description :
        shDescr = driver.findElement(By.cssSelector("input[name ^= \"short_description\"]"));
        shDescr.sendKeys("This is a short description.");

        // Description :
        fullDescr = driver.findElement(By.cssSelector("div.trumbowyg-editor"));
        fullDescr.click();
        fullDescr.sendKeys("This is a full description.");
        fullDescr.sendKeys(Keys.ENTER);
        fullDescr.sendKeys("This is a full description second row.");

        // Head Title :
        headTitle = driver.findElement(By.cssSelector("input[name ^= head_title]"));
        headTitleStr = "SUPER DUCK";
        headTitle.sendKeys("SUPER DUCK");

        // Meta Description :
        metaDescr = driver.findElement(By.cssSelector("input[name ^= meta_description]"));
        metaDescr.sendKeys("This is a meta description.");


        //                                         PRICES TAB :

        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.tabs a[href $= tab-prices]")));
        driver.findElement(By.cssSelector("div.tabs a[href $= tab-prices]")).click();   // "Prices" Tab
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#tab-prices")));

        // Purchase Price and Currency :
        pPrice = driver.findElement(By.cssSelector("input[name ^= purchase_price]"));
        pPrice.sendKeys("110");

        pPriceCC = new Select(driver.findElement(By.cssSelector("select[name ^= purchase_price_currency_code]")));
        pPriceCC.selectByValue("EUR");

        // Price USD and Price Incl. Tax :
        usdPrice = driver.findElement(By.cssSelector("input[name = \"prices[USD]\"]"));
        usdPrice.sendKeys("120");

//        usdPriceTax = driver.findElement(By.cssSelector("input[name = \"gross_prices[USD]\"]"));
//        usdPriceTax.sendKeys("121");

        // Price EUR and Price Incl. Tax :
        eurPrice = driver.findElement(By.cssSelector("input[name = \"prices[EUR]\"]"));
        eurPrice.sendKeys("100");


        //              SAVE :

        btnSave = driver.findElement(By.cssSelector("button[name = save]"));
        btnSave.click();

        driver.findElement(By.cssSelector("ul.list-vertical a[href $= catalog]")).click();  //  "Catalog"  menu item

        //              CHECK "SUPER DUCK" in Catalogue :

        catRows = driver.findElements(By.cssSelector("tr.row"));
        superDuckPresented = 0;
        for (WebElement catRow : catRows){
            catItems = catRow.findElements(By.cssSelector("td a"));
            if (catItems.size() > 0) {
                String catItemLinkText = catItems.get(0).getText();
                if (catItemLinkText.equalsIgnoreCase(headTitleStr)) {      // first link text = "SUPER DUCK" ?
                    superDuckPresented = 1;
                    break;
                }
            }
        }

        assertTrue(superDuckPresented == 1);


        bp = 1; // just for breakpoint :)



    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
