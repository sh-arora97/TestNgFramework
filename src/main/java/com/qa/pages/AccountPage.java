package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    WebDriver driver;
    @FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div/p/strong")
    private WebElement editYourAccountInformationOption;
    public AccountPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    //Actions
    public boolean getDisplayStatusOfEditYourAccountInformation(){
       boolean displayStatus= editYourAccountInformationOption.isDisplayed();
       return displayStatus;
    }
}
