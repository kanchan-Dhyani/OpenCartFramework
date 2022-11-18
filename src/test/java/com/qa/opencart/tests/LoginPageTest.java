package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic-2: Design Login Page tests")
@Story("US-1:Design Login page features with title, url,header, logout tests")
public class LoginPageTest extends BaseTest{
	
	
	@BeforeClass
	public void loginSetup()
	{
		loginPage = homePage.goToLoginPage();
	}
	
	 @Test
	 @Description("TC_01:Login Page Title Test")
	 @Severity(SeverityLevel.NORMAL)
	 public void loginPageTitleTest()
	 {
		 Assert.assertEquals(loginPage.getLoginPageTitle(), AppConstants.LOGIN_PAGE_TITLE);
	 }
	 
	 
	 @Test
	 @Description("TC_02:Login Page Url Test")
	 @Severity(SeverityLevel.NORMAL)
	 public void loginPageUrlTest()
	 {
		 Assert.assertEquals(loginPage.getLoginPageURL().contains(AppConstants.LOGIN_PAGE_URL_FRACTION), true);
	 }
	 
	 
	 @Test
	 @Description("TC_03:forgot password exist or not")
	 @Severity(SeverityLevel.NORMAL)
	 public void forgotPasswordLinkExistTest()
	 {
		 Assert.assertEquals(loginPage.isForgotPasswordLinkExist(), true);
	 }
	 
	 
	
	 @Test
	 @Description("TC_04:User able to login or not")
	 @Severity(SeverityLevel.CRITICAL)
	 public void loginTest()
	 {
		 accountsPage = loginPage.doLogin(prop.getProperty("user"), prop.getProperty("password"));
		 Assert.assertEquals(accountsPage.getAccountsPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	 }
	 
	 
	 @DataProvider
	 public Object[][] getEXCELdataForloginNegativeTest()
	 {
		 Object[][] loginData = ExcelUtil.getTestdata("loginNegative");
		 return loginData;
	 }
	 
	 
	 @Test(dataProvider = "getEXCELdataForloginNegativeTest")
	 public void loginNegativeTest(String user, String password)
	 {
		boolean actual = loginPage.doNegativeLogin(user, password);
		 Assert.assertEquals(actual, true);
	 }
	 
	 
}
