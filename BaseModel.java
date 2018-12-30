/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sojinfotech.keyworddriven.pageModel;

import com.sojinfotech.keyworddriven.excecutionEngine.DriverScript;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author SAHIL
 */
public class BaseModel {
    
       public static WebDriver driver;

    public static void openBrowser(String object, String value) {
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\SAHIL\\Desktop\\chromedriver.exe");
            driver = new ChromeDriver();

        } catch (Exception e) {
            System.err.println(e);
            DriverScript.bResult = false;
        }
    }

    public static void navigate(By object, String value) {
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(value);
        } catch (Exception e) {
            System.err.println(e);
            DriverScript.bResult = false;
        }
    }

    public static void click(By object, String value) {
        try {
            driver.findElement(object).click();
        } catch (Exception e) {
            System.err.println(e);
            DriverScript.bResult = false;
        }
    }

    public static void inputValues(By object, String value) {
        try {
            driver.findElement(object).sendKeys(value);
        } catch (Exception e) {
            System.err.println(e);
            DriverScript.bResult = false;
        }
    }

    public static void waitFor(String object, String value) throws Exception {
        try {
            Thread.sleep(Integer.parseInt(value));
        } catch (Exception e) {
            System.err.println(e);
            DriverScript.bResult = false;
        }
    }

    public static void closeBrowser(String object, String value) {
        try {
            driver.quit();
        } catch (Exception e) {
            System.err.println(e);
            DriverScript.bResult = false;
        }
    }

    public static void isElementExit(By object, String value) {
        try {
            WebElement element = driver.findElement(object);
            if (element == null || !element.isDisplayed()) {
                DriverScript.bResult = false;
            }
        } catch (Exception e) {
            System.err.println(e);
            DriverScript.bResult = false;
        }
    }

    public static void checkMenuUrl(By object, String value) {
        try {
            driver.findElement(object).click();
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.equals(value)) {
                DriverScript.bResult = false;
            }
        } catch (Exception e) {
            System.err.println(e);
            DriverScript.bResult = false;
        }
    }

    public static void closeDialog(By object, String value) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(value));
          
            wait.until(ExpectedConditions.presenceOfElementLocated(object));
            driver.findElement(object).click();
        }catch (Exception e) {
            System.err.println(e);
            DriverScript.bResult = false;
        }
    }
    
}
