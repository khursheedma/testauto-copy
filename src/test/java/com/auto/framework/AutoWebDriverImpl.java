package com.auto.framework;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.auto.config.GlobalDataStore;

public class AutoWebDriverImpl implements AutoWebDriver {
	
	/*
	 *  Initialize the WebDriver
	 */
	
	WebDriver driver;
	
	public void init(String Browser) {
		
		System.out.println("BEGIN: The webDriver init method " + Browser);
		
		String userDir = System.getProperty("user.dir");
		
		String OS = OSDetector();
		
		if (Browser.equalsIgnoreCase("chrome") && (OS.equals("Mac"))) {
			System.out.println(" The Chrome Driver " + userDir + GlobalDataStore.ChromeDriver_MAC);
			System.setProperty("webdriver.chrome.driver", userDir + GlobalDataStore.ChromeDriver_MAC);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			//options.addArguments("--headless");
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);		
		}
		
		if (Browser.equals("Chrome") && (OS.equals("Windows"))) {
			//Log.info("Windows chrome Browser ");
			System.setProperty("webdriver.chrome.driver", userDir + GlobalDataStore.ChromeDriver_WIN);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			//options.addArguments("--headless");
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);

		}
		
		if (Browser.equals("FireFox") || (Browser.equals("firefox"))) {
			System.out.println("entering in FireFox");
			
			if (OS.equals("Mac")) {
				System.out.println("In Firefox Driver and Mac " + userDir + GlobalDataStore.GeckoDriver_MAC);
				System.setProperty("webdriver.gecko.driver", userDir + GlobalDataStore.GeckoDriver_MAC);
				driver = new FirefoxDriver();
			}
			else {
				System.out.println("In Firefox Driver");
				System.setProperty("webdriver.gecko.driver", userDir + GlobalDataStore.GeckoDriver_WIN);
				driver = new FirefoxDriver();
			}
		}
		
		System.out.println("END:The webDriver Init Method");
	}

	public String OSDetector() {
		
		String os = System.getProperty("os.name").toLowerCase();
		
		if (os.contains("win")) {
			return "windows";
		} else if (os.contains("nux") || os.contains("nix")) {
			return "Linux";
		} else if (os.contains("mac")) {
			return "Mac";
		} else if (os.contains("sunos")) {
			return "Solaris";
		} else {
			return "Other";
		}
	}
	
	public Boolean navigateTo(final String urlString) {
		
		Boolean mainPageFound = false;
		
		try {
			System.out.println("The Navigate URL " + urlString);
			String navigateUrl;
			
			navigateUrl = urlString;
			
			this.driver.get(navigateUrl);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			String currentUrl = getCurrentUrl();
			
			if (currentUrl != null) {
				mainPageFound = true;
				//driver.manage().window().maximize();
				driver.manage().window().fullscreen();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return mainPageFound;
	}

	
	public String getCurrentUrl() {
		
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
		
	}
	
	public WebDriver getDriverInstance() {
		
		return driver;
		
	}
	
	public String getElementText(By element) {
		
		try {
			if (driver.findElement(element).isDisplayed()) {
				System.out.println("The element exists");
				return driver.findElement(element).getText();
			}
		} catch (NoSuchElementException e) {
			e.getStackTrace();
		}
		return null;
		
	}
	
	public WebElement FindElement(By element) {
		
		System.out.println("entering in FindElement ");
		if (driver.findElement(element).isDisplayed()) {
			System.out.println("the element exists");
			return driver.findElement(element);
		} 
		else {
			return null;
		}
		
	}
	
	public List<WebElement> findElements(By element){
		
		return driver.findElements(element);
		
	}
	
	public void sendKeys(WebElement element, String name) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
		clickableElement.sendKeys(name);
		
	}
	
	public void clickElement(WebElement element) {
		
		System.out.println("START: click element value " + element);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		try {
			
			WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
			clickableElement.click();
			
		} catch (StaleElementReferenceException e) {
			
			System.out.println("Element " + element.getText() + " does not exist " + e.getStackTrace());
			
		} catch (NoSuchElementException e) {
			
			System.out.println("Element " + element.getText() + " does not exist " + e.getStackTrace());
			
		} catch (Exception e) {
			
			System.out.println("Element does not exist " + e.getStackTrace());
			
		}
		
		System.out.println("END: click element");
	}
	
	public String acceptPopAlert() {
		
		WebDriverWait wait = new WebDriverWait(driver, 15 /* time out in seconds */);
		
		String AlertText = null;
		
		if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
			System.out.println("alert was not present");
		}
		else {
			System.out.println("alert was present");
			Alert alert = driver.switchTo().alert();
			AlertText = alert.getText();
			alert.accept();
		}
		return AlertText;
		
	}
	
	public void quitWebDriver() {
		if (this.driver != null) {
			this.driver.quit();
			this.driver = null;
		}	
	}

	public void closeWebBrowser() {
		System.out.println("Close Browser");
		this.driver.close();	
	}
}
