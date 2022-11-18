package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchIcon =By.xpath("//div[@id='search']//button");
	private By accPageHeaders = By.xpath("//div[@id='content']/h2");
	
	
	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	// ***********************ACTIONS**********************************************
	
	
	@Step("get accounts page title")
	public String getAccountsPageTitle()
	{
		
		String title = eleUtil.waitForTitleToBe(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE);
		System.out.println("Accounts Page title is : " + title);
		return title;
	}
	
	@Step("get accounts page url")
	public String getAccountsPageURL()
	{
		String url = eleUtil.waitForURL(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION);
		System.out.println("Login Page URL is : " + url);
		return url;
	}
	
	@Step("if logout link there")
	public boolean isLogoutLinkExist()
	{
	 
	  return eleUtil.waitForElementPresence(logoutLink, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}
	
	
	@Step("if logout link there")
	public boolean isSearchFieldExist()
	{
		 return eleUtil.waitForElementPresence(searchField, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public List<String> getAccountsSectionHeadersList()
	{
		return eleUtil.getAllElementsTextList(accPageHeaders, AppConstants.MEDIUM_DEFAULT_TIME_OUT);
	}
	
	
	public LogoutPage logOutUser()
	{
		eleUtil.doClick(logoutLink);
		return new LogoutPage(driver);
	}

	
	
	
	
	
	
	
	// **********************COMMON PAGE ACTIONS*******************************************************
	@Step("search {0}")
	public SearchResultsPage doSearch(String productName)
	{
		System.out.println("Searching for " + productName);
		eleUtil.doSendKeysWithWait(searchField, AppConstants.SMALL_DEFAULT_TIME_OUT,productName );
		eleUtil.doClick(searchIcon);
		
		return new SearchResultsPage(driver);
	}
	
	
	
	
}

