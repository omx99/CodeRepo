package TestLibrary.org.Utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class excelDeletionAndCreation {

	
	@Test
	public  void removefileandcreate(String path) throws IOException {
	
	File file = new File(path ); //filepath is being passes through //ioc    //+ getFileName()     //and filename through a method 

	 if (file.exists()) {
	     file.delete(); //you might want to check if delete was successfull
	     }
	 
	 
 	    XSSFWorkbook workbook = new XSSFWorkbook();
     XSSFSheet sheet = workbook.createSheet("TEST DATA");
     FileOutputStream fileOut = new FileOutputStream(path);
   
         workbook.write(fileOut);
     
	}
	
	@Test
	public  void removefileandcreateforReport(String path) throws IOException {
	
	File file = new File(path ); //filepath is being passes through //ioc    //+ getFileName()     //and filename through a method 

	 if (file.exists()) {
	     file.delete(); //you might want to check if delete was successfull
	     }
	 
	 
 	    XSSFWorkbook workbook = new XSSFWorkbook();
     XSSFSheet sheet = workbook.createSheet("REPORT DATA");
     FileOutputStream fileOut = new FileOutputStream(path);
   
         workbook.write(fileOut);
     
	}
}
