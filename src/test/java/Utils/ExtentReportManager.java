package Utils;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;




public class ExtentReportManager extends BaseClass implements ITestListener  {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
	public void onStart(ITestContext context) {
		
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\myReport.html");
		
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extentReports = new ExtentReports();
		
		extentReports.attachReporter(sparkReporter);
		
		extentReports.setSystemInfo("Computer Name", "HP");
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("Tester Name", "M Navish");
		extentReports.setSystemInfo("OS", "Windows11");
		extentReports.setSystemInfo("Browser Name", "Chrome,Edge");
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extentReports.setSystemInfo("Groups", includedGroups.toString());
		}
		
	}
	
	public void onTestSuccess(ITestResult result) {
		
		extentTest = extentReports.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups()); // to display groups in report
		extentTest.log(Status.PASS, "Test case PASSED is : "+result.getName());
		
		try {
			String imgPath = new ScreenShot(driver).getScreenShot(result.getName());
			extentTest.addScreenCaptureFromPath(imgPath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		extentTest = extentReports.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups()); // to display groups in report
		extentTest.log(Status.FAIL, "Test case FAILED is : "+result.getName());
		extentTest.log(Status.FAIL, "Test case FAILED due to : "+result.getThrowable());
		
		try {
			String imgPath = new ScreenShot(driver).getScreenShot(result.getName());
			extentTest.addScreenCaptureFromPath(imgPath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		
		extentTest = extentReports.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups()); // to display groups in report
		extentTest.log(Status.SKIP, "Test case Skipped is : "+result.getName());
		extentTest.log(Status.SKIP, "Test case Skipped due to : "+result.getThrowable());
		
	}
	
	public void onFinish(ITestContext context) {
		
		extentReports.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\Reports\\myReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI()); //Immediately it will open the report after test execution
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
