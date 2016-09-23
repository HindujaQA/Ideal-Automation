package com.ideal.selenium.po;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;


import org.junit.internal.runners.statements.Fail;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import com.ideal.selenium.po.elements.ElemProp;

import com.ideal.selenium.basePage.page;
import com.ideal.selenium.po.EmployeeExitPageUser;



public class TopNav {
	
	public EmployeeExitPageUser goToEmployeeExitByUrl(){
		String currentUrl = page.driver.getCurrentUrl();
		if(currentUrl.trim().toUpperCase().contains("/IDEAL/"))
			currentUrl = currentUrl.replaceAll("(http://10.18.1.54)(/iDeal/)", "$1$2")+"request_management/exit_management/";
		else
			currentUrl = currentUrl.replaceAll("(http://10.18.1.54/)(\\w+-*\\w+)(.*)", "$1$2")+"request_management/exit_management/";
		page.driver.navigate().to(currentUrl);		
		return new EmployeeExitPageUser();
	}
	
	public ResourceRequestFormPage goToRRFByUrl(){
		String currentUrl = page.driver.getCurrentUrl();
		if(currentUrl.trim().toUpperCase().contains("/IDEAL/"))
			currentUrl = currentUrl.replaceAll("(http://10.18.1.54)(/iDeal/)", "$1$2")+"request_management/rrf_requestor/";
		else
			currentUrl = currentUrl.replaceAll("(http://10.18.1.54/)(\\w+-*\\w+)(.*)", "$1$2")+"request_management/rrf_requestor/";
		page.driver.navigate().to(currentUrl);		
		return new ResourceRequestFormPage();
	}
	


}
