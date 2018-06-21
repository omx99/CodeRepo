package TestLibrary.org.Utility;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBase {
	   protected WebDriver driver= null;
	   String ORpropertiesfilePath=".\\ObjectRep.properties";
	   protected ReadPropertyFile propertyObject = new ReadPropertyFile();
	   public String browserss;
	   
	@Parameters("Browser")
	@BeforeClass
	public  void LoginFunction(String Browser) throws Exception {
		  Properties propertyData = propertyObject.ReadObject(ORpropertiesfilePath);
		  String browser = Browser;
	try{ 
		  		setbrowservalue(browser);
		if (browser.equalsIgnoreCase("firefox")){
				    
				FirefoxProfile ff = new FirefoxProfile();
				ff.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
				driver = new FirefoxDriver(ff);

				}
			//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
				//set path to chromedriver.exe
			
				System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
				//create chrome instance
				ChromeOptions o = new ChromeOptions();
				o.addArguments("enable-extensions");
				o.addArguments("--start-maximized");
				//o.setExperimentalOption("useAutomationExtension", false);
				o.addArguments("test-type");
				o.addArguments("start-maximized");
				o.addArguments("--js-flags=--expose-gc");  
				o.addArguments("--enable-precise-memory-info"); 
				o.addArguments("--disable-popup-blocking");
				o.addArguments("--disable-default-apps");
				o.addArguments("test-type=browser");
				o.addArguments("disable-infobars");
				
				 driver = new ChromeDriver(o);

			}
			//Check if parameter passed as 'InternetExplorer'
		else if(browser.equalsIgnoreCase("IE")){
				//set path to InternetExplorer.exe
				System.setProperty("webdriver.ie.driver","Drivers/IEDriverServer.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
				//capabilities.setCapability( InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				
				 driver = new InternetExplorerDriver(capabilities);
				//create InternetExplorer instance
				//driver = new InternetExplorerDriver();
					}
			else{

				throw new Exception("Browser is not correct");
			}

			 //Calling Select service method to select Shipment View as Service
			 
			//driver.get("https://devsso.secure.fedex.com/S/SPI/");  
		   	  driver.get("https://testsso.secure.fedex.com/S/SPI/"); 
			Thread.sleep(1000);
			  driver.findElement(By.name(propertyData.getProperty("loginId"))).sendKeys("5258334");
		      driver.findElement(By.name(propertyData.getProperty("pwdId"))).sendKeys("5258334");
		      driver.findElement(By.name(propertyData.getProperty("submitButton"))).click();
		      
		      Thread.sleep(500);
		        
		      WebElement servElement = driver.findElement(By.xpath("//select[@ng-model='redirectLocation']"));
		   	  Select servDropdown = new Select(servElement);
			   	  
		   	  servDropdown.selectByVisibleText("Shipment View");
		   	  
	}catch(Exception e){
		e.printStackTrace();
	}
}
	
	public void setbrowservalue(String b){
	browserss=b;
				}
	
	@AfterClass
	public void LogOutFunction() {
		driver.quit();
	}
}
