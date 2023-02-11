package com.qa.testcases;

import com.qa.base.Base;
import com.qa.pages.AccountPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class LoginTest extends Base {
    LoginPage loginPage;
   public WebDriver driver;
    public LoginTest(){
        super();
    }
    @BeforeMethod
    public void setup(){
        driver=initializeBrowserAndOpenApplicationURL(properties.getProperty("browser"));
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMyAccount();
         loginPage= homePage.clickOnLoginOption();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1,dataProvider = "validCredentialSupplier")
    public void verifyLoginWithValidCredentials(String email,String password){
      AccountPage accountPage= loginPage.login(email,password);
        Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformation());
    }

    @DataProvider(name = "validCredentialSupplier")
    public Object[][] supplyTestData(){
        Object[] [] data = Utilities.getTestDataFromExcel("Login");

        return data;
    }
    @Test(priority = 2)
    public void loginWithInvalidCredentials(){
        loginPage.login(Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
        loginPage.clickOnLoginButton();
        String actualWarningMessage=loginPage.retrieveEmailPasswordNotMatchingWarningMessagetext();
        String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatch");
       Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Actual Warning message is not displayed");
    }
    @Test(priority = 3)
    public void verifyLoginWithoutProvidingCredentials(){
        loginPage.clickOnLoginButton();
        String actualWarningMessage=loginPage.retrieveEmailPasswordNotMatchingWarningMessagetext();
        String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatch");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Actual Warning message is not displayed");
    }
}
