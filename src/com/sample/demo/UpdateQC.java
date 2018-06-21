package library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.enums.StatusAs;
import atu.alm.wrapper.exceptions.ALMServiceException;

public class UpdateQC {

	private static String qcUrl = null;
	private static String qcUserId = null;
	private static String qcPwd = null;
	private static String qcDomain = null;
	private static String qcProject = null;
	private static ALMServiceWrapper wrapper = null;

	public static String LegEquipment = "LegEquipment";
	public static String LegsTest = "LegsTest";
	public static String LoginTest = "LoginTest";
	public static String MileageLimitValidation = "MileageLimitValidation";
	public static String MTLleg = "MTLleg";
	public static String TrailerRoutes = "TrailerRoutes";
	public static String TrailorTest = "TrailorTest";

	public static int LegEquipmentID = 62875;
	public static int LegsTestID = 62975;
	public static int LoginTestID = 62876;
	public static int MileageLimitValidationID = 62877;
	public static int MTLlegID = 62878;
	public static int TrailerRoutesID = 62879;
	public static int TrailorTestID = 62880;

	Logger logger = null;
	String locale = null;
	String className = "UpdateQC";

	public UpdateQC(Logger logger) {
		this.logger = logger;
		try {
			FileInputStream fis = new FileInputStream(Constants.propertyFile);
			Properties properties = new Properties();
			properties.load(fis);

			qcUrl = properties.getProperty("qcUrl");
			qcUserId = properties.getProperty("qcUserId");
			qcPwd = properties.getProperty("qcPwd");
			qcDomain = properties.getProperty("qcDomain");
			qcProject = properties.getProperty("qcProject");

			wrapper = new ALMServiceWrapper(qcUrl);
			wrapper.connect(qcUserId, qcPwd, qcDomain, qcProject);

		} catch (FileNotFoundException e) {
			logger.info("", e);
		} catch (IOException e) {
			logger.info("", e);
		} catch (ALMServiceException e) {
			logger.info("", e);
		}
	}

	public void statusUploadToQC(String qcPath, String className, int testSetId, String method, String status) {
		logger.info("Updating QC status as " + "\"" + status + "\"" + " for TestSet " + "\"" + className + "\""
				+ " and TestCase " + "\"" + method + "\"");
		try {
			if (status.equalsIgnoreCase("pass")) {
				wrapper.updateResult(qcPath, className, testSetId, method, StatusAs.PASSED);
			} else if (status.equalsIgnoreCase("fail")) {
				wrapper.updateResult(qcPath, className, testSetId, method, StatusAs.FAILED);
			}
		} catch (ALMServiceException e) {
			logger.info("", e);
		}
	}

	public void closeALMWrapper() {
		wrapper.close();
	}

	/*
	 * public static void main(String[] args) { UpdateQC updateQC = new
	 * UpdateQC(); updateQC.statusUploadToQC(UpdateQC.LegEquipment,
	 * UpdateQC.LegEquipmentID,"loginPageTest" , Constants.PASS); }
	 */

}
