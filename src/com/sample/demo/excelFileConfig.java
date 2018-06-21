package TestLibrary.org.Utility;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class excelFileConfig {
	  Workbook wrk1 = null;
	  Sheet st1= null;
	  String trackingNum= null;
	  String comment = null;
	  String shipActNum= null;
	  String consigAcctNum=null;
	  String billToAcctNum= null;
	  public void readDatafromExcel() throws BiffException, IOException{

		     
			 System.out.println("launching Browser");		
			  //**********Read Excel and read test data
			  wrk1 = Workbook.getWorkbook(new File(".\\TestDataSprint01.xls"));
			 
			  st1 = wrk1.getSheet(0);

 	     	  
//			   public void read() throws IOException  {
//	                File inputWorkbook = new File(inputFile);
//	                Workbook w;
//	                try {
//	                        w = Workbook.getWorkbook(inputWorkbook);
//	                        // Get the first sheet
//	                        Sheet sheet = w.getSheet(0);
//	                        // Loop over first 10 column and lines
//
//	                        for (int j = 0; j < sheet.getColumns(); j++) {
//	                                for (int i = 0; i < sheet.getRows(); i++) {
//	                                        Cell cell = sheet.getCell(j, i);
//	                                        CellType type = cell.getType();
//	                                        if (type == CellType.LABEL) {
//	                                                System.out.println("I got a label "
//	                                                                + cell.getContents());
//	                                        }
//
//	                                        if (type == CellType.NUMBER) {
//	                                                System.out.println("I got a number "
//	                                                                + cell.getContents());
//	                                        }
//
//	                                }
//	                        }
//	                } catch (BiffException e) {
//	                        e.printStackTrace();
//	                }
//	        }
	  }
} 
	

