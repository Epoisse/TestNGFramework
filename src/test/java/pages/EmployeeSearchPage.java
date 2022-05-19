package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;


public class EmployeeSearchPage extends CommonMethods {

    @FindBy(id="menu_pim_viewPimModule")
    public WebElement pimOption;

    @FindBy(linkText = "Add Employee")
    public WebElement addEmployeeOption;

    @FindBy(id="menu_pim_viewEmployeeList")
    public WebElement empListOption;

    @FindBy(xpath = "(//*[@type='text'])[1]")
    public WebElement nameField;

    @FindBy(id="empsearch_id")
    public WebElement idField;

    @FindBy(id="searchBtn")
    public WebElement searchButton;

    @FindBy(xpath = "//*[@id = 'resultTable']/tbody/tr/td[2]")
    public List<WebElement> rowData;

    @FindBy(xpath = "//*[@id='resultTable']/tbody/tr/td")
    public List<WebElement> tableRows;


    public EmployeeSearchPage(){
        PageFactory.initElements(driver, this);
    }

}
