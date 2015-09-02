package com.rpxcorp.cp.selenium.po;

import com.rpxcorp.cp.selenium.basePage.page;
import com.rpxcorp.cp.selenium.po.elements.HeaderElem;

public class AccountsInAdminPage extends page {
	
public PriorArtPageInIntelligence hoverNclickOnInteligenceMenu(){
		
		hoverNclickOnSubNavLink(HeaderElem.LNK_INTELLIGENCE,HeaderElem.TXT_INTELLIGENCE_PRIORART_TITLE);
		return new PriorArtPageInIntelligence();
	}

private void hoverNclickOnSubNavLink(String lnkIntelligence,
		String txtIntelligencePriorartTitle) {
	// TODO Auto-generated method stub
	
}

}
