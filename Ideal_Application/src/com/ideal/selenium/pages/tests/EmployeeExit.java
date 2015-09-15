package com.ideal.selenium.pages.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ideal.selenium.basePage.page;
import com.ideal.selenium.po.DashBoardPage;
import com.ideal.selenium.po.EmployeeExitPageUser;
import com.ideal.selenium.po.HomePage;
import com.ideal.selenium.po.TopNav;
import com.ideal.selenium.po.elements.ElemProp;
import com.ideal.selenium.po.elements.EmployeeExitProp;



public class EmployeeExit {
	private HomePage home = null;
	private DashBoardPage dbPage = null;
	private EmployeeExitPageUser EmployeeExitPage = null;
	//private TopNav topMenu = null;
	
	@BeforeTest 
	public DashBoardPage initDBPageBeforeTest(){
		if(dbPage == null){
			HomePage home = new HomePage();
			 home.Login();
			//System.out.println("page title:"+dbPage.getTitle());
			if (page.driver.getTitle().trim().toUpperCase().contains("ACTION CONTROLLER")|| 
					page.driver.getTitle().trim().toUpperCase().contains("EXCEPTION CAUGHT")||
					page.driver.getTitle().trim().toUpperCase().contains("RPX 500 ERROR")){
				
				System.out.println("In 500 error loop");
					
				Assert.assertTrue(home.FindByClassName("home_title_about").getText().trim().toUpperCase().contains(ElemProp.IdealTitle), "Not in Ideal Home Page");				
			
			}else{
				System.out.println("In Ideal Home Page");
				
				Assert.assertTrue(home.FindByClassName("home_title").getText().trim().toUpperCase().contains(ElemProp.IdealTitle.trim().toUpperCase()) , "Not in Ideal Home Page");				
			}
		}
		return dbPage;

	} 
	
	
	@BeforeMethod 
	public EmployeeExitPageUser initEmployeeExitPageBeforeTest(){
		if(dbPage == null){
			HomePage home = new HomePage();
			 home.Login();
			//System.out.println("page title:"+dbPage.getTitle());
			if (page.driver.getTitle().trim().toUpperCase().contains("ACTION CONTROLLER")|| 
					page.driver.getTitle().trim().toUpperCase().contains("EXCEPTION CAUGHT")||
					page.driver.getTitle().trim().toUpperCase().contains("RPX 500 ERROR")){
				
				System.out.println("In 500 error loop");
					
				Assert.assertTrue(home.FindByClassName("home_title_about").getText().trim().toUpperCase().contains(ElemProp.IdealTitle), "Not in Ideal Home Page");				
			
			}else{
				System.out.println("In Ideal Home Page");
				
				Assert.assertTrue(home.FindByClassName("home_title").getText().trim().toUpperCase().contains(ElemProp.IdealTitle.trim().toUpperCase()) , "Not in Ideal Home Page");				
			}
		}	
			if(!(page.driver.getTitle().contains("Defiance Enterprise Portal"))){
			//System.out.println("In before method");
			EmployeeExitPage = page.topMenu.goToEmployeeExitByUrl();
			System.out.println(EmployeeExitPage.getTitle());
			
		}
		
		return EmployeeExitPage;

	} 
	


	
	@Test (description = "Employee Exit")
	public void ExitProcess(){
		
		if(!(page.driver.getTitle().contains("Exit Management Process")))
		{
			try{
			EmployeeExitPage = page.topMenu.goToEmployeeExitByUrl();
			}catch(NullPointerException e){
				System.out.println("catch");
			}
		}
	}
}
