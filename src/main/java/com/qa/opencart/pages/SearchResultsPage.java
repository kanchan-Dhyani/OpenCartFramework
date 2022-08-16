package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class SearchResultsPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	
		
	public  SearchResultsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//********************** BY LOCATORS **********************************
	
	By productCount = By.xpath("//div[@id='content']//div[@class='product-thumb']");
	
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

}
