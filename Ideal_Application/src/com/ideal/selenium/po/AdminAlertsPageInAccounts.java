package com.rpxcorp.cp.selenium.po;

import org.openqa.selenium.By;

import com.rpxcorp.cp.selenium.basePage.page;
import com.rpxcorp.cp.selenium.po.elements.AdminAlertsElem;
import com.rpxcorp.cp.selenium.po.elements.AdminUserElem;
import com.rpxcorp.cp.selenium.po.elements.PatentDetailsElem;

public class AdminAlertsPageInAccounts extends page{
	
	public AccountsInAdminPage clickAccountMenu(){
		driver.findElement(By.cssSelector(AdminUserElem.CSS_ACCOUNTS_MENU)).click();
/*<<<<<<< HEAD
=======
		
>>>>>>> 548faee9e178d92216b13818a4611d699e370618*/
		return new AccountsInAdminPage();
	}
	
	

}
