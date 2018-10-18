package com.auto.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface AutoWebDriver {
	
	void init(String Browswer);
	//void initSauceLabs(String Chrome) throws MalformedURLException;
	
	Boolean navigateTo(final String relativeURL);
	void sendKeys(WebElement element, String name);
	void clickElement(WebElement element);
	String getElementText(By element);
	WebElement FindElement(By element);
	List <WebElement> findElements(By element);
	String acceptPopAlert();
	
	/*
	 * Quits the web driver
	 */
	void quitWebDriver();
	
	/*
	 * closes the browser window
	 */
	
	void closeWebBrowser();
	
}
