
package com.ideal.selenium.po;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ideal.selenium.basePage.page;
import com.ideal.selenium.po.elements.ElemProp;




public class HomePage extends page{
	
	public HomePage (){
		driver.get(CONFG.getProperty("URL_HOMEPAGE"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);		 
	}
	
	
	public DashBoardPage  Login(){
		
		FindById(ElemProp.UserName).clear();
		 FindById(ElemProp.UserName).sendKeys(CONFG.getProperty("USER_EMAIL"));
		 FindById(ElemProp.Password).clear();
		 FindById(ElemProp.Password).sendKeys(CONFG.getProperty("USER_PASS_CODE"));

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);	
		 		 
		 FindById(ElemProp.LoginBtn).click();
		 page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".sessions-page")));
		 return new DashBoardPage();
		
	 }

	
	public void LoginInvalidCredentials() {
		
		FindById(ElemProp.UserName).clear();
		 FindById(ElemProp.UserName).sendKeys(CONFG.getProperty("INV_USER_EMAIL"));
		 FindById(ElemProp.Password).clear();
		 FindById(ElemProp.Password).sendKeys(CONFG.getProperty("INV_USER_PASS_CODE"));

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);	
		 		 
		 FindById(ElemProp.LoginBtn).click();
		 page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".sessions-page")));

	}
	
	
	


}

