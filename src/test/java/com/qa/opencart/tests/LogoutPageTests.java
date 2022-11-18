package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LogoutPageTests extends BaseTest {

	@BeforeClass
  public void LogoutSetup()
  {
	  accountsPage = homePage.goToLoginPage().doLogin(prop.getProperty("user"), prop.getProperty("password"));
	  logoutPage = accountsPage.logOutUser();
  }
  
  @Test
  public void isLoginButtonExist()
  {
	Assert.assertEquals( logoutPage.isLoginButtonExist(),true);  
  }
  
  
  @Test
  public void isRegisterButtonExist()
  {
	Assert.assertEquals( logoutPage.isRegisterButtonExist(),true);  
  }
}
