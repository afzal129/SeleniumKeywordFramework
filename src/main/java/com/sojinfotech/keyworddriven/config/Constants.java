/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sojinfotech.keyworddriven.config;

/**
 *
 * @author admin
 */
public class Constants {

        //List of System Variables
        public static final String URL = "http://www.store.demoqa.com";
        public static final String Path_TestData
                = "D:\\Sourcecode\\DemoSourceCode\\KeywordDriven\\src\\main\\java\\com\\sojinfotech\\keyworddriven\\dataEngine\\DataEngine.xlsx";
        public static final String File_TestData = "DataEngine.xlsx";

        //List of Data Sheet Column Numbers
        public static final int Col_TestCaseID = 0;
        public static final int Col_TestScenarioID = 1;
        public static final int COL_LOCATOR = 3;
        public static final int COL_ELEMENT_NAME = 4;

        public static final int Col_ActionKeyword = 5;
        public static int Col_TestStepResult = 7;
        //New entry in Constant variable
        public static final int Col_RunMode = 2;

        //List of Data Engine Excel sheets
        public static final String Sheet_TestSteps = "Test Steps";
        public static final int COL_INPUT_VALUES = 6;

        //New entry in Constant variable
        public static final String Sheet_TestCases = "Test Cases";

        //List of Test Data
        public static final String UserName = "testuser_3";
        public static final String Password = "Test@123";

        public static final String KEYWORD_FAIL = "FAIL";
        public static final String KEYWORD_PASS = "PASS";

        public static final int Col_Result = 3;

}
