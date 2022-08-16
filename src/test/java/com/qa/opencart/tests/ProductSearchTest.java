package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("Epic-3: Design Product Search tests")
@Story("US-1:Design Product search , images count tests")
public class ProductSearchTest extends BaseTest {
	
	
	@BeforeClass
	public void ProductSearchSetup() {
		accountsPage = homePage.goToLoginPage().doLogin(prop.getProperty("user"), prop.getProperty("password"));
	}
	
	
	@DataProvider
	public Object[][] getDataForProductSearch()
	{
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"iPhone","iPhone"},
			{"Apple Cinema 30\"","Apple Cinema 30\""},
			{"Canon EOS 5D","Canon EOS 5D"}
		};
	}
	
	@Test(dataProvider="getDataForProductSearch")
	public void productSearchTest(String searchProductName, String resultProductName)
	{
	 searchResultPage=	accountsPage.doSearch(searchProductName);
	 productInfoPage = searchResultPage.selectProduct(resultProductName);
	 String actualHeader = productInfoPage.getProductHeader();
	 
	 Assert.assertEquals(actualHeader, resultProductName);
	 
	}
	
	
	@DataProvider
	public Object[][] getDataForproductsCount()
	{
		return new Object[][] {
			{"MacBook",3},
			{"iPhone",1},
			{"Apple Cinema 30\"",1},
			{"Canon EOS 5D",1}
		};
	}
	
	@Test(dataProvider = "getDataForproductsCount")
	public void productsCountTest(String searchProductName, int expectedCount)
	{
	 searchResultPage=	accountsPage.doSearch(searchProductName);
	 int actualCount = searchResultPage.getSearchProductCount();
	
	 
	 Assert.assertEquals(actualCount, expectedCount);
	 
	}
	
	@DataProvider
	public Object[][] getDataForproductAddImagesCount()
	{
		return new Object[][] {
			{"MacBook","MacBook Pro",4},
			{"iPhone","iPhone",6},
			{"Apple Cinema 30\"","Apple Cinema 30\"",6},
			{"Canon EOS 5D","Canon EOS 5D",3}
		};
	}
	
	@Test(dataProvider="getDataForproductAddImagesCount")
	public void productAddImagesCountTest(String searchProductName, String resultProductName, int expectedImagesCount)
	{
	 searchResultPage=	accountsPage.doSearch(searchProductName);
	 productInfoPage = searchResultPage.selectProduct(resultProductName);
	 int actualImagesCount = productInfoPage.getProductImagesCount();
	 
	 Assert.assertEquals(actualImagesCount, expectedImagesCount);
	 
	}
	
	
	
	@DataProvider
	public Object[][] getDataForproductMetaData()
	{
		return new Object[][] {
			{"MacBook","MacBook Pro","Apple","Product 18","800","In Stock","$2,000.00","Ex Tax: $2,000.00"},
			
		};
	}
	
	@Test(dataProvider="getDataForproductMetaData")
	public void productMetaDataTest(String searchKey, String ProductName,String brand, String proCode, String rewardPoint, String availability,String productPrice, String productPriceExTax)
	{
		 searchResultPage=	accountsPage.doSearch(searchKey);
		 productInfoPage = searchResultPage.selectProduct(ProductName);
		 
		Map<String, String> actualDataMap= productInfoPage.getProductMetaData();
		
		String actBrand = actualDataMap.get("Brand");
		String actProdCode = actualDataMap.get("Product Code");
		String actRewardPoints = actualDataMap.get("Reward Points");
		String actAvailability = actualDataMap.get("Availability");
		String actProductPrice = actualDataMap.get("ProductPrice");
		String actProductPriceExTax = actualDataMap.get("ProductPriceExTax");
		
		softAssert.assertEquals(actBrand, brand);
		softAssert.assertEquals(actProdCode, proCode);
		softAssert.assertEquals(actRewardPoints, rewardPoint);
		softAssert.assertEquals(actProductPrice, productPrice);
		softAssert.assertEquals(actAvailability, availability);
		softAssert.assertEquals(actProductPriceExTax, productPriceExTax);
		
		softAssert.assertAll();
	}

}
