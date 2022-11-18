package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;
import com.qa.opencart.util.JavaScriptUtil;

import io.qameta.allure.Step;

public class SearchResultsPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	JavascriptExecutor js;
	Actions act;
	JavaScriptUtil jsUtil;
	
	
	public  SearchResultsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		js = (JavascriptExecutor)driver;
		this.act = new Actions(driver);
		
	}
	
	//********************** BY LOCATORS **********************************
	
	By productCount = By.xpath("//div[@id='content']//div[@class='product-thumb']");
	By checkout = By.xpath("(//a[contains(@href,'checkout/checkout')])[2]");
	By cartTotal = By.id("cart-total");
	
	// *************************ACTIONS*******************************
	
	@Step("gets search product count")
	public int getSearchProductCount()
	{
		return eleUtil.waitForElementsTobeVisibleUsingLocator(AppConstants.MEDIUM_DEFAULT_TIME_OUT, productCount).size();
	}
	
	@Step("selects product {0}")
	public ProductInfoPage selectProduct(String productName)
	{
		System.out.println("celected product : " + productName);
		eleUtil.waitForElementVisibility(By.linkText(productName), AppConstants.SMALL_DEFAULT_TIME_OUT).click();
		
		return new ProductInfoPage(driver);
	}
	
	
	public void addProductToCart(String productName)
	{
		
		By addToCartBttnLoc = By.xpath("(//a[text()='"+productName+"']//ancestor::div[@class='caption']//following-sibling::div/button)[1]");
		eleUtil.doClickWithWait(addToCartBttnLoc, AppConstants.MEDIUM_DEFAULT_TIME_OUT);
		
		
		 
	}
	
	public ShoppingCart getShoppingCart()
	{
		
		
         eleUtil.doClickWithWait(cartTotal, AppConstants.SMALL_DEFAULT_TIME_OUT);
				 
		eleUtil.doClickWithWait(checkout, AppConstants.SMALL_DEFAULT_TIME_OUT);
		
		return new ShoppingCart(driver);
		
	}

}
