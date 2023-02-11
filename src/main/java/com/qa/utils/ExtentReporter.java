package com.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ExtentReporter {
    public  static ExtentReports generateExtentReport(){
        ExtentReports extentReports=new ExtentReports();
        File extentReportFile=new File(System.getProperty("user.dir")+ "\\target\\ExtentReports\\extentReport.html");
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter(extentReportFile);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Test Automation Result Report");
        sparkReporter.config().setDocumentTitle("TN Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
        extentReports.attachReporter(sparkReporter);

        Properties properties=new Properties();
        File configPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties");
        try{
            FileInputStream fileInputStream=new FileInputStream(configPropFile);
            properties.load(fileInputStream);

        }catch (Throwable e){
            e.printStackTrace();
        }


        extentReports.setSystemInfo("APPLICATION URL",properties.getProperty("url"));
        extentReports.setSystemInfo("BROWSER NAME",properties.getProperty("browser"));
        extentReports.setSystemInfo("EMAIL",properties.getProperty("validEmail"));
        extentReports.setSystemInfo("PASSWORD",properties.getProperty("validPassword"));
        extentReports.setSystemInfo("OPERATING SYSTEM",System.getProperty("os.name"));
        extentReports.setSystemInfo("USER NAME",System.getProperty("user.name"));
        extentReports.setSystemInfo("JAVA VERSION",System.getProperty("java.version"));

        return extentReports;

    }
}
