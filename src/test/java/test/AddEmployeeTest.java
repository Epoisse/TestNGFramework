package test;

import org.junit.experimental.theories.Theories;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends CommonMethods {
    // read the configuration file for username and password
    // and add an employee

    @Test
    public void addEmployee() throws InterruptedException {
        loginPage.loginMethod(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        click(dashboardPage.pimOption);
        click(dashboardPage.addEmployeeButton);
        sendText(addEmployeePage.firstNameField, "Teyfur");
        sendText(addEmployeePage.lastNameField, "Trump");
        sendText(addEmployeePage.middleNameField, "Jr");
        // get the employee Id
        String empID = addEmployeePage.empIdLocator.getAttribute("value");

        click(addEmployeePage.saveButton);

        click(employeeSearchPage.pimOption);
        click(employeeSearchPage.empListOption);

        sendText(employeeSearchPage.idField, empID);
        click(employeeSearchPage.searchButton);

        List<WebElement> rowData = employeeSearchPage.rowData;
        for (WebElement row : rowData) {
            String actualId = row.getText();
            Assert.assertEquals(actualId, empID);
        }
    }

    @DataProvider(name = "newEmployee")
    public Object[][] data() {
        return ExcelReader.getExcelData(Constants.TESTDATE_FILEPATH, "EmployeeData");
    }

    @Test(dataProvider = "newEmployee")
    public static void addMultipleEmployee(String firstName, String middleName, String lastName) throws InterruptedException {
        loginPage.loginMethod(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));
        click(dashboardPage.pimOption);
        click(dashboardPage.addEmployeeButton);
        sendText(addEmployeePage.firstNameField, firstName);
        sendText(addEmployeePage.lastNameField, lastName);
        sendText(addEmployeePage.middleNameField, middleName);
        // get the employee ID
        String empID = addEmployeePage.empIdLocator.getAttribute("value");

        click(addEmployeePage.saveButton);

        click(employeeSearchPage.pimOption);
        click(employeeSearchPage.empListOption);

        sendText(employeeSearchPage.idField, empID);
        click(employeeSearchPage.searchButton);

        Map<String, String> rowData = getDataFromEmployeeSearchResult();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(rowData.get("firstName"),firstName);
        softAssert.assertEquals(rowData.get("middleName"),middleName);
        softAssert.assertEquals(rowData.get("lastName"), lastName);
        softAssert.assertAll();
    }
}



