package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	
		
	public   ProductInfoPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//********************** BY LOCATORS **********************************
	
	By productHeader =  By.xpath("//div[@id='content']//h1");
	By productAdditionalImages = By.cssSelector("div#content ul.thumbnails img");
	By prodInfo =  By.xpath("(//div[@id='content']//ul[@class= 'list-unstyled'])[1]/li");
	By prodPriceInfo =  By.xpath("(//div[@id='content']//ul[@class= 'list-unstyled'])[2]/li");
	
	// *************************ACTIONS*******************************
	
	public String getProductHeader()
	{
		
		String HeaderText = eleUtil.getElementText(productHeader, AppConstants.SMALL_DEFAULT_TIME_OUT);
		System.out.println("Header text is : "+ HeaderText);
		return HeaderText;
		
	}

	
	public int getProductImagesCount()
	{
		
		int count = eleUtil.waitForElementsTobeVisibleUsingLocator(AppConstants.SMALL_DEFAULT_TIME_OUT,productAdditionalImages).size();
		System.out.println("Product images count is : "+ count);
		return count;
		
	}
	
	
	public Map<String, String> getProductMetaData()
	{
		HashMap<String, String> productMap = new HashMap();
		List<WebElement> eleList = eleUtil.waitForElementsTobeVisibleUsingLocator(AppConstants.SMALL_DEFAULT_TIME_OUT, prodInfo);
		List<WebElement> priceList = eleUtil.waitForElementsTobeVisibleUsingLocator(AppConstants.SMALL_DEFAULT_TIME_OUT, prodPriceInfo);
		
		for(WebElement e : eleList)
		{
			String[] str = e.getText().split(":");
			productMap.put(str[0].trim(), str[1].trim());
		}
		
		productMap.put("ProductPrice", priceList.get(0).getText().trim());
		productMap.put("ProductPriceExTax", priceList.get(1).getText().trim());
		
		return productMap;
	}
	
	
}
