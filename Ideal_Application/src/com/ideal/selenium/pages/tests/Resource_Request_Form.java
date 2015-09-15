package com.ideal.selenium.pages.tests;

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

import com.ideal.selenium.basePage.page;
import com.ideal.selenium.po.DashBoardPage;
import com.ideal.selenium.po.HomePage;
import com.ideal.selenium.po.elements.ElemProp;

public classTestResource_Request_Form extends page{
	private HomePage home = null;
	private DashBoardPage dbPage = null;
	
	@BeforeMethod (alwaysRun=true) 
	public DashBoardPage initDBPageBeforeTest(){
		if(dbPage == null){
			HomePage home= new HomePage();
			home.Login();
			//System.out.println("page title:"+dbPage.getTitle());
			if (page.driver.getTitle().trim().toUpperCase().contains("ACTION CONTROLLER")|| 
					page.driver.getTitle().trim().toUpperCase().contains("EXCEPTION CAUGHT")||
					page.driver.getTitle().trim().toUpperCase().contains("RPX 500 ERROR")){
				
				System.out.println("In 500 error loop");
					
				Assert.assertTrue(home.FindByClassName(ElemProp.MY_PROFILE_HEADING).getText().trim().toUpperCase().contains(ElemProp.IdealTitle), "Not in Ideal Home Page");				
			
			}else{
				System.out.println("In 500 error else loop");
				
				Assert.assertTrue(home.FindByClassName(ElemProp.MY_PROFILE_HEADING).getText().trim().toUpperCase().contains(ElemProp.IdealTitle), "Not in Ideal Home Page");				
			}
				}
		return dbPage;

	} 

	@Test (description = "RRF Creation")
	public void CreateRRF(){
		DashBoardPage dbPage = new DashBoardPage();
		System.out.println("In RRF Creation");
		page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ElemProp.MY_PROFILE_HEADING)));
		dbPage.MovetoElement("MSS","Requestor");
			
		

	}


}
