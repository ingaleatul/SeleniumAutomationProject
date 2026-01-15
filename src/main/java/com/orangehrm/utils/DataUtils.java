package com.orangehrm.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class DataUtils {
    
    /**
     * Reads test data from a CSV file and returns it as a 2D Object array
     * @param filePath Path to the CSV file
     * @return 2D Object array containing test data
     */
    public static Object[][] readCsvData(String filePath) {
        List<Object[]> testData = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header row
            String line = br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                testData.add(values);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + filePath, e);
        }
        
        return testData.toArray(new Object[0][]);
    }
    
    /**
     * Reads test data from an Excel file and returns it as a 2D Object array
     * @param filePath Path to the Excel file
     * @param sheetName Name of the sheet containing test data
     * @return 2D Object array containing test data
     */
    public static Object[][] readExcelData(String filePath, String sheetName) {
        List<Object[]> testData = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in Excel file: " + filePath);
            }
            
            Iterator<Row> rowIterator = sheet.iterator();
            // Skip header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                List<Object> rowData = new ArrayList<>();
                
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            rowData.add(cell.getBooleanCellValue());
                            break;
                        default:
                            rowData.add("");
                    }
                }
                testData.add(rowData.toArray());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + filePath, e);
        }
        
        return testData.toArray(new Object[0][]);
    }
    
    /**
     * Gets the absolute path of a file in the test resources folder
     * @param relativePath Relative path from the test resources folder
     * @return Absolute path of the file
     */
    public static String getTestResourcePath(String relativePath) {
        return System.getProperty("user.dir") + "/src/test/resources/" + relativePath;
    }
}
