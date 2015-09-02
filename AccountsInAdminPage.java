package com.rpxcorp.cp.selenium.po;

import com.rpxcorp.cp.selenium.basePage.page;
import com.rpxcorp.cp.selenium.po.elements.HeaderElem;

public class AccountsInAdminPage extends page {
	
public PriorArtPageInIntelligence hoverNclickOnInteligenceMenu(){
		
		hoverNclickOnSubNavLink(HeaderElem.LNK_INTELLIGENCE,HeaderElem.TXT_INTELLIGENCE_PRIORART_TITLE);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1 .subheader")));
		String resultsKeyword = FindByCssSelector("h1 span.subheader").getText();
		String keyword_trim = resultsKeyword.substring(1, resultsKeyword.length()-1);
		return keyword_trim;
		
	}

private void hoverNclickOnSubNavLink(String lnkIntelligence,
		String txtIntelligencePriorartTitle) {
	// TODO Auto-generated method stub
	
}

}
