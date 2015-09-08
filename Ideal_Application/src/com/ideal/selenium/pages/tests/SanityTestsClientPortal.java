package com.rpxcorp.cp.selenium.pages.tests;



import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rpxcorp.cp.selenium.basePage.page;
import com.rpxcorp.cp.selenium.po.ClientListPageInMyPortal;
import com.rpxcorp.cp.selenium.po.DashBoardPage;
import com.rpxcorp.cp.selenium.po.AdvancedSearchPage;
import com.rpxcorp.cp.selenium.po.HomePage;
import com.rpxcorp.cp.selenium.po.MarketPlacePageInIntelligence;
import com.rpxcorp.cp.selenium.po.MyAlertsPageInMyPortal;
import com.rpxcorp.cp.selenium.po.MyAssertionsPageInMyPortal;
import com.rpxcorp.cp.selenium.po.MyProfilePageInMyPortal;
import com.rpxcorp.cp.selenium.po.MyReportsPageInMyPortal;
import com.rpxcorp.cp.selenium.po.NewsPageInIntelligence;
import com.rpxcorp.cp.selenium.po.NpeProfilesPageInIntelligence;
import com.rpxcorp.cp.selenium.po.RpxPatentsPageInPortfolio;
import com.rpxcorp.cp.selenium.po.RpxPortfolioPageInPortfolio;
import com.rpxcorp.cp.selenium.po.RpxReportsPageInIntelligence;
import com.rpxcorp.cp.selenium.po.UserPreferencesPage;
import com.rpxcorp.cp.selenium.po.elements.AdvSearchElem;
import com.rpxcorp.cp.selenium.po.elements.ClientListElem;
import com.rpxcorp.cp.selenium.po.elements.DbElem;
import com.rpxcorp.cp.selenium.po.elements.ElemProp;
import com.rpxcorp.cp.selenium.po.elements.HeaderElem;
import com.rpxcorp.cp.selenium.po.elements.MarketplaceElem;
import com.rpxcorp.cp.selenium.po.elements.MyAlertElem;
import com.rpxcorp.cp.selenium.po.elements.MyAssertionElem;
import com.rpxcorp.cp.selenium.po.elements.MyProfileElem;
import com.rpxcorp.cp.selenium.po.elements.MyReportsElem;
import com.rpxcorp.cp.selenium.po.elements.NewsElem;
import com.rpxcorp.cp.selenium.po.elements.NpeProfileElem;
import com.rpxcorp.cp.selenium.po.elements.RpxPatentsElem;
import com.rpxcorp.cp.selenium.po.elements.RpxPortfolioElem;
import com.rpxcorp.cp.selenium.po.elements.RpxReportsElem;
import com.rpxcorp.cp.selenium.po.elements.UserPrefElem;
import com.rpxcorp.cp.selenium.util.TestUtil;

public class SanityTestsClientPortal {
	
	private HomePage home = null;
	private DashBoardPage dbPage = null;
	
	@BeforeTest (alwaysRun=true)
	public DashBoardPage initDBPageBeforeTest(){
		if(dbPage == null){
			home = new HomePage();
			Assert.assertTrue(home.getTitle().trim().toUpperCase().contains(ElemProp.PG_TITLE), "Not in Home page, current page title:"+page.driver.getTitle());
			dbPage = home.Login();
			System.out.println("page title:"+dbPage.getTitle());
			if (page.driver.getTitle().trim().toUpperCase().contains("ACTION CONTROLLER")|| 
					page.driver.getTitle().trim().toUpperCase().contains("EXCEPTION CAUGHT")||
					page.driver.getTitle().trim().toUpperCase().contains("RPX 500 ERROR")){
				//go to User preferences
				UserPreferencesPage userPrefPage = page.topMenu.goToUserPreferencesByUrl();
				Assert.assertTrue(userPrefPage.getTitle().trim().toUpperCase().contains(UserPrefElem.TXT_USER_PREFERENCES), "Not in User Preferences page, current page title :"+page.driver.getTitle());				
			
			}else{
				Assert.assertTrue(dbPage.getTitle().trim().toUpperCase().contains(HeaderElem.TXT_DASHBOARD), "Not in Dashboard page, current page title:"+page.driver.getTitle());
			}
			
			//--Assert.assertTrue(dbPage.getTitle().trim().toUpperCase().contains(HeaderElem.TXT_DASHBOARD), "Not in Dashboard page, current page title:"+page.driver.getTitle());
			/*
			String successSignInText = dbPage.FindByCssSelector("#flash_name").getText();

			if(successSignInText.contains("Signed in successfully")){
				if (dbPage.getTitle().contains("Dashboard"))				
					System.out.println("Signed in Successfully....In Dashboard page");
				else
					throw new IllegalStateException("User is not in Dashboard Page: " + page.driver.getTitle());				
			}else{
				throw new IllegalStateException("User is not in Dashboard Page: " + page.driver.getTitle());
			}
			*/
		}
		return dbPage;

	} 

	
	@AfterTest (alwaysRun=true)
	public void stopAfterTest(){
		//dbPage.Logout();
		page.topMenu.clickOnLogOutLink();
		
		//page.topMenu.Logout();
		Assert.assertTrue(page.driver.getCurrentUrl().equals(page.CONFG.getProperty("URL_HOME")),"Unable to Log Out,current page: "+page.driver.getCurrentUrl());
		System.out.println("Signed out successfully");
		System.out.println("Done with the test");
	}

	@BeforeMethod (alwaysRun=true)
	public DashBoardPage reInitDBPage(){
		if(dbPage == null){
			home = new HomePage();
			Assert.assertTrue(home.getTitle().trim().toUpperCase().contains(ElemProp.PG_TITLE), "Not in Home page, current page title:"+page.driver.getTitle());
			dbPage = home.Login();
			System.out.println("page title:"+dbPage.getTitle());
			if (page.driver.getTitle().trim().toUpperCase().contains("ACTION CONTROLLER")|| 
					page.driver.getTitle().trim().toUpperCase().contains("EXCEPTION CAUGHT")||
					page.driver.getTitle().trim().toUpperCase().contains("RPX 500 ERROR")){
				UserPreferencesPage userPrefPage = page.topMenu.goToUserPreferencesByUrl();
				Assert.assertTrue(userPrefPage.getTitle().trim().toUpperCase().contains(UserPrefElem.TXT_USER_PREFERENCES), "Not in User Preferences page, current page title :"+page.driver.getTitle());				
			
			} else{
				Assert.assertTrue(dbPage.getTitle().trim().toUpperCase().contains(HeaderElem.TXT_DASHBOARD), "Not in Dashboard page, current page title:"+page.driver.getTitle());
			}
		}
		return dbPage;
	}

	@AfterMethod (alwaysRun=true)
	public void testDBPageInstance(){
		
		if(page.driver.getTitle().contains("Dashboard")){
			System.out.println("Already in Dashboard page");
		}else if (page.driver.getTitle().trim().toUpperCase().contains("ACTION CONTROLLER")|| 
				page.driver.getTitle().trim().toUpperCase().contains("EXCEPTION CAUGHT")||
				page.driver.getTitle().trim().toUpperCase().contains("RPX 500 ERROR")){
			UserPreferencesPage userPrefPage = page.topMenu.goToUserPreferencesByUrl();
			Assert.assertTrue(userPrefPage.getTitle().trim().toUpperCase().contains(UserPrefElem.TXT_USER_PREFERENCES), "Not in User Preferences page, current page title :"+page.driver.getTitle());
			
		}else{
			System.out.println("We are Not in Dashboard page");
			//page.driver.navigate().refresh();
			System.out.println("page Title: "+page.driver.getTitle());
			System.out.println("page URL: "+page.driver.getCurrentUrl());
		}
	
	}
	
	
	@Test (groups = {"sanity"}) 
	//(description = "Checks for top navigation link, \"Home\" in header and destination page (Dashboard) user should be redirected to.")	
	public void CheckDashBoardByUrl(){
		
		System.out.println("In CheckDashBoardLink");
		if(!TestUtil.isTestExecutable("CheckDashBoardLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckDashBoardLink", page.testExcel));
		
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_DASHBOARD)),HeaderElem.LNK_DASHBOARD+":link doesn't exist in top menu");
		
		DashBoardPage dashboard = page.topMenu.goToDashboardByUrl();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(DbElem.HDR_DB_PAGE)));
		Assert.assertTrue(dashboard.getTitle().trim().toUpperCase().matches(HeaderElem.TXT_DASHBOARD+".*"), "Not in Dashboard page, current page title :"+page.driver.getTitle());
		Assert.assertTrue(dashboard.isElementByCSSPresent(DbElem.PAGE_DB_CLASS),"Not in Dashboard page, current page title :"+page.driver.getTitle());
		
	}	

	@Test (groups = {"sanity"}) 
	//(description = "Checks for top navigation link, \"My Portal\" in header and destination page (My Alerts) user should be redirected to.")
	public void CheckMyPortalByUrl(){
		System.out.println("In CheckMyPortalLink");
		if(!TestUtil.isTestExecutable("CheckMyPortalLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckMyPortalLink", page.testExcel));
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_MY_PORTAL)),HeaderElem.LNK_MY_PORTAL+":link doesn't exist in top menu");
		
		MyAlertsPageInMyPortal myAlertsPage = page.topMenu.goToMyAlertsByUrl();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(MyAlertElem.HDR_MY_ALERTS)));
		Assert.assertTrue(myAlertsPage.FindByTagName(MyAlertElem.HDR_MY_ALERTS).getText().trim().toUpperCase().matches(".*"+MyAlertElem.TXT_MY_ALERTS+".*"));
		Assert.assertTrue(myAlertsPage.getTitle().trim().toUpperCase().matches(".*"+MyAlertElem.TXT_MY_ALERTS+".*"));
		
	}

	
	@Test (groups = {"sanity"})
	//(description = "Checks for top navigation link, \"Intelligence\" in header and destination page (Marketplace) user should be redirected to.")
	public void CheckIntelligenceByUrl(){
		System.out.println("In CheckIntelligenceLink");
		if(!TestUtil.isTestExecutable("CheckIntelligenceLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckIntelligenceLink", page.testExcel));
		
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_INTELLIGENCE)),HeaderElem.LNK_INTELLIGENCE+":link doesn't exist in top menu");

		MarketPlacePageInIntelligence marketPlacePage = page.topMenu.goToMarketplaceByUrl();
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(MarketplaceElem.CLASS_DATA_TABLE_PROCESSING)));
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.id(MarketplaceElem.ID_TAB_MARKETPLACE)));
		if(marketPlacePage.isAlertPresent()){
			page.alert.accept();
		}
		
		Assert.assertTrue(marketPlacePage.getTitle().trim().toUpperCase().contains(MarketplaceElem.TXT_PAGE_TITLE),"Not in Marketplace page, current page title :"+page.driver.getTitle());
		
	}
	
	@Test (groups = {"sanity"})
	public void CheckPortfolioByUrl(){
		System.out.println("In CheckPortfolioLink");
		if(!TestUtil.isTestExecutable("CheckPortfolioLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckPortfolioLink", page.testExcel));
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_PORTFOLIO)),HeaderElem.LNK_PORTFOLIO+":link doesn't exist in top menu");
		
		RpxPortfolioPageInPortfolio rpxPortfolioPage = page.topMenu.goToRpxPortfolioByUrl();
		if(rpxPortfolioPage.isAlertPresent()){
			page.alert.accept();
		}
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(RpxPortfolioElem.HDR_RPX_PORTFOLIO)));
		String hdr_title = rpxPortfolioPage.FindByTagName(RpxPortfolioElem.HDR_RPX_PORTFOLIO).getText();
		Assert.assertTrue(hdr_title.trim().toUpperCase().matches(".*"+RpxPortfolioElem.TXT_PAGE_TITLE+".*"), "Page header is not matching:"+page.driver.findElement(By.tagName(RpxPortfolioElem.HDR_RPX_PORTFOLIO)).getText());
		
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(RpxPortfolioElem.CLASS_DATA_TABLE_PROCESSING)));
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.id(RpxPortfolioElem.ID_TAB_RPX_PORTFOLIO)));
		
		Assert.assertTrue(rpxPortfolioPage.isElementsByIdPresent(RpxPortfolioElem.ID_TAB_RPX_PORTFOLIO),"data_table is missing");
		Assert.assertTrue(rpxPortfolioPage.isElementByCSSPresent(RpxPortfolioElem.CSS_TAB_RPX_PORTFOLIO), "data_table is missing");

	}
	
	@Test (groups = {"sanity"})
	public void CheckMarketplaceByUrl(){
		System.out.println("In CheckMarketplaceLink");
		if(!TestUtil.isTestExecutable("CheckMarketplaceLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckMarketplaceLink", page.testExcel));
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_INTELLIGENCE)),HeaderElem.LNK_INTELLIGENCE+":link doesn't exist in top menu");
		
		MarketPlacePageInIntelligence marketPlacePage = page.topMenu.goToMarketplaceByUrl();
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(MarketplaceElem.CLASS_DATA_TABLE_PROCESSING)));
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("data_table")));
		if(marketPlacePage.isAlertPresent()){
			page.alert.accept();
		}
		
		Assert.assertTrue(marketPlacePage.getTitle().trim().toUpperCase().contains(MarketplaceElem.TXT_PAGE_TITLE),"Not in Marketplace page, current page title :"+page.driver.getTitle());
		
	}
	
	@Test (groups = {"sanity"})
	public void CheckNPEProfilesByUrl(){
		System.out.println("In CheckNPEProfilesLink");
		if(!TestUtil.isTestExecutable("CheckNPEProfilesLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckNPEProfilesLink", page.testExcel));	
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_INTELLIGENCE)),HeaderElem.LNK_INTELLIGENCE+":link doesn't exist in top menu");
		
		NpeProfilesPageInIntelligence npeProfilesPage = page.topMenu.goToNpeProfilesByUrl();
		if(npeProfilesPage.isAlertPresent()){
			page.alert.accept();
		}
		if(npeProfilesPage.isElementPresent(By.cssSelector(NpeProfileElem.CSS_DATA_TABLE_WRAPPER))){
			page.wait.until(ExpectedConditions.presenceOfElementLocated(By.id(NpeProfileElem.ID_DATA_TABLE)));
		}
		Assert.assertTrue(npeProfilesPage.FindByTagName(NpeProfileElem.HDR_NPE_PROFILES).getText().trim().toUpperCase().matches(".*"+NpeProfileElem.TXT_NPE_PROFILES+".*"));
		Assert.assertTrue(npeProfilesPage.getTitle().trim().toUpperCase().matches(".*"+NpeProfileElem.TXT_NPE_PROFILES+".*"));
		
	}
	
	@Test (groups = {"sanity"})
	public void CheckNewsByUrl(){
		System.out.println("In CheckNewsLink");
		if(!TestUtil.isTestExecutable("CheckNewsLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckNewsLink", page.testExcel));
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_INTELLIGENCE)),HeaderElem.LNK_INTELLIGENCE+":link doesn't exist in top menu");
		
		NewsPageInIntelligence newsPage = page.topMenu.goToNewsByUrl();
		Assert.assertFalse(newsPage.isElementByCSSPresent(NewsElem.HDR_NO_NEWS),"News are missing in News Page");
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(NewsElem.HDR_NEWS)));
		Assert.assertTrue(newsPage.FindByCssSelector(NewsElem.HDR_NEWS).getText().trim().toUpperCase().contains(NewsElem.HDR_TXT_NEWS), "Header mismatch"+newsPage.FindByCssSelector(NewsElem.HDR_NEWS).getText());
		
	}
	
	@Test (groups = {"sanity"})
	public void CheckRPXReportsByUrl(){
		System.out.println("In CheckRPXReportsLink");
		if(!TestUtil.isTestExecutable("CheckRPXReportsLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckRPXReportsLink", page.testExcel));
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_INTELLIGENCE)),HeaderElem.LNK_INTELLIGENCE+":link doesn't exist in top menu");
		
		RpxReportsPageInIntelligence rpxReportsPage = page.topMenu.goToRpxReportsByUrl();
		page.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(RpxReportsElem.LIST_YEAR_REPORTS)));
		System.out.println("Header: "+rpxReportsPage.FindByTagName(RpxReportsElem.HDR_RPX_REPORTS).getText());
		Assert.assertTrue(rpxReportsPage.FindByTagName(RpxReportsElem.HDR_RPX_REPORTS).getText().trim().toUpperCase().matches(".*"+RpxReportsElem.TXT_HDR_RPX_REPORTS+".*"));
		Assert.assertTrue(rpxReportsPage.getTitle().trim().toUpperCase().contains(RpxReportsElem.TXT_HDR_RPX_REPORTS));
		
	}
	
	@Test (groups = {"sanity"})
	public void CheckRpxProfileByUrl(){
		System.out.println("In CheckMyProfileLink");
		if(!TestUtil.isTestExecutable("CheckMyProfileLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckMyProfileLink", page.testExcel));
		
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_MY_PORTAL)),HeaderElem.LNK_MY_PORTAL+":link doesn't exist in top menu");
		
		MyProfilePageInMyPortal profilePage = page.topMenu.goToRpxEntityDetailsByUrl();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(MyProfileElem.HDR_MY_PROFILE)));
		//Assert.assertTrue(profilePage.FindByTagName("h2").getText().contains(profilePage.FindByCssSelector("#header_tools a").getText()));
		Assert.assertTrue(profilePage.isElementPresent(By.cssSelector(MyProfileElem.CSS_PAGE_BODY)),"Not in entities page,viewing page:"+page.driver.getCurrentUrl());
	}
	
	@Test (groups = {"sanity"})
	public void CheckMyAlertsByUrl(){
		System.out.println("In CheckMyAlertsLink");
		if(!TestUtil.isTestExecutable("CheckMyAlertsLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckMyAlertsLink", page.testExcel));
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_MY_PORTAL)),HeaderElem.LNK_MY_PORTAL+":link doesn't exist in top menu");
		
		MyAlertsPageInMyPortal myAlertsPage = page.topMenu.goToMyAlertsByUrl();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(MyAlertElem.HDR_MY_ALERTS)));
		Assert.assertTrue(myAlertsPage.FindByTagName(MyAlertElem.HDR_MY_ALERTS).getText().trim().toUpperCase().matches(".*"+MyAlertElem.TXT_MY_ALERTS+".*"));
		Assert.assertTrue(myAlertsPage.getTitle().trim().toUpperCase().matches(".*"+MyAlertElem.TXT_MY_ALERTS+".*"));
		
	}
	
	@Test (groups = {"sanity"})
	public void CheckMyAssertionsByUrl(){
		System.out.println("In CheckMyAssertionsLink");
		if(!TestUtil.isTestExecutable("CheckMyAssertionsLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckMyAssertionsLink", page.testExcel));
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_MY_PORTAL)),HeaderElem.LNK_MY_PORTAL+":link doesn't exist in top menu");
		
		MyAssertionsPageInMyPortal myAssertionsPage = page.topMenu.goToMyAssertionsByUrl();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(MyAssertionElem.HDR_MY_ASSERTIONS)));
		Assert.assertTrue(myAssertionsPage.FindByTagName(MyAssertionElem.HDR_MY_ASSERTIONS).getText().trim().toUpperCase().matches(".*"+MyAssertionElem.HDR_TXT_TBL_MY_ASSERTIONS+".*"),"My Assertions page header mismatch, current page header:"+myAssertionsPage.FindByTagName("h1").getText());
		Assert.assertTrue(myAssertionsPage.getTitle().trim().toUpperCase().matches(".*"+MyAssertionElem.HDR_TXT_TBL_MY_ASSERTIONS+".*"));
		Assert.assertTrue(myAssertionsPage.isPartialLinkTextPresent(MyAssertionElem.TXT_LNK_ADD_ASSERTION));
		Assert.assertTrue(myAssertionsPage.isLinkPresent(MyAssertionElem.TXT_LNK_DOWNLOAD_CSV));	
		
	}
	
	@Test (groups = {"sanity"})
	public void CheckMyReportsByUrl(){
		System.out.println("In CheckMyReportsLink");
		if(!TestUtil.isTestExecutable("CheckMyReportsLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckMyReportsLink", page.testExcel));
		
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_MY_PORTAL)),HeaderElem.LNK_MY_PORTAL+":link doesn't exist in top menu");
		
		MyReportsPageInMyPortal myReportsPage = page.topMenu.goToMyReportsByUrl();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(MyReportsElem.HDR_MY_REPORTS)));
		Assert.assertTrue(myReportsPage.FindByTagName(MyReportsElem.HDR_MY_REPORTS).getText().trim().toUpperCase().matches(".*"+MyReportsElem.HDR_TXT_MY_REPORTS+".*"));
		Assert.assertTrue(myReportsPage.getTitle().trim().toUpperCase().matches(".*"+MyReportsElem.HDR_TXT_MY_REPORTS+".*"));
				
	}
	
	
	@Test (groups = {"sanity"})
	public void CheckClientListByUrl(){
		System.out.println("In CheckClientListLink");
		if(!TestUtil.isTestExecutable("CheckClientListLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckClientListLink", page.testExcel));
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_MY_PORTAL)),HeaderElem.LNK_MY_PORTAL+":link doesn't exist in top menu");
		
		ClientListPageInMyPortal clientListPage = page.topMenu.goToClientListByUrl();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(ClientListElem.HDR_CLIENT_LIST)));
		Assert.assertTrue(clientListPage.FindByTagName(ClientListElem.HDR_CLIENT_LIST).getText().trim().toUpperCase().matches(".*"+ClientListElem.TXT_RPX_CLIENTS+".*"));
		Assert.assertTrue(clientListPage.getTitle().trim().toUpperCase().matches(".*"+ClientListElem.TXT_CLIENT_LIST+".*"));
		
	}
	
	@Test (groups = {"sanity"})
	public void CheckRPXPatentsByUrl(){
		System.out.println("In CheckRPXPatentsLink");
		if(!TestUtil.isTestExecutable("CheckRPXPatentsLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckRPXPatentsLink", page.testExcel));
		
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_PORTFOLIO)),HeaderElem.LNK_PORTFOLIO+":link doesn't exist in top menu");
		
		RpxPatentsPageInPortfolio rpxPatentsPage = page.topMenu.goToRpxPatentsByUrl();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(RpxPatentsElem.HDR_RPX_PATENTS)));
		if(rpxPatentsPage.isAlertPresent()){
			page.alert.dismiss();
		}		
		Assert.assertTrue(rpxPatentsPage.FindByTagName(RpxPatentsElem.HDR_RPX_PATENTS).getText().trim().toUpperCase().matches(".*"+RpxPatentsElem.TXT_RPX_PATENTS+".*"));
		Assert.assertTrue(rpxPatentsPage.getTitle().trim().toUpperCase().matches(".*"+RpxPatentsElem.TXT_RPX_PATENTS+".*"));
		
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(RpxPatentsElem.ID_DATA_TABLE_PROCESSING)));
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.id(RpxPatentsElem.ID_RPX_PAT_TABLE)));
		Assert.assertTrue(rpxPatentsPage.isElementsByIdPresent(RpxPatentsElem.ID_RPX_PAT_TABLE),"data_table is missing");
		Assert.assertTrue(rpxPatentsPage.isElementByCSSPresent(RpxPatentsElem.CSS_RPX_PAT_TABLE), "data_table is missing");

		
	}
	
	@Test (groups = {"sanity"})
	public void CheckRPXPortfolioByUrl(){
		System.out.println("In CheckRPXPortfolioLink");
		if(!TestUtil.isTestExecutable("CheckRPXPortfolioLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckRPXPortfolioLink", page.testExcel));
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_PORTFOLIO)),HeaderElem.LNK_PORTFOLIO+":link doesn't exist in top menu");
		
		RpxPortfolioPageInPortfolio rpxPortfolioPage = page.topMenu.goToRpxPortfolioByUrl();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(RpxPortfolioElem.HDR_RPX_PORTFOLIO)));
		if (rpxPortfolioPage.isAlertPresent()){
			page.alert.accept();
		}
		Assert.assertTrue(rpxPortfolioPage.FindByTagName(RpxPortfolioElem.HDR_RPX_PORTFOLIO).getText().trim().toUpperCase().matches(".*"+RpxPortfolioElem.TXT_HDR_RPX_PORTFOLIO+".*"));
		Assert.assertTrue(rpxPortfolioPage.getTitle().trim().toUpperCase().matches(".*"+RpxPortfolioElem.TXT_HDR_RPX_PORTFOLIO+".*"));
		
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(RpxPortfolioElem.ID_DATA_TABLE_PROCESSING)));
		
		if (rpxPortfolioPage.isAlertPresent()){
			page.alert.accept();
		}
	
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.id(RpxPortfolioElem.ID_TAB_RPX_PORTFOLIO)));
		Assert.assertTrue(rpxPortfolioPage.isElementByIdPresent(RpxPortfolioElem.ID_TAB_RPX_PORTFOLIO),"data_table is missing");
		Assert.assertTrue(rpxPortfolioPage.isElementByCSSPresent(RpxPortfolioElem.CSS_TAB_RPX_PORTFOLIO), "data_table is missing");
	
	}
	
	
	@Test (groups = {"sanity"})
	public void CheckAdvancedSearchByUrl(){
		
		System.out.println("In CheckAdvancedSearchLink");
		if(!TestUtil.isTestExecutable("CheckAdvancedSearchLink", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("CheckAdvancedSearchLink", page.testExcel));
		Assert.assertTrue(page.topMenu.isElementPresentInTopMenu(By.partialLinkText(HeaderElem.LNK_ADVANCED_SEARCH)),HeaderElem.LNK_ADVANCED_SEARCH+":link doesn't exist in top menu");
		
		AdvancedSearchPage advSearchPage = page.topMenu.goToAdvancedSearchByUrl();		
		Assert.assertTrue(advSearchPage.getTitle().trim().toUpperCase().contains(AdvSearchElem.TITLE_ADV_SEARCH),"Not in Advanced Search page, current page title:"+advSearchPage.getTitle());
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(AdvSearchElem.TABS_SEARCH)));
		Assert.assertEquals(advSearchPage.FindByTagName(AdvSearchElem.HDR_ADV_SEARCH).getText().trim().toUpperCase(),AdvSearchElem.TXT_ADV_SEARCH);
		Assert.assertEquals(advSearchPage.FindByCssSelector(AdvSearchElem.CSS_TAB_ACTIVE).getText().trim().toUpperCase(),AdvSearchElem.TXT_PATENT_LITIGATION);
		
	}

	
	
	
	
	

}
