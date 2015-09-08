package com.rpxcorp.cp.selenium.po;

//import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.rpxcorp.cp.selenium.basePage.page;
import com.rpxcorp.cp.selenium.po.elements.AcqDetailsElem;
import com.rpxcorp.cp.selenium.po.elements.MyProfileElem;



public class AcquisitionDetailsPage extends page {
	
	
	public String getTotalRowsAcqDetails(){
		List<WebElement> rowsAcq = driver.findElements(By.cssSelector("table.opportunity_patent_list>tbody>tr"));
		int count_rowsAcq = rowsAcq.size();
		System.out.println("Total number of rows in Acquisition table from Acquisition Details page:"+count_rowsAcq);
		return String.valueOf(count_rowsAcq);
	}
	
	public String getPatentsCountOppStats(){
		List<WebElement> listPanel = driver.findElements(By.cssSelector(AcqDetailsElem.LIST_SIDEBAR_PANELS));
		String patCount ="";
		String oppStats ="";
		for(WebElement item:listPanel){
			if(item.findElement(By.tagName(AcqDetailsElem.HDR_SIDEBAR_PANEL)).getText().trim().toUpperCase().equals(AcqDetailsElem.TXT_PANEL_HDR_OPP_STATISTICS)){
				oppStats = driver.findElement(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS)).getText();
				List<WebElement> listStats = driver.findElements(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS_ELEMENTS));
				for(WebElement boldEle:listStats){
					if(boldEle.getText().trim().toUpperCase().contains(AcqDetailsElem.TXT_US_PATENTS)){
						patCount = oppStats.substring(oppStats.indexOf(boldEle.getText())+boldEle.getText().length());
						patCount = patCount.replaceAll("(.*)([\n\r\t])(.*)","$1").replaceAll(":", "").trim();
						break;											
					}
				}				
			}
		}
		return patCount;
	}
	
	public String getIntakeDateOppStats(){
		List<WebElement> listPanel = driver.findElements(By.cssSelector(AcqDetailsElem.LIST_SIDEBAR_PANELS));
		String intakeDate ="";
		String oppStats ="";
		for(WebElement item:listPanel){
			if(item.findElement(By.tagName(AcqDetailsElem.HDR_SIDEBAR_PANEL)).getText().trim().toUpperCase().equals(AcqDetailsElem.TXT_PANEL_HDR_OPP_STATISTICS)){
				oppStats = driver.findElement(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS)).getText();
				List<WebElement> listStats = driver.findElements(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS_ELEMENTS));
				for(WebElement boldEle:listStats){
					if(boldEle.getText().trim().toUpperCase().contains(AcqDetailsElem.TXT_INTAKE_DATE)){
						intakeDate = oppStats.substring(oppStats.indexOf(boldEle.getText())+boldEle.getText().length());
						intakeDate = intakeDate.replaceAll("(.*)([\n\r\t])(.*)","$1").replaceAll(":", "").trim();
						break;											
					}
				}				
			}
		}		
		
		String dateIntake = ChangeDateFormat(intakeDate, "MMMM dd,yyyy", "MM/dd/yyyy");
		System.out.println("dateIntake:"+dateIntake);
		return dateIntake;
		
	}
	
	public String getReviewStatusOppStats(){
		List<WebElement> listPanel = driver.findElements(By.cssSelector(AcqDetailsElem.LIST_SIDEBAR_PANELS));
		String revStatus ="";
		String oppStats ="";
		for(WebElement item:listPanel){
			if(item.findElement(By.tagName(AcqDetailsElem.HDR_SIDEBAR_PANEL)).getText().trim().toUpperCase().equals(AcqDetailsElem.TXT_PANEL_HDR_OPP_STATISTICS)){
				oppStats = driver.findElement(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS)).getText();
				List<WebElement> listStats = driver.findElements(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS_ELEMENTS));
				for(WebElement boldEle:listStats){
					if(boldEle.getText().trim().toUpperCase().contains(AcqDetailsElem.TXT_REVIEW_STATUS)){
						revStatus = oppStats.substring(oppStats.indexOf(boldEle.getText())+boldEle.getText().length());
						revStatus = revStatus.replaceAll("(.*)([\n\r\t])(.*)","$1").replaceAll(":", "").trim();
						break;											
					}
				}				
			}
		}
		return revStatus;
	}
	
	public String getPortfolioNameOppStats(){		
		List<WebElement> listPanel = driver.findElements(By.cssSelector(AcqDetailsElem.LIST_SIDEBAR_PANELS));
		String accName ="";
		String oppStats ="";
		for(WebElement item:listPanel){
			if(item.findElement(By.tagName(AcqDetailsElem.HDR_SIDEBAR_PANEL)).getText().trim().toUpperCase().equals(AcqDetailsElem.TXT_PANEL_HDR_OPP_STATISTICS)){
				oppStats = driver.findElement(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS)).getText();
				//System.out.println("Opp Stats:"+oppStats);
				List<WebElement> listStats = driver.findElements(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS_ELEMENTS));
				//System.out.println("count of bold elements:"+listStats.size());
				for(WebElement boldEle:listStats){
					//System.out.println("bold element text:"+boldEle.getText());
					if(boldEle.getText().trim().toUpperCase().contains(AcqDetailsElem.TXT_ACCOUNT_NAME)){
						//System.out.println("Account name:"+oppStats);
						accName = oppStats.substring(oppStats.indexOf(boldEle.getText())+boldEle.getText().length());
						accName = accName.replaceAll("(.*)([\n\r\t])(.*)","$1").replaceAll(":", "").trim();
						break;											
					}
				}				
			}
		}
		return accName;
		
	}
	
	public String getNameMarketSectorOppStats(){
		List<WebElement> listPanel = driver.findElements(By.cssSelector(AcqDetailsElem.LIST_SIDEBAR_PANELS));
		String nameMarketSec ="";
		String oppStats ="";
		for(WebElement item:listPanel){
			if(item.findElement(By.tagName(AcqDetailsElem.HDR_SIDEBAR_PANEL)).getText().trim().toUpperCase().equals(AcqDetailsElem.TXT_PANEL_HDR_OPP_STATISTICS)){
				oppStats = driver.findElement(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS)).getText();
				List<WebElement> listStats = driver.findElements(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS_ELEMENTS));
				for(WebElement boldEle:listStats){
					if(boldEle.getText().trim().toUpperCase().contains(AcqDetailsElem.TXT_MARKET_SECTOR)){
						nameMarketSec = oppStats.substring(oppStats.indexOf(boldEle.getText())+boldEle.getText().length());
						nameMarketSec = nameMarketSec.replaceAll("(.*)([\n\r\t])(.*)","$1").replaceAll(":", "").trim();
						break;											
					}
				}				
			}
		}
		return nameMarketSec;
	}
	
	public String getBidDeadlineOppStats(){		
		List<WebElement> listPanel = driver.findElements(By.cssSelector(AcqDetailsElem.LIST_SIDEBAR_PANELS));
		String bidDeadline ="";
		String oppStats ="";
		for(WebElement item:listPanel){
			if(item.findElement(By.tagName(AcqDetailsElem.HDR_SIDEBAR_PANEL)).getText().trim().toUpperCase().equals(AcqDetailsElem.TXT_PANEL_HDR_OPP_STATISTICS)){
				oppStats = driver.findElement(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS)).getText();
				List<WebElement> listStats = driver.findElements(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS_ELEMENTS));
				for(WebElement boldEle:listStats){
					if(boldEle.getText().trim().toUpperCase().contains(AcqDetailsElem.TXT_BID_DEADLINE)){
						bidDeadline = oppStats.substring(oppStats.indexOf(boldEle.getText())+boldEle.getText().length());
						bidDeadline = bidDeadline.replaceAll("(.*)([\n\r\t])(.*)","$1").replaceAll(":", "").trim();
						break;											
					}
				}				
			}
		}
		return bidDeadline;
		
	}
	
	public String getSellerMaterialOppStats(){		
		List<WebElement> listPanel = driver.findElements(By.cssSelector(AcqDetailsElem.LIST_SIDEBAR_PANELS));
		String sellerMat ="";
		//String oppStats ="";
		for(WebElement item:listPanel){
			if(item.findElement(By.tagName(AcqDetailsElem.HDR_SIDEBAR_PANEL)).getText().trim().toUpperCase().equals(AcqDetailsElem.TXT_PANEL_HDR_OPP_STATISTICS)){
				//oppStats = driver.findElement(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS)).getText();
				List<WebElement> listStats = driver.findElements(By.cssSelector(AcqDetailsElem.CSS_OPP_STATS_ELEMENTS));
				for(WebElement boldEle:listStats){
					if(boldEle.getText().trim().toUpperCase().contains(AcqDetailsElem.TXT_SELLER_MATERIAL)){
						//sellerMat = oppStats.substring(oppStats.indexOf(boldEle.getText())+boldEle.getText().length());
						//sellerMat = sellerMat.replaceAll("(.*)([\n\r\t])(.*)","$1").replaceAll(":", "").trim();
						sellerMat = driver.findElement(By.cssSelector(AcqDetailsElem.CSS_SELLER_MATERIAL)).getText().trim();
						break;											
					}
				}				
			}
		}
		return sellerMat;
	}
	
	public String ChangeDateFormat(String Date,String fromFormat,String toFormat){
		
		Date formattedIntakeDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(fromFormat);
		try {
			formattedIntakeDate = sdf.parse(Date);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf2 = new SimpleDateFormat(toFormat);		
		String formattedDate = sdf2.format(formattedIntakeDate);		
		return formattedDate;		
	}
	
	public String getProfileNameInProd(){
		String entityName = driver.findElement(By.tagName(AcqDetailsElem.HDR_ACQ_DETAILS_PAGE)).getText();
		//System.out.println(entityName);
		if(isElementByCSSPresent(AcqDetailsElem.CSS_SUBHEADER)){
			String txtSubHdrNote = driver.findElement(By.cssSelector(AcqDetailsElem.CSS_SUBHEADER)).getText().trim(); 
			if(!txtSubHdrNote.isEmpty()){
				entityName = entityName.substring(txtSubHdrNote.length());
			}						
		}
	
		System.out.println("Profile Name in acquisition details page is:"+entityName);
		return entityName;
	}
	
	
	

}
