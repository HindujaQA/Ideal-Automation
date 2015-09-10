package com.ideal.selenium.pages.tests;

import org.testng.annotations.Test;

import com.ideal.selenium.po.*;

public class TestLogin {
	//private HomePage home = null;
	
	@Test (description = "Login ")
	public void checkLogin(){
		HomePage home= new HomePage();
		home.Login();


	}
}