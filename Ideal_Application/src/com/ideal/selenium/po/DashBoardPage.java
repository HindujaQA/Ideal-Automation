package com.ideal.selenium.po;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ideal.selenium.basePage.page;

      
public class DashBoardPage extends page {




	/*public UserPreferencesPage clickOnViewingMemberLink(){

		//driver.findElement(By.cssSelector("#header_tools a")).click();
		FindByCssSelector("#header_tools a").click();
		return new UserPreferencesPage();

	}*/

	/*public NewsPageInIntelligence clickOnSeeMoreLinkAccordion(int sec_link){

		String start_sec = ".section:nth-child(";
		String end_sec = "n) .options a.see_more_links";
		System.out.println("Click on See more link against section: "+driver.findElement(By.cssSelector(".section:nth-child("+sec_link+"n) h4")).getText().substring(2));
		driver.findElement(By.cssSelector(start_sec+sec_link+end_sec)).click();
		return new NewsPageInIntelligence();

	}
*/
	

	public String getAccordionState(int accordian){
		String start_litWatch_acc = ".section:nth-child(2n) ul.accordion > li:nth-child(";
		String end_litWatch_acc = "n) .content.hide";
		String accor_litWatch_state = driver.findElement(By.cssSelector(start_litWatch_acc+accordian+end_litWatch_acc)).getCssValue("display");
		return accor_litWatch_state;
	}


	public String getAccordionTitle(int section,int accordion){
		String title_litWatch_text = getElementAccordionTitle(section,accordion).getText();
		return title_litWatch_text;
	}

	public String getAccordionDate(int section,int accordion){
		String date_litWatch_text = getElementAccordionDate(section,accordion).getText();
		return date_litWatch_text;
	}

	public String getAccordionNews(int section, int accordion){
		String news_litWatch_text = getElementAccordionNews(section,accordion).getText();
		return news_litWatch_text;
	}

	public WebElement getElementAccordionNews(int section, int accordion){
		String start_active_litWatch_ele = ".section:nth-of-type("+section+") ul.accordion > li.active:nth-of-type(";
		String end_news_litWatch = ") p+p";
		WebElement ele_news_litWatch = driver.findElement(By.cssSelector(start_active_litWatch_ele+accordion+end_news_litWatch));
		return ele_news_litWatch;
	}

	public WebElement getElementAccordionDate(int section, int accordion){
		String start_active_litWatch_ele = ".section:nth-of-type("+section+") ul.accordion > li.active:nth-of-type(";
		String end_date_litWatch = ") h6";
		WebElement ele_date_litWatch = driver.findElement(By.cssSelector(start_active_litWatch_ele+accordion+end_date_litWatch));
		return ele_date_litWatch;

	}

	public WebElement getElementAccordionTitle(int section,int accordion){
		String start_title = ".section:nth-of-type("+section+") ul.accordion > li:nth-of-type(";
		String end_title = ") h5";
		WebElement ele_title_litWatch = driver.findElement(By.cssSelector(start_title+accordion+end_title));
		return ele_title_litWatch;

	}

	public WebElement getElementKeyLitPatWatTitle(int accordian,int section) {
		String start_KeyLitPatWat_ele_title = ".section:nth-child("+section+"n) ul.accordion > li:nth-child(";
		String end_KeyLitPatWat__ele_title = "n) h5";
		WebElement ele_title_KeyLitPatWat = driver.findElement(By.cssSelector(start_KeyLitPatWat_ele_title+accordian+end_KeyLitPatWat__ele_title));
		return ele_title_KeyLitPatWat;
	}

	public String getKeyLitPatWatTitle(int accordian,int section) {
		String start_KeyLitPatWat_title = ".section:nth-child("+section+"n) ul.accordion > li:nth-child(";
		String end_KeyLitPatWat_title = "n) h5";
		String title_KeyLitPatWat_text = driver.findElement(By.cssSelector(start_KeyLitPatWat_title+accordian+end_KeyLitPatWat_title)).getText();
		return title_KeyLitPatWat_text;
	}

	public String getKeyLitPatWatDate(int accordian,int section) {
		String start_active_KeyLitPatWat_ele = ".section:nth-child("+section+"n) ul.accordion > li.active:nth-child(";
		String end_date_KeyLitPatWat = "n) h6";
		String date_KeyLitPatWat_text = driver.findElement(By.cssSelector(start_active_KeyLitPatWat_ele+accordian+end_date_KeyLitPatWat)).getText();
		return date_KeyLitPatWat_text;
	}

	public String getKeyLitPatWatNews(int accordian,int section) {
		String start_active_KeyLitPatWat_ele = ".section:nth-child("+section+"n) ul.accordion > li.active:nth-child(";
		String end_news_KeyLitPatWat = "n) p+p";
		String news_KeyLitPatWat_text = driver.findElement(By.cssSelector(start_active_KeyLitPatWat_ele+accordian+end_news_KeyLitPatWat)).getText();
		return news_KeyLitPatWat_text;
	}

	public String getKeyLitPatWatAccordionState(int accordian,int section){
		String start_KeyLitPatWat_acc = ".section:nth-child("+section+"n) ul.accordion > li:nth-child(";
		String end_KeyLitPatWat_acc = "n) .content.hide";
		String accor_KeyLitPatWat_state = driver.findElement(By.cssSelector(start_KeyLitPatWat_acc+accordian+end_KeyLitPatWat_acc)).getCssValue("display");
		return accor_KeyLitPatWat_state;
	}

	public boolean activeCasesFound() {
		//boolean caseFound = false;

		try{
			WebElement noCaseFound = driver.findElement(By.cssSelector("#sidebar .panel:nth-child(2n) .no_case_found"));
			System.out.println("Cases Not Found");
			System.out.println(noCaseFound.getText());
			return false;

			//caseFound = true;
		}catch (NoSuchElementException e){
			//caseFound = true;
			System.out.println("Cases Found");
			List<WebElement> summaryCases = driver.findElements(By.cssSelector("#sidebar .panel:nth-child(2n) #case_stats .note"));
			List<WebElement> countCases = driver.findElements(By.cssSelector("#sidebar .panel:nth-child(2n) #case_stats .note:nth-child(n)+span"));
			System.out.println("Number of Case details: "+summaryCases.size());
			for (int i=0;i<summaryCases.size();i++)
			System.out.println(summaryCases.get(i).getText()+": "+countCases.get(i).getText());

			return true;
		}

		//return caseFound;
	}

	public String getActiveCaseCount(){

		String caseCount = "";
		List<WebElement> summaryCases = driver.findElements(By.cssSelector("#sidebar .panel:nth-child(2n) #case_stats .note"));
		List<WebElement> countCases = driver.findElements(By.cssSelector("#sidebar .panel:nth-child(2n) #case_stats .note:nth-child(n)+span"));
		for (int i=0;i<summaryCases.size();i++)
			if(summaryCases.get(i).getText().contains("Active Cases")){
				caseCount = countCases.get(i).getText();

			}

		return caseCount;

	}

	public boolean isViewingPreferencesSaved(){
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#flash_name")));
		String alertViewPreference = driver.findElement(By.cssSelector("#flash_name")).getText();
		if(alertViewPreference.contains("Viewing preferences saved")){
			System.out.print(alertViewPreference);
			return true;

		}else{
			System.out.println("Unable to save Viewing preference");
			return false;
		}

	}

	public WebElement getElementRecentNPECasePlaintiff(int row){
		String start_npeCase_ele = ".section:nth-child(5n) .super-panel .row:nth-child(";
		String end_plaintiff_npeCase = "n) .plaintiffs";
		WebElement ele_npeCasePlaintiff = driver.findElement(By.cssSelector(start_npeCase_ele+row+end_plaintiff_npeCase));
		return ele_npeCasePlaintiff;
	}

	public WebElement getElementRecentNPECaseDate(int row){
		String start_npeCase_ele = ".section:nth-child(5n) .super-panel .row:nth-child(";
		String end_date_npeCase = "n) p.note b";
		WebElement ele_npeCaseDate = driver.findElement(By.cssSelector(start_npeCase_ele+row+end_date_npeCase));
		return ele_npeCaseDate;
	}

	public WebElement getElementRecentNPECaseLink(int row){
		String start_npeCase_ele = ".section:nth-child(5n) .super-panel .row:nth-child(";
		String end_link_npeCase = "n) h5 > a";
		WebElement ele_npeCaseLink = driver.findElement(By.cssSelector(start_npeCase_ele+row+end_link_npeCase));
		return ele_npeCaseLink;
	}

	public String getNameRecentNPECasePlaintiff(int row){
		String start_npeCase_ele = ".section:nth-child(5n) .super-panel .row:nth-child(";
		String end_plaintiff_npeCase = "n) .plaintiffs";
		String name_npeCasePlaintiff = driver.findElement(By.cssSelector(start_npeCase_ele+row+end_plaintiff_npeCase)).getText();
		return name_npeCasePlaintiff;
	}

	public String getTextRecentNPECaseDate(int row){
		//String start_npeCase_ele = ".section:nth-child(5n) .super-panel .row:nth-child(";
		//String end_date_npeCase = "n) p.note b";
		//String text_npeCaseDate = driver.findElement(By.cssSelector(start_npeCase_ele+row+end_date_npeCase)).getText();
		String text_npeCaseDate = getElementRecentNPECaseDate(row).getText();
		return text_npeCaseDate;
	}

	public String getTextRecentNPECaseLink(int row){
		String text_npeCaseLink = getElementRecentNPECaseLink(row).getText();
		return text_npeCaseLink;
	}


	public AcquisitionDetailsPage goToAcquisitionDetailsPage(String acqId){
		String currentUrl = page.driver.getCurrentUrl();	
		String acqPageUrl;
		if(currentUrl.trim().toUpperCase().contains(".RPXCORP.COM"))
		{
			currentUrl = currentUrl.replaceAll("(https://)(.*)(.rpxcorp.com)(.*)", "$1$2$3");
			acqPageUrl = currentUrl+"/acquisitions/"+acqId;			
		}
		else
		{
			currentUrl = currentUrl.replaceAll("(https*://)(\\w+-*\\w+)(.*)", "$1$2");
			acqPageUrl = currentUrl+"/acquisitions/"+acqId;			
		}
		driver.get(acqPageUrl);
		return new AcquisitionDetailsPage();
	}


	public int getTotalRowsInTable(String cssPropTable){
		List<WebElement> listTotalRows = driver.findElements(By.cssSelector(cssPropTable+">tbody>tr"));
		int countRows = listTotalRows.size();				
		return countRows;
	}

	public boolean isColumnlistedInTable(String cssPropTable,String colName){
		List<WebElement> tableHdr = driver.findElements(By.cssSelector(cssPropTable+">thead>tr th"));
		//System.out.println("Total headers listed in results table:"+tableHdr.size());
		for(int i=0;i<tableHdr.size();i++){
			//System.out.print(tableHdr.get(i).getText());
			if(tableHdr.get(i).getText().trim().toUpperCase().matches(".*"+colName.trim().toUpperCase()+".*")){
				return true;
			}			
		}				
		return false;
	}

	public int getColNumInTable(String cssPropTable,String colName){
		int colNum = 0;		
		List<WebElement> tableHdr = driver.findElements(By.cssSelector(cssPropTable+">thead>tr th"));
		int count_tableHdr = tableHdr.size();
		for(int i=0;i<count_tableHdr;i++){
			//System.out.print(tableHdr.get(i).getText());
			if(tableHdr.get(i).getText().trim().toUpperCase().matches(".*"+colName.trim().toUpperCase()+".*")){
				colNum = i;
			}			
		}		
		return colNum+1;		
	}
	
	public String getRowDataInTable(String cssPropTable,int row,int col){
		String rowDataInRow = driver.findElement(By.cssSelector(cssPropTable+">tbody>tr:nth-of-type("+row+")>td:nth-of-type("+col+")")).getText();
		return rowDataInRow;
	}


	
	
	

}
