package com.qa.testcases;

import com.qa.base.Base;
import com.qa.pages.AccountSuccessPage;
import com.qa.pages.HomePage;
import com.qa.pages.RegisterPage;
import com.qa.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class Register extends Base {
    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;
    WebDriver driver;
    public Register(){
        super();
    }
    @BeforeMethod
    public void setup(){
        driver=initializeBrowserAndOpenApplicationURL(properties.getProperty("browser"));
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMyAccount();
        registerPage=  homePage.clickOnRegisterOption();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void verifyRegisteringAnAccountWithMandatoryFields(){
        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
        registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(properties.getProperty("validPassword"));
        registerPage.enterConfirmPassword(properties.getProperty("validPassword"));
        registerPage.selectPrivacyPolicy();
        accountSuccessPage=  registerPage.clickOnContinueButton();
        String actualSuccessHeading= accountSuccessPage.retrieveAccountSuccessPageHeading();
        Assert.assertEquals(actualSuccessHeading,"Your Account Has Been Created!","Account Success Page is not displayed");

    }
    @Test(priority = 2)
    public void verifyRegisteringAccountByProvidingAllFields(){
        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
        registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(properties.getProperty("validPassword"));
        registerPage.enterConfirmPassword(properties.getProperty("validPassword"));
        registerPage.selectYesNewsLetterOption();
        registerPage.selectPrivacyPolicy();
        accountSuccessPage=  registerPage.clickOnContinueButton();
        String actualSuccessHeading= accountSuccessPage.retrieveAccountSuccessPageHeading();
        Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page is not displayed");
    }
    @Test(priority = 3)
    public void verifyRegisteringAccountWithExistingEmailAddress(){
        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmailAddress(properties.getProperty("validEmail"));
        registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(properties.getProperty("validPassword"));
        registerPage.enterConfirmPassword(properties.getProperty("validPassword"));
        registerPage.selectYesNewsLetterOption();
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();
        String actualWarningMessage=registerPage.retrieveDuplicateEmailAddressWarning();
        Assert.assertTrue(actualWarningMessage.contains(dataProp.getProperty("duplicateEmailWarning")),"Warnig message regarding duplicate email address is not displayed");
    }
}