//package com.sample.demo;
//
//import com.sample.demo.Sample;
//
//import java.util.List;
//
//import org.junit.Assert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.Select;
//import org.testng.Reporter;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Optional;
//import org.testng.annotations.Test;
//
//public class testNGSample extends Sample {
//	
//	public Sample sample;
//	public WebDriver driver;
//	
//	
////	@BeforeTest
//	
//	public void setup()
//	{
//		WebDriver driver = new FirefoxDriver();
//		
//		driver.get("https://www.goibibo.com/");
//		
//		driver.manage().window().maximize();
//	
//	}
//	
//	@Test
//	public void example(){
//		Sample sample= new Sample();
//		
//		Assert.assertEquals(5, sample.exampleforTestNG());
//		Reporter.log("<div>>>>>" +"calculations wrong" + "</div><BR/>");
//	}
//	
//	@Test
//
//	public void testFlightRoundTrip(){
//		WebDriver driver = new FirefoxDriver();
//		
//		driver.get("https://www.goibibo.com/");
//		
//		driver.manage().window().maximize();
//		sample.ibibocode();
//		
//		
//	}
//	
//}
