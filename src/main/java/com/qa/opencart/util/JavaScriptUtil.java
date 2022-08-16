package com.qa.opencart.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.webdriver.WebDriverBrowser;

public class JavaScriptUtil {

	  private WebDriver driver;
	  private JavascriptExecutor js;
	  
	  public JavaScriptUtil(WebDriver driver)
	  {
		  this.driver = driver;
		  this.js= (JavascriptExecutor)driver;
	  }
	
	  
	  public String getTitleByJS()
	  {
		 return js.executeScript("return document.title;").toString();
	  }
	  
	  public void goForwardByJs()
	  {
		  js.executeScript("history.go(1);");
	  }
	  
	  
	  public void goBackwardByJs()
	  {
		  js.executeScript("history.go(-1);");
	  }
	  
	  public void refreshBrowserByJs()
	  {
		  js.executeScript("history.go(0);");
	  }
	  
	  
	  public void generateAlert(String message)
	  {
		  js.executeScript("alert'"+message+"'");
	  }
	  
	  
	  public String getPageInnerText()
	  {
		  return js.executeScript("return document.documentElement.innerText;").toString();
	  }
	  
	  
	  
	  public void drawBorder(WebElement element)
		{
			js.executeScript("arguments[0].style.border='3px red solid'", element);
		}

	  
	  public void scrollPageDown()
	  {
		  js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	  }
	  
	  
	  public void scrollPageDown(String height)
	  {
		  js.executeScript("window.scrollTo(0, "+height+");");
	  }
	  
	  public void scrollPageUp()
	  {
		  js.executeScript("window.scrollTo( document.body.scrollHeight,0);");
	  }
	  
	  
	  public void scrollToView(WebElement element)
	  {
		  js.executeScript("arguments[0].scrollIntoView(true);",element);
	  }
	  
	  
	  public void clickElementByJs(WebElement element)
	  {
		  js.executeScript("arguments[0].click();",element);
	  }
	  
	  public void sendKeysUsingDocumentById(String id, String value)
	  {
		  js.executeScript("document.getElementById('"+id+"').value= '"+value+"'");
	  }
}
