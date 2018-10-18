package com.auto.framework;

import java.net.MalformedURLException;

public class AutoWebDriverFactory {
	
	/*
	 *  Get a WebDriver instance
	 *  @return An instance of a webDriver
	 *  @param BrowserName
	 *  @throws MalformedURLException
	 */

	public static AutoWebDriverImpl getWebDriverInstance(String BrowserName) throws MalformedURLException {
		
		AutoWebDriverImpl webDriver = new AutoWebDriverImpl();
		webDriver.init(BrowserName);
		return webDriver;
		
	}
}
