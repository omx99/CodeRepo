//package com.sample.demo;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.Proxy.ProxyType;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.support.ui.Select;
//
//import jxl.Cell;
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;
//import jxl.write.Label;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;
//import jxl.write.WriteException;
//
//public class Sample {
//	/*
////
////	WebDriver driver;
//	public static void main(String[] args) throws Exception {
//
//		Sample sample = new Sample();
//
////		sample.propertiesFileWrite();
////		sample.propertiesFileRead();
//		sample.jxlFileRead();
//
//		
//		 * Main closes here
//		 
//	}
//
//	public void propertiesFileWrite() {
//
//		Properties prop = new Properties();
//
//		
//		 * Output stream for properties file
//		 
//		OutputStream output = null;
//
//		try {
//
//			output = new FileOutputStream("db.properties");
//
//			// set the properties value
//			prop.setProperty("database", "localhost111");
//			prop.setProperty("dbuser", "mkyong");
//			prop.setProperty("dbpassword", "password");
//
//			// save properties to project root folder
//			prop.store(output, null);
//
//		} catch (IOException io) {
//			io.printStackTrace();
//		} finally {
//			if (output != null) {
//				try {
//					output.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//		
//		 * o/p closed
//		 
//	}
//
//	public void propertiesFileRead() {
//
//		Properties prop = new Properties();
//		
//		 * input steam opened and writtten the data
//		 
//
//		InputStream input = null;
//
//		try {
//
//			input = new FileInputStream("db.properties");
//
//			// load a properties file
//			prop.load(input);
//
//			// get the property value and print it out
//			System.out.println(prop.getProperty("database"));
//			System.out.println(prop.getProperty("dbuser"));
//			System.out.println(prop.getProperty("dbpassword"));
//
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		} finally {
//			if (input != null) {
//				try {
//					input.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	
//	
//	
//
//	
//	 * 
//	 * jxl example
//	 
//
//	public void jxlFileRead() throws BiffException, IOException, WriteException
// {
//		
//		 WritableWorkbook wworkbook;
//	      wworkbook = Workbook.createWorkbook(new File("output.xls"));
//	      WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
//	      Label label = new Label(0, 2, "A label record");
//	      wsheet.addCell(label);
////	      Number number = new Number(3, 4, 3.1459) ;
////	      wsheet.addCell(number);
//	      wworkbook.write();
//	      wworkbook.close();
//
//	      Workbook workbook = Workbook.getWorkbook(new File("output.xls"));
//	      Sheet sheet = workbook.getSheet(0);
//	      Cell cell1 = sheet.getCell(0, 2);
//	      System.out.println(cell1.getContents());
////	      Cell cell2 = sheet.getCell(3, 4);
////	      System.out.println(cell2.getContents());
//	      workbook.close();
//	   }
//	
//
//	
//	
//	
//	public void jxlFileWrite() {
//
//	}
//
//	public int exampleforTestNG() {
//		// TODO Auto-generated method stub
//		return 5;
//	}
//
//	
//	public void ibibocode()
//	{
////	
////		driver.findElement(By.xpath(".//*[@id='gosuggest_inputSrc']")).sendKeys("MAA");
////		driver.findElement(By.xpath(".//*[@id='gosuggest_inputDest']")).sendKeys("BOM");
////		
////		Select dropdown = new Select(driver.findElement(By.xpath(".//*[@id='gi_class']")));
////		dropdown.selectByIndex(2);
////	
////		
////		WebElement dateWidget = driver.findElement(By.xpath(".//*[@id='searchWidgetCommon']/div/div[3]/div[1]/div[1]/div/input"));
////		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
////
////		for (WebElement cell: columns){
////		   //Select 13th Date 
////		   if (cell.getText().equals("13")){
////		      cell.findElement(By.linkText("13")).click();
////		      break;
////		 }
////		
////		   
////			
////		
////	}
////		driver.findElement(By.xpath(".//*[@id='gi_search_btn']")).click(); 
////}
////	
////	*/
////	
////	
////	
////	
////
////	FirefoxProfile ff = new FirefoxProfile();
////	ff.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
////	FirefoxDriver driver = new FirefoxDriver(ff);
////
////	driver.get("https://finance.corp.syntel.in/psp/syfnprod/?cmd=login&languageCd=ENG&");
////	driver.manage().window().maximize();
////	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
////	System.out.println("browser is open");
////
////	driver.findElement(By.id("userid")).sendKeys("OM5027726");
////	driver.findElement(By.id("pwd")).sendKeys("Feb@2017");
////	driver.findElement(By.className("ps-button")).click();
////	driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
////	System.out.println("login Successful");
////
////	
////	driver.findElement(By.className("EOPP_SCSECTIONFOLDERLINK")).click();
////	System.out.println("click on Timesheet: Successful");
////	
////	
////	driver.switchTo().frame("ptifrmtgtframe");
////	System.out.println("frame detected");
////	driver.findElement(By.linkText("Daily Timesheet")).click();
////	System.out.println("click on Dailytime sheet: Successful");
////
////	
////	driver.switchTo().frame("ptifrmtgtframe");
//////	 WebElement table_element = driver.findElement(By.className("PSLEVEL1GRID"));
//////        List<WebElement> tr_collection=table_element.findElements(By.xpath("id('SY_TIME_DTL$scrolli$0')/tbody/tr"));
//////
//////        System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
//////        int row_num,col_num;
//////        row_num=1;
//////        for(WebElement trElement : tr_collection)
//////        {
//////            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
//////            System.out.println("NUMBER OF COLUMNS="+td_collection.size());
//////            col_num=1;
//////            for(WebElement tdElement : td_collection)
//////            {
//////                System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
//////                col_num++;
//////            }
//////            row_num++;
//////        } 
////	
////	String SY_TIME_DTL_DATExpath= ".//*[@id='SY_TIME_DTL_DATE$']";
////	String SY_TIME_WRK_DAYS_UOMxpath= ".//*[@id='SY_TIME_WRK_DAYS_UOM$']";
////	String SY_TIME_DTL_HOURS_PER_DAYxpath= ".//*[@id='SY_TIME_DTL_HOURS_PER_DAY$']";
////	String SY_TIME_DTL_TRCxpath= ".//*[@id='SY_TIME_DTL_TRC$']";
////	String SY_TIME_WRK_PROJECT_NAMExpath= ".//*[@id='SY_TIME_WRK_PROJECT_NAME$']";
////	String Y_TIME_WRK_PRIMARY_FLGxpath= ".//*[@id='SY_TIME_WRK_PRIMARY_FLG$']";
////	
////	
////	 WritableWorkbook wworkbook;
////    wworkbook = Workbook.createWorkbook(new File("D://output.xls"));
////    WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
////
//////    Number number = new Number(3, 4, 3.1459) ;
//////    wsheet.addCell(number);
////
////    
////	for(int i=0;i<16;i++)
////	{
////	System.out.println(	driver.findElement(By.xpath(".//*[@id='SY_TIME_DTL_DATE$"+i+"']")).getText());
////	
////    Label label = new Label(0, i, driver.findElement(By.xpath(".//*[@id='SY_TIME_DTL_DATE$"+i+"']")).getText());
////    wsheet.addCell(label);
////    wworkbook.write();	
////		
////	}
////	
////	wworkbook.close();
////	driver.close();
////	}
//	
//	
////	}	
//
//*/
//}
