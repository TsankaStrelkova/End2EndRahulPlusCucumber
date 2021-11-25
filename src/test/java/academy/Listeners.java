package academy;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;
import resources.Base;

public class Listeners extends Base implements ITestListener {
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	
	// Thread local needed for thread safe in parallel execution 
	ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();
	private static Logger log =  LogManager.getLogger(Base.class.getName());
	
	public void onTestStart(ITestResult result) {
		test= extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		//Screenshot
		extentTest.get().fail(result.getThrowable());
		WebDriver driver = null;
		String testMethodName =result.getMethod().getMethodName();
		
		try {
			// give life of driver for parallel execution
			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch(Exception e)
		{
			
		}
		try {
			  // take java script errors form console
		
			 LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
			 List<LogEntry> logs = entry.getAll();
		     for (LogEntry le: logs)
		     {
		    	 String errorMessage = le.getMessage().toString();
		    	 System.out.println("TEST: " +result.getMethod().getMethodName() +" Java script error: "+errorMessage);
		    	 log.error("TEST: " +result.getMethod().getMethodName() +" Java script error: "+errorMessage);
				
		     }
				  
			  // add screenshot
			  extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName,driver), result.getMethod().getMethodName());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}