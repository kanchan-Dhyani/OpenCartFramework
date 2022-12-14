package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class HomePage {

	private WebDriver driver ;
	private ElementUtil eleUtil;
	private Actions act;
	private By myAccount = By.xpath("//a[@title='My Account']");
	private By register = By.linkText("Register");
	private By login = By.linkText("Login");
	
	//*********CONSTRUCTOR*****
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil= new ElementUtil(driver);
		act = new Actions(driver);
	}
	
	
	
	//******************ACTIONS*********************
	
	public RegisterPage goToRegisterPage()
	{
		act.moveToElement(eleUtil.getWebElement(myAccount)).click().moveToElement(eleUtil.getWebElement(register)).click().perform();
		return new RegisterPage(driver);
	}
	
	
	public LoginPage goToLoginPage()
	{
		act.moveToElement(eleUtil.getWebElement(myAccount)).click().moveToElement(eleUtil.getWebElement(login)).click().perform();
		return new LoginPage(driver);
	}
	
	public String verifyFooterLink(String FooterLinkName, String footerLinkTitle)
	{
		By el = By.linkText(FooterLinkName);
		eleUtil.doClickWithWait(el, AppConstants.SMALL_DEFAULT_TIME_OUT);
		
		return eleUtil.waitForTitleToBe(AppConstants.SMALL_DEFAULT_TIME_OUT, footerLinkTitle);
		
	}
	
	
}
