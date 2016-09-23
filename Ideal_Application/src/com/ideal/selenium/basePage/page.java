package com.ideal.selenium.basePage;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;




import com.ideal.selenium.util.ReadExcel;
import com.ideal.selenium.po.TopNav;

public class page {

	public static WebDriver driver = null ;
	public static WebDriverWait wait;
	public static Properties CONFG = null;
	public static Properties OR = null;
	public static ReadExcel testExcel = new ReadExcel(System.getProperty("user.dir")+"\\src\\com\\ideal\\selenium\\util\\IDEAL_TESTDATA.xlsx");
	public static Alert alert;
	public static String pageTitle ="";
	public static Logger PAGE_LOGS = Logger.getLogger("cpLogger");
	public static boolean LoggedIn = false;
	public static TopNav topMenu = null;

	public page(){

		if(driver==null){

			//driver = new FirefoxDriver();
			CONFG = new Properties();
			//System.out.println(System.getProperty("user.dir"));
			String configPath = System.getProperty("user.dir")+"\\src\\com\\ideal\\selenium\\config\\config.properties";

			//FileInputStream inp;
			try {
				//load Config properties
				FileInputStream inp = new FileInputStream(configPath);
				CONFG.load(inp);

			}catch (Exception e) {
				e.printStackTrace();
			}

			Reporter.log("Browser: "+CONFG.getProperty("BROWSER").trim().toUpperCase());

			if(CONFG.getProperty("URL_HOMEPAGE").trim().toUpperCase().contains("IDEAL")){
				Reporter.log("Environment:Testing Environment");
			}
			//Reporter.log("URL		 :"+CONFG.getProperty("URL_HOMEPAGE"));
			if (CONFG.getProperty("BROWSER").trim().toUpperCase().equals("FIREFOX"))
				driver = new FirefoxDriver();
			else if (CONFG.getProperty("BROWSER").trim().toUpperCase().equals("CHROME")){
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver_win32_2.7\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if (CONFG.getProperty("BROWSER").trim().toUpperCase().equals("IE")){
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\IEDriverServer_Win32_2.39\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();

			}

			else if (CONFG.getProperty("BROWSER").trim().toUpperCase().equals("HTMLUNIT"))
				driver = new HtmlUnitDriver();

			//changed to 30 seconds as Marketplace page is taking more time to load.
			wait = new WebDriverWait(driver,50);
			topMenu = new TopNav();
			//driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

	}


	public WebElement FindByClassName(String className){
		try{
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
			WebElement element = driver.findElement(By.className(className));
			return element;
		}catch (Exception e){
			//report error
			e.printStackTrace();
			return null;
		}

	}

	public WebElement FindByCssSelector(String selector){
		try{
			WebElement element = driver.findElement(By.cssSelector(selector));
			return element;
		}catch (Exception e){
			//report error
			e.printStackTrace();
			return null;
		}

	}

/*	public WebElement FindByCssSelector(String selector){
		try{
			WebElement element = driver.findElement(By.cssSelector(OR.getProperty(selector)));
			return element;
		}catch (Exception e){
			//report error
			e.printStackTrace();
			return null;
		}
	}*/

	public WebElement FindById(String id){
		try{
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
			WebElement element =  driver.findElement(By.id(id));
			return element;
		}catch (Exception e){
			//report error
			e.printStackTrace();
			return null;
		}

	}

	public WebElement FindByLink(String linkText){
		try{
			WebElement element = driver.findElement(By.linkText(linkText));
			return element;
		}catch (Exception e){
			//report error
			e.printStackTrace();
			return null;
		}

	}

	public WebElement FindByPartialLinkText(String linkText){
		try{
			WebElement element = driver.findElement(By.partialLinkText(linkText));
			return element;
		}catch (Exception e){
			//report error
			e.printStackTrace();
			return null;
		}

	}

	public WebElement FindByName(String name){
		try{
			WebElement element = driver.findElement(By.name(name));
			return element;
		}catch (Exception e){
			//report error
			e.printStackTrace();
			return null;
		}

	}

	public WebElement FindByTagName(String tagName){
		try{
			WebElement element = driver.findElement(By.tagName(tagName));
			return element;
		}catch (Exception e){
			//report error
			e.printStackTrace();
			return null;
		}

	}

	public WebElement FindByXpath(String xpathExpression){
		try{
			WebElement element = driver.findElement(By.xpath(xpathExpression));
			return element;
		}catch (Exception e){
			//report error
			e.printStackTrace();
			return null;
		}

	}

	public List<WebElement> FindElementsByCssSelector(String selector){
		try{
			List<WebElement> elements = driver.findElements(By.cssSelector(selector));
			return elements;
		}catch (Exception e){
			//report error
			e.printStackTrace();
			return null;
		}

	}

	public List<WebElement> FindElementsById(String id){
		try{
			List<WebElement> elements = driver.findElements(By.id(id));
			return elements;
		}catch (Exception e){
			//report error
			e.printStackTrace();
			return null;
		}

	}

	public String getCurrentUrl(){
		//driver.navigate().refresh();
		String url = driver.getCurrentUrl();
		return url;
	}

	public String getTitle(){
		pageTitle = driver.getTitle();
		return pageTitle;
	}




	public SearchResultsPage clickOnSearchTopNavBar(String keyword){

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_input")));
		WebElement site_search = driver.findElement(By.id("search_input"));
		//WebElement site_search = driver.findElement(By.name("searchq"));
		System.out.println("In Search Function");
		site_search.clear();
		site_search.sendKeys(keyword);
		//site_search.submit();
		WebElement search_btn = driver.findElement(By.cssSelector("a.postfix.alert.button.submit_button"));
		search_btn.click();
		return new SearchResultsPage();

	}

	/*
	public boolean isElementByIdPresent(String id){
		try{
			driver.findElement(By.id(id));
		}catch (Exception e){
			return false;
		}

		return true;
	}
	*/
	public boolean isElementByIdPresent(String id){
		int count = driver.findElements(By.id(id)).size();
		//System.out.println("count of childs:"+count);
		if(count>0)
			return true;
		else
			return false;

	}



	public boolean isElementsByIdPresent(String id){
		try{
			driver.findElements(By.id(id));
		}catch (Exception e){
			return false;
		}

		return true;
	}

	/*public boolean isElementByCSSPresent(String selector){
		try{
			driver.findElement(By.cssSelector(selector));
		}catch (Exception e){
			return false;
		}

		return true;
	}*/
	//modified code as try catch takes more time.
	//count(>0) is '1' if element is found(true) otherwise it is zero(false)
	public boolean isElementByCSSPresent(String selector){
		int count = driver.findElements(By.cssSelector(selector)).size();
		//System.out.println("count of childs:"+count);
		if(count>0)
			return true;
		else
			return false;

	}

	//isListOfElementsByCssPresent
	public boolean isElementsByCSSPresent(String selector){
		try{
			driver.findElements(By.cssSelector(selector));
		}catch (Exception e){
			return false;
		}

		return true;
	}


	public boolean isLinkPresent(String linkText){
		/*try{
			//driver.findElement(By.linkText(linkText));
			page.wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
			driver.findElement(By.linkText(linkText));
			//driver.findElement(By.partialLinkText(linkText));
		}catch (Exception e){
			return false;
		}

		return true;
		*/

		int count = driver.findElements(By.linkText(linkText)).size();
		//System.out.println("count of childs:"+count);
		if(count>0)
			return true;
		else
			return false;

	}

	public boolean isPartialLinkTextPresent(String linkText){
		try{
			page.wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(linkText)));
			driver.findElement(By.partialLinkText(linkText));
		}catch (Exception e){
			return false;
		}

		return true;
	}


	public boolean isAlertPresent(){
		try{
			alert = driver.switchTo().alert();
			return true;
		}catch (Exception e){
			return false;
		}

	}

	
	

	public boolean isChildElementByTagPresent(WebElement parent,String tagName){

		int count = parent.findElements(By.tagName(tagName)).size();
		if(count>0)
			 return true;
		else
			 return false;

	}

	public boolean isElementByClassNamePresent(String className){
		try{
			driver.findElement(By.className(className));
		}catch (Exception e){
			return false;
		}

		return true;
	}

	public boolean isElementByTagPresent(String tagName){
		try{
			driver.findElement(By.tagName(tagName));
		}catch (Exception e){
			return false;
		}
		return true;
	}
	public page navigateBack(){
		page.driver.navigate().back();
		return new page();
	}

	public int getListSizeByCss(String selector){
		int count = driver.findElements(By.cssSelector(selector)).size();
		return count;
	}

	public boolean isChildElementsByCssPresent(String selector){

		int count = driver.findElements(By.cssSelector(selector)).size();
		System.out.println("count of childs:"+count);
		if(count>0) // child exists
			return true;
		else        // no child
			return false;

	}

	public boolean isChildElementByCssPresent(WebElement parent,String selector){

		int count = parent.findElements(By.cssSelector(selector)).size();
		if(count>0)
			 return true;
		else
			 return false;

	}


	public List<WebElement> FindElementsByTagName(String tagName){
		try{
			List<WebElement> elements = driver.findElements(By.tagName(tagName));
			return elements;
		}catch (Exception e){
			//report error
			e.printStackTrace();
			return null;
		}

	}

	/*public SearchResultsPage SearchFor(String keyword){

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_input")));
		//WebElement site_search = driver.findElement(By.id("search_input"));
		WebElement site_search = FindById("search_input");
		//WebElement site_search = driver.findElement(By.name("searchq"));
		System.out.println("In Search Function");
		site_search.clear();
		if(keyword==""){
			site_search.sendKeys(keyword);
			site_search.submit();
		}else{
			site_search.sendKeys(keyword);
			WebElement search_btn = FindByCssSelector("a.postfix.alert.button.submit_button");
			search_btn.click();
		}

		//WebElement search_btn = driver.findElement(By.cssSelector("a.postfix.alert.button.submit_button"));
		//--WebElement search_btn = FindByCssSelector("a.postfix.alert.button.submit_button");
		//--search_btn.click();
		return new SearchResultsPage();
	}*/

	public boolean isElementPresent(By by){

		/*try {
			 driver.findElement(by);
		      return true;
		 }catch (NoSuchElementException e) {
			 return false;
		 }
		*/
		 int count = driver.findElements(by).size();

		 if(count>0)
			 return true;
		 else
			 return false;

	}


	public boolean isTextToBePresentInElement(By by, String text){
		String txtElement = driver.findElement(by).getText();

		if(txtElement.trim().toUpperCase().contains(text.trim().toUpperCase()))
			return true;
		else
			return false;

	}

	public boolean isTextToBePresentInChildElement(WebElement parent,By by, String text){
		String txtElement = parent.findElement(by).getText();

		if(txtElement.trim().toUpperCase().contains(text.trim().toUpperCase()))
			return true;
		else
			return false;

	}

	public boolean listsMatchingWordFrom(String str,String anotherStr){
		ArrayList<String> wordsInKeyword = new ArrayList<String>();
		int count = anotherStr.trim().split(" ").length;
		String[] split = anotherStr.trim().split(" ");
		System.out.println(count+":words listed in string:"+anotherStr);
		for(int i=1;i<=count;i++){
			wordsInKeyword.add(split[i-1]);
		}

		for(String word:wordsInKeyword){
			////only word chars except &,-
			if(str.trim().replaceAll("[^-&\\w]", "").toUpperCase().contains(word.trim().replaceAll("[^-&\\w]", "").toUpperCase())){
				System.out.println(word+":found in:"+str);
				return true;
			}
		}
		return false;

	}
	//ex: str:"Ohio Northern District Court" matches keyword:"Ohio Northern District Court"
	//OR
	//ex: str:"Ohio Northern District Court" matches keyword:Ohio/Northern/District/Court
	public boolean isKeywordListedInString(String str,String keyword){
		if(str.trim().toUpperCase().contains(keyword.trim().toUpperCase())){
			return true;
		}else if(listsMatchingWordFrom(str, keyword)){
			return true;
		}
		return false;
	}



	public void switchToNewFirstTab(String currentTab){
		//get handles to new tabs
		ArrayList<String> newTabs = new ArrayList<String> (driver.getWindowHandles());
		int totalTabs = newTabs.size();
		//--System.out.println("total tabs:"+totalTabs);
		if(totalTabs>1){
			newTabs.remove(currentTab);
			//Switch to new tab
			driver.switchTo().window(newTabs.get(totalTabs-2));
		}

	}

	public void switchToOldTabCloseNewTab(String currentTab){
		driver.close();
		driver.switchTo().window(currentTab);
	}


/*	
	
	public void SelectFromDateInCalendar(String dateField,String fromMonth,String fromYear,String fromDay){

		driver.findElement(By.cssSelector(dateField)).click();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(NewsElem.CSS_DATE_PICKER)));

		Select selectYear = new Select(driver.findElement(By.cssSelector(NewsElem.CSS_SELECT_YEAR)));
		selectYear.selectByVisibleText(fromYear);
		Select selectMonth = new Select(driver.findElement(By.cssSelector(NewsElem.CSS_SELECT_MONTH)));
		if(fromMonth.matches("\\d*")){
			//since in calendar values for months are from '0' to '11'
			fromMonth = String.valueOf(Integer.valueOf(fromMonth)-1);
			selectMonth.selectByValue(fromMonth);
		}else if(fromMonth.matches("\\D*")){
			selectMonth.selectByVisibleText(fromMonth);
		}
		//selectMonth.selectByVisibleText(fromMonth);
		List<WebElement> dayList= driver.findElements(By.cssSelector(NewsElem.CSS_SELECT_DAY));
		boolean foundDay = false;
		for(WebElement selectDay:dayList){
			if(selectDay.getText().equals(fromDay)){
				selectDay.click();
				foundDay = true;
				break;
			}
		}
		if(foundDay == false){
			System.out.println("Invalid from day:"+fromDay+" opted in calendar");
			driver.findElement(By.cssSelector(dateField)).click();
		}
		//String dateSelected = driver.findElement(By.cssSelector(dateField)).getAttribute("value");
		//return dateSelected;

	}
*/
	
	
	/*
	public void SelectToDateInCalendar(String dateField,String toMonth,String toYear,String toDay){

		driver.findElement(By.cssSelector(dateField)).click();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(NewsElem.CSS_DATE_PICKER)));

		Select selectYear = new Select(driver.findElement(By.cssSelector(NewsElem.CSS_SELECT_YEAR)));

		try{
			selectYear.selectByVisibleText(toYear);
		}catch(Exception e){
			System.out.println("Invalid year:"+toYear+" w.r.t from date selection");
			selectYear.getFirstSelectedOption().getAttribute("value");
		}

		Select selectMonth = new Select(driver.findElement(By.cssSelector(NewsElem.CSS_SELECT_MONTH)));

		try{
			//selectMonth.selectByVisibleText(toMonth);
			if(toMonth.matches("\\d*")){
				//since in calendar values for months are from '0' to '11'
				toMonth = String.valueOf(Integer.valueOf(toMonth)-1);
				selectMonth.selectByValue(toMonth);
			}else if(toMonth.matches("\\D*")){
				selectMonth.selectByVisibleText(toMonth);
			}
		}catch(Exception e){
			System.out.println("Invalid month:"+toMonth+" selected w.r.t from date year:"+toYear);
			selectMonth.getFirstSelectedOption().getAttribute("value");
		}

		List<WebElement> dayList= driver.findElements(By.cssSelector(NewsElem.CSS_SELECT_DAY));
		boolean foundDay = false;
		for(WebElement selectDay:dayList){
			if(selectDay.getText().equals(toDay)){
				selectDay.click();
				foundDay = true;
				break;
			}
		}
		if(foundDay == false){
			System.out.println("Invalid to day:"+toDay+" opted in calendar");
			driver.findElement(By.cssSelector(dateField)).click();
		}

		//String dateSelected = driver.findElement(By.cssSelector(dateField)).getAttribute("value");
		//return dateSelected;

	}
	//get value of attribute "value"
	//Used for month comparison
	public String getMonthInSelectedDate(String dateField){
		String selectedMonth = "";
		driver.findElement(By.cssSelector(dateField)).click();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(NewsElem.CSS_DATE_PICKER)));
		Select selectMonth = new Select(driver.findElement(By.cssSelector(NewsElem.CSS_SELECT_MONTH)));
		selectedMonth = selectMonth.getFirstSelectedOption().getAttribute("value");
		return selectedMonth;
	}
*/
	
	
	
	/*
	public String getYearInSelectedDate(String dateField){
		String selectedYear = "";
		driver.findElement(By.cssSelector(dateField)).click();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(NewsElem.CSS_DATE_PICKER)));
		Select selectYear = new Select(driver.findElement(By.cssSelector(NewsElem.CSS_SELECT_YEAR)));
		selectedYear = selectYear.getFirstSelectedOption().getAttribute("value");
		return selectedYear;
	}
	*/
	
	
	/*
	public String getDayInSelectedDate(String dateField){
		String selectedDay = "";
		driver.findElement(By.cssSelector(dateField)).click();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(NewsElem.CSS_DATE_PICKER)));
		selectedDay = driver.findElement(By.cssSelector(NewsElem.CSS_SELECTED_DAY)).getText();
		return selectedDay;
	}
*/
	
	/*
	public String getSelectedDateInDateField(String dateField){
		String selectedDate = "";
		String selectedMonth="";
		String selectedDay="";
		String selectedYear="";
		driver.findElement(By.cssSelector(dateField)).click();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(NewsElem.CSS_DATE_PICKER)));
		Select selectMonth = new Select(driver.findElement(By.cssSelector(NewsElem.CSS_SELECT_MONTH)));
		selectedMonth = selectMonth.getFirstSelectedOption().getAttribute("value");
		Select selectYear = new Select(driver.findElement(By.cssSelector(NewsElem.CSS_SELECT_YEAR)));
		selectedYear = selectYear.getFirstSelectedOption().getAttribute("value");
		selectedDay = driver.findElement(By.cssSelector(NewsElem.CSS_SELECTED_DAY)).getText();
		selectedMonth = String.valueOf(Integer.valueOf(selectedMonth)+1);
		if(Integer.valueOf(selectedDay)<9){
			//Calendar lists '1' instead of "01" for day. Results list "01"
			selectedDay = "0"+selectedDay;
			//selectedDate = String.valueOf(Integer.valueOf(selectedMonth)+1)+"/"+"0"+selectedDay+"/"+selectedYear;
		}
		//selectedDate = String.valueOf(Integer.valueOf(selectedMonth)+1)+"/"+selectedDay+"/"+selectedYear;
		if(Integer.valueOf(selectedMonth)<9){
			//Calendar lists '1' instead of "01" for month. Results list "01"
			selectedMonth = "0"+selectedMonth;
		}
		selectedDate = selectedMonth+"/"+selectedDay+"/"+selectedYear;

		return selectedDate;
	}
*/
	
	
	
	public boolean listsFirstMatchingWordFrom(String str,String anotherStr){
		ArrayList<String> wordsInKeyword = new ArrayList<String>();
		int count = anotherStr.trim().split(" ").length;
		//String[] split = anotherStr.trim().split(" ");
		String[] split = anotherStr.trim().split(" ", 2);
		System.out.println(count+":words listed in string:"+anotherStr);
		for(int i=1;i<=count;i++){
			wordsInKeyword.add(split[i-1]);
		}

		for(String word:wordsInKeyword){
			if(str.trim().toUpperCase().contains(word.trim().toUpperCase())){
				System.out.println(word+":found in:"+str);
				return true;
			}
		}
		return false;

	}
	//ex: str:"Ohio Northern District Court" matches keyword:"Ohio Northern District Court"
	//OR
	//ex: str:"Ohio Northern District Court" matches keyword:Ohio/Northern/District/Court
	public boolean isFirstKeywordListedInString(String str,String keyword){
		if(str.trim().toUpperCase().contains(keyword.trim().toUpperCase())){
			return true;
		}else if(listsFirstMatchingWordFrom(str, keyword)){
			return true;
		}
		return false;
	}

	
	/*
	public String getMonthNameInSelectedDate(String dateField){
		String selectedMonth = "";
		driver.findElement(By.cssSelector(dateField)).click();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(NewsElem.CSS_DATE_PICKER)));
		Select selectMonth = new Select(driver.findElement(By.cssSelector(NewsElem.CSS_SELECT_MONTH)));
		selectedMonth = selectMonth.getFirstSelectedOption().getText();
		return selectedMonth;
	}
*/
	
	/*
	public int getIntValYearInSelectedDate(String dateField){
		int selectedYear = 0;
		driver.findElement(By.cssSelector(dateField)).click();
		page.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(NewsElem.CSS_DATE_PICKER)));
		Select selectYear = new Select(driver.findElement(By.cssSelector(NewsElem.CSS_SELECT_YEAR)));
		String yearSselected = selectYear.getFirstSelectedOption().getAttribute("value");
		selectedYear = Integer.valueOf(yearSselected);
		return selectedYear;
	}
	
	*/
	
	

	/*public String getPreviousMonthDateInDateField(String dateField){
		String prevMonthDate = "";
		String selectedDate = getSelectedDateInDateField(dateField);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = new GregorianCalendar();
		try {
			Date date_formatted = sdf.parse(selectedDate);
			//System.out.println("SDF PARSE:"+date_formatted);
			//setting calendar to selectedDate
			calendar.setTime(date_formatted);
			Date date1 = calendar.getTime();
			System.out.println("Date from selected Date:"+date1);
			calendar.add(Calendar.MONTH, -1);
			Date newDate1 = calendar.getTime();
			System.out.println("new Date from previous month:"+newDate1);
			System.out.println("new Formatted Previous month Date:"+sdf.format(newDate1));
			prevMonthDate = sdf.format(newDate1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return prevMonthDate;

	}*/


	public boolean isColumnlistedInTable(String cssTableProp, String colName){
		List<WebElement> tableHdr = driver.findElements(By.cssSelector(cssTableProp+">thead>tr th"));
		//System.out.println("Total headers listed in table:"+tableHdr.size());
		for(int i=0;i<tableHdr.size();i++){
			//System.out.print(tableHdr.get(i).getText());
			if(tableHdr.get(i).getText().trim().toUpperCase().matches(".*"+colName.trim().toUpperCase()+".*")){
				return true;
			}
		}
		return false;
	}

	public int getColNumInTable(String cssTableProp, String colName){
		int colNum = 0;
		List<WebElement> tableHdr = driver.findElements(By.cssSelector(cssTableProp+">thead>tr th"));
		int count_tableHdr = tableHdr.size();
		for(int i=0;i<count_tableHdr;i++){
			//System.out.print(tableHdr.get(i).getText());
			if(tableHdr.get(i).getText().trim().toUpperCase().matches(".*"+colName.trim().toUpperCase()+".*")){
				colNum = i;
			}
		}
		return colNum+1;
	}

	public String getCurrentWindowHandle(){
		String currentTab = driver.getWindowHandle();
		return currentTab;
	}

	public boolean isChildElementPresent(WebElement parent,By by){

		int count = parent.findElements(by).size();
		if(count>0)
			 return true;
		else
			 return false;

	}
	//Used to get title in Entity/Litigation panel titles
	public String getPanelTitle(String cssPropPanel){
		String hdrPanel = driver.findElement(By.cssSelector(cssPropPanel+" h2")).getText();
		//System.out.println("hdr panel after trim:"+hdrPanel.trim());

		if(isElementByCSSPresent(cssPropPanel+" h2 a.view-as-search")){
			String txtViewsAsSearch = driver.findElement(By.cssSelector(cssPropPanel+" h2 a.view-as-search")).getText().trim();
			if(!txtViewsAsSearch.isEmpty()){
				hdrPanel = hdrPanel.substring(0, hdrPanel.indexOf(txtViewsAsSearch));
			}
		}
		if(isElementByCSSPresent(cssPropPanel+" h2 span.status")){
			String statusTagText = driver.findElement(By.cssSelector(cssPropPanel+" h2 span.status")).getText();
			if(!statusTagText.isEmpty()){
				hdrPanel = hdrPanel.substring(0, hdrPanel.indexOf(statusTagText));
			}
		}
		hdrPanel = hdrPanel.trim().replaceAll("(\\d*)(.*)", "$2").trim();
		//System.out.println("Panel header is:"+hdrPanel);
		return hdrPanel;
	}

	public String getRowDataInTable(int rowNum,int colNum,String cssTableProp){
		String rowDataInRow = driver.findElement(By.cssSelector(cssTableProp+">tbody>tr:nth-of-type("+rowNum+")>td:nth-of-type("+colNum+")")).getText();
		return rowDataInRow;
	}

	public String getLnkRowDataInTable(int rowNum,int colNum,String cssTableProp){
		String rowDataInRow = driver.findElement(By.cssSelector(cssTableProp+">tbody>tr:nth-of-type("+rowNum+")>td:nth-of-type("+colNum+")>a")).getText();
		return rowDataInRow;
	}
	
	public int getCountInPanelTitle(String cssPropPanel){
		String hdrPanel = driver.findElement(By.cssSelector(cssPropPanel+" h2")).getText();
		//System.out.println("hdr panel after trim:"+hdrPanel.trim());

		if(isElementByCSSPresent(cssPropPanel+" h2 a.view-as-search")){
			String txtViewsAsSearch = driver.findElement(By.cssSelector(cssPropPanel+" h2 a.view-as-search")).getText().trim();
			if(!txtViewsAsSearch.isEmpty()){
				hdrPanel = hdrPanel.substring(0, hdrPanel.indexOf(txtViewsAsSearch));
			}
		}
		if(isElementByCSSPresent(cssPropPanel+" h2 span.status")){
			String statusTagText = driver.findElement(By.cssSelector(cssPropPanel+" h2 span.status")).getText();
			if(!statusTagText.isEmpty()){
				hdrPanel = hdrPanel.substring(0, hdrPanel.indexOf(statusTagText));
			}
		}

		System.out.println("Count in Panel header is:"+hdrPanel.trim().replaceAll("(\\d*)(.*)", "$1").trim());

		int countPanel = Integer.valueOf(hdrPanel.trim().replaceAll("(\\d*)(.*)", "$1").trim());
		return countPanel;
	}
	
	public String getUrlForLnkRowDataInTable(int rowNum,int colNum,String cssTableProp){
		String rowDataInRow = driver.findElement(By.cssSelector(cssTableProp+">tbody>tr:nth-of-type("+rowNum+")>td:nth-of-type("+colNum+")>a")).getAttribute("href");
		return rowDataInRow;
	}
	
	public boolean isColListedInTable(String cssTableProp, String colName){
		List<WebElement> tableHdr = driver.findElements(By.cssSelector(cssTableProp+">thead>tr th"));
		int count_tableHdr = tableHdr.size();
		for(int i=0;i<count_tableHdr;i++){
			//System.out.print(tableHdr.get(i).getText());
			if(tableHdr.get(i).getText().trim().toUpperCase().matches(".*"+colName.trim().toUpperCase()+".*")){
				return true;
			}
		}
		return false;
	}
	
	//pick the random value between 1 & n (maxValue)
	public int pickRandomValue(int maxValue){
		//default
		int r=1;
		int[] numbers = new int[maxValue];
		for(int i=0;i<numbers.length;i++){
			r= (int)(Math.random()*maxValue);
			return r;
		}		
		return r;
	}

	//pick x number of random values between 1 & n (maxValue)
	public int[] pickRandomValues(int x,int maxValue){
		//array of random values
		int[] res= new int[x];
		int[] numbers = new int[maxValue];
		//storing valid values 1 to maxValue in numbers
		for(int i=1;i<=numbers.length;i++)
			numbers[i-1]= i; 

		for(int i=0;i<res.length;i++){
			//generate random value between 0 & maxValue -1
			int r = (int) (Math.random()*maxValue);			
			//pick value from random location
			res[i] = numbers[r];
			//move the last element into the random location
			numbers[r] = numbers[maxValue - 1];
			maxValue--;
		}
		return res;		
	}
	
	//function can used while adding new assertions & also to modify existing assertions.
	/*public void changeAssertion(String cssAsserElement){
		
		String valueLabel = driver.findElement(By.cssSelector(cssAsserElement+MyAssertionElem.CURRENT_VALUE)).getText();
		String label = driver.findElement(By.cssSelector(cssAsserElement+MyAssertionElem.CURRENT_LABEL)).getText();
		System.out.println(label+":label current value:"+valueLabel);
		driver.findElement(By.cssSelector(cssAsserElement+MyAssertionElem.CURRENT_VALUE)).click();
		page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MyAssertionElem.SELECT_STATUS_BOX)));
		List<WebElement> listStatus = driver.findElements(By.cssSelector(MyAssertionElem.LIST_SELECT_ASSERT_STATUS));
		System.out.println(listStatus.size()+": values listed under assertion label:"+label);
		int randomValue = pickRandomValue(listStatus.size());
		String status = listStatus.get(randomValue).getText();
		System.out.println("Value to select:"+status);
		listStatus.get(randomValue).click();		
		page.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(MyAssertionElem.SELECT_STATUS_BOX)));
		String selectedValue = driver.findElement(By.cssSelector(cssAsserElement+MyAssertionElem.CURRENT_VALUE)).getText();
		System.out.println(label+":label new value:"+selectedValue);
		
	}*/
	
	
	/*
	public String getAssertionStatusValue(String cssAsserElement){
		String valueLabel = driver.findElement(By.cssSelector(cssAsserElement+MyAssertionElem.CURRENT_VALUE)).getText();
		String label = driver.findElement(By.cssSelector(cssAsserElement+MyAssertionElem.CURRENT_LABEL)).getText();
		System.out.println(label+":label current value:"+valueLabel);
		return valueLabel;
	}*/
	
	
	
	public boolean isElementDisplayed(By by){
		 int count = driver.findElements(by).size();
		 if((count>0)&&(driver.findElement(by).isDisplayed())){			 
			 return true;
		 }else
			 return false;

	}
	
	
	public boolean isAttributePresentInElement(String cssattrbiute, String inputattribute){
		  try{
		  String a = driver.findElement(By.cssSelector(cssattrbiute)).getAttribute(inputattribute);
		  if(a!=null){
			  return true;
		  }else{
			  String ex = "attribute not present";
			  throw new Exception(ex);
		  }
		  }
		  catch(Exception e)
		  {
		    return false;
		   }
		   
		 // return true;
		 }


	public boolean isAttributevalueListed(String cssTableProp, String text,String attribute){
		  List<WebElement> tableHdr = driver.findElements(By.cssSelector(cssTableProp+">thead>tr th"));
		  int count_tableHdr = tableHdr.size();
		  for(int i=0;i<count_tableHdr;i++){
		   //System.out.println(tableHdr.get(i).getText());
		   if(tableHdr.get(i).getAttribute(attribute).trim().toUpperCase().matches(".*"+text.trim().toUpperCase()+".*")){
		    //System.out.println("number:"+i+tableHdr.get(i).getAttribute(attribute));
		    return true;
		   }
		  }
		  return false;
		 }
	public boolean columnASCsortingoption(String columntosort)
	{
		
	ArrayList<String> beforesort = new ArrayList<String>();
	ArrayList<String> sortedvalue = new ArrayList<String>();
	
	List<WebElement> columnlisttochksort = driver.findElements(By.cssSelector(columntosort));
	for(WebElement sortingColvalue : columnlisttochksort)
				{
					beforesort.add(sortingColvalue.getText().trim().toUpperCase());
					sortedvalue.add(sortingColvalue.getText().trim().toUpperCase());
					
				}
	
	Collections.sort(sortedvalue);
	
	
	
	System.out.println("before sorting:"+beforesort);
	System.out.println("sortedvalue:"+sortedvalue);
	for(int i=0;i<columnlisttochksort.size();i++){
		if(beforesort.get(i).equals(sortedvalue.get(i))){
			return true;
			
		}
		
	}
	return false;
	
	}

	public boolean columnDSCsortingoption(String columntosort)
	{

		ArrayList<String> beforesort = new ArrayList<String>();
		ArrayList<String> sortedvalue = new ArrayList<String>();

		List<WebElement> columnlisttochksort = driver.findElements(By.cssSelector(columntosort));
		for(WebElement sortingColvalue : columnlisttochksort)
			{
				beforesort.add(sortingColvalue.getText().trim().toUpperCase());
				sortedvalue.add(sortingColvalue.getText().trim().toUpperCase());
				
			}

		Collections.sort(sortedvalue);
		Collections.reverse(sortedvalue);
		
		System.out.println("before sorting:"+beforesort);
		System.out.println("sortedvalue:"+sortedvalue);	
		for(int i=0;i<columnlisttochksort.size();i++){
			if(beforesort.get(i).equals(sortedvalue.get(i))){
				return true;
				
			}
			
		}
		return false;
		
	}	
	
	public boolean columnintstringDSCsortingoption(String columntosort)
	{

		ArrayList<String> beforesort = new ArrayList<String>();
		ArrayList<String> sortedvalue = new ArrayList<String>();
		ArrayList<Integer> integersort = new ArrayList<Integer>();
		ArrayList<String> stringsort = new ArrayList<String>();
		List<WebElement> columnlisttochksort = driver.findElements(By.cssSelector(columntosort));
		for(WebElement sortingColvalue : columnlisttochksort)
			{
				beforesort.add(sortingColvalue.getText().trim().toUpperCase());
				sortedvalue.add(sortingColvalue.getText().trim().toUpperCase());
				
			}

		Collections.sort(sortedvalue);
		Collections.reverse(sortedvalue);
		
	
	//	if(sortedvalue1.matches("[0-9]"+"(.*)")){
			
			for(String sortingColvalue : sortedvalue){
				String splitat =" ";
				String[] tmp = sortingColvalue.split(splitat);
				integersort.add(Integer.parseInt(tmp[0]));
				stringsort.add(tmp[1]);
			}
			Collections.sort(integersort);
			Collections.reverse(integersort);
			System.out.println("before sorting:"+beforesort);
			System.out.println("sortedvalue:"+integersort+" "+stringsort);
			
			
			for(int i=0;i<columnlisttochksort.size();i++){
				System.out.println(beforesort.get(i).toString());
				System.out.println(integersort.get(i).toString()+" "+stringsort.get(i).toString());
			}
			
			try{
			for(int i=0;i<columnlisttochksort.size();i++){
				if(beforesort.get(i).matches(integersort.get(i)+" "+stringsort.get(i)+".*")){
					System.out.println("sortedvalue:"+integersort.get(i)+" "+stringsort.get(i));
				}
					else{
				    	  Exception e =  null;
						throw e ;
						}
				
			}
			return true;
			}catch(Exception e){
				return false;
			}
	
		
		
	}
	
	
	  public boolean columndateASCsortingoption(String columntosort){
		    
		    ArrayList<String> beforesort = new ArrayList<String>();
		    ArrayList<String> sortedvalue = new ArrayList<String>();
		     
		    List<WebElement> columnlisttochksort = driver.findElements(By.cssSelector(columntosort));
		    for(WebElement sortingColvalue : columnlisttochksort)
		    
		     {
		     if(sortingColvalue.getText().matches("")){
		      beforesort.remove(sortingColvalue.getText().trim().toUpperCase());
		      sortedvalue.remove(sortingColvalue.getText().trim().toUpperCase());
		     }
		     else{
		      beforesort.add(sortingColvalue.getText().trim().toUpperCase());
		        sortedvalue.add(sortingColvalue.getText().trim().toUpperCase());
		      
		     }
		     }
		     
		     Collections.sort(sortedvalue, new Comparator<String>() {
		            DateFormat f = new SimpleDateFormat("MM/dd/yyyy");
		            public int compare(String o1, String o2) {
		                try {
		                    return f.parse(o1).compareTo(f.parse(o2));
		                } catch (java.text.ParseException e) {
		                    throw new IllegalArgumentException(e);
		                }
		            }
		        });
		     
		     System.out.println("before sorting:"+beforesort);
		     System.out.println("revesrted date:"+sortedvalue);
		  try{   
		     for(int i=0;i<beforesort.size();i++){
		      if(beforesort.get(i).equals(sortedvalue.get(i))){
		     System.out.println(beforesort.get(i).toString()+"matched to "+sortedvalue.get(i).toString());
		    	  				
				}
		      else{
		    	  Exception e =  null;
				throw e ;
		      }
		      
		     }
		     return true;
		  }
		  catch(Exception e)
		  {
		    	  return false;
		  }
	  }

	  public boolean columndateDSCsortingoption(String columntosort){
	    
	    ArrayList<String> beforesort = new ArrayList<String>();
	    ArrayList<String> sortedvalue = new ArrayList<String>();
	     
	    List<WebElement> columnlisttochksort = driver.findElements(By.cssSelector(columntosort));
	    for(WebElement sortingColvalue : columnlisttochksort)
	    
	     {
	     if(sortingColvalue.getText().matches("")){
	      beforesort.remove(sortingColvalue.getText().trim().toUpperCase());
	      sortedvalue.remove(sortingColvalue.getText().trim().toUpperCase());
	     }
	     else{
	      beforesort.add(sortingColvalue.getText().trim().toUpperCase());
	        sortedvalue.add(sortingColvalue.getText().trim().toUpperCase());
	      
	     }
	     }
	     
	     Collections.sort(sortedvalue, new Comparator<String>() {
	            DateFormat f = new SimpleDateFormat("MM/dd/yyyy");
	            public int compare(String o1, String o2) {
	                try {
	                    return f.parse(o1).compareTo(f.parse(o2));
	                } catch (java.text.ParseException e) {
	                    throw new IllegalArgumentException(e);
	                }
	            }
	        });
	     Collections.reverse(sortedvalue);
	     System.out.println("before sorting:"+beforesort);
	     System.out.println("revesrted date:"+sortedvalue);
	     
	     try{
	    	for(int i=0;i<sortedvalue.size();i++){
	    
	      if(beforesort.get(i).toString().equals(sortedvalue.get(i).toString())){
	    	  
			//	System.out.println(beforesort.get(i).toString()+"matched to "+sortedvalue.get(i).toString());
	      }
	      else{
	    	  Exception e =  null;
			throw e ;
	      }
	      
	     }
	     return true;
	  }
	  catch(Exception e)
	  {
	    	  return false;
	  }
	  }
	  
	  public String getFormattedDate(String toDateFormat,String fromDateFormat,String dateToConvert){
			String formattedDate = "";
			Date fromDate =null;
			//SimpleDateFormat fromSdf = new SimpleDateFormat("MM/dd/yyyy");
			//SimpleDateFormat toSdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat fromSdf = new SimpleDateFormat(fromDateFormat);
			SimpleDateFormat toSdf = new SimpleDateFormat(toDateFormat);
			
			try {
				fromDate = fromSdf.parse(dateToConvert);
				formattedDate = toSdf.format(fromDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return formattedDate;
		}
		
		public Date getDate(String dateToConvert) throws ParseException {
			Date dateConv =null;
			SimpleDateFormat toSdf = new SimpleDateFormat("yyyy-MM-dd");
			dateConv = toSdf.parse(dateToConvert);		
			return dateConv;
		}
		
		
		public String getCaseKeyInLitUrl(String urlLit){
			String caseKey=urlLit.replaceAll("(https*://)(\\w+-*\\w+)(.*/lit\\w*/)(\\w+-\\d+)(.*)", "$4");
			return caseKey;
		}
		
		
		
	//	public String getIdInEntityAliasUrl(String urlEntAliasDetails){
	//		String id=urlEntAliasDetails.replaceAll("(https*://)(\\w+-*\\w+)(.*/lit\\w*/)(\\w+-\\d+)(.*)", "$4");
	//		if(urlEntAliasDetails.trim().toUpperCase().contains(litDetailsElem.URL_ENTITIES_FORMAT)){
	//			id=urlEntAliasDetails.replaceAll("(https*://)(\\w+-*\\w+)(.*/ent\\w*/)(\\d+)(-*.*)", "$4");		
	//		}else if(urlEntAliasDetails.trim().toUpperCase().contains(litDetailsElem.URL_ALIAS_FORMAT)){				
	//			id=urlEntAliasDetails.replaceAll("(https*://)(\\w+-*\\w+)(.*/aliases\\w*/)(\\d+)(-*.*)", "$4");			
	//		}
	//		return id;
	//	}
		
		
		
		//Selects an option from a dropdown(@parameter=css selector, selectText )
		public String selectOptionFromDropDown(String selector, String selectText)
		{
			try{
				
				driver.findElement(By.cssSelector(selector)).click();
			    new Select(driver.findElement(By.cssSelector(selector))).selectByVisibleText(selectText);
			    
			    
			}
			catch(Exception e){
				System.out.println("Exception in selectOptionFromDropDown:" + e);
			}
			return null;
					
		}
		
		
	public void MovetoElement(String Menu ,String Submenu ){
		try{
			WebElement element = FindByLink( Menu);
			Actions action = new Actions(driver);
			
			page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(Menu)));
			
	        action.moveToElement(element).perform();

	        page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(Submenu)));
	        
	        WebElement subMenu = FindByLink(Submenu);
	        
			
	        action.moveToElement(subMenu).click().perform();
	        
	       

	       // action.perform();
		}catch (Exception e){
			//report error
			e.printStackTrace();
			
		}

	}
	  
		 public void  switchToframeByID(String Frame){
			page.driver.switchTo().frame(driver.findElement(By.id( Frame)));
		  
		 }
}
