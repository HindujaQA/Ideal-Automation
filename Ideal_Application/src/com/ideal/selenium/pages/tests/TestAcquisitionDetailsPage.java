package com.ideal.selenium.pages.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rpxcorp.cp.selenium.basePage.page;
import com.rpxcorp.cp.selenium.po.AcquisitionDetailsPage;
import com.rpxcorp.cp.selenium.po.AdvSearchResultsPages;
import com.rpxcorp.cp.selenium.po.AdvancedSearchPage;
import com.rpxcorp.cp.selenium.po.DashBoardPage;
import com.rpxcorp.cp.selenium.po.HomePage;
import com.rpxcorp.cp.selenium.po.MarketPlacePageInIntelligence;
import com.rpxcorp.cp.selenium.po.UserPreferencesPage;
import com.rpxcorp.cp.selenium.po.elements.AcqDetailsElem;
import com.rpxcorp.cp.selenium.po.elements.AdvSearchElem;
import com.rpxcorp.cp.selenium.po.elements.AdvSearchPatLitElem;
import com.rpxcorp.cp.selenium.po.elements.AdvSearchResultsElem;
import com.rpxcorp.cp.selenium.po.elements.HeaderElem;
import com.rpxcorp.cp.selenium.po.elements.MarketplaceElem;
import com.rpxcorp.cp.selenium.po.elements.UserPrefElem;
import com.rpxcorp.cp.selenium.util.TestLinkResults;

public class TestAcquisitionDetailsPage {
	
	private HomePage home = null;
	private DashBoardPage dbPage = null;
	private AdvancedSearchPage AdvSearchPage = null;
	private AdvSearchResultsPages searchResultsPage=null;
	private MarketPlacePageInIntelligence marketplacePage = null;
	private AcquisitionDetailsPage AcqDetasilsPage =null;
	String HDR = "";
	String Abt_Para = "";
	String result = "f"; 	
	
	@BeforeTest (alwaysRun=true)
	public DashBoardPage initDBPageBeforeTest(){
		if(dbPage == null){
			home = new HomePage();
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
		}
		return dbPage;

	} 

	
	@BeforeMethod
	public AdvancedSearchPage initAdvSearchPage() { //initAdvSearchPage
		if(dbPage == null){
			home = new HomePage();
			dbPage = home.Login();
			System.out.println("Signing again after executing method in Test My Profile class");
			if (page.driver.getTitle().trim().toUpperCase().contains("ACTION CONTROLLER")|| 
					page.driver.getTitle().trim().toUpperCase().contains("EXCEPTION CAUGHT")||
					page.driver.getTitle().trim().toUpperCase().contains("RPX 500 ERROR")){
				//go to User preferences
				UserPreferencesPage userPrefPage = page.topMenu.goToUserPreferencesByUrl();
				Assert.assertTrue(userPrefPage.getTitle().trim().toUpperCase().contains(UserPrefElem.TXT_USER_PREFERENCES), "Not in User Preferences page, current page title :"+page.driver.getTitle());
			
			}else{
				Assert.assertTrue(dbPage.getTitle().trim().toUpperCase().contains(HeaderElem.TXT_DASHBOARD), "Not in Dashboard page, current page title:"+page.driver.getTitle());
			}
			
		}else if(page.driver.getTitle().trim().toUpperCase().contains(".*"+AdvSearchPatLitElem.HDR_BROWSER_TITILE.trim().toUpperCase()+".*")){
			System.out.println("In Advanced Search page before executing method");			
		}else{
			
			if (page.driver.getTitle().trim().toUpperCase().contains("ACTION CONTROLLER")|| 
					page.driver.getTitle().trim().toUpperCase().contains("EXCEPTION CAUGHT")||
					page.driver.getTitle().trim().toUpperCase().contains("RPX 500 ERROR")){
				//go to User preferences
				UserPreferencesPage userPrefPage = page.topMenu.goToUserPreferencesByUrl();
				Assert.assertTrue(userPrefPage.getTitle().trim().toUpperCase().contains(UserPrefElem.TXT_USER_PREFERENCES), "Not in User Preferences page, current page title :"+page.driver.getTitle());
			
			}
			
			page.wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(HeaderElem.LNK_ADVANCED_SEARCH)));
			
			AdvSearchPage = page.topMenu.clickOnAdvancedSearch();
				
			page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(AdvSearchPatLitElem.HDR_ALL_PARTIES_SEC)));
			
			// Page title validation
			System.out.println(AdvSearchPage.getTitle());
			
			// Advanced Search Page validation
			//--Assert.assertEquals(PatLitSearchPage.getTitle().trim().toUpperCase(),AdvSearchPatLitElem.HDR_BROWSER_TITILE.trim().toUpperCase());     
			Assert.assertTrue(AdvSearchPage.getTitle().trim().toUpperCase().contains(AdvSearchPatLitElem.HDR_BROWSER_TITILE.trim().toUpperCase()), "Not in search results page,current page"+AdvSearchPage.getCurrentUrl());
			
		}
		return AdvSearchPage;
	
	}
	
	
	
	
	@AfterMethod
	public void testDBPageInstance(){
		
		if(page.driver.getTitle().contains("Dashboard")){
			System.out.println("Already in Dashboard page");
		}else if (page.driver.getTitle().trim().toUpperCase().contains("ACTION CONTROLLER")|| 
				page.driver.getTitle().trim().toUpperCase().contains("EXCEPTION CAUGHT")||
				page.driver.getTitle().trim().toUpperCase().contains("RPX 500 ERROR")){
			//go to User preferences
			UserPreferencesPage userPrefPage = page.topMenu.goToUserPreferencesByUrl();
			//---Assert.assertTrue(userPrefPage.getTitle().trim().toUpperCase().contains(UserPrefElem.TXT_USER_PREFERENCES), "Not in User Preferences page, current page title :"+page.driver.getTitle());
			if(!userPrefPage.getTitle().trim().toUpperCase().contains(UserPrefElem.TXT_USER_PREFERENCES))
				System.out.println("Not in User Preferences page, current page title :"+page.driver.getTitle());
			
			
		}else{
			System.out.println("We are Not in Dashboard page");
			//page.driver.navigate().refresh();
			System.out.println("page Title: "+page.driver.getTitle());
			System.out.println("page URL: "+page.driver.getCurrentUrl());
		}		
	
	}
	
		
	
	@Test (description = "RPX-6349 Tags section validation")
	public void checkTagsinProfilePage(){
	
		AdvSearchPage.FindByCssSelector(AdvSearchElem.TAB_MARKET_PLACE_SEARCH).click();
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.OVERLAY_LOADING)));
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.CSS_LOADING)));
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(AdvSearchPatLitElem.BTN_SEARCH)));
		//String searchInput="Comms VOIP";
		AdvSearchPage.FindByCssSelector(AdvSearchElem.TXTBOX_MKT_PLACE_TECHNOLOGY_AREA).click();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(AdvSearchElem.TXTBOX_MKT_PLACE_TECHNOLOGY_AREA_LIST)));
		AdvSearchPage.FindByCssSelector(AdvSearchElem.TXTBOX_MKT_PLACE_TECHNOLOGY_AREA_LIST).click();
		//AdvSearchPage.FindByCssSelector(".select2-drop:nth-child(1n) .select2-result-label").submit();
		//AdvSearchPage.FindByCssSelector("#marketplaceTab .select2-choices").click();
		String selectedTag = AdvSearchPage.FindByCssSelector(AdvSearchElem.TXTBOX_MKT_PLACE_TECHNOLOGY_AREA).getText();
		//AdvSearchPage.FindByCssSelector("#marketplaceTab .select2-choices").submit();
		System.out.println("SELECTED TAG IS  : " + selectedTag);
		AdvSearchResultsPages searchResultsPage = AdvSearchPage.searchMarketPlaceByTag();
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.OVERLAY_LOADING)));
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.CSS_LOADING)));
			
			String accordianHDR=searchResultsPage.FindByCssSelector(".handle .title").getText();
			System.out.println("Accordian HDR :"+accordianHDR);
			List<WebElement> searchResultRow = searchResultsPage.FindElementsByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE);
			
			int searchResultRowCount = searchResultRow.size();
			System.out.println("Row count is:"+searchResultRowCount);
			
			//for(int row=1; row<=searchResultRowCount*2; row+=2){
				for(int row=1; row<=3; row+=2){
			
				System.out.println("Row value:"+row);
				
				String rowValueResult = searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).getText();
				System.out.println(searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).getText());
				
				System.out.println("Result row  :"+rowValueResult);
				//searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).click();
				AcqDetasilsPage = searchResultsPage.clickOnProfileTitle(row);
				String currentTab = page.driver.getWindowHandle();
				searchResultsPage.switchToNewFirstTab(currentTab);
				page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(AdvSearchElem.HDR_TAGS_MKT_PLACE)));
				TestLinkResults.appendTestCaseResults("CP-733","p","Tags section has displayed in Acquisition details page");			
				List<WebElement> AllTags = AcqDetasilsPage.FindElementsByCssSelector(AdvSearchElem.CSS_TAGS_LIST_IN_MKT_PLACE);
				System.out.println("TAGS COUNT : " + AllTags.size());
				for(int tagrow=1;tagrow<=AllTags.size();tagrow++){
				String tagsValue = AcqDetasilsPage.FindByCssSelector(AdvSearchElem.CSS_EACH_TAG_IN_MKT_PLACE_Start+tagrow+AdvSearchElem.CSS_EACH_TAG_IN_MKT_PLACE_End).getText();
				System.out.println("DISPLAYED TAGS  : " + tagsValue);
				if(tagsValue.contains(selectedTag)){
					result = "p";
				break;	
				}else{
					result = "f";					
				}
								
				}
				System.out.println("INPUT TAG VALUE : "+selectedTag);
				AcqDetasilsPage.switchToOldTabCloseNewTab(currentTab);
				TestLinkResults.appendTestCaseResults("CP-548",result,"Given Search criteria tag has displayed in Acquisition details page");
				
				
				}
	}
				
				
				
	@Test (description = "RPX-7052 : Verify whether 'Analysis Notes' section removed from this page")
	public void checkAnalysisNotesSectionIsRemovedOrNot(){
		//Marketplace/Acquisition Detail page - Analyst Notes
		AdvSearchPage.FindByCssSelector(AdvSearchElem.TAB_MARKET_PLACE_SEARCH).click();
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.OVERLAY_LOADING)));
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.CSS_LOADING)));
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(AdvSearchPatLitElem.BTN_SEARCH)));
		AdvSearchResultsPages searchResultsPage = AdvSearchPage.searchInMarketPlaceTab();
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.OVERLAY_LOADING)));
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.CSS_LOADING)));
			
			String accordianHDR=searchResultsPage.FindByCssSelector(".handle .title").getText();
			System.out.println("Accordian HDR :"+accordianHDR);
			List<WebElement> searchResultRow = searchResultsPage.FindElementsByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE);
			
			int searchResultRowCount = searchResultRow.size();
			System.out.println("Row count is:"+searchResultRowCount);
			
			//for(int row=1; row<=searchResultRowCount*2; row+=2){
				for(int row=1; row<=3; row+=2){
			
				System.out.println("Row value:"+row);
				
				String rowValueResult = searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).getText();
				System.out.println(searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).getText());
				
				System.out.println("Result row  :"+rowValueResult);
				//searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).click();
				AcqDetasilsPage = searchResultsPage.clickOnProfileTitle(row);
				String currentTab = page.driver.getWindowHandle();
				searchResultsPage.switchToNewFirstTab(currentTab);
				page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(AdvSearchElem.HDR_TAGS_MKT_PLACE)));
							
				List<WebElement> AllJumpLinks = AcqDetasilsPage.FindElementsByCssSelector(AdvSearchElem.CSS_TAGS_LIST_IN_MKT_PLACE);
				System.out.println("JUMP LINKS : " + AllJumpLinks.size());
				for(int tagrow=1;tagrow<=AllJumpLinks.size();tagrow++){
				String linkText = AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.CSS_MKT_PLACE_JUMPLINKS_Start+tagrow+AcqDetailsElem.CSS_MKT_PLACE_JUMPLINKS_End).getText();
				System.out.println("JUMP LINK TEXT IS  : " + linkText);
				Assert.assertFalse(linkText.toUpperCase().contains("ANALYSIS NOTES"), "Analysis Notes Section is not removed. Refer RPX-7052 defect");
				}
				
				AcqDetasilsPage.switchToOldTabCloseNewTab(currentTab);
				
				}
		
	}


	@Test (description = "RPX-7057 : Verify whether new fields are displayed in Right side of Acquisition details page")
	public void checkAcquisitionDetailsPgRightSideContent(){
		AdvSearchPage.FindByCssSelector(AdvSearchElem.TAB_MARKET_PLACE_SEARCH).click();
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.OVERLAY_LOADING)));
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.CSS_LOADING)));
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(AdvSearchPatLitElem.BTN_SEARCH)));
		AdvSearchResultsPages searchResultsPage = AdvSearchPage.searchInMarketPlaceTab();
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.OVERLAY_LOADING)));
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.CSS_LOADING)));
			
			String accordianHDR=searchResultsPage.FindByCssSelector(".handle .title").getText();
			System.out.println("Accordian HDR :"+accordianHDR);
			List<WebElement> searchResultRow = searchResultsPage.FindElementsByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE);
			
			int searchResultRowCount = searchResultRow.size();
			System.out.println("Row count is:"+searchResultRowCount);
			
			//for(int row=1; row<=searchResultRowCount*2; row+=2){
				for(int row=1; row<=4; row+=2){
			
				System.out.println("Row value:"+row);
				
				String rowValueResult = searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).getText();
				System.out.println(searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).getText());
				
				System.out.println("Result row  :"+rowValueResult);
						//searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).click();
				AcqDetasilsPage = searchResultsPage.clickOnProfileTitle(row);
				String currentTab = page.driver.getWindowHandle();
				searchResultsPage.switchToNewFirstTab(currentTab);
				page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(AdvSearchElem.HDR_TAGS_MKT_PLACE)));
				Assert.assertTrue(AcqDetasilsPage.isElementByCSSPresent(AcqDetailsElem.TXT_ACQ_DETAILS_BIDS_DUE), "Acquisition details page doesn't have Bids Due field in RHS side");
				Assert.assertTrue(AcqDetasilsPage.isElementByCSSPresent(AcqDetailsElem.TXT_ACQ_DETAILS_ACQ_LEADE), "Acquisition details page doesn't have Acquisition Lead field in RHS side");
				Assert.assertTrue(AcqDetasilsPage.isElementByCSSPresent(AcqDetailsElem.TXT_ACQ_DETAILS_ANALYSIS_POINT_OF_CONTACT), "Acquisition details page doesn't have Analysis Point of Contact field in RHS side");
				AcqDetasilsPage.switchToOldTabCloseNewTab(currentTab);
				
				}		
	}

		
	@Test (description = "RPX-6938: verify the Current Assignee sections in Acquisition details page")
	public void checkCurrentAssigneeSection(){

		//marketplacePage = page.topMenu.hoverNclickOnMarketplace();	
		marketplacePage = page.topMenu.goToMarketplaceByUrl();
		Assert.assertTrue(marketplacePage.isElementByCSSPresent(MarketplaceElem.CSS_BROWSE_MARKETPLACE_BUTTON),"Browse MarketPlace button is not present");
		marketplacePage.clickOnBrowseMarketPlace();
		
		//Assert.assertTrue(marketplacePage.isElementByCSSPresent(AdvSearchResultsElem.CSS_PAT_LIT_SEARCH_TABLE),"Search results are not present");

		/*page.topMenu.selectAdvSearchTypeInSearchBanner("Marketplace");
		marketplacePage.FindById("searchq").clear();
		marketplacePage.FindById("searchq").sendKeys("Siemens Gesture");
		marketplacePage.FindById("searchq").submit();
		*/
		
		searchResultsPage = page.topMenu.SearchForMarketplaceInAdvSearchBanner("Siemens Gesture");		

		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.OVERLAY_LOADING)));
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.CSS_LOADING)));
		
		String marketPlaceName= marketplacePage.FindByCssSelector(AdvSearchResultsElem.start_TAB_ROW+1+") a").getText().trim().toUpperCase();
			//String accordianHDR=searchResultsPage.FindByCssSelector(".handle .title").getText();
			System.out.println("Accordian HDR :"+marketPlaceName);
			List<WebElement> searchResultRow = marketplacePage.FindElementsByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE);
			
			int searchResultRowCount = searchResultRow.size();
			System.out.println("Row count is:"+searchResultRowCount);
			
			//for(int row=1; row<=searchResultRowCount*2; row+=2){
				for(int row=1; row<=1; row+=2){
			
				System.out.println("Row value:"+row);
				System.out.println(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END);
				String rowValueResult = marketplacePage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).getText();
				System.out.println(marketplacePage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).getText());
				
				System.out.println("Result row  :"+rowValueResult);
						//searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).click();
				AcqDetasilsPage = marketplacePage.clickOnProfileTitle(row);
				String currentTab = page.driver.getWindowHandle();
				marketplacePage.switchToNewFirstTab(currentTab);
				page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(AdvSearchElem.HDR_TAGS_MKT_PLACE)));
				
			// Current Assignee section validation
				Assert.assertTrue(AcqDetasilsPage.isElementByCSSPresent(AcqDetailsElem.HDR_CURR_ASSIGNEE_SEC), "Header section of Current Assignee is not displayed ");
				Assert.assertTrue(AcqDetasilsPage.isElementByCSSPresent(AcqDetailsElem.TXT_CURR_ASSIGNEE_CONTENT), "Content section of Current Assignee is not displayed");
				Assert.assertTrue(AcqDetasilsPage.isElementByCSSPresent(AcqDetailsElem.CSS_TBL_CURR_ASSIGNEE), "Table section of Current Assignee is not displayed");
				
				if(AcqDetasilsPage.isElementByCSSPresent(AcqDetailsElem.CSS_TBL_CURR_ASSIGNEE)){					
					result = "p";
				}else if(AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_CURR_ASS_FOR_NO_DATA).getText().toLowerCase().contains("no related  assignee portfolios to display")){
					result = "p";
					
				}else{
					result = "f";
				}
									
				TestLinkResults.appendTestCaseResults("CP-974", result, "Current Assignee table and records are displayed");
				
				if (AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_TBL_CURR_ASS_COLM_PORTFOLIO_NAME).getText().toUpperCase().contains("PORTFOLIO NAME")||
					AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_TBL_CURR_ASS_COLM_TYPE).getText().toUpperCase().contains("TYPE")||
					AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_TBL_CURR_ASS_COLM_BROKER).getText().toUpperCase().contains("BROKER")||
					AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_TBL_CURR_ASS_COLM_NO_OF_SOLDPART).getText().toUpperCase().contains("NO OF SOLD PART")||
					AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_TBL_CURR_ASS_COLM_BUYER).getText().toUpperCase().contains("BUYER")||
					AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_TBL_CURR_ASS_COLM_SOLD_TO_NPE).getText().toUpperCase().contains("SOLD TO NPE")||
					AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_TBL_CURR_ASS_COLM_RESULTING_DEF).getText().toUpperCase().contains("RESULTING DEFENDANTS")||
					AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_TBL_CURR_ASS_COLM_RECEIVED).getText().toUpperCase().contains("RECEIVED")){
					result = "p";	
				}else{
					result = "f";	
				}
				TestLinkResults.appendTestCaseResults("CP-795", result, "Column headers are sortables");
				TestLinkResults.appendTestCaseResults("CP-796", result, "Valid Column headers texts are displayed in current Assignee section ");
				List<WebElement> tblRes = AcqDetasilsPage.FindElementsByCssSelector("#current_assignee  tr");
				System.out.println("SO CURR ASSIG TBL RECORDS SIZE: " + tblRes.size());
				int recCount;
				if(tblRes.size()>6){
				  recCount = 5; 				  
				}else{
					recCount = tblRes.size()-1;
				}
				
				for(int rec=1;rec<=recCount;rec++){
				for(int i=1;i<=8;i++){
					System.out.println(AcqDetailsElem.CSS_TBL_CURR_ASS_VALUE_START+i+AcqDetailsElem.CSS_TBL_CURR_ASS_VALUE_END);
					String tempVal = AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.CSS_TBL_CURR_ASS_VALUE_START+i+AcqDetailsElem.CSS_TBL_CURR_ASS_VALUE_END).getText();
					System.out.println(tempVal);
					if(tempVal.isEmpty()){
					result = "f";		
				}else{
					result = "p";	
					}
							
				}
				}
				AcqDetasilsPage.switchToOldTabCloseNewTab(currentTab);
				TestLinkResults.appendTestCaseResults("CP-794", result, "Valid content has displayed");
				
			}
	
}	
	
@Test (description = "RPX-6991: verify the Assignments sections in Acquisition details page")
	public void checkAssignmentSection(){
				
		AdvSearchPage.FindByCssSelector(AdvSearchElem.TAB_MARKET_PLACE_SEARCH).click();
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.OVERLAY_LOADING)));
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.CSS_LOADING)));
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(AdvSearchPatLitElem.BTN_SEARCH)));
		AdvSearchResultsPages searchResultsPage = AdvSearchPage.searchInMarketPlaceTab();
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.OVERLAY_LOADING)));
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(AdvSearchResultsElem.CSS_LOADING)));
			
			String accordianHDR=searchResultsPage.FindByCssSelector(".handle .title").getText();
			System.out.println("Accordian HDR :"+accordianHDR);
			List<WebElement> searchResultRow = searchResultsPage.FindElementsByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE);
			
			int searchResultRowCount = searchResultRow.size();
			System.out.println("Row count is:"+searchResultRowCount);
			
			//for(int row=1; row<=searchResultRowCount*2; row+=2){
				for(int row=1; row<=4; row+=2){
			
				System.out.println("Row value:"+row);
				
				String rowValueResult = searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).getText();
				System.out.println(searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).getText());
				
				System.out.println("Result row  :"+rowValueResult);
				//searchResultsPage.FindByCssSelector(AdvSearchResultsElem.ACCORDIAN_TITLE_START+row+AdvSearchResultsElem.ACCORDIAN_TITLE_END).click();
				AcqDetasilsPage = searchResultsPage.clickOnProfileTitle(row);
				String currentTab = page.driver.getWindowHandle();
				searchResultsPage.switchToNewFirstTab(currentTab);
				page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(AdvSearchElem.HDR_TAGS_MKT_PLACE)));
		
				
				// Assignment Section validation				
				if(AcqDetasilsPage.isElementByCSSPresent(AcqDetailsElem.CSS_TBL_CURR_ASSIGNEE)){					
					result = "p";
				}else if(AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_CURR_ASS_FOR_NO_DATA).getText().toLowerCase().contains("no related  assignee portfolios to display")){
					result = "p";
					
				}else{
					result = "f";
				}
				TestLinkResults.appendTestCaseResults("CP-978", result, "Assignment Table displayed records");
				
				Assert.assertTrue(AcqDetasilsPage.isElementByCSSPresent(AcqDetailsElem.HDR_ASSIGNMENT_SEC), "Header section of Assignment is not displayed");
				if(AcqDetasilsPage.isElementByCSSPresent(AcqDetailsElem.TXT_TBL_ASSIGNMENT_COLM_ASSIGNEE)){
				if (AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_TBL_ASSIGNMENT_COLM_ASSIGNEE).getText().toUpperCase().contains("ASSIGNEE")||
					AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_TBL_ASSIGNMENT_COLM_INTO_PORTFOLIO).getText().toUpperCase().contains("INTO PORTFOLIO")||
					AcqDetasilsPage.FindByCssSelector(AcqDetailsElem.TXT_TBL_ASSIGNMENT_COLM_OUT_OF_PORTFOLIO).getText().toUpperCase().contains("OUT OF PORTFOLIO")
					) {
					result = "p";
					TestLinkResults.appendTestCaseResults("CP-795", result, "valid Assignment Table column headers are displayed");
				}
				
				//page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(AcqDetailsElem.TXT_TBL_ASSIGNMENT_COLM_ASSIGNEE)));
				List<WebElement> tblRes = AcqDetasilsPage.FindElementsByCssSelector("#assignments tr");
				System.out.println("SO ASSIGNMENT TBL RECORDS SIZE: " + tblRes.size());
				int recSize = (tblRes.size()-1)/2;
				for(int i=1;i<=recSize;i++){
					String tempVal1 = searchResultsPage.FindByCssSelector(AcqDetailsElem.CSS_TBL_ASSINGMENT_VALUE_START+i+AcqDetailsElem.CSS_TBL_ASSINGMENT_VALUE_END).getText();
					System.out.println(tempVal1);
				if(tempVal1.isEmpty()){
					result = "p";
				}else{
					result = "f";					
				}
				
				TestLinkResults.appendTestCaseResults("CP-794", result, "Assignment Table records are displayed");
						
				} 
				}
				else{
					TestLinkResults.appendTestCaseResults("CP-794", result, "Assignment no records are displayed");
					TestLinkResults.appendTestCaseResults("CP-795", result, "but no assignment is displayed");
				}// above for loop ends
				AcqDetasilsPage.switchToOldTabCloseNewTab(currentTab);
			} 
		
	
		
		}
		

}
	
				
	

	
	

	


