package com.qa.listeners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.utils.ExtentReporter;
import com.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyListeners implements ITestListener {
    ExtentReports extentReports;
    ExtentTest extentTest;
    String testName;
    @Override
    public void onStart(ITestContext context) {
        extentReports= ExtentReporter.generateExtentReport();
    }
    @Override
    public void onTestStart(ITestResult result) {
        testName= result.getName();
       extentTest = extentReports.createTest(testName);
        extentTest.log(Status.INFO,testName + "Started Executing");
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS,testName + " Got successfully executed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
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
        String destinationScreenshotPath = Utilities.captureScreenshot(driver,result.getName());

        extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL,testName + "Got Failed");
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL, testName + "Got Skipped");
    }
    @Override
    public void onFinish(ITestContext context) {
        //If we don't add flush that all details in report will be gone
        extentReports.flush();
        String pathOfExtentReport=System.getProperty("user.dir")+ "\\target\\ExtentReports\\extentReport.html";
        File extentReport=new File(pathOfExtentReport);
        //This is to open extentReport automatically
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
