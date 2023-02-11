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

public class SearchTest extends Base {
    SearchPage searchPage;
   public WebDriver driver;
    HomePage homePage;
    public SearchTest(){
        super();
    }
    @BeforeMethod
    public void setup(){
        driver=initializeBrowserAndOpenApplicationURL(properties.getProperty("browser"));
        homePage=new HomePage(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority = 1)
    public void verifySearchWithValidProducts(){
        homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
        searchPage=  homePage.clickOnSearchButton();
        Assert.assertTrue(searchPage.displayStatusOfHpValidProduct(),"Valid product HP is not displayed in search results");
    }
    @Test(priority = 2)
    public void verifySearchWithInvalidProduct(){
        homePage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
        searchPage=  homePage.clickOnSearchButton();
        Assert.assertTrue(Boolean.parseBoolean("klll"),dataProp.getProperty("noProductTextInSearch"));
    }
    @Test(priority = 3)
    public void verifySearchWithoutAnyProduct(){
        searchPage=  homePage.clickOnSearchButton();
        Assert.assertTrue(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("noProductTextInSearch"));
    }
}
