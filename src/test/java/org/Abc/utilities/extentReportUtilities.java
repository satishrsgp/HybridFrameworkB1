package org.Abc.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.Abc.testCases.baseClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class extentReportUtilities extends baseClass implements ITestListener  {

    public ExtentSparkReporter sparkReporter; // This is used for Setting themes to the UI report
    public ExtentReports extentReports; // It will add system details to the report
    public ExtentTest extentTest; // It will create an entries for a test and its status

    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportName = "TestReport_"+timestamp+".html";
        sparkReporter = new ExtentSparkReporter("Report/"+reportName);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setReportName("Regression testing");
        sparkReporter.config().setDocumentTitle("Automation Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        String user = System.getProperty("user.name");
        String browser = context.getCurrentXmlTest().getParameter("browser");
        String os = context.getCurrentXmlTest().getParameter("os");
        extentReports.setSystemInfo("Computer name","local laptop");
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("Tester name",user);
        extentReports.setSystemInfo("os",os);
        extentReports.setSystemInfo("Browser",browser);
        extentReports.setSystemInfo("Agile team","ABC");

        List<String> includegroups = context.getCurrentXmlTest().getIncludedGroups();
        if(!includegroups.isEmpty()){
            extentReports.setSystemInfo("Included Groups",includegroups.toString());
        }
        List<String> excludegroups = context.getCurrentXmlTest().getExcludedGroups();
        if(!excludegroups.isEmpty()){
            extentReports.setSystemInfo("Excluded Groups",excludegroups.toString());
        }

    }

    public void onTestSuccess(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.PASS,"Test case passed :"+result.getName());
    }

    public void onTestFailure(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.FAIL,"Test case Failed :"+result.getName());
        extentTest.log(Status.FAIL,"Test case Failed with exception :"+result.getThrowable().getMessage());

        try{
            String testcaseName = result.getName();
            baseClass bc = new baseClass();
            String  imagePath = bc.captureScreenshot(testcaseName);
            extentTest.addScreenCaptureFromPath(imagePath); //attaches the screenshot to report
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void onTestSkipped(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.SKIP,"Test case Skipped :"+result.getName());
        extentTest.log(Status.SKIP,"Test case Skipped with exception :"+result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        extentReports.flush(); // This will write all your test text to the report
    }







}
