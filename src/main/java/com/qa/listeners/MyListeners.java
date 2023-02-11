package com.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.utils.ExtentReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class MyListeners implements ITestListener {
    ExtentReports extentReports;
    ExtentTest extentTest;
    @Override
    public void onStart(ITestContext context) {
        extentReports= ExtentReporter.generateExtentReport();

    }

    @Override
    public void onTestStart(ITestResult result) {
       String testName= result.getName();
       extentTest = extentReports.createTest(testName);
        extentTest.log(Status.INFO,testName + "Started Executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName=result.getName();
        extentTest.log(Status.PASS,testName + " Got successfully executed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName= result.getName();
        System.out.println("Screenshot Taken");
        WebDriver driver=null;
        //This is to add driver for taking screenshot
        try {
            driver= (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenshotPath=System.getProperty("user.dir")+ "\\target\\Screenshots\\"+testName +".png";
        try {
            FileHandler.copy(srcScreenshot,new File(destinationScreenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL,testName + "Got Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName= result.getName();
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL, testName + "Got Skipped");
    }



    @Override
    public void onFinish(ITestContext context) {
        //If we don't add flush that all details in report will be gone
        extentReports.flush();

    }
}
