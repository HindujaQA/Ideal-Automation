package com.ideal.selenium.util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.ideal.selenium.basePage.page;


public class TestScriptListener extends TestListenerAdapter{
	
	@Override
	public void onTestFailure(ITestResult result) {
		printTestResults(result);
		String methodName = result.getMethod().getMethodName();
		getScreenShot(methodName);
		
	}

	
	
	
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
	
	private void getScreenShot(String sSfilename){
		
		String currTime = getCurrentTime();
		File screenshot = ((TakesScreenshot) page.driver).getScreenshotAs(OutputType.FILE); 
		File ssFileType = new File(System.getProperty("user.dir")+"\\screenshots\\"+sSfilename+currTime+".jpg"); 
		try {
			FileUtils.copyFile(screenshot, ssFileType);			
		} catch (IOException e) {	
			e.printStackTrace();
		}
		
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
	
	
	

}
