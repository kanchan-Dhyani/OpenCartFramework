package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ShoppingCart {

	private WebDriver driver;
	ElementUtil eleUtil;
	JavascriptExecutor js;
	// **************BY LOCATOR*************
	private By checkout= By.linkText("Checkout");
	private By continueShopping = By.linkText("Continue Shopping");
	By country = By.id("input-country");
	By zone = By.id("input-zone");
	By id = By.id("input-postcode");
	By postcode = By.id("input-postcode");
	By checkoutFinalbtn = By.xpath("//div[@class=\"pull-right\"]/a");
	By estShipTab = By.linkText("Estimate Shipping & Taxes") ;
	
	// **************CONSTRUCTOR*************
	public ShoppingCart(WebDriver driver)
	{
		this.driver= driver;
		eleUtil = new ElementUtil(driver);
		js = (JavascriptExecutor)driver;
	}
	
	
	// **************PAGE ACTIONS*************
	
	public boolean isCheckOutButtonExist()
	{
		return eleUtil.waitForElementPresence(checkout, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public boolean isContinueShoppingButtonExist()
	{
		return eleUtil.waitForElementPresence(continueShopping, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}
	
	
	public String checkout()
	{
		eleUtil.doClickWithWait(estShipTab, AppConstants.SMALL_DEFAULT_TIME_OUT);
		
		WebElement countryEl = eleUtil.waitForElementVisibility(country, AppConstants.SMALL_DEFAULT_TIME_OUT);
		Select selCountry = new Select(countryEl);
		selCountry.selectByValue("99");
		
		WebElement zoneEl = eleUtil.waitForElementVisibility(zone, AppConstants.MEDIUM_DEFAULT_TIME_OUT);
		Select selZone = new Select(zoneEl);
		selZone.selectByIndex(1);
		
		
		eleUtil.doSendKeys(postcode, "12345");
		
		eleUtil.doClick(checkoutFinalbtn);
		//return js.executeScript("return window.getComputedStyle(document.querySelector('i.fa.fa-exclamation-circle'),'::before').getPropertyValue('content')").toString();
		String msg = eleUtil.getElementText(By.xpath("//div[@class='alert alert-danger alert-dismissible']"), AppConstants.SMALL_DEFAULT_TIME_OUT).trim();
		System.out.println(msg.substring(0,msg.length()-1));
		return msg.substring(0,msg.length()-2);
	}
}
