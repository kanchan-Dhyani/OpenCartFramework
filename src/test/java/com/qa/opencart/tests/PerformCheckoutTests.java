package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class PerformCheckoutTests extends BaseTest{
	
	@BeforeClass
	public void performCheckoutSetup()
	{
		accountsPage = homePage.goToLoginPage().doLogin(prop.getProperty("user"), prop.getProperty("password"));
	}

	
	@DataProvider
	public Object[][] getDataForaddItemsToCart()
	{
		return new Object[][] {
			{"Samsung Galaxy Tab 10.1"},
			{"Nikon D300"}
		};
	}
	
	@Test(dataProvider = "getDataForaddItemsToCart")
	public void addItemsToCart( String productName)
	{
		searchResultPage = accountsPage.doSearch(productName);
		searchResultPage.addProductToCart(productName);
	}
	
	@Test(dependsOnMethods = "addItemsToCart" )
	public void checkoutCartNegative()
	{
		shopCart = searchResultPage.getShoppingCart();
		String act = shopCart.checkout();
		Assert.assertEquals(act, AppConstants.CHECKOUT_FAIL_MESSAGE);
	}
}
