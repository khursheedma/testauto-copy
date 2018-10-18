package com.auto.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class GlobalDataStore {
	
	private static Logger logger;
	private Properties configFile = new Properties();
	public static String HomePage;
	public static String AutoWebHomePage;
	public static String GeckoDriver_MAC;
	public static String GeckoDriver_WIN;
	public static String ChromeDriver_MAC;
	public static String ChromeDriver_WIN;
	public static String LogCategory;
	
	/*
	 *  Initialize log4j appenders
	 */
	
	public static void setLogCategory(String LogFile) {
		
		LogCategory = LogFile;
		
		logger = logger.getLogger(LogCategory);
	}

	public void initParameters() {
		
		String baseDir = System.getProperty("user.dir");
		
		String propFile = "selenium.properties";
		FileInputStream fis = null;
		
		try {
			
			fis = new FileInputStream(baseDir + "/" + "src/test/resources/" + propFile);
			configFile.load(fis);
			
			GeckoDriver_MAC = configFile.getProperty("GECKO_DRIVER_MAC");
			GeckoDriver_WIN = configFile.getProperty("GECKO_DRIVER_WIN");
			HomePage = configFile.getProperty("BASE_URL");
			
			ChromeDriver_MAC = configFile.getProperty("CHROME_DRIVER_MAC");
			ChromeDriver_WIN = configFile.getProperty("CHROME_DRIVER_WIN");
			AutoWebHomePage = configFile.getProperty("TEST_HOMEPAGE");
	
		} catch (FileNotFoundException e) {
			
			System.out.println("File not found" + e.getMessage());
			
		} catch (IOException e) {
			
			System.out.println("IO Exception" + e.getMessage());
		}
	}
}
