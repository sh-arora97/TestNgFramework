package com.qa.base;

import com.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Base {
    WebDriver driver;
   public Properties properties;
   public Properties dataProp;


    public Base(){
         properties=new Properties();
        File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties");
        dataProp=new Properties();
        File dataPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\testData\\testData.properties");
        try {
            FileInputStream fileInputStream=new FileInputStream(dataPropFile);
            dataProp.load(fileInputStream);

        }catch (Throwable e){
            e.printStackTrace();
        }
        try{
            FileInputStream file=new FileInputStream(propFile);
            properties.load(file);

        }catch (Throwable e){
            e.printStackTrace();
        }

    }
    public WebDriver initializeBrowserAndOpenApplicationURL(String browserName){

        if (browserName.equalsIgnoreCase("chrome")){
            driver=new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")) {
            driver=new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("edge")){
            driver=new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
        driver.get(properties.getProperty("url"));
        return driver;

    }
}
