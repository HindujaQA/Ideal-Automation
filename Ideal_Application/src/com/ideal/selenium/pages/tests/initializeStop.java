//package com.rpxcorp.cp.selenium.pages.tests;
package com.ideal.selenium.pages.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



import com.ideal.selenium.basePage.page;
//import com.ideal.selenium.util.TestLinkResults;



public class initializeStop {
	
	//private HomePage homePage = null;
	private page emptyPage = null;
	
	@BeforeSuite
	public page initBrowser(){
		//homePage = new HomePage();
		//return homePage;
		emptyPage = new page();
		//TestLinkResults.createXmlResults();
		return emptyPage;
	}
	
	@AfterSuite
	 public void stop(){
		//dbPage.Logout();
		 //page.topMenu.Logout();
		page.driver.close();
		 
	 }
	
}
