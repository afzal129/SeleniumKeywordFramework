/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sojinfotech.keyworddriven.Utility;

import com.sojinfotech.keyworddriven.config.Constants;
import com.sojinfotech.keyworddriven.excecutionEngine.DriverScript;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

        private static XSSFWorkbook excelWBook;
        private static XSSFCell cell;
        private static XSSFRow row;

        private static XSSFSheet excelWSheet;

        //This method is to set the File path and to open the Excel file
        //Pass Excel Path and SheetName as Arguments to this method
        public static void setExcelFile(String Path) throws Exception {
                FileInputStream ExcelFile = new FileInputStream(Path);
                excelWBook = new XSSFWorkbook(ExcelFile);
        }

        //This method is to read the test data from the Excel cell
        //In this we are passing Arguments as Row Num, Col Num & Sheet Name
        public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception {
                excelWSheet = excelWBook.getSheet(SheetName);
                try {
                        cell = excelWSheet.getRow(RowNum).getCell(ColNum);
                        String CellData = cell.getStringCellValue();
                        return CellData;
                } catch (Exception e) {
                        return "";
                }
        }

        //This method is to get the row count used of the excel sheet
        public static int getRowCount(String SheetName) {
                excelWSheet = excelWBook.getSheet(SheetName);
                int number = excelWSheet.getLastRowNum() + 1;
                return number;
        }

        //This method is to get the Row number of the test case
        //This methods takes three arguments(Test Case name , Column Number & Sheet name)
        public static int getRowContains(String sTestCaseName, int colNum, String SheetName) throws Exception {
                int i;
                excelWSheet = excelWBook.getSheet(SheetName);
                int rowCount = ExcelUtils.getRowCount(SheetName);
                for (i = 0; i < rowCount; i++) {
                        if (ExcelUtils.getCellData(i, colNum, SheetName).equalsIgnoreCase(sTestCaseName)) {
                                break;
                        }
                }
                return i;
        }

        //This method is to get the count of the test steps of test case
        //This method takes three arguments (Sheet name, Test Case Id & Test case row number)
        public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception {
                for (int i = iTestCaseStart; i <= ExcelUtils.getRowCount(SheetName); i++) {
                        if (!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))) {
                                int number = i;
                                return number;
                        }
                }
                excelWSheet = excelWBook.getSheet(SheetName);
                int number = excelWSheet.getLastRowNum() + 1;
                return number;
        }

        public static void setCellData(String Result, int RowNum, int ColNum, String SheetName) throws Exception {
                try {

                        excelWSheet = excelWBook.getSheet(SheetName);
                        row = excelWSheet.getRow(RowNum);
                        cell = row.getCell(ColNum, row.RETURN_BLANK_AS_NULL);
                        if (cell == null) {
                                cell = row.createCell(ColNum);
                                cell.setCellValue(Result);
                        } else {
                                cell.setCellValue(Result);
                        }
                        try (FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData)) {
                                excelWBook.write(fileOut);
                                //fileOut.flush();
                        }
                        excelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestData));
                } catch (Exception e) {
                        DriverScript.bResult = false;
                }
        }

}
