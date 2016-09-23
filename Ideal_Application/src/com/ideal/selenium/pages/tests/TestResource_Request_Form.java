package com.ideal.selenium.pages.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByClassName;
import org.testng.Assert;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.ideal.selenium.basePage.page;
import com.ideal.selenium.po.DashBoardPage;
import com.ideal.selenium.po.HomePage;
import com.ideal.selenium.po.ResourceRequestFormPage;
import com.ideal.selenium.po.elements.ElemProp;
import com.ideal.selenium.po.elements.ResourceRequestFormElem;


public class TestResource_Request_Form extends page{
	private HomePage home ;
	private DashBoardPage dbPage ;
	private ResourceRequestFormPage RRFPage ;
	
	
	@BeforeTest (alwaysRun=true) 
	public DashBoardPage initDBPageBeforeTest(){
		if(dbPage == null){
			HomePage home= new HomePage();
			dbPage=home.Login("Login",2,"USER_EMAIL","USER_PASS_CODE");
			//System.out.println("page title:"+dbPage.getTitle());
			if (page.driver.getTitle().trim().toUpperCase().contains("ACTION CONTROLLER")|| 
					page.driver.getTitle().trim().toUpperCase().contains("EXCEPTION CAUGHT")||
					page.driver.getTitle().trim().toUpperCase().contains("RPX 500 ERROR")){
				
				System.out.println("In 500 error loop");
					
				Assert.assertFalse(home.FindByClassName(ElemProp.MY_PROFILE_HEADING).getText().trim().toUpperCase().contains(ElemProp.IdealTitle), "Not in Ideal Home Page");				
			
			}else{
				page.wait.until(ExpectedConditions.presenceOfElementLocated(By.className(ElemProp.MY_PROFILE_HEADING)));
				Assert.assertTrue(home.FindByClassName(ElemProp.MY_PROFILE_HEADING).getText().trim().toUpperCase().contains(ElemProp.IdealTitle.trim().toUpperCase()) , "Not in Ideal Home Page");				
			}
				}
		return dbPage;

	} 
	@BeforeMethod (alwaysRun=true) 
	public ResourceRequestFormPage beforeMethodRRFPage(){
		if(dbPage == null){
			HomePage home= new HomePage();
			dbPage=home.Login("Login",2,"USER_EMAIL","USER_PASS_CODE");
			//System.out.println("page title:"+dbPage.getTitle());
			if (page.driver.getTitle().trim().toUpperCase().contains("ACTION CONTROLLER")|| 
					page.driver.getTitle().trim().toUpperCase().contains("EXCEPTION CAUGHT")||
					page.driver.getTitle().trim().toUpperCase().contains("RPX 500 ERROR")){
				
				System.out.println("In 500 error loop");
					
				Assert.assertTrue(home.FindByClassName(ElemProp.MY_PROFILE_HEADING).getText().trim().toUpperCase().contains(ElemProp.IdealTitle), "Not in Ideal Home Page");				
			
			}else{
				System.out.println("Header Expected"+home.FindByClassName(ElemProp.MY_PROFILE_HEADING).getText().trim().toUpperCase());
				System.out.println("Header Actual" +ElemProp.IdealTitle.trim().toUpperCase());
				Assert.assertTrue(home.FindByClassName(ElemProp.MY_PROFILE_HEADING).getText().trim().toUpperCase().contains(ElemProp.IdealTitle.trim().toUpperCase()) , "Not in Ideal Home Page");				
			}
		}
		
		RRFPage=page.topMenu.goToRRFByUrl();
				
	return RRFPage;

} 


	@Test (description = "RRF Creation")
	public void CreateRRF(){
		System.out.println("In RRF Creation");
		RRFPage.switchToframeByID(ResourceRequestFormElem.RRF_IFRAME);
		RRFPage.FindByCssSelector(ResourceRequestFormElem.NEW_REQUEST).click();
		page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ResourceRequestFormElem.NEW_REQUEST_HEADER)));
		Assert.assertEquals(RRFPage.FindByClassName(ResourceRequestFormElem.NEW_REQUEST_HEADER).getText().trim().toUpperCase(), ResourceRequestFormElem.NEW_REQUEST_HDR_NAME.trim().toUpperCase());
		
		//Tab Request Details
		
		RRFPage.FindByCssSelector(ResourceRequestFormElem.TAB_REQUEST_DETAIL).click();
		
		WebElement businessGrp = RRFPage.FindById(ResourceRequestFormElem.BUSINESS_GRP); 
		Select BusinessGRP= new Select(businessGrp);
		BusinessGRP.selectByVisibleText(page.testExcel.getStringCellData("RRF_Creation", 2, "Value"));
		
		WebElement practiceGrp = RRFPage.FindById(ResourceRequestFormElem.PRACTICE_GRP); 
		Select PracticeGrp= new Select(practiceGrp);
		PracticeGrp.selectByVisibleText(page.testExcel.getStringCellData("RRF_Creation", 6, "Value"));			

		WebElement SubPracticeGRP = RRFPage.FindById(ResourceRequestFormElem.SUB_PRACTICE_GRP); 
		Select Sub_PracticeGRP = new Select(SubPracticeGRP);
		Sub_PracticeGRP.selectByVisibleText(page.testExcel.getStringCellData("RRF_Creation", 9, "Value"));
		

		WebElement ResourceType = RRFPage.FindById(ResourceRequestFormElem.RESOURCE_TYPE); 
		Select Resource_Type = new Select(ResourceType);
		Resource_Type.selectByVisibleText(page.testExcel.getStringCellData("RRF_Creation", 3, "Value"));
		
		WebElement RecruitmentType = RRFPage.FindById(ResourceRequestFormElem.REQ_TYPE); 
		Select Recruitment_Type = new Select(RecruitmentType);
		Recruitment_Type.selectByVisibleText(page.testExcel.getStringCellData("RRF_Creation", 7, "Value"));


		WebElement ProposedBand = RRFPage.FindById(ResourceRequestFormElem.PROPOSED_BAND); 
		Select Proposed_Band = new Select(ProposedBand);
		Proposed_Band.selectByVisibleText(page.testExcel.getStringCellData("RRF_Creation", 10, "Value"));
		
		WebElement Country= RRFPage.FindById(ResourceRequestFormElem.COUNTRY); 
		Select Country_1 = new Select(Country);
		Country_1.selectByVisibleText(page.testExcel.getStringCellData("RRF_Creation", 4, "Value"));
		
		WebElement Region= RRFPage.FindById(ResourceRequestFormElem.REGION); 
		Select Region_1 = new Select(Region);
		Region_1.selectByVisibleText(page.testExcel.getStringCellData("RRF_Creation", 8, "Value"));
		
		
		WebElement City= RRFPage.FindById(ResourceRequestFormElem.CITY); 
		Select CITY_1 = new Select(City);
		CITY_1.selectByVisibleText(page.testExcel.getStringCellData("RRF_Creation", 11, "Value"));
		

		WebElement Project_Location= RRFPage.FindById(ResourceRequestFormElem.PROJECT_LOCATION); 
		Select Project_Loc = new Select(Project_Location);
		Project_Loc.selectByVisibleText(page.testExcel.getStringCellData("RRF_Creation", 5, "Value"));
		
/*		RRFPage.FindById(ResourceRequestFormElem.SAVE_BTN).click();
		page.driver.switchTo().defaultContent();
		RRFPage.switchToframeByID(ResourceRequestFormElem.RRF_IFRAME);
		
		page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ResourceRequestFormElem.SAVE_MSG_LOC)));
		
		Assert.assertEquals(RRFPage.FindById((ResourceRequestFormElem.SAVE_MSG_LOC)).getText().trim().toUpperCase(), (ResourceRequestFormElem.SAVE_MSG).trim().toUpperCase());
		
		// RRF Profile Details		*/
		
		RRFPage.FindByCssSelector(ResourceRequestFormElem.PROFILE_DETAILS).click();
		page.driver.switchTo().defaultContent();
		RRFPage.switchToframeByID(ResourceRequestFormElem.RRF_IFRAME);
		page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ResourceRequestFormElem.QUALIFICATION)));
		
		System.out.println("Value :"+page.testExcel.getStringCellData("RRF_Creation", 12, "Value"));
		//RRFPage.FindByCssSelector("#profile_details_div .travelDetails_new tr :nth-child(1)#totalExperienceFrom").sendKeys(page.testExcel.getStringCellData("RRF_Creation", 12, "Value")); 
		RRFPage.FindByCssSelector(ResourceRequestFormElem.TOTAL_EXP_FRM).sendKeys(page.testExcel.getStringCellData("RRF_Creation", 12, "Value"));
		RRFPage.FindByCssSelector(ResourceRequestFormElem.TOTAL_EXP_TO).sendKeys(page.testExcel.getStringCellData("RRF_Creation", 13, "Value"));
		RRFPage.FindByCssSelector(ResourceRequestFormElem.RELEVANT_EXP_FRM).sendKeys(page.testExcel.getStringCellData("RRF_Creation", 14, "Value"));
		RRFPage.FindByCssSelector(ResourceRequestFormElem.RELEVANT_EXP_TO).sendKeys(page.testExcel.getStringCellData("RRF_Creation", 15, "Value"));
		RRFPage.FindByCssSelector(ResourceRequestFormElem.NO_OF_RESOURCES).sendKeys(page.testExcel.getStringCellData("RRF_Creation", 16, "Value"));    
		RRFPage.FindByCssSelector(ResourceRequestFormElem.QUALIFICATION).sendKeys(page.testExcel.getStringCellData("RRF_Creation", 17, "Value"));
		RRFPage.FindByCssSelector(ResourceRequestFormElem.CERTIFICATION).sendKeys(page.testExcel.getStringCellData("RRF_Creation", 18, "Value"));
		String Select_Text=page.testExcel.getStringCellData("RRF_Creation", 19, "Value");
		System.out.println("Value Of Domain"+Select_Text);
		RRFPage.selectOptionFromDropDown(ResourceRequestFormElem.DOMAIN, Select_Text);
		RRFPage.selectOptionFromDropDown(ResourceRequestFormElem.SKILL_TYPE, page.testExcel.getStringCellData("RRF_Creation", 20, "Value"));
		RRFPage.selectOptionFromDropDown(ResourceRequestFormElem.TOOLS, page.testExcel.getStringCellData("RRF_Creation", 21, "Value"));
		RRFPage.selectOptionFromDropDown(ResourceRequestFormElem.AREA_OF_EXPERTISE, page.testExcel.getStringCellData("RRF_Creation", 22, "Value"));
		
		RRFPage.FindByCssSelector(ResourceRequestFormElem.CUST_PROJ_BILL_DTL).click();
		
		page.driver.switchTo().defaultContent();
		RRFPage.switchToframeByID(ResourceRequestFormElem.RRF_IFRAME);
		
		RRFPage.FindByCssSelector(ResourceRequestFormElem.ONBOARD_DATE).click();
		RRFPage.FindByLink(page.testExcel.getStringCellData("RRF_Creation", 24, "Value")).click();
		RRFPage.FindByCssSelector(ResourceRequestFormElem.JOB_TITLE).sendKeys(page.testExcel.getStringCellData("RRF_Creation", 25, "Value"));
		RRFPage.FindByCssSelector(ResourceRequestFormElem.JOB_DESCRIPTION).sendKeys(page.testExcel.getStringCellData("RRF_Creation", 26, "Value"));
		RRFPage.FindByCssSelector(ResourceRequestFormElem.COMMENT).sendKeys(page.testExcel.getStringCellData("RRF_Creation", 27, "Value"));
		RRFPage.FindByCssSelector(ResourceRequestFormElem.VALIDATE_DATE).click();
		page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-datepicker-div")));
		RRFPage.FindByCssSelector("#ui-datepicker-div .ui-datepicker-header .ui-icon.ui-icon-circle-triangle-e").click();
		RRFPage.FindByLink(page.testExcel.getStringCellData("RRF_Creation", 31, "Value")).click();
		page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ResourceRequestFormElem.VALIDATE_ALERT)));
		RRFPage.FindByCssSelector(ResourceRequestFormElem.VALIDATE_ALERT).click();
		
		RRFPage.FindByCssSelector(ResourceRequestFormElem.SUBMIT_BTN).click();
		
		
		
		
		
		
		
		
	}
}
