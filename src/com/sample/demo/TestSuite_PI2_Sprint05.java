package Test_Shipment_Inq_PI_02.org.TestSprints_PI_2;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import TestLibrary.org.Utility.TestBase;
import TestLibrary.org.Utility.TestSuiteBean;
import TestLibrary.org.Utility.excelDeletionAndCreation;
import Test_Shipment_Inq_PI_01.org.TestSuite.Sprint05.US_110024_Display_OPLA_Flag;
import Test_Shipment_Inq_PI_02.org.TestSuite.PI2_Sprint05.US_164444_Service_Center_with_and_without_other_attributes;
import Test_Shipment_Inq_PI_02.org.TestSuite.PI2_Sprint05.US_169709_Select_All_feature_for_expandable;
import Test_Shipment_Inq_PI_02.org.TestSuite.PI2_Sprint05.US_169713_172358_Shipment_view_Box_Centering_Elements_alignment;

public class TestSuite_PI2_Sprint05 extends TestBase {

	excelDeletionAndCreation excelconfig =new excelDeletionAndCreation();
	US_164444_Service_Center_with_and_without_other_attributes US_164444 = new  US_164444_Service_Center_with_and_without_other_attributes();
	US_169709_Select_All_feature_for_expandable US_169709=new  US_169709_Select_All_feature_for_expandable();
	Test_Shipment_Inq_PI_02.org.TestSuite.PI2_Sprint05.US_169703_Shipment_search_cancel US_169703  =new  Test_Shipment_Inq_PI_02.org.TestSuite.PI2_Sprint05.US_169703_Shipment_search_cancel();
	US_110024_Display_OPLA_Flag US_161757 = new US_110024_Display_OPLA_Flag();
	US_169713_172358_Shipment_view_Box_Centering_Elements_alignment US_169713_172358 = new	US_169713_172358_Shipment_view_Box_Centering_Elements_alignment();
	
	Properties propertyData = null;
	String ORpropertiesfilePath = ".\\ObjectRep.properties";
	@Test (alwaysRun =true)
	public void DeleteAndCreateExcelFiles() throws Exception {

		UpdateTestSuiteData();
		propertyData = propertyObject.ReadObject(ORpropertiesfilePath);
		
		String testdatapath= propertyData.getProperty("TestDataFilePathForPI2Sprint5");
		excelconfig.removefileandcreate(testdatapath);
					
	}
	
	private void UpdateTestSuiteData() throws IOException {

		 TestSuiteBean.setTestSprintBrowser(browserss);
		 
	} 
	

	@Test(priority=0)
	public void US_169709_Select_All_feature_for_expandable() throws Exception {
		US_169709.testExpandaleButton(driver);;
		
	}
	
	
	@Test(priority=0)
	public void US_161757_Shipment_View_Display_OPLA_Flag() throws Exception {
				US_161757.testOPLAflag(driver);
	}
			 


	@Test(priority=0)
	public void US_164444_Service_Center_with_and_without_other_attributes() throws Exception {
		US_164444.RetrieveTestData();	
		US_164444.TestSVCsearchWithShipperAnd7days(driver);	
		US_164444.TestSVCsearchWithConsigneeAnd14days(driver);
	//	US_164444.TestSVCsearchWithCountryAnd30days(driver);
	}
	
	@Test(priority=0)
	public void US_169703_Shipment_search_cancel() throws Exception {
		US_169703.Test_Cancel_button_Functionality(driver);
		
	}
	
	
	@Test(priority=0)
	public void US_169713_172358_Shipment_view_Box_Centering_Elements_alignment() throws Exception {
		US_169713_172358.TestViewFunctionalityForDebtor(driver);
		
	}

}
