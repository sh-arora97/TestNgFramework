package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    WebDriver driver;
    @FindBy(linkText = "HP LP3065")
    private WebElement validHpProduct;
    @FindBy(xpath = "//*[@id=\"content\"]/p[2]")
    private WebElement noProductMessage;

    public SearchPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //Actions
    public Boolean displayStatusOfHpValidProduct(){
     boolean displayStatus=   validHpProduct.isDisplayed();
     return displayStatus;
    }
    public Boolean retrieveNoProductMessageText(){
       boolean noProductMessageText= noProductMessage.isDisplayed();
       return noProductMessageText;

    }
}
