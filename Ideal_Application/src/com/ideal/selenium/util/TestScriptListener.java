package com.ideal.selenium.util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;

import com.ideal.selenium.basePage.page;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestScriptListener implements ITestListener, ISuiteListener, IInvokedMethodListener{
	ExtentReports extent;
	ExtentTest logger; 
	
	

    public void onTestStart(ITestResult tr) {
       
        System.out.println("TestCase started now====" +tr.toString());
       
    
		//extent = new ExtentReports("D:\\Git\\Ideal-Automation\\Ideal_Application\\src\\com\\ideal\\selenium\\util\\1.testreport\\testreport.html");
		logger=extent.startTest(tr.getName());
		LogStatus status = logger.getRunStatus();
		System.out.println(status); 
	}
	
	   /* public void generateReport( List<ISuite> suites) {
	        extent = new ExtentReports("D:\\Git\\Ideal-Automation\\Ideal_Application\\src\\com\\ideal\\selenium\\util\\testreport.html", true);
	  
	        for (ISuite suite : suites) {
	            Map<String, ISuiteResult> result = suite.getResults();
	  
	            for (ISuiteResult r : result.values()) {
	                ITestContext context = r.getTestContext();
	  
	                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
	                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
	                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
	            }
	        }
	  
	        extent.flush();
	        extent.close();
	    }
	  
	    private void buildTestNodes(IResultMap tests, LogStatus status) {
	        ExtentTest test;
	  
	        if (tests.size() > 0) {
	            for (ITestResult result : tests.getAllResults()) {
	                test = extent.startTest(result.getMethod().getMethodName());
	  
	                Date startedTime = getTime(result.getStartMillis());
	             Date endedTime = getTime(result.getEndMillis());
	  
	                for (String group : result.getMethod().getGroups())
	                    test.assignCategory(group);
	  
	                String message = "Test " + status.toString().toLowerCase() + "ed";
	  
	                if (result.getThrowable() != null)
	                    message = result.getThrowable().getMessage();
	  
	                test.log(status, message);
	  
	                extent.endTest(test);
	            }
	        }
	    }
	  
	    private Date getTime(long millis) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTimeInMillis(millis);
	        return calendar.getTime();        
	    }
	*/
	
	
	private void printTestResults(ITestResult result){
		String status = null;
		switch (result.getStatus()){
		case ITestResult.SUCCESS:
			status = "PASS";
			break;
		case ITestResult.FAILURE:
			status = "FAIL";
			break;
		case ITestResult.SKIP:
			status = "SKIP";
			break;		
		
		}
		System.out.println("Test result:"+status);
	}
	
	public void onTestFailure(ITestResult result) {
		printTestResults(result);
		String methodName = result.getMethod().getMethodName();
		getScreenShot(methodName);
		String Screenshot_path=getScreenShot(methodName);
		logger.log(LogStatus.FAIL, Screenshot_path);
		logger.log(LogStatus.INFO, "Snapshot below: "+logger.addScreenCapture(Screenshot_path));
		System.out.println("Result Failed");
		
	}
		
		
		
	
		public void onTestSuccess(ITestResult tr) {
			logger.log(LogStatus.PASS, tr.getName());
			
			System.out.println("Result success");
		}

	/*public void start()
	{
		extent = new ExtentReports(".\\src\\test\\resources\\1.testreport\\testreport.html");
		
		
	}*/
	private String getScreenShot(String sSfilename){
		
		String currTime = getCurrentTime();
		File screenshot = ((TakesScreenshot) page.driver).getScreenshotAs(OutputType.FILE); 
		String dest = "D:\\Git\\Ideal-Automation\\Ideal_Application\\src\\"+sSfilename+currTime+".jpg";
		File ssFileType = new File(dest); 
		try {
			FileUtils.copyFile(screenshot, ssFileType);			
		} catch (IOException e) {	
			e.printStackTrace();
		}
		return dest;
		
	}
	
	private String getCurrentTime(){
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		int dom = calendar.get(Calendar.DAY_OF_MONTH);
		String month = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US);
		String dow = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		String newTime = "_"+dow+"_"+dom+"_"+month+"_"+hour+"_"+min+"_"+sec;
		//System.out.println("new time:"+newTime);
		return newTime;
	}

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}

	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		//extent.flush();
		System.out.println("Suite Stopped");
		extent.endTest(logger);
		extent.flush();
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		System.out.println("Suite Started");
		extent = new ExtentReports("D:\\Git\\Ideal-Automation\\Ideal_Application\\src\\com\\ideal\\selenium\\util\\1.testreport\\testreport.html");
	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSkipped(ITestResult tr) {
		logger.log(LogStatus.SKIP, tr.getName());
		
		System.out.println("Result skipped");
		
	}
	
	
	

}
