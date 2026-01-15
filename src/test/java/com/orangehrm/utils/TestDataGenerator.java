package com.orangehrm.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestDataGenerator {
    
    public static void createTestDataExcel(String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("LoginTestData");
            
            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"username", "password", "expected"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
            
            // Data rows
            Object[][] testData = {
                {"standard_user", "secret_sauce", "true"},
                {"locked_out_user", "secret_sauce", "false"},
                {"problem_user", "secret_sauce", "true"},
                {"performance_glitch_user", "secret_sauce", "true"}
            };
            
            for (int i = 0; i < testData.length; i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < testData[i].length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(testData[i][j].toString());
                }
            }
            
            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error creating Excel test data file", e);
        }
    }
    
    public static void main(String[] args) {
        String excelFilePath = DataUtils.getTestResourcePath("testdata/login_test_data.xlsx");
        createTestDataExcel(excelFilePath);
        System.out.println("Test data Excel file created at: " + excelFilePath);
    }
}
