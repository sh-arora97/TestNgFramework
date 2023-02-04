package com.qa.testcases;

import com.qa.base.Base;
import com.qa.pages.HomePage;
import com.qa.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search extends Base {
    SearchPage searchPage;
    WebDriver driver;
    public Search(){
        super();
    }
    @BeforeMethod
    public void setup(){
        driver=initializeBrowserAndOpenApplicationURL(properties.getProperty("browser"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority = 1)
    public void verifySearchWithValidProducts(){
        HomePage homePage=new HomePage(driver);
        homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
        searchPage=  homePage.clickOnSearchButton();
        Assert.assertTrue(searchPage.displayStatusOfHpValidProduct(),"Valid product HP is not displayed in search results");
    }
    @Test(priority = 2)
    public void verifySearchWithInvalidProduct(){
        HomePage homePage=new HomePage(driver);
        homePage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
        searchPage=  homePage.clickOnSearchButton();
        Assert.assertTrue(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("noProductTextInSearch"));
    }
    @Test(priority = 3)
    public void verifySearchWithoutAnyProduct(){
        HomePage homePage=new HomePage(driver);
        searchPage=  homePage.clickOnSearchButton();
        Assert.assertTrue(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("noProductTextInSearch"));
    }
}
