/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sojinfotech.keyworddriven.excecutionEngine;

import com.sojinfotech.keyworddriven.Utility.ExcelUtils;
import com.sojinfotech.keyworddriven.Utility.LocatorFactory;
import com.sojinfotech.keyworddriven.config.ActionKeywords;
import com.sojinfotech.keyworddriven.config.Constants;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import org.openqa.selenium.By;

/**
 *
 * @author admin
 */
public class DriverScript {

        public static Properties OR;
        public static ActionKeywords actionKeywords;
        public static String sActionKeyword;
        public static String locatorType;
        public static Method method[];
        public static int iTestStep;
        public static int iTestLastStep;
        public static String sTestCaseID;
        public static String sRunMode;
        public static String locatorValue;

        public static boolean bResult;

        public DriverScript() throws NoSuchMethodException, SecurityException {
                actionKeywords = new ActionKeywords();
                method = actionKeywords.getClass().getMethods();
        }

        //The main script is divided in to three parts now
        //First is main[] method, execution starts from here
        public static void main(String[] args) throws Exception {
                ExcelUtils.setExcelFile(Constants.Path_TestData);

                DriverScript startEngine = new DriverScript();
                startEngine.execute_TestCase();
        }

        //Second method, this is to figure out the test cases execution one by one
        //And to figure out test step execution one by one
        private void execute_TestCase() throws Exception {
                int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
                for (int iTestcase = 1; iTestcase < iTotalTestCases; iTestcase++) {
                        //Setting the value of bResult variable to 'true' before starting every test case
                        bResult = true;
                        sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
                        sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode, Constants.Sheet_TestCases);
                        if (sRunMode.toLowerCase().equals("yes")) {
                                iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
                                iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);

                                //Setting the value of bResult variable to 'true' before starting every test step
                                bResult = true;
                                for (; iTestStep < iTestLastStep; iTestStep++) {
                                        sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword, Constants.Sheet_TestSteps);
                                        locatorType = ExcelUtils.getCellData(iTestStep, Constants.COL_LOCATOR, Constants.Sheet_TestSteps);
                                        locatorValue = ExcelUtils.getCellData(iTestStep, Constants.COL_ELEMENT_NAME, Constants.Sheet_TestSteps);

                                        String value = ExcelUtils.getCellData(iTestStep, Constants.COL_INPUT_VALUES, Constants.Sheet_TestSteps);
                                        execute_Actions(value);
                                        //This is the result code, this code will execute after each test step
                                        //The execution flow will go in to this only if the value of bResult is 'false'
                                        if (bResult == false) {
                                                //If 'false' then store the test case result as Fail
                                                ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestcase, Constants.Col_Result, Constants.Sheet_TestCases);
                                                //By this break statement, execution flow will not execute any more test step of the failed test case
                                                break;
                                        }
                                }
                                //This will only execute after the last step of the test case, if value is not 'false' at any step
                                if (bResult == true) {
                                        //Storing the result as Pass in the excel sheet
                                        ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestcase, Constants.Col_Result, Constants.Sheet_TestCases);
                                }
                        }
                }
        }

        private static void execute_Actions(String value) throws Exception {
                for (int i = 0; i < method.length; i++) {
                        if (method[i].getName().equals(sActionKeyword)) {
                                By by = LocatorFactory.getObject(locatorType, locatorValue);
                                method[i].invoke(actionKeywords, by, value);
                                //This code block will execute after every test step
                                if (bResult == true) {
                                        //If the executed test step value is true, Pass the test step in Excel sheet
                                        ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
                                        break;
                                } else {
                                        //If the executed test step value is false, Fail the test step in Excel sheet
                                        ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
                                        //In case of false, the test execution will not reach to last step of closing browser
                                        //So it make sense to close the browser before moving on to next test case
                                        ActionKeywords.closeBrowser("", "");
                                        break;
                                }
                        }
                }
        }
}
