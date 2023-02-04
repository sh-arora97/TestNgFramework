package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    //Objects
    @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")
    private WebElement myAccountDropmenu;
    @FindBy(linkText = "Login")
    private WebElement loginOption;
    @FindBy(linkText = "Register")
    private WebElement registerOption;
    @FindBy(name = "search")
    private WebElement searchBoxField;
    @FindBy(xpath = "//*[@id=\"search\"]/span/button")
    private WebElement searchButton;


    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    //Actions
    public void clickOnMyAccount(){
        myAccountDropmenu.click();
    }
    public LoginPage clickOnLoginOption(){
        loginOption.click();
        return new LoginPage(driver);
    }
    public RegisterPage clickOnRegisterOption(){
        registerOption.click();
        return new RegisterPage(driver);
    }
    public void enterProductIntoSearchBoxField(String productText){
        searchBoxField.sendKeys(productText);
    }
    public SearchPage clickOnSearchButton(){
        searchButton.click();
        return new SearchPage(driver);
    }
}
