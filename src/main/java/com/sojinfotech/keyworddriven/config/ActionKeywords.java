/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sojinfotech.keyworddriven.config;

import com.sojinfotech.keyworddriven.excecutionEngine.DriverScript;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author admin
 */
public class ActionKeywords {

        public static WebDriver driver;

        public static void openBrowser(String object, String value) {
                try {
                        System.setProperty("webdriver.gecko.driver", "E:\\setup\\Selenium\\geckodriver-v0.21.0-win64\\geckodriver.exe");
                        driver = new FirefoxDriver();
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
                        Thread.sleep(5000);
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
}
