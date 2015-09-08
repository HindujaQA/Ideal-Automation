package com.ideal.selenium.basePage;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResultsPage extends page{
	
	
	
	public String SearchedFor(){
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1 .subheader")));
		String resultsKeyword = FindByCssSelector("h1 span.subheader").getText();
		String keyword_trim = resultsKeyword.substring(1, resultsKeyword.length()-1);
		return keyword_trim;
	}
	
	
	public boolean SearchFound(){
		
		
		
		
		
		return true;
	}
	
	
	
	
	

}
