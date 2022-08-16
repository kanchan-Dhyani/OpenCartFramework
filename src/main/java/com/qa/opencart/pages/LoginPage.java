package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;




public class LoginPage {
	//driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
	//*******************BY LOCATORS******************************
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPassLink = By.linkText("Forgotten Password");
	private By errorMessage = By.cssSelector("div.alert-dismissible");
	//*******************CONSTRUCTOR******************************
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	
	
	
	//*******************PAGE ACTIONS******************************
	public String getLoginPageTitle()
	{
		
		String title = eleUtil.waitForTitleToBe(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("Login Page title is : " + title);
		return title;
	}
	
	
	public String getLoginPageURL()
	{
		String url = eleUtil.waitForURL(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION);
		System.out.println("Login Page URL is : " + url);
		return url;
	}
	
	
	public boolean isForgotPasswordLinkExist()
	{
	 
	  return eleUtil.waitForElementPresence(forgotPassLink, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public AccountsPage doLogin(String userName, String pwd)
	{
		System.out.println("App credentials : " + userName+ "/"+pwd);
	 
	  eleUtil.doSendKeysWithWait(emailId, AppConstants.MEDIUM_DEFAULT_TIME_OUT,userName);
	  eleUtil.doSendKeys(password, pwd);
	  eleUtil.doClick(loginButton);
	  
	  return new AccountsPage(driver);
	  
	}
	
	
	public boolean doNegativeLogin(String userName, String pwd)
	{
		System.out.println("App credentials : " + userName+ "/"+pwd);
	 
	  eleUtil.doSendKeysWithWait(emailId, AppConstants.MEDIUM_DEFAULT_TIME_OUT,userName);
	  eleUtil.doSendKeys(password, pwd);
	  eleUtil.doClick(loginButton);
	   String errorMesg = eleUtil.getElementText(errorMessage, AppConstants.SMALL_DEFAULT_TIME_OUT).trim();
		System.out.println(errorMesg);
       
	   if(errorMesg.equalsIgnoreCase(AppConstants.LOGIN_FAILURE_MESSAGE)) return true;
	   else return false;
	  
	}
	
	
}
