package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void registerPageSetup()
	{
		registerPage = homePage.goToRegisterPage();
	}
	
	
	@DataProvider
	public Object[][] getDataForRegisterUserTest()
	{
		return new Object[][] {
			{"Kanchan4","Dhyani","abc4@gmail.com","1234567","hello123","true"},
			
			{"Shalini1","Dhyani","abc2@gmail.com","1234567","hello123","true"}
		};
	
	}
	
	
	@DataProvider
	public Object[][] getEXCELDataForRegisterUserTest()
	{
	 Object regData[][] = ExcelUtil.getTestdata(AppConstants.REGISTER_SHEET_NAME);
		return regData;
				
	}
	
	
	@Test (dataProvider= "getEXCELDataForRegisterUserTest")
	public void registerUserTest(String firstName, String lastName, String email, String phone, String password, String subscribe)
	{
		
		String actualMessage = registerPage.registerUser( firstName,  lastName,  email,  phone,  password,  subscribe);
		
		
		//Assert.assertEquals(actualMessage.contains(AppConstants.REGISTER_SUCCESS_MESSAGE), true);
		
		softAssert.assertEquals(actualMessage.contains(AppConstants.REGISTER_SUCCESS_MESSAGE), true);
		if(actualMessage.contains(AppConstants.REGISTER_SUCCESS_MESSAGE))
		{
			registerPage.logOutUser();
			registerPage = homePage.goToRegisterPage();
		}
		
		softAssert.assertAll();
	}

}
