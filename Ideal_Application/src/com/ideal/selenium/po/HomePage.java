
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
		
		 FindByName(ElemProp.ID_USER_NAME).clear();
		 FindByName(ElemProp.ID_USER_NAME).sendKeys(CONFG.getProperty("USER_EMAIL"));
		 FindByName(ElemProp.ID_USER_PASS_CODE).clear();
		 FindByName(ElemProp.ID_USER_PASS_CODE).sendKeys(CONFG.getProperty("USER_PASS_CODE"));
		 FindById(ElemProp.CL_LOGIN_BUTTON_VALUE).click();
		 page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".sessions-page")));
		 return new DashBoardPage();
		
	 }

	public void clearLoginCredentials() {
		
		FindById(ElemProp.ID_USER_NAME).clear();
		FindById(ElemProp.ID_USER_PASS_CODE).clear();

	}

	public void LoginInvalidCredentials(String user_EMAIL,String user_INVALID_PWD) {
		
		FindById(ElemProp.ID_USER_NAME).sendKeys(user_EMAIL);
		FindById(ElemProp.ID_USER_PASS_CODE).clear();
		FindById(ElemProp.ID_USER_PASS_CODE).sendKeys(user_INVALID_PWD);
		FindByCssSelector(ElemProp.CL_LOGIN_BUTTON_VALUE).click();
	}
	
	
	


}

