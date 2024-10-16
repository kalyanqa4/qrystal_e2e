package com.qrystal.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ExcelUtils {
    public static List<List<String>> getDataByTagName (String filePath, String tagName) throws IOException {
        List<List<String>> dataList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);  // Assuming data is in the first sheet
        boolean tagFound = false;
        for (Row row : sheet) {
            Cell tagCell = row.getCell(0);         //assuming tag is in the first column
            if (tagCell != null && tagCell.getStringCellValue().equals(tagName)) {
                tagFound = true;  // We found the tag, extract the entire row
                List<String> rowData = new ArrayList<>();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    rowData.add(cell.getStringCellValue());
                }
                dataList.add(rowData);  // Add the row to the dataList
            }
        }
        workbook.close();
        fis.close();

        if (!tagFound) {
            System.out.println("Tag not found in the sheet.");
        }
        return dataList;
    }
}
