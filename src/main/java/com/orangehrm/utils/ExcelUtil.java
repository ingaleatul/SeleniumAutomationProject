package com.orangehrm.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static Object[][] getTestData(String sheetName) throws IOException {
        String csvFile = "src/test/resources/" + sheetName + ".csv"; // Using sheetName as the base for the file name
        List<String[]> records = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
        }

        Object[][] data = new Object[records.size()][];
        records.toArray(data);
        return data;
    }
}
