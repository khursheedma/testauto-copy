package com.auto.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.auto.config.GlobalDataStore;
import com.auto.framework.AutoWebDriverImpl;

// test 

public class AutoHomePage {

		AutoWebDriverImpl driver;
		
		By homePageTitle = By.xpath("//div[@class='localnav-content']/span[1]");
		By programLocationButton = By.id("program-location-label");
		By countryDropDownSelection = By.id("select-country");

		
		GlobalDataStore gds = new GlobalDataStore();
		WebDriver driverInstance;
		public static String LogCategory;
		public static Logger logger;
		
		public void setLogCategory(String LogFile) {
			
			LogCategory = LogFile;
			
			logger = Logger.getLogger(LogCategory);
			
		}
		
		public void setWebDriver(AutoWebDriverImpl webDriver) {
			
			logger.info("BEGIN: Set WebDriver");
			this.driver = webDriver;
			logger.info("END: Set WebDriver");
			
		}
		
		public String getHomePageTitle(){
	    	
			logger.info("START: getHomePageTitle");
	    	System.out.println("comming here");    
	    	logger.info("END: getHomePageTitle");
	        return    driver.FindElement(homePageTitle).getText();
	        
		}
		
		public Boolean checkProgramLocationButtonEnabled() {
		 	
			System.out.println("comming here in checkProgramLocationButtonEnabled");
		 	logger.info("START: checkProgramLocationButtonEnabled");
		 	logger.info("END: checkProgramLocationButtonEnabled");
		    return driver.FindElement(programLocationButton).isEnabled();
		    
		}
		
		public void clickProgramLocationButton() {
			
			logger.info("START: clickProgramLocationButton");
		 	
			if (checkProgramLocationButtonEnabled()) {
				driver.clickElement(driver.FindElement(programLocationButton));
			}
			
			logger.info("END: clickProgramLocationButton");
		}
		
		public void selectCountriesDropDown() throws InterruptedException {
			
			logger.info("START: selectCountriesDropDown");
			
			Select cSelect = new Select(driver.FindElement(countryDropDownSelection));
			
			// Print all the options for the selected drop down
			// Get the size of the Select element
			List<WebElement> cSize = cSelect.getOptions();
			int cListSize = cSize.size();
	 
			// Setting up the loop to print all the options
			for(int i =1; i < cListSize ; i++) {
				// Storing the value of the option	
				String cValue = cSelect.getOptions().get(i).getText();
				// Printing the stored value
				System.out.println(cValue);
				logger.info(cValue);
				cSelect.selectByIndex(i);
				// Using sleep command so that changes can be noticed
				Thread.sleep(1000);
			}	
			logger.info("END: selectCountriesDropDown");
		}
		
}
