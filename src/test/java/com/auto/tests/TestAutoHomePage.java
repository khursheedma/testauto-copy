package com.auto.tests;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.auto.config.GlobalDataStore;
import com.auto.framework.AutoWebDriverFactory;
import com.auto.framework.AutoWebDriverImpl;
import com.auto.pages.AutoHomePage;

import junit.framework.Assert;



public class TestAutoHomePage {
	
	final static Logger logger = Logger.getLogger("AUTO_TESTS");
	
	AutoWebDriverImpl driver;
	GlobalDataStore gds = new GlobalDataStore();
	String AutoTestHomePage;
	Boolean HomePageLaunch = false;
	AutoHomePage objHomePage;
	
	@Parameters( {"BrowserName"} )
	@BeforeClass
	public void setUp(@Optional("FireFox") String BrowserName) throws MalformedURLException {
		
		logger.info("START: In setUp method");
		GlobalDataStore.setLogCategory("AUTO_TESTS");
		gds.initParameters();
		
		driver = AutoWebDriverFactory.getWebDriverInstance(BrowserName);
		AutoTestHomePage = GlobalDataStore.AutoWebHomePage;
		HomePageLaunch = driver.navigateTo(AutoTestHomePage);
		objHomePage = new AutoHomePage();
		objHomePage.setLogCategory("AUTO_TESTS");
		objHomePage.setWebDriver(driver);
		
		logger.info("END: In setUp method");
	}
	
	/*
	 *  test home page launching
	 */
	
	@Test
	public void test_HomePage_Title() throws InterruptedException {
		
		logger.info("START: test_HomePage_Title");
		objHomePage.setLogCategory("AUTO_TESTS");
		
		if (HomePageLaunch == true) {
			
			String actualHomePageTitle = objHomePage.getHomePageTitle();
			logger.info("The home page title " + actualHomePageTitle);
			Assert.assertEquals("Today at Apple", actualHomePageTitle);
			
		}
		objHomePage.clickProgramLocationButton();
		
		objHomePage.selectCountriesDropDown();
		
		logger.info("END: test_HomePage_Title");
	}
	
	

}
