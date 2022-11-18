package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class LogoutPage {
	
	//*******************BY LOCATORS******************************
	private By continueLoc = By.linkText("Continue");
	private By loginBttn = By.linkText("Login");
	private By registerBttn = By.linkText("Register");
	
	//*******************CONSTRUCTORS******************************
	private WebDriver driver;
	private ElementUtil el;
	public LogoutPage(WebDriver driver)
	{
		this.driver = driver;
		this.el = new ElementUtil(driver);
	}
	
	//*******************PAGE ACTIONS******************************
	
	public String getPageTitle()
	{
		return el.waitForTitleToBe(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.LOGOUT_PAGE_TITLE);
	}
	
	public boolean isLoginButtonExist()
	{
		return el.waitForElementPresence(loginBttn, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public boolean isRegisterButtonExist()
	{
		return el.waitForElementPresence(registerBttn, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}

}
