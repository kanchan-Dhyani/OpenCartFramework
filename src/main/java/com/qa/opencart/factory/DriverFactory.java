package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.StringUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author kanchan
 *
 */
public class DriverFactory {
	
	//public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static  ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method is used to initialize the driver based on browser name.
	 * @param browserName
	 * @return driver
	 */
	public WebDriver initDriver(String browserName)
	{
		System.out.println("Browser name is : " + browserName);
		optionsManager= new OptionsManager(prop);
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver= new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver= new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			//driver= new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		}
		else
		{
			System.out.println("Please pass the right browser Name" );
		}
		
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
	
	return getDriver();
	}
	
	
	
	
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	
	
	
	
	
	
	public Properties initProp()
	{
		try {
		 this.prop = new Properties();
		 FileInputStream ip = null;
		 
		 String envName = System.getProperty("env");
		 ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
		 
		 if(envName == null )
		 {
			 ip = new FileInputStream("./src/test/resources/config/config.properties");
			
		 }
		 else {
			 if(envName == "stage")
			 {
				 ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
			 } else if(envName == "dev")
			 {
				 ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
			 } else if(envName.equals("qa"))
			 {
				 ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			 }
			 else
			 {
				 System.out.println("Please pass the correct env name...Env name passed is :" + envName);
			 }
		 }
		 
		 System.out.println("loading prop");
			this.prop.load(ip);
		 
		 
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return prop;
	}
	

	
	/**
	 * Takes Screenshot and return the path
	 */

	 public static String getScreenshot(String methodName)
	 {
		File srcFile =  ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"./screenshot/"+methodName+System.currentTimeMillis()+".png";
		
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	 }
}
