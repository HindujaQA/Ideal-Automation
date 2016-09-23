package com.ideal.selenium.pages.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.ideal.selenium.basePage.page;
import com.ideal.selenium.po.*;
import com.ideal.selenium.po.elements.ElemProp;
import org.testng.SkipException;
import com.ideal.selenium.util.TestUtil;

public class TestLogin {
	
	@Test (description = "Login ")
	public void checkLogin(){
		if(!TestUtil.isTestExecutable("checkLogin", page.testExcel))
			throw new SkipException("Test execution is set to NO for reason:"+TestUtil.getReasonForNoExecution("checkLogin", page.testExcel));
		
		HomePage home= new HomePage();
		home.Login();
		page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#main_container > div.center_content > div:nth-child(5) > div.home_title_icon_profile > div")));
			
		home.MovetoElement("MSS", "Requestor");
		
	}
}