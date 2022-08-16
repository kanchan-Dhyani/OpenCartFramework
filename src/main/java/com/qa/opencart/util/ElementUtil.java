package com.qa.opencart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	 private WebDriver driver;
	 private Actions act ;
	 
	 public ElementUtil(WebDriver driver)
	 {
		 this.driver = driver;
		 this.act= new Actions(driver);
	 }
	 
	 
	 
	 public WebElement getWebElement(By locator)
	 {
		 return driver.findElement(locator);
	 }
	 
	 
	 
	 public void doClick(By locator)
	 {
		 getWebElement(locator).click();
	 }
	 
	 
	 
	 public void doClick(String locatorType, String selector)
	 {
		 getWebElement(getBy(locatorType, selector)).click();
	 }
	 
	 
	 
	 public void doSendKeys(By locator, String key )
	 {
		WebElement ele =  getWebElement(locator);
		ele.clear();
		ele.sendKeys(key);
	 }
	 
	 
	 
	 
	 public void doSendKeys(String LocatorType, String selector, String value)
	 {
		 By locator = getBy(LocatorType, selector);
		 getWebElement(locator).sendKeys(value);
	 }
	 
	 
	 public String doElementGetText(By locator)
	 {
		return getWebElement(locator).getText();
	 }
	 
	 
	 public String doElementAttributeValue(By locator, String attrName)
	 {
		return getWebElement(locator).getAttribute(attrName);
	 }
	 
	 
	 public By getBy(String LocatorType, String selector)
	 {
		 By locator = null;
		 switch (LocatorType.toLowerCase()) {
		 case "id":
			  locator = By.id(selector);
			 break;
		 case "name":
			  locator = By.name(selector);
			 break;
		 case "class":
			  locator = By.className(selector);
			 break;
		 case "xpath":
			  locator = By.xpath(selector);
			 break;
		 case "cssselector":
			  locator = By.cssSelector(selector);
			 break;
		 case "linktext":
			  locator = By.linkText(selector);
			 break;
		 case "partiallinktext":
			  locator = By.partialLinkText(selector);
			 break;
		 case "tagname":
			  locator = By.tagName(selector);
			 break;
			 
		 default:
			  break;
			 
		 }
		 
		 return locator;
	 }
	 
	 
	 public  List<WebElement> getWebElements(By locator) 
	 {
		 return driver.findElements(locator);
	 }
	 
//	 public int pageLinksCount(By locator)
//	 {
//		List<WebElement> elementList =  getWebElements(locator);
//		for(WebElement el : elementList)
//		{
//			el.get
//		}
//	 } 
	 
	
	 
	 /**
	  * returns text of elements with non empty text
	  * @param locator
	  * @return
	  */
	 public List<String> getElementsTextList(By locator)
	 {
		 List<WebElement> elList = getWebElements(locator);
		 List<String> textlist = new ArrayList();
		 for(WebElement el : elList)
		 {
			 String text = el.getText();
			 
			 if(!text.isEmpty())
			    textlist.add(text);
		 }
		 
		 return textlist;
	 }
	 
	 /**
	  * returns the count of elements with empty text
	  * @param locator
	  * @return
	  */
	 
	 public int getEmptyElementsTextCount(By locator)
	 {
		 return getElementsTextList(locator).size();
	 }
	 
	 /**
	  * return the count of elements with empty text
	  */
	 public int getEmptyElementsTextSize(By locator)
	 {
		int totalNoOfElements = getPageElementsCount(locator) ;
		int totalNoOfElWithText = getEmptyElementsTextCount(locator);
		
		return totalNoOfElements - totalNoOfElWithText;
	 }
	 
	 /**
	  * returns the particular attribute of a certain type of html element
	  * @param locator
	  * @param attribute
	  * @return
	  */
	 
	 public List<String> getElementAttributeList(By locator, String attrName)
	 {
		 List<WebElement> eleList = getWebElements(locator);
		 List<String> eleAttrValList = new ArrayList();
		 for(WebElement e : eleList)
		 {
			String attrVal =  e.getAttribute(attrName);
			if(attrVal != null)
				eleAttrValList.add(attrVal);
			
		 }
		 
		 return eleAttrValList;
	 }
	 
	 /**
	  * performs searches and clicks on the suggName
	  * @param searchLoc - locator of search box
	  * @param suggListLocator -locator of suggestion list
	  * @param searchKey - key we need to search
	  * @param suggName - the key which we want to click
	  */
	 
	 public void performSearch(By searchLoc,By suggListLocator, String searchKey,String suggName)
		{
	        doSendKeys(searchLoc, searchKey);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<WebElement> suggList = getWebElements(suggListLocator);
			
			
			for(WebElement e : suggList)
			{
			 String eletext = e.getText();
			  if(eletext.contains(suggName))
			  {
				  e.click();
				  break;
			  }
			
			}
		}
	 
	 
	 
	 
	 public void selectItem(String productName)
	 {
		 By loc = By.xpath("//li[text()=' " +productName+ "']");
		 doClick(loc);
	 }
	 
	 // *************************************ELEMENT DISPLAY UTILS METHODS****************************************************
	 
	 /**
	  * returns true if an element is visible on the page
	  * @param locator
	  * @return
	  */
	 public boolean doIsdisplayed(By locator)
	 {
		 return getWebElement(locator).isDisplayed();
	 }
	 
	 
	 /**
	  * returns the count of an element on the page
	  * @param locator
	  * @return
	  */
	 public int getPageElementsCount(By locator)
	 {
		return  getWebElements(locator).size();
		
	 }
	 
	 
	 
	 
	 /**
	  * This method is used to verify if single element exists on UI or not.
	  * @param locator
	  * @return
	  */
	 
	 public boolean checkSingleElementExistOrNot(By locator)
	 {
		if( getPageElementsCount(locator) == 1)
			return true;
		
		return false;
	 }
	 
	 
	 /**
	  * This method is used to verify if multiple elements referred by a locator exists  or not.
	  * @param locator
	  * @return
	  */
	 public boolean checkMultipleElementsExistOrNot(By locator)
	 {
		 if( getPageElementsCount(locator) > 0)
				return true;
			
			return false;
	 }
	 
	 /**
	  * if you want to send keys using Actions class send keys method
	  * @param locator
	  * @param key
	  */
	 
	 public  void doActionsSendKeys(By locator, String key)
	 {
		
		act.sendKeys(getWebElement(locator), key).perform();;
	 }
	 
	 /**
	  * if you want to click using Actions class click method
	  * @param locator
	  */
	 public  void doActionsClick(By locator)
	 {
		
		act.click(getWebElement(locator));
	 }
	 
	 
	 
	 // ****************************************************************************************************************
	 //                                    WAIT UTILS
	 //*******************************************************************************************************************************
	 
	 
	 /**
		 * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that the element is visible.
		 * It checks only DOM.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementPresence(By locator, int timeOut )
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		/**
		 * 
		 * @param locator
		 * @param timeOut
		 * @param value
		 */
		public void doSendKeysWithWait(By locator, int timeOut, String value)
		{
			WebElement ele = waitForElementVisibility(locator,timeOut);
			ele.clear();
			ele.sendKeys(value);
		}
		
		/**
		 * 
		 * @param locator
		 * @param timeOut
		 */
		
		public void doClickWithWait(By locator, int timeOut)
		{
			waitForElementVisibility(locator,timeOut).click();;
		}
		
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page and visible.Visibility means 
		 * that the element is not only displayed but also has a height and width that isgreater than 0.
		 * It checks both DOM and page.
		 * default polling time 500 ms
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		
		public WebElement waitForElementVisibility(By locator, int timeOut)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page and visible.Visibility means 
		 * that the element is not only displayed but also has a height and width that isgreater than 0.
		 * It checks both DOM and page.
		 * default polling is customized
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		
		public WebElement waitForElementVisibility(By locator, int timeOut,int pollingTime)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		/**
		 * 
		 * @param timeOut
		 * @return
		 */
		
		public Alert waitForAlert(int timeOut)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.alertIsPresent());
		}
		
		/**
		 * 
		 * @param timeOut
		 * @return
		 */
		
		public String getAlertText(int timeOut)
		{
			return waitForAlert(timeOut).getText();
		}
	 
		/**
		 * 
		 * @param timeOut
		 */
		
		public void acceptAlert(int timeOut) 
		{
			waitForAlert(timeOut).accept();
		}
	 
		/**
		 * 
		 * @param timeOut
		 */
		public void dismissAlert(int timeOut)
		{
			waitForAlert(timeOut).dismiss();;
		}
		
		
		/**
		 * 
		 * @param value
		 * @param timeOut
		 */
		public void alertSendKeys(String value, int timeOut)
		{
			waitForAlert(timeOut).sendKeys(value);
		}
		
		/**
		 * An expectation for checking that all elements present on the web page that match the locatorare visible. Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		
		
		 public List<String> getAllElementsTextList(By locator,int timeOut)
		 {
			 List<WebElement> elList = waitForElementsTobeVisibleUsingLocator(timeOut,locator);
			 List<String> textlist = new ArrayList();
			 for(WebElement el : elList)
			 {
				 String text = el.getText();
				 
				 if(!text.isEmpty())
				    textlist.add(text);
			 }
			 
			 return textlist;
		 }
		 
		 /**
		  * Get the visible (i.e. not hidden by CSS) text of this element, including sub-elements. 
		  * @param locator
		  * @param timeOut
		  * @return
		  */
		 public String getElementText(By locator,int timeOut)
		 {
			 WebElement ele = waitForElementVisibility(locator,timeOut);
			 
			 
			 return ele.getText();
		 }
		/**
		 * 
		 * @param timeOut
		 * @param value
		 * @return
		 */
		public String waitForTitleContains(int timeOut, String titleFractionValue)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if( wait.until(ExpectedConditions.titleContains(titleFractionValue)))
			{
				return driver.getTitle();
			}else
				return null;
		
		}
		
		/**
		 *  An expectation for checking the title of a page.
		 * @param timeOut
		 * @param titleValue
		 * @return
		 */
		
		public String waitForTitleToBe(int timeOut, String titleValue)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if( wait.until(ExpectedConditions.titleIs(titleValue)))
			{
				return driver.getTitle();
			}else
				return null;
		
		}
		
		/**|An expectation for the URL of the current page to contain specific text.
		 * 
		 * @param timeOut
		 * @param urlFractionValue
		 * @return
		 */
		
		public String waitForURL(int timeOut, String urlFractionValue)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if(wait.until(ExpectedConditions.urlContains(urlFractionValue)))
			{
				return driver.getCurrentUrl();
			}
			else
			{
				System.out.println("URL is not found");
				return null;
			}
		}
		
		/**
		 * An expectation for the URL of the current page to be a specific url.
		 * @param timeOut
		 * @param url
		 * @return
		 */
		
		public String waitForUrlToBe(int timeOut, String url)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if(wait.until(ExpectedConditions.urlToBe(url)))
			{
				return driver.getCurrentUrl();
			}
			else
			{
				System.out.println("URL is not found");
				return null;
			}
		}
		
		/**
		 * 
		 *An expectation for checking whether the given frame is available to switch to. 
		 *If the frameis available it switches the given driver to the specified frame.
		 * @param timeOut
		 * @param locator
		 */
		public void waitForFrameUsingLocatorAndSwitchToIt(int timeOut, By locator)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		}
		
		/**
		 * An expectation for checking whether the given frame is available to switch to. 

			If the frameis available it switches the given driver to the specified frameIndex.

		 * @param timeOut
		 * @param index
		 */
		public void waitForFrameUsingIndexAndSwitchToIt(int timeOut, int index)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
		}
		
		/**
		 * frameLocator used to find the frame (id or name)
		 * @param timeOut
		 * @param frameNameOrId
		 */
		
		
		public void waitForFrameUsingNameOrIdAndSwitchToIt(int timeOut, String frameNameOrId)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrId));
		}
		
		/**
		 * frameLocator used to find the frame (webelement)
		 * @param timeOut
		 * @param locator
		 */

		public void waitForFrameUsingWebElementOrIdAndSwitchToIt(int timeOut,By locator)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getWebElement(locator)));
		}
		
		/**An expectation for checking an element is visible and enabled such that you can click it.checks clickable elements if they are r
		 * @param timeOut
		 * @param locator
		 * @return
		 */
		public WebElement elementIsReadyForClick(int timeOut, By locator)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		
		
		/**\
		 * An expectation for checking an element is visible and enabled such that you can click it and click on it
		 * @param timeOut
		 * @param locator
		 */
		
		public void clickElementWhenReady(int timeOut, By locator)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			 wait.until(ExpectedConditions.elementToBeClickable(locator)).click();;
		}
		
		
		/**
		 * An expectation for checking that all elements present on the web page that match the locatorare visible. Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
		 * @param timeOut
		 * @param locator locator used to find the element
		 * @return
		 */
				
		public List<WebElement> waitForElementsTobeVisibleUsingLocator(int timeOut, By locator)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		
		/**
		 * An expectation for checking that all elements present on the web page that match the locatorare visible. Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
		 * @param timeOut
		 * @param elements elements list of WebElements, eg : driver.findElement(firstName),driver.findElement(lastName)
		 * @return
		 */
		
		public List<WebElement> waitForSpecificWebElementsTobeVisible(int timeOut, WebElement...elements)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		}
		
		/**
		 * An expectation for checking that all elements present on the web page that match the locatorare visible. Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
		 * @param timeOut
		 * @param elements elements list of WebElements
		 * @return
		 */
		
		public List<WebElement> waitForListOfWebElementsTobeVisible(int timeOut, List<WebElement> eleList)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElements(eleList));
		}
		
		
		
		/**
		 * 
Fluent Wait for WebDriver
An expectation for checking that an element is present on the DOM of a page and visible.Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.

		 * @param timeOut
		 * @param locator
		 * @return
		 */
		
		
		
		public WebElement waitForElementVisibilityWithFluentWait(int timeOut, By locator)
		{
			FluentWait<WebDriver> fluentWait = new FluentWait(driver);
			return fluentWait.pollingEvery(Duration.ofSeconds(5))
			         .withTimeout(Duration.ofSeconds(10))
			         .withMessage("element not found")
			         .ignoring(NoSuchElementException.class)
			         .until(ExpectedConditions.elementToBeClickable(locator))
			         ;
			
			
		}
		
		/**
		 * wait method created from scratch
		 * @param timeOut in seconds
		 * @param locator
		 * @param interval in milliseconds
		 * @return
		 */
		
		public WebElement retryingElement(int timeOut, By locator, long interval)
		{
			WebElement ele = null;
			long counter = 0;
			int attempt = 1;
			while(counter < timeOut * 1000)
			{
				try {
					ele = driver.findElement(locator);
					
					break;
				}catch(org.openqa.selenium.NoSuchElementException e)
				{
					System.out.println("Element not found in attempt " + attempt);
					try {
						Thread.sleep(interval);
					} catch (InterruptedException e1) {
					
						e1.printStackTrace();
					}
				}
				counter = counter+interval;
				attempt++;
			}
			
			if(ele == null)
			{
				try {
				throw new Exception("NOSUCHELEMENTFOUNDEXCEPTION");
				}
				catch(Exception e)
				{
					System.out.println("Element is not found..tried for : "+timeOut+" Seconds " +
				      "with interval of " +interval+ " MilliSeconds");
				}
			}
			
			return ele;
			
		}
		
		/**
		 * 
		 * @param timeOut
		 */
		
		
		public void waitForPageLoadUsingJS(int timeOut)
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			
			String state = null;
			long endTime = System.currentTimeMillis() + timeOut*1000;
			
			
			while( System.currentTimeMillis() < endTime)
			{
				
				 state = js.executeScript("return document.readyState").toString();
				 System.out.println("state : " + state);
				 if(state == "complete"   )
				 {
					 break;
				 }
				
			}
		}
		
		
		
		
		
		

 }