package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CommonMethods;

public class LoginTest extends CommonMethods {

    @Test
    public void logoIsPresent() {
        // assert that logo is present on the loginPage
        boolean isDisplayed = loginPage.logo.isDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @DataProvider(name = "Credentials")
    public Object[][] data() {

        Object[][] loginData = {
                {"Admin", "abc", "Invalid credentials"},
                {"Admin", "xyz", "Invalid credential"},
                {"Admin", "", "Password cannot be empty"},
                {"", "hum", "Username cannot be empty"}
        };
        return loginData;
    }

    @Test(dataProvider = "Credentials")
    public void invalidCredentials(String userName, String password, String errorMsg) {

        loginPage.usernameBox.sendKeys(userName);
        loginPage.passwordBox.sendKeys(password);
        loginPage.loginBtn.click();
        String msg=loginPage.errorMessage.getText();

        Assert.assertEquals(msg, errorMsg);

    }
}

