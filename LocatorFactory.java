/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sojinfotech.keyworddriven.Utility;

import org.openqa.selenium.By;

/**
 *
 * @author admin
 */
public class LocatorFactory {

        public static By getObject(String type, String pageObject) {
                type = type.toLowerCase();
                if (type.equals("xpath")) {
                        return By.xpath(pageObject);
                }
                if (type.equals("id")) {
                        return By.id(pageObject);
                }
                if (type.equals("linktext")) {
                        return By.linkText(pageObject);
                }
                if (type.equals("name")) {
                        return By.name(pageObject);
                }
                if (type.equals("tagname")) {
                        return By.tagName(pageObject);
                }

                if (type.equals("classname")) {
                        return By.className(pageObject);
                }

                if (type.equals("cssselector")) {
                        return By.cssSelector(pageObject);
                }
                return null;
        }

}
