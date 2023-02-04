package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    //Objects
    @FindBy(id = "input-email")
    private WebElement emailAddressField;
    @FindBy(id = "input-password")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div/form/input")
    private WebElement LoginButton;
    @FindBy(xpath = "//*[@id=\"account-login\"]/div[1]")
    private WebElement emailPasswordNotMatchingWarning;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //Actions
    public void enterEmailAddress(String emailText){
        emailAddressField.sendKeys(emailText);
    }
    public void enterPassword(String passwordText){
        passwordField.sendKeys(passwordText);
    }
    public AccountPage clickOnLoginButton(){
        LoginButton.click();
        return new  AccountPage(driver);
    }
    public String retrieveEmailPasswordNotMatchingWarningMessagetext(){
      String warningText=  emailPasswordNotMatchingWarning.getText();
      return warningText;
    }
    public AccountPage login(String emailText,String passwordText){
        emailAddressField.sendKeys(emailText);
        passwordField.sendKeys(passwordText);
        LoginButton.click();
        return new  AccountPage(driver);
    }
}
