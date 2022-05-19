package utils;

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
    public static Sheet sheet = null;
    public static Workbook book;

    public static void openExcelFile(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            book = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getSheet(String sheetName) {
        sheet = book.getSheet(sheetName);
    }

    public static int getRowCount() {
        return sheet.getLastRowNum();
    }

    public static int getColsCount(int rowIndex) {
        return sheet.getRow(rowIndex).getLastCellNum();
    }

    public static String getCellData(int rowIndex, int collIndex) {
        return sheet.getRow(rowIndex).getCell(collIndex).toString();
    }

    public static String[][] getExcelData(String filePath, String sheetName) {
        openExcelFile(filePath);
        getSheet(sheetName);
        String[][] data = new String[getRowCount()][getColsCount(0)];
        for (int i = 1; i <= getRowCount(); i++) {
            for (int j = 0; j < getColsCount(0); j++) {
                try {
                    data[i - 1][j] = getCellData(i, j);
                } catch (NullPointerException e) {
                    data[i - 1][j] = "";
                }
            }
        }
        return data;
    }
}
