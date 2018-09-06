package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static javax.swing.text.html.CSS.getAttribute;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class myLitecartCountriesAndZones {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        //driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        driver = new FirefoxDriver();

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        String countryText, prevText, zone, zoneName, zoneName2, prevZoneName, prevZoneName2, countryName;
        List <WebElement> content3, theRow, zone2, content5zone, zoneRows, zoneNames2;
        WebElement leftPane, content1, content4, zonesLink, countryLink,zone1, zoneRow, country, zoneTable;
        int bp, i, k, n, j, zonesNumber;

        driver.get("http://localhost/litecart/admin/");

        driver.findElement(By.name("username")).sendKeys("admin");      //LOGIN
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);

        // *********************************    COUNTRIES PART :        *********************************

        wait.until(presenceOfElementLocated(By.cssSelector("a[href *= countries]")));
        driver.get(driver.findElement(By.cssSelector("a[href *= \"countries\"]")).getAttribute("href"));

        bp = 1; // just for breakpoint

        content1 = driver.findElement(By.className("dataTable"));
        content3 = content1.findElements(By.className("row"));
        n = content3.size();

        prevText = "";  // previous country text

        bp = 1; // just for breakpoint

        for (j = 0; j < n; j++) {                //  by Countries
            content4 = content3.get(j);

            countryLink = content4.findElement(By.cssSelector("a"));
            countryText = countryLink.getText();
            assertTrue(countryText.compareTo(prevText) > 0);     // by Alphabet
            prevText = countryText;

            //bp = countryText.compareTo(prevText);

            theRow = content4.findElements(By.cssSelector("td"));
            zone = theRow.get(5).getText();

            if (zone.compareTo("0") > 0) {          // zone string > 0 ?

                //countryLink.click();

                driver.get(countryLink.getAttribute("href"));

                wait.until(presenceOfElementLocated(By.cssSelector("#table-zones")));
                zone1 = driver.findElement(By.cssSelector("#table-zones"));
                zone2 = zone1.findElements(By.cssSelector("tr"));
                k = zone2.size();
                prevZoneName = "";
                for (i = 1; i < k - 1; i++) {           //  exclude 1st and last "tr" rows
                    zoneRow = zone2.get(i);     // zone row
                    content5zone = zoneRow.findElements(By.cssSelector("td"));
                    zoneName = content5zone.get(2).getText();
                    assertTrue(zoneName.compareTo(prevZoneName) > 0);   // by Alphabet
                    prevZoneName = zoneName;

                    bp = 1; // just for breakpoint
                }

                // RESTORE COUNTRIES CONTENT :

                wait.until(presenceOfElementLocated(By.cssSelector("a[href *= countries]")));
                driver.get(driver.findElement(By.cssSelector("a[href *= \"countries\"]")).getAttribute("href"));

                content1 = driver.findElement(By.className("dataTable"));
                content3 = content1.findElements(By.className("row"));            //  Countries content

                bp = 1; // just for breakpoint
            } // end "if" zones presented
        }

        bp = 1; // just for breakpoint

        // ********************************************* ZONES PART :   *********************************

//        leftPane = driver.findElement(By.className("list-vertical"));   // Countries / Zones
//        zonesLink = leftPane.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones']"));
        wait.until(presenceOfElementLocated(By.cssSelector("a[href *= 'geo_zones']")));
        driver.get(driver.findElement(By.cssSelector("a[href *= \"geo_zones\"]")).getAttribute("href"));


//        zonesLink.click();

        content1 = driver.findElement(By.className("dataTable"));
        content3 = content1.findElements(By.className("row"));   // all rows
        n = content3.size();

        for (j = 0; j < n; j++) {                //  by Countries
            content4 = content3.get(j); // country row
            zonesNumber = Integer.parseInt(content4.findElements(By.cssSelector("td")).get(3).getText());  // for debug
            theRow = content4.findElements(By.cssSelector("a"));   // row elements
            country = theRow.get(0);    // country link
            countryName = country.getText();   // for debugging only
            driver.get(country.getAttribute("href"));
//            country.click();

            bp = 1; // just for breakpoint

            wait.until(presenceOfElementLocated(By.cssSelector("#table-zones")));
            zoneTable = driver.findElement(By.cssSelector("#table-zones"));
            //zoneTable = driver.findElement(By.cssSelector("table.dataTable"));
            zoneRows = zoneTable.findElements(By.cssSelector("tr"));   // all rows
            k=zoneRows.size();

            prevZoneName2 = "";
            for(i=1; i < k-1; i++){ // by country zones (exclude header and footer)
                zoneNames2 = zoneRows.get(i).findElements(By.cssSelector("td select option[selected=selected]"));
                zoneName2 = zoneNames2.get(1).getText();

                assertTrue(zoneName2.compareTo(prevZoneName2) > 0);   // zones are by Alphabet
                prevZoneName2 = zoneName2;

                bp = 1; // just for breakpoint
            }

            // RESTORE ZONES CONTENT :

//            leftPane = driver.findElement(By.className("list-vertical"));   // Countries / Zones
//            zonesLink = leftPane.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones']"));
//            zonesLink.click();

            wait.until(presenceOfElementLocated(By.cssSelector("a[href *= 'geo_zones']")));
            driver.get(driver.findElement(By.cssSelector("a[href *= \"geo_zones\"]")).getAttribute("href"));


            content1 = driver.findElement(By.className("dataTable"));
            content3 = content1.findElements(By.className("row"));   // all rows

        } // end "For" by Countries

        bp = 1; // just for breakpoint
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
