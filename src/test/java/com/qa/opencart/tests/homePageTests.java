package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class homePageTests extends BaseTest {

	 @BeforeClass
	public void setupHomePageTests()
	{
		
	}
	 
	 @DataProvider
	 public Object[][] getFooterLinksNameAndTitle()
	 {
		 return new Object[][]
		{
			 {"About Us","About Us"},
			 { "Contact Us","Contact Us"},
			 {"Gift Certificates","Purchase a Gift Certificate"},
			 {"Specials","Special Offers"}
		 };
	 }
	 
	 @Test(dataProvider= "getFooterLinksNameAndTitle")
	 public void footerLinksTest(String footerLinkName, String footerLinkTitle)
	 {
		String actTitle = homePage.verifyFooterLink(footerLinkName,footerLinkTitle);
		Assert.assertEquals(actTitle, footerLinkTitle);
	 }
}
