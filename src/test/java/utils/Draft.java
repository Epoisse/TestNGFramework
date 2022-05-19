package utils;

import java.util.Arrays;

public class Draft extends CommonMethods {
    public static void main(String[] args) {
      /*  String [][] ecxel=ExcelReader.getExcelData(Constants.TESTDATE_FILEPATH, "EmployeeData");
        for (int i = 0; i < ecxel.length; i++) {
            for (int j = 0; j < ecxel[i].length; j++) {
                System.out.println(ecxel[i][j]);
            }
        }*/

        System.out.println(Arrays.toString(splitString("dlsafj; lfsfdds", "; ")));
    }
}
