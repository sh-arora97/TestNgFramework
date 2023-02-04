package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;
    @FindBy(id = "input-firstname")
    private WebElement firstNameField;
    @FindBy(id = "input-lastname")
    private WebElement lastNameField;
    @FindBy(id = "input-email")
    private WebElement emailAddressField;
    @FindBy(id = "input-telephone")
    private WebElement telephoneField;
    @FindBy(id = "input-password")
    private WebElement passwordField;
    @FindBy(id = "input-confirm")
    private WebElement passwordConfirmField;
    @FindBy(name = "agree")
    private WebElement privacyPolicyField;
    @FindBy(xpath = "//*[@id=\"content\"]/form/div/div/input[2]")
    private WebElement continueButton;
    @FindBy(xpath = "//*[@id=\"content\"]/form/fieldset[3]/div/div/label[1]/input")
    private WebElement yesNewsLetterOption;
    @FindBy(xpath = "//*[@id=\"account-register\"]/div[1]")
    private WebElement duplicateEmailAddressWarning;

    public RegisterPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    //Actions
    public void enterFirstName(String firstNameText){
        firstNameField.sendKeys(firstNameText);
    }
    public  void enterLastName(String lastNameText){
        lastNameField.sendKeys(lastNameText);
    }
    public void enterEmailAddress(String emailText){
        emailAddressField.sendKeys(emailText);
    }
    public void enterTelephone(String telephoneText){
        telephoneField.sendKeys(telephoneText);
    }
    public void enterPassword(String passwordText){
        passwordField.sendKeys(passwordText);
    }
    public void enterConfirmPassword(String confirmPasswordText){
        passwordConfirmField.sendKeys(confirmPasswordText);
    }
    public void selectPrivacyPolicy(){
        privacyPolicyField.click();
    }
    public AccountSuccessPage clickOnContinueButton(){
        continueButton.click();
        return new AccountSuccessPage(driver);
    }
    public void selectYesNewsLetterOption(){
        yesNewsLetterOption.click();
    }
    public String retrieveDuplicateEmailAddressWarning(){
       String duplicateEmailWarningText= duplicateEmailAddressWarning.getText();
       return duplicateEmailWarningText;
    }

}
