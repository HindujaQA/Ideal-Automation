package com.ideal.selenium.po.elements;

public class ResourceRequestFormElem {
	public static final String NEW_REQUEST="body > div:nth-child(1) > div:nth-child(1) > div.goToList > a.add";
	public static final String NEW_REQUEST_HEADER="page_heading";

	public static final String NEW_REQUEST_HDR_NAME="Add Request";
	public static final String TAB_REQUEST_DETAIL="#tabs > ul > li.ui-state-default.ui-corner-top.ui-tabs-selected.ui-state-active > a > span";
	public static final String RRF_IFRAME="rightMenu";
	public static final String BUSINESS_GRP="companyStructureId";
	public static final String PRACTICE_GRP="practiceGroup";
	public static final String SUB_PRACTICE_GRP="subPracticeGroup";
	public static final String RESOURCE_TYPE="resourceType";
	public static final String REQ_TYPE="billableType";
	public static final String PROPOSED_BAND="proposedBandId";
	public static final String COUNTRY="countryId";
	public static final String REGION="regionId";
	public static final String CITY="cityId";
	public static final String PROJECT_LOCATION="locationId";
	
	public static final String PROFILE_DETAILS="#tabs ul li:nth-of-type(2) >a";
	public static final String TOTAL_EXP_FRM="#profile_details_div .travelDetails_new tr :nth-child(1)#totalExperienceFrom";
	public static final String TOTAL_EXP_TO="#profile_details_div .travelDetails_new tr #totalExperienceTo";
	public static final String RELEVANT_EXP_FRM="#profile_details_div .travelDetails_new tr #relevantExperienceFrom";
	public static final String RELEVANT_EXP_TO="#profile_details_div .travelDetails_new tr #relevantExperienceTo";
	public static final String NO_OF_RESOURCES="#profile_details_div .travelDetails_new tr #requestedNoOfResources";
	public static final String QUALIFICATION="#profile_details_div .travelDetails_new tr #qualification";
	public static final String CERTIFICATION="#profile_details_div .travelDetails_new tr #certification";
	public static final String DOMAIN="#profile_details_div .travelDetails_new tr #domainId>option";	
	public static final String SKILL_TYPE="#profile_details_div .travelDetails_new tr #skillType>option";
	public static final String TOOLS="#profile_details_div .travelDetails_new tr #streamsId>option";
	public static final String AREA_OF_EXPERTISE="#profile_details_div .travelDetails_new tr #skillsId>option";
	
	public static final String CUST_PROJ_BILL_DTL="#tabs ul li:nth-of-type(3) >a";
	public static final String ONBOARD_DATE="#onBoardDate";
	public static final String JOB_TITLE="#jobTitle";
	public static final String JOB_DESCRIPTION	="#jobDescription";
	public static final String COMMENT="#requesterComments";
	public static final String BUH_APPROVER="#buhName";
	public static final String CHANGE_BUH="#No";
	public static final String VALIDATE_DATE="#validityDate";
	public static final String VALIDATE_ALERT=".ui-dialog .ui-dialog-buttonpane .ui-button .ui-button-text";
	
	
	
	
	
	public static final String SUBMIT_BTN="#jobSubmit";
	public static final String SAVE_BTN="jobSave";
	public static final String SAVE_MSG_LOC="ActionMessage";
	public static final String SAVE_MSG="Data Updated Successfully!";
}
