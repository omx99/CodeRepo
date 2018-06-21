package Test_Shipment_Inq_PI_02.org.TestSprints_PI_2;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import TestLibrary.org.Utility.TestBase;
import TestLibrary.org.Utility.TestSuiteBean;
import TestLibrary.org.Utility.excelDeletionAndCreation;
import Test_Shipment_Inq_PI_02.org.TestSuite.PI2_Sprint04.US_160681_Shipment_View_Revenue_Detail;
import Test_Shipment_Inq_PI_02.org.TestSuite.PI2_Sprint04.US_164422_Historical_Versions_Custom_View_Selection_box;
import Test_Shipment_Inq_PI_02.org.TestSuite.PI2_Sprint04.US_164367_Revenue_Detail_Historical_Shipment_View;


public class TestSuite_PI2_Sprint04 extends TestBase {

	excelDeletionAndCreation excelconfig =new excelDeletionAndCreation();

	Test_Shipment_Inq_PI_02.org.TestSuite.PI2_Sprint04.US_169142_Shipment_View_Revenue_Historical_Freight_Bill_Messaging US_169142 = new Test_Shipment_Inq_PI_02.org.TestSuite.PI2_Sprint04.US_169142_Shipment_View_Revenue_Historical_Freight_Bill_Messaging();
	Test_Shipment_Inq_PI_02.org.TestSuite.PI2_Sprint04.US_182935_Shipment_View_Historical_Freight_Bill_Messaging US_182935=new Test_Shipment_Inq_PI_02.org.TestSuite.PI2_Sprint04.US_182935_Shipment_View_Historical_Freight_Bill_Messaging();
	US_164422_Historical_Versions_Custom_View_Selection_box US_164422 = new US_164422_Historical_Versions_Custom_View_Selection_box();
	US_164367_Revenue_Detail_Historical_Shipment_View US_164367 = new US_164367_Revenue_Detail_Historical_Shipment_View();
	US_160681_Shipment_View_Revenue_Detail US_160681 = new  US_160681_Shipment_View_Revenue_Detail();
	
	Properties propertyData = null;
	String ORpropertiesfilePath = ".\\ObjectRep.properties";
	@Test (alwaysRun =true)
	public void DeleteAndCreateExcelFiles() throws Exception {

		UpdateTestSuiteData();
		propertyData = propertyObject.ReadObject(ORpropertiesfilePath);
		
		String testdatapath= propertyData.getProperty("TestDataFilePathForPI2Sprint4");
		excelconfig.removefileandcreate(testdatapath);
		if(browserss.contains("IE")){
			String path1=propertyData.getProperty("ReportFilePathForPI2Sprint4_IE");
				excelconfig.removefileandcreateforReport(path1);
			
			}
			else{
				String path1=propertyData.getProperty("ReportFilePathForPI2Sprint4_chrome");
				excelconfig.removefileandcreateforReport(path1);
			}
			
	}
	
	private void UpdateTestSuiteData() throws IOException {

		 TestSuiteBean.setTestSprintBrowser(browserss);
		 
	} 
	
	
	@Test(priority=0)
	public void US_169142_Shipment_View_Revenue_Historical_Freight_Bill_Messaging() throws Exception {
		US_169142.RetrieveTestData();
		US_169142.VerifyRevenueVersions(driver);
		US_169142.VerifyUnposted(driver);
		
	}
	
	@Test(priority=0)
	public void US_182935_Shipment_View_Historical_Freight_Bill_Messaging() throws Exception {
		US_182935.RetrieveTestData();
		US_182935.VerifyShipmentViewVersions(driver);
		
	}
	
@Test(priority=0)
	public void US_164422_Historical_Versions_Custom_View_Selection_box() throws Exception {
		US_164422.RetrieveTestData();
		US_164422.TestNumberofRevenueVersions(driver);
	
	}
	
	
	@Test(priority=0)
	public void US_164367_Revenue_Detail_Historical_Versions_View() throws Exception {
		US_164367.RetrieveTestData();
		US_164367.TestNumberofShipmentVersions(driver);
	}
	
	
	@Test(priority=0)
	public void US_160681_Shipment_View_Revenue_Detail() throws Exception {
		US_160681.RetrieveTestData();
		US_160681.TestNumberofRevenueDetailsofShipmentVersions(driver);
		US_160681.checkForVerificationErrors();
		
	}
	
	
}
