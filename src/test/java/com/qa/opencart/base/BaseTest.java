package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.util.ExcelUtil;

public class BaseTest {
	
	public WebDriver driver;
	public DriverFactory df;
	public HomePage homePage;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	public SearchResultsPage searchResultPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerPage;
	public Properties prop;
	public SoftAssert softAssert;
	@BeforeTest
	public void setup()
	{
		
        System.out.println("Test");
		this.df = new DriverFactory();
		this.prop = df.initProp();
		this.driver = df.initDriver(prop.getProperty("browser"));
		//System.out.println(String.format("prop.getProperty(\"user\") %s,  prop.getProperty(\"password\") : %s : " ,prop.getProperty("user"),  prop.getProperty("password")));
		this.homePage= new HomePage(driver);
		//loginPage = new LoginPage(driver);
		this.softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown()
	{
		//System.out.println("quitting");
		driver.quit();
	}
}
