package com.qa.opencart.tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic-1: Design Account Page tests")
@Story("US-1:Design Account page features with title, url,header, logout tests")
public class AccountsPageTest extends BaseTest {
	
	 @BeforeClass
	 public void AccSetup()
	 {
		 
		// System.out.println("null test : " +homePage== null);
		// System.out.println(String.format("prop.getProperty(\"user\") %s,  prop.getProperty(\"password\") : %s : " ,prop.getProperty("user"),  prop.getProperty("password")));
		 accountsPage = homePage.goToLoginPage().doLogin(prop.getProperty("user"), prop.getProperty("password"));
		 
	 }
	 
	 
	 @Test
	 @Description("TC_01:Account Page Title Test")
	 @Severity(SeverityLevel.NORMAL)
	 public void AccountsPageTitleTest()
	 {
		 Assert.assertEquals(accountsPage.getAccountsPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	 }
	 
	 
	 @Test
	 @Description("TC_02:Account Page Url Test")
	 @Severity(SeverityLevel.NORMAL)
	 public void AccountsPageUrlTest()
	 {
		 Assert.assertEquals(accountsPage.getAccountsPageURL().contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION), true);
	 }
	 
	 
	 @Test
	 @Description("TC_03:Verify logout link is there or not")
	 @Severity(SeverityLevel.CRITICAL)
	 public void AccountsPagelogoutLinkExistTest()
	 {
		 Assert.assertEquals(accountsPage.isLogoutLinkExist(), true);
	 }
	 
	 
	 @Test
	 @Description("TC_04:Account Page header test")
	 @Severity(SeverityLevel.NORMAL)
	 public void accPageHeaderTest()
	 {
		 List<String> actHeadersList = accountsPage.getAccountsSectionHeadersList();
		 Collections.sort(actHeadersList);
		 List<String>  expectedHeadersList  = AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST;
		   Collections.sort(expectedHeadersList);
		   
		   
		  Assert.assertEquals(actHeadersList, expectedHeadersList);
	 }

}
