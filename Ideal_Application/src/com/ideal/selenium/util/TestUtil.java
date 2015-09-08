package com.ideal.selenium.util;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;

import com.ideal.selenium.basePage.page;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;

public class TestUtil {
	
	//public static Properties CONFG = null;
	
	public static boolean isTestExecutable(String testCaseName, ReadExcel testCaseXls){
		
		for(int rowNum=2;rowNum<=testCaseXls.getRowCount("TestCases");rowNum++){
			
			if(testCaseName.trim().toUpperCase().equals(testCaseXls.getStringCellData("TestCases", rowNum, "TCID").trim().toUpperCase())){
				if(testCaseXls.getStringCellData("TestCases", rowNum, "Execute").trim().toUpperCase().equals("YES")){
					System.out.println(testCaseXls.getStringCellData("TestCases", rowNum, "Execute"));
					return true;
				}else{
					System.out.println(testCaseXls.getStringCellData("TestCases", rowNum, "Execute"));
					return false;
				}
										
			}					
			
		}		
		return false;
	}	
	
	
	
	
	public static String getReasonForNoExecution(String testCaseName, ReadExcel testCaseXls){
		
		int rowNum=1;
		String reason = "No reason specified";
		for(rowNum=2;rowNum<=testCaseXls.getRowCount("TestCases");rowNum++){

			if(testCaseName.trim().toUpperCase().equals(testCaseXls.getStringCellData("TestCases", rowNum, "TCID").trim().toUpperCase())){
				if((testCaseXls.getStringCellData("TestCases", rowNum, "Execute").trim().toUpperCase().equals("NO")&&(!testCaseXls.getStringCellData("TestCases", rowNum, "Reason").trim().isEmpty()))){
					System.out.println(testCaseXls.getStringCellData("TestCases", rowNum, "Reason"));
					reason = testCaseXls.getStringCellData("TestCases", rowNum, "Reason");
					return reason;
				}
			}					
			
		}
		return reason;		
		
	}	



	
	//Test Results update in TestLink
	public static void updateTestLinkResult(String testCase, String exception, String result) {
		TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(page.CONFG.getProperty("DEV_KEY"), page.CONFG.getProperty("SERVER_URL"));
		try {
			testlinkAPIClient.reportTestCaseResult(page.CONFG.getProperty("PROJECT_NAME"), page.CONFG.getProperty("PLAN_NAME"), testCase, page.CONFG.getProperty("BUILD_NAME"), exception, result);
			
			
			
		} catch (TestLinkAPIException e) {		
			e.printStackTrace();
		}

	}
	
	
public static Object[][] getData(String testFuncName, String sheetName, ReadExcel testDataExcel){
		
		int startRowInd = 0;
		System.out.println("row count from:"+testDataExcel.getRowCount(sheetName));
		System.out.println(testDataExcel.getStringCellData(sheetName, 1, "TestFunction"));
		
		for (int rowNum=1;rowNum<=testDataExcel.getRowCount(sheetName);rowNum++){
			if(testFuncName.trim().toUpperCase().equals(testDataExcel.getStringCellData(sheetName, rowNum, 0).trim().toUpperCase())){
					startRowInd = rowNum;
					break;				
			}			
		}
		
		System.out.println("Test starts from :"+startRowInd);
		
		int paramStartRow = startRowInd+1;
		int paramCount = 0; 
		int paramStartCol = 0;
		while(!testDataExcel.getStringCellData(sheetName, paramStartRow,paramStartCol).equals("")){
			//System.out.print(testDataExcel.getStringCellData(sheetName, paramStartRow,paramStartCol)+"----");
			paramCount++;
			paramStartCol++;
		}
		System.out.println("Total number of columns:"+ paramCount);
		
		int dataStartInd = startRowInd+2;
		int rowCount = 0;		
		while(!testDataExcel.getStringCellData(sheetName, (dataStartInd+rowCount),0).equals("")){			
			rowCount++;
		}
		
		System.out.println("Total number of test data rows:"+ rowCount);
		
		Object[][] data = new Object[rowCount][1];
		Hashtable<String,String> table =null;
		
		for(int rNum = dataStartInd;rNum<(dataStartInd+rowCount);rNum++){
			table = new Hashtable<String,String>();
			for(int cNum = 0;cNum<paramCount;cNum++){
				table.put(testDataExcel.getStringCellData(sheetName, paramStartRow, cNum),testDataExcel.getStringCellData(sheetName, rNum, cNum));
				System.out.print(testDataExcel.getStringCellData(sheetName, rNum, cNum)+"--");
				
			}
			System.out.println();
			data[rNum-dataStartInd][0] = table;
			
		}
		
		return data;
		
	}
	
	
	
	
	
	
	

}
