package com.ideal.selenium.pages.tests;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.rpxcorp.cp.selenium.basePage.page;
import com.rpxcorp.cp.selenium.po.AdminAlertsPageInAccounts;
import com.rpxcorp.cp.selenium.po.DashBoardPage;
import com.rpxcorp.cp.selenium.po.HomePage;
import com.rpxcorp.cp.selenium.po.UserPreferencesPage;
import com.rpxcorp.cp.selenium.po.elements.AdminAlertsElem;
import com.rpxcorp.cp.selenium.po.elements.DbElem;
import com.rpxcorp.cp.selenium.po.elements.HeaderElem;
import com.rpxcorp.cp.selenium.po.elements.MyAlertElem;
import com.rpxcorp.cp.selenium.po.elements.UserPrefElem;
import com.rpxcorp.cp.selenium.util.ReadExcel;

public class TestAdminAlerts {
	
	private HomePage home = null;
	private DashBoardPage dbPage = null;
	private AdminAlertsPageInAccounts adminAlertsPage = null;

	
	@BeforeTest
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
	public AdminAlertsPageInAccounts initAdminAlertPage(){
		if(dbPage == null){
			home = new HomePage();
			dbPage = home.Login();
			String successSignInText = dbPage.FindById(DbElem.ID_FLASH_TEXT).getText();
			System.out.println("Signing again after executing method in Test My Profile class");
			if(successSignInText.contains("Signed in successfully")){
				if (dbPage.getTitle().contains("Dashboard"))				
					System.out.println("Signed in Successfully....In Dashboard page");
				else
					throw new IllegalStateException("User is not in Dashboard Page: " + page.driver.getTitle());				
			}else{
				throw new IllegalStateException("User is not in Dashboard Page: " + page.driver.getTitle());
			}
			
		}else if(page.driver.getTitle().matches(".*"+MyAlertElem.HDR_MY_ALERTS+".*")){
			System.out.println("In My Alerts page before executing method");
		}else{
			
			if(!(page.driver.getTitle().contains("Client Portal Admin")))
					{
			adminAlertsPage = page.topMenu.goToAdminAlertsByUrl();
			//page.wait.until(ExpectedConditions.visibilityOf(adminAlertsPage.FindByCssSelector(AdminAlertsElem.HDR_ADMIN_PAGE)));
			//LIST_TOP_NAV_LINKS
			page.wait.until(ExpectedConditions.visibilityOf(adminAlertsPage.FindByCssSelector(AdminAlertsElem.LIST_TOP_NAV_LINKS)));
			// Page title validation
			//page.driver.manage().timeouts().implicitlyWait(40L, TimeUnit.SECONDS);
			System.out.println(adminAlertsPage.getTitle());
					}
			
			
			// My Alerts Page Content validation
			//Assert.assertEquals(myAlertsPage.FindByCssSelector(MyAlertElem.HDR_LITIGATION_ALERT).getText().trim().toUpperCase(), MyAlertElem.HDR_LITIGATION_ALERT.trim().toUpperCase());
		
		}
		return adminAlertsPage;	
	}
	
	@Test (description = "Getting Email Preview for ")
	public void checkEmailPreview(){
		if(!(page.driver.getTitle().contains("Client Portal Admin")))
		{
		adminAlertsPage = page.topMenu.goToAdminAlertsByUrl();
		}
		List<WebElement> listPara =adminAlertsPage.FindElementsByCssSelector(AdminAlertsElem.LIST_LNKS_ADMIN_TXT);
		for(WebElement para:listPara){			
			//System.out.println("Terms of Service Para text:"+para.getText());
			List<WebElement> listLinks = para.findElements(By.tagName("a"));
			System.out.println("Total links in para:"+listLinks.size());
			for(WebElement lnk:listLinks){
				System.out.println("Terms of Service link text:"+lnk.getText());
				System.out.println("Terms of Service link URL :"+lnk.getAttribute("href"));
			}
		}
		
		//page.wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(AdminAlertsElem.CSS_ALERTS_BUTTON)));
		//adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_ALERTS_BUTTON ).click();
		
		String pathExcel = System.getProperty("user.dir")+"\\src\\com\\rpxcorp\\cp\\selenium\\util\\TestCase.xlsx";
		System.out.println(pathExcel);
		ReadExcel testDataExcel = new ReadExcel(pathExcel);
		System.out.println("Reading from xls at: "+pathExcel);
		
		for(int rowNum=2;rowNum<=testDataExcel.getRowCount("Qa-cpEmails");rowNum++){
			System.out.println("RowNum:"+rowNum);
	
		 String excel_Data = testDataExcel.getStringCellData("Qa-cpEmails", rowNum, "EmailId").trim();
		 
		 System.out.println("Emaild is" +excel_Data);
		
		 adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_EMAIL_FIELD ).clear();
		 adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_EMAIL_FIELD ).sendKeys(excel_Data);
		
			 
		 adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_DATE_FIELD ).click();
		 Assert.assertTrue(adminAlertsPage.isElementByCSSPresent(AdminAlertsElem.CSS_CLK_PREVIEW_BUTTON),AdminAlertsElem.CSS_CLK_PREVIEW_BUTTON+" :  Calender Box doesn't exists in Alerts page");
		 page.wait.until(ExpectedConditions.visibilityOf(adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_CALENDER_BOX)));
		 adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_DATEPICK_BOX ).click();
	
		 page.wait.until(ExpectedConditions.visibilityOf(adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_CLK_PREVIEW_BUTTON)));
		 adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_CLK_PREVIEW_BUTTON ).click();
		 
		// New window or New Tab Handling
			String currentTab = page.driver.getWindowHandle();
			adminAlertsPage.switchToNewFirstTab(currentTab);
			
			String RPXAlertsPage = page.driver.getTitle().toUpperCase();
			System.out.println("Current Page title:" +RPXAlertsPage);
			
			if(page.driver.getTitle().toUpperCase().contains("ALERTS")){
			page.wait.until(ExpectedConditions.visibilityOf(adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_PANEL_ALERT_PAGE)));
			System.out.println("Current page is Alerts Preview");
			//page.wait.until(ExpectedConditions.visibilityOfAllElements(adminAlertsPage.FindElementsByCssSelector("table>tbody>tr>td>table[width='550']")));
			//Assert.assertEquals(adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_DETAIL_UPDATE_TITLE).getText().trim().toUpperCase(), AdminAlertsElem.TXT_DETAIL_UPDATE_TITLE.trim().toUpperCase());
			//System.out.println("DETAILED UPDATE title validation done");
			adminAlertsPage.switchToOldTabCloseNewTab(currentTab);
			}else{
				///Assert.assertTrue(page.driver.getTitle().toUpperCase().contains("ALERTS"), "Email Preview page is not displayed for : "+excel_Data);
				adminAlertsPage.switchToOldTabCloseNewTab(currentTab);
			}
			}
			
		}
	
	
	@Test (description = "Getting Email address from Excel")
	public void checkEmptyEmailField(){
		
		//page.wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(AdminAlertsElem.CSS_ALERTS_BUTTON)));
		//adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_ALERTS_BUTTON ).click();
		if(!(page.driver.getTitle().contains("Client Portal Admin")))
		{
		adminAlertsPage = page.topMenu.goToAdminAlertsByUrl();
		}
		adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_EMAIL_FIELD ).clear();
				
		adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_DATE_FIELD ).click();
		Assert.assertTrue(adminAlertsPage.isElementByCSSPresent(AdminAlertsElem.CSS_CLK_PREVIEW_BUTTON),AdminAlertsElem.CSS_CLK_PREVIEW_BUTTON+" :  Calender Box doesn't exists in Alerts page");
		page.wait.until(ExpectedConditions.visibilityOf(adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_CALENDER_BOX)));
		adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_DATEPICK_BOX ).click();
			
		page.wait.until(ExpectedConditions.visibilityOf(adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_CLK_PREVIEW_BUTTON)));
		adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_CLK_PREVIEW_BUTTON ).click();
		
		// New window or New Tab Handling
		String currentTab = page.driver.getWindowHandle();
		adminAlertsPage.switchToNewFirstTab(currentTab);
		
		String RPXAlertsPage = page.driver.getTitle().toUpperCase();
		System.out.println("Current Page title:" +RPXAlertsPage);
		
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(AdminAlertsElem.CSS_INVALID_EMAIL_ERROR)));
		String errorText=adminAlertsPage.FindByCssSelector(AdminAlertsElem.CSS_INVALID_EMAIL_ERROR).getText();
		System.out.println("Error message in  Alert Preview Page :" +errorText);
		Assert.assertTrue(errorText.toLowerCase().trim().contains("email you entered does not exist"), "Valid Error message is not displayed for blank email id - Alert Preview ");
		adminAlertsPage.switchToOldTabCloseNewTab(currentTab);
	}
	
	
	@Test (description = "defect:RPX-6797 verify whether delete icon displayed in preview Email page")
	public void checkAlertPagePreviewEmail(){
		
		String email_ID = "mgandhivel@rpxcorp.com";
		String Day = "9"; String Month = "Jul"; String Year = "2014";
		//page.wait.until(ExpectedConditions.visibilityOf(adminAlertsPage.FindByCssSelector(AdminAlertsElem.HDR_ADMIN_PAGE)));
		System.out.println("page title:"+page.driver.getTitle());
		if(!(page.driver.getTitle().contains("Client Portal Admin")))
		{
		AdminAlertsPageInAccounts alertsEmailPreivewPage = page.topMenu.goToAdminAlertsByUrl();
		
		alertsEmailPreivewPage.FindByCssSelector(AdminAlertsElem.CSS_EMAIL_FIELD ).clear();
		alertsEmailPreivewPage.FindByCssSelector(AdminAlertsElem.CSS_EMAIL_FIELD ).sendKeys(email_ID);
			
		alertsEmailPreivewPage.SelectFromDateInCalendar(AdminAlertsElem.TXT_DATE_FIELD,Month,Year,Day);
		//page.wait.until(ExpectedConditions.visibilityOf(adminAlertsPage.FindByCssSelector(AdminAlertsElem.BTN_PREVIEW_EMAIL)));
		alertsEmailPreivewPage.FindByCssSelector(AdminAlertsElem.BTN_PREVIEW_EMAIL).click();
		
		// New window or New Tab Handling
		String currentTab = page.driver.getWindowHandle();
		System.out.println("Current tab"+currentTab);
		ArrayList<String> newTabs = new ArrayList<String> (page.driver.getWindowHandles());
		newTabs.remove(currentTab);
		alertsEmailPreivewPage.switchToNewFirstTab(currentTab);
		
		
		if(alertsEmailPreivewPage.isElementByCSSPresent(AdminAlertsElem.CSS_PREVIEW_EMAIL_550_TBL)){
			Assert.assertTrue(alertsEmailPreivewPage.FindByCssSelector(AdminAlertsElem.IMG_DELETE_IN_PREVIEW_EMAIL).isDisplayed(), "Delete Icon is not displayed in Email Preview of "+ email_ID + " and Date is : "+Day+Month+Year);
			System.out.println("DELETE ICON DISPLAYED");
		}
		alertsEmailPreivewPage.switchToOldTabCloseNewTab(currentTab);
		
		}
	}


	@Test (description = "defect:RPX-6944 and RPX-6807 verify preview Email page with specific date")
	public void checkPreviewEmailWithSpecificDate(){
		
		if(!(page.driver.getTitle().contains("Client Portal Admin")))
		{
		AdminAlertsPageInAccounts alertsEmailPreivewPage = page.topMenu.goToAdminAlertsByUrl();
		
		String pathExcel = System.getProperty("user.dir")+"\\src\\com\\rpxcorp\\cp\\selenium\\util\\TestData.xlsx";
		System.out.println(pathExcel);
		ReadExcel testDataExcel = new ReadExcel(pathExcel);
		System.out.println("Reading from xls at: "+pathExcel);
		
		for(int rowNum=2;rowNum<=testDataExcel.getRowCount("Email_Preview_data");rowNum++){
		
		
		String email_ID = testDataExcel.getStringCellData("Email_Preview_data", rowNum, "EmailID").trim();
		String dateVal = testDataExcel.getStringCellData("Email_Preview_data", rowNum, "Date").trim();
		String[] previewDate = dateVal.split("-");
		String Day = previewDate[0]; String Month = previewDate[1]; String Year = previewDate[2];
		//page.wait.until(ExpectedConditions.visibilityOf(adminAlertsPage.FindByCssSelector(AdminAlertsElem.HDR_ADMIN_PAGE)));
		System.out.println("page title:"+page.driver.getTitle());
			
		alertsEmailPreivewPage.FindByCssSelector(AdminAlertsElem.CSS_EMAIL_FIELD ).clear();
		alertsEmailPreivewPage.FindByCssSelector(AdminAlertsElem.CSS_EMAIL_FIELD ).sendKeys(email_ID);
			
		alertsEmailPreivewPage.SelectFromDateInCalendar(AdminAlertsElem.TXT_DATE_FIELD,Month,Year,Day);
		//page.wait.until(ExpectedConditions.visibilityOf(adminAlertsPage.FindByCssSelector(AdminAlertsElem.BTN_PREVIEW_EMAIL)));
		alertsEmailPreivewPage.FindByCssSelector(AdminAlertsElem.BTN_PREVIEW_EMAIL).click();
		
		// New window or New Tab Handling
		String currentTab = page.driver.getWindowHandle();
		
		ArrayList<String> newTabs = new ArrayList<String> (page.driver.getWindowHandles());
		newTabs.remove(currentTab);
		alertsEmailPreivewPage.switchToNewFirstTab(currentTab);
		System.out.println(" Email preview page title:"+page.driver.getTitle());
		WebDriverWait emailPreview = new WebDriverWait(page.driver,50);
		emailPreview.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(AdminAlertsElem.IMG_RPX_LOGO_PREVIEW_EMAIL)));		
		if(!alertsEmailPreivewPage.getTitle().trim().toUpperCase().contains("RPX 500 ERROR")|| 
		page.driver.getTitle().trim().toUpperCase().contains("RPX NOT FOUND")){
			Assert.assertTrue(alertsEmailPreivewPage.FindByCssSelector(AdminAlertsElem.CSS_PREVIEW_EMAIL_MAIN_TBL_CONTENT).isDisplayed(), "Delete Icon is not displayed in Email Preview of "+ email_ID + " and Date is : "+Day+Month+Year);
			System.out.println("PREVIEW EMAIL CONTENT DISPLAYED");
		}else{
			Assert.assertTrue(alertsEmailPreivewPage.getTitle().trim().toUpperCase().contains("RPX 500 ERROR"), "RPX 500 Error Page displayed for Alerts email Preview");
			}
		alertsEmailPreivewPage.switchToOldTabCloseNewTab(currentTab);
		}
		
		}
	}

	
	}
	






			
