package objectRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoricalAuditsRepository extends CommonRepository {

	public static final String className = "HistoricalAuditsRepository";

	public static final String NumberOfPagesInHistoricalAuditsTable = ".//*[@id='linehaulAA:detailListAudit_paginatorbottom']/span/a";

	public static final String ActionColumnElements_Xpath = ".//*[@id='linehaulAA:detailListAudit']//tr//td[1]";

	public static final String TableNameColumnElements_Xpath = ".//*[@id='linehaulAA:detailListAudit']//tr//td[2]";

	public static final String DateTimeColumnElements_Xpath = ".//*[@id='linehaulAA:detailListAudit']//tr//td[4]";

	@FindBy(xpath = ".//*[@id='linehaulAA:from_input']")
	public static WebElement FromDateTextBox;

	@FindBy(xpath = ".//*[@id='linehaulAA:to_input']")
	public static WebElement ToDateTextBox;

	@FindBy(xpath = ".//*[@id='linehaulAA:locate']")
	public static WebElement LocateButton;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData']//tr[2]//td[2]")
	public static WebElement PROCESS_TYPE_CD;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData']//tr[4]//td[2]")
	public static WebElement HOURLY_CALC_AMT;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData']//tr[5]//td[2]")
	public static WebElement MILEAGE_CALC_AMT;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData']//tr[8]//td[2]")
	public static WebElement LAST_UPDT_USR_NM_MTL;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData_row_1']/td[2]")
	public static WebElement ORIG_CTR_CD;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData_row_2']/td[2]")
	public static WebElement DEST_CTR_CD;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData_row_3']/td[2]")
	public static WebElement SVC_LVL_CD_Lanes;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData_row_6']/td[2]")
	public static WebElement SVC_DAY_NBR_Lanes;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData_row_8']/td[2]")
	public static WebElement LN_MILE_NBR_Lanes;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData_row_10']/td[2]")
	public static WebElement LAST_UPDT_USR_NM_Legs_Lanes;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData_row_3']/td[2]")
	public static WebElement RTE_CD;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData_row_11']/td[2]")
	public static WebElement LEG_DESC;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData_row_12']/td[2]")
	public static WebElement LAST_UPDT_USR_NM_Legs;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData_row_16']/td[2]")
	public static WebElement COMM_TXT;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData_row_1']/td[1]")
	public static WebElement SubHistoricalTable_FirstElement;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAudit_row_0']/td[2]")
	public static WebElement TableColumn_FirstElement;

	@FindBy(xpath = ".//*[@id='linehaulAA:detailListAuditData_row_7']/td[2]")
	public static WebElement LN_TM_NBR_Lanes;

	static {
		PageFactory.initElements(driver, new HistoricalAuditsRepository());
	}

	public static boolean verifyAuditRecordAddedInHistoricalAuditForDeletedMTLRow(String UserID,
			Map<String, String> map) throws InterruptedException {
		locale = className + "." + "verifyAuditRecordAddedInHistoricalAuditForDeletedMTLRow()";
		logger.info("Entering: " + locale);
		boolean flag = false;
		int row = 0;
		String TableName = "DISPATCH_LEG_SUPPLEMENTAL_TYPE*";
		String xpathOfUserColumn;
		String xpathOfTableNameColumn;
		String UserColumnData = null;
		String TableNameColumnData = null;
		WebElement userColumnElement = null;
		WebElement TableNameColumnElement = null;
		List<WebElement> actionColumnElements = new ArrayList<WebElement>();
		actionColumnElements = driver.findElements(By.xpath(ActionColumnElements_Xpath));
		for (WebElement element : actionColumnElements) {
			if (element.getText().equals("D")) {
				xpathOfUserColumn = ".//*[@id='linehaulAA:detailListAudit_row_" + row + "']/td[3]";
				xpathOfTableNameColumn = ".//*[@id='linehaulAA:detailListAudit_row_" + row + "']/td[2]";
				userColumnElement = driver.findElement(By.xpath(xpathOfUserColumn));
				TableNameColumnElement = driver.findElement(By.xpath(xpathOfTableNameColumn));
				UserColumnData = userColumnElement.getText();
				TableNameColumnData = TableNameColumnElement.getText();
				if (TableName.equals(TableNameColumnData)) {
					if (UserColumnData.equals(UserID)) {
						logger.info(
								"Found row with Action:" + "D" + " , TableName:" + TableName + " and UserID:" + UserID);
						logger.info("Hence clicking on the row");
						userColumnElement.click();
						flag = verifyValuesAreSameInHistoricalAuditsRecordAndInMTLScreen(UserID, map);
						break;
					}
				}
			}
			row++;
		}
		return flag;
	}

	public static boolean verifyAuditRecordAddedInHistoricalAuditForDeletedRowInLegsPage(String Origin,
			String Destination, String Route, String Description, String UserID, String Comment)
			throws InterruptedException {
		locale = className + "." + "verifyAuditRecordAddedInHistoricalAuditForDeletedRowInLegsPage()";
		logger.info("Entering: " + locale);
		waitUntillElementAppears(TableColumn_FirstElement);
		boolean flag = false;
		int row = 0;
		String TableName = "DISPATCH_LEG*";
		String xpathOfUserColumn;
		String xpathOfTableNameColumn;
		String UserColumnData = null;
		String TableNameColumnData = null;
		WebElement userColumnElement = null;
		WebElement TableNameColumnElement = null;
		List<WebElement> actionColumnElements = new ArrayList<WebElement>();
		actionColumnElements = driver.findElements(By.xpath(ActionColumnElements_Xpath));
		for (WebElement element : actionColumnElements) {
			if (element.getText().equals("D")) {
				xpathOfUserColumn = ".//*[@id='linehaulAA:detailListAudit_row_" + row + "']/td[3]";
				xpathOfTableNameColumn = ".//*[@id='linehaulAA:detailListAudit_row_" + row + "']/td[2]";
				userColumnElement = driver.findElement(By.xpath(xpathOfUserColumn));
				TableNameColumnElement = driver.findElement(By.xpath(xpathOfTableNameColumn));
				UserColumnData = userColumnElement.getText();
				TableNameColumnData = TableNameColumnElement.getText();
				if (TableName.equals(TableNameColumnData)) {
					if (UserColumnData.equals(UserID)) {
						logger.info(
								"Found row with Action:" + "D" + " , TableName:" + TableName + " and UserID:" + UserID);
						logger.info("Hence clicking on the row");
						userColumnElement.click();
						waitUntillElementAppears(SubHistoricalTable_FirstElement);
						flag = verifyValuesAreSameInHistoricalAuditsRecordAndInLegsPage(Origin, Destination, Route,
								Description, UserID, Comment);
						break;
					}
				}
			}
			row++;
		}
		return flag;
	}

	public static boolean verifyValuesAreSameInHistoricalAuditsRecordAndInMTLScreen(String UserID,
			Map<String, String> map) throws InterruptedException {
		locale = className + "." + "verifyValuesAreSameInHistoricalAuditsRecordAndInMTLScreen()";
		logger.info("Entering: " + locale);
		boolean flag = false;
		String PROCESS_TYPE_CD_VALUE;
		String HOURLY_CALC_AMT_VALUE;
		String MILEAGE_CALC_AMT_VALUE;
		String LAST_UPDT_USR_NM_MTL_VALUE;

		String HOURLY_CALC_AMT_VALUE_UPTO_1_DECIMAL;
		String MILEAGE_CALC_AMT_VALUE_UPTO_1_DECIMAL;

		Thread.sleep(2000);

		waitUntillElementAppears(PROCESS_TYPE_CD);
		PROCESS_TYPE_CD_VALUE = PROCESS_TYPE_CD.getText();
		HOURLY_CALC_AMT_VALUE = HOURLY_CALC_AMT.getText();
		MILEAGE_CALC_AMT_VALUE = MILEAGE_CALC_AMT.getText();
		LAST_UPDT_USR_NM_MTL_VALUE = LAST_UPDT_USR_NM_MTL.getText();

		logger.info("GPD_VALUE: " + map.get("GPD"));
		logger.info("PROCESS_TYPE_CD_VALUE: " + PROCESS_TYPE_CD_VALUE);
		if (PROCESS_TYPE_CD_VALUE.equals(map.get("GPD"))) {
			logger.info("PROCESS_TYPE_CD_VALUE is same in MTL Table and Historical Audits Table");
			HOURLY_CALC_AMT_VALUE_UPTO_1_DECIMAL = map.get("Hourly");
			HOURLY_CALC_AMT_VALUE_UPTO_1_DECIMAL = HOURLY_CALC_AMT_VALUE_UPTO_1_DECIMAL.substring(0,
					HOURLY_CALC_AMT_VALUE_UPTO_1_DECIMAL.lastIndexOf(".") + 2);
			logger.info("HOURLY_VALUE: " + HOURLY_CALC_AMT_VALUE_UPTO_1_DECIMAL);
			logger.info("HOURLY_CALC_AMT_VALUE: " + HOURLY_CALC_AMT_VALUE);
			if (HOURLY_CALC_AMT_VALUE.equals(HOURLY_CALC_AMT_VALUE_UPTO_1_DECIMAL)) {
				logger.info("HOURLY_CALC_AMT_VALUE is same in MTL Table and Historical Audits Table");
				MILEAGE_CALC_AMT_VALUE_UPTO_1_DECIMAL = map.get("Mileage");
				MILEAGE_CALC_AMT_VALUE_UPTO_1_DECIMAL = MILEAGE_CALC_AMT_VALUE_UPTO_1_DECIMAL.substring(0,
						MILEAGE_CALC_AMT_VALUE_UPTO_1_DECIMAL.lastIndexOf(".") + 2);
				logger.info("MILEAGE__VALUE: " + MILEAGE_CALC_AMT_VALUE_UPTO_1_DECIMAL);
				logger.info("MILEAGE_CALC_AMT_VALUE: " + MILEAGE_CALC_AMT_VALUE);
				if (MILEAGE_CALC_AMT_VALUE.equals(MILEAGE_CALC_AMT_VALUE_UPTO_1_DECIMAL)) {
					logger.info("MILEAGE_CALC_AMT_VALUE is same in MTL Table and Historical Audits Table");
					logger.info("UserID__VALUE: " + UserID);
					logger.info("LAST_UPDT_USR_NM_MTL_VALUE: " + LAST_UPDT_USR_NM_MTL_VALUE);
					if (LAST_UPDT_USR_NM_MTL_VALUE.equals(UserID)) {
						logger.info("LAST_UPDT_USR_NM_MTL_VALUE is same in MTL Table and Historical Audits Table");
						logger.info(
								"Hence the record deleted in MTL table is successfully added in Historical Audits Record");
						flag = true;
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyValuesAreSameInHistoricalAuditsRecordAndInLegsPage(String Origin, String Destination,
			String Route, String Description, String UserID, String Comment) throws InterruptedException {
		locale = className + "." + "verifyValuesAreSameInHistoricalAuditsRecordAndInLegsPage()";
		logger.info("Entering: " + locale);
		boolean flag = false;
		String ORIG_CTR_CD_VALUE;
		String DEST_CTR_CD_VALUE;
		String RTE_CD_VALUE;
		String LEG_DESC_VALUE;
		String LAST_UPDT_USR_NM_Legs_VALUE;
		String COMM_TXT_VALUE;

		Thread.sleep(2000);

		waitUntillElementAppears(ORIG_CTR_CD);

		ORIG_CTR_CD_VALUE = ORIG_CTR_CD.getText();
		DEST_CTR_CD_VALUE = DEST_CTR_CD.getText();
		RTE_CD_VALUE = RTE_CD.getText();
		LEG_DESC_VALUE = LEG_DESC.getText();
		LAST_UPDT_USR_NM_Legs_VALUE = LAST_UPDT_USR_NM_Legs.getText();
		COMM_TXT_VALUE = COMM_TXT.getText();

		logger.info("Origin: " + Origin);
		logger.info("ORIG_CTR_CD_VALUE: " + ORIG_CTR_CD_VALUE);
		logger.info("Destination: " + Destination);
		logger.info("DEST_CTR_CD_VALUE: " + DEST_CTR_CD_VALUE);
		logger.info("Route: " + Route);
		logger.info("RTE_CD_VALUE: " + RTE_CD_VALUE);
		logger.info("Description: " + Description);
		logger.info("LEG_DESC_VALUE: " + LEG_DESC_VALUE);
		logger.info("UserID: " + UserID);
		logger.info("LAST_UPDT_USR_NM_Legs_VALUE: " + LAST_UPDT_USR_NM_Legs_VALUE);
		logger.info("Comment: " + Comment);
		logger.info("COMM_TXT_VALUE: " + COMM_TXT_VALUE);

		if (ORIG_CTR_CD_VALUE.equalsIgnoreCase(Origin)) {
			logger.info("ORIG_CTR_CD_VALUE is same in Legs Page Table and Historical Audits Table");
			if (DEST_CTR_CD_VALUE.equalsIgnoreCase(Destination)) {
				logger.info("DEST_CTR_CD_VALUE is same in Legs Page Table and Historical Audits Table");
				if (RTE_CD_VALUE.equalsIgnoreCase(Route)) {
					logger.info("RTE_CD_VALUE is same in Legs Page Table and Historical Audits Table");
					if (LEG_DESC_VALUE.equalsIgnoreCase(Description)) {
						logger.info("LEG_DESC_VALUE is same in Legs Page Table and Historical Audits Table");
						if (LAST_UPDT_USR_NM_Legs_VALUE.equalsIgnoreCase(UserID)) {
							logger.info(
									"LAST_UPDT_USR_NM_Legs_VALUE is same in Legs Page Table and Historical Audits Table");
							if (COMM_TXT_VALUE.equalsIgnoreCase(Comment)) {
								logger.info("COMM_TXT_VALUE is same in Legs Page Table and Historical Audits Table");
								logger.info(
										"Hence the record deleted in Legs Page is successfully added in Historical Audits Record");
								flag = true;
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static String verifyMTLTablesArePresentInHistoricalAuditsScreen() throws InterruptedException {
		locale = className + "." + "verifyMTLTablesArePresentInHistoricalAuditsScreen()";
		logger.info("Entering: " + locale);
		String returnString = null;
		boolean flag1 = false;
		boolean flag2 = false;
		int rowCount = 0;
		String MTLTableName1 = "DISPATCH_LEG_SUPPLEMENTAL";
		String MTLTableName2 = "DISPATCH_LEG_SUPPLEMENTAL_TYPE";

		List<WebElement> NumberOfPages = driver.findElements(By.xpath(NumberOfPagesInHistoricalAuditsTable));
		logger.info("The total number of pages in HistoricalAudits table is: " + "\"" + NumberOfPages.size() + "\"");
		for (int i = 1; i <= NumberOfPages.size(); i++) {
			String pageElement_Xpath = ".//*[@id='linehaulAA:detailListAudit_paginatorbottom']/span/a[" + i + "]";
			WebElement pageElement = driver.findElement(By.xpath(pageElement_Xpath));
			pageElement.click();
			Thread.sleep(5000);
			List<WebElement> TableNameColumnElements = driver.findElements(By.xpath(TableNameColumnElements_Xpath));
			rowCount = rowCount + TableNameColumnElements.size();
			for (WebElement element : TableNameColumnElements) {
				waitUntillElementNotStale(element);
				if (element.getText().contains(MTLTableName1)) {
					logger.info(
							"MTLTableName: " + "\"" + MTLTableName1 + "\"" + " is present in Historical Audits Screen");
					flag1 = true;
					break;
				}
			}
			for (WebElement element : TableNameColumnElements) {
				if (element.getText().contains(MTLTableName2)) {
					logger.info(
							"MTLTableName: " + "\"" + MTLTableName2 + "\"" + " is present in Historical Audits Screen");
					flag2 = true;
					break;
				}
			}
			if (flag1 && flag2) {
				break;
			}
		}
		logger.info("The total rowCount is: " + "\"" + rowCount + "\"");
		if (!flag1 && !flag2) {
			logger.info("Both MTL tables are not present in Historical Audits Screen");
			returnString = "Both MTL tables not present";
		} else if (!flag1) {
			logger.info("\"" + MTLTableName1 + "\"" + " Table not present");
			returnString = "\"" + MTLTableName1 + "\"" + " Table not present";
		} else if (!flag2) {
			logger.info("\"" + MTLTableName2 + "\"" + " Table not present");
			returnString = "\"" + MTLTableName2 + "\"" + " Table not present";
		} else if (flag1 && flag2) {
			logger.info("Both MTL tables are present in Historical Audits Screen");
			returnString = "Both MTL tables are present";
		}
		return returnString;
	}

	public static boolean verifyPresenceOfExcludedFromDateAndToDate(String FromDate, String ToDate)
			throws InterruptedException {
		locale = className + "." + "verifyPresenceOfExcludedFromDateAndToDate()";
		logger.info("Entering: " + locale);
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag = true;
		int rowCount = 0;
		// Convert from dd-MMM-yyyy to MM/dd/yy format
		// this format is needed to enter data to FromDateTextBox and
		// ToDateTextBox fields
		FromDate = dateConverter(FromDate, "dd-MMM-yyyy", "MM/dd/yy");
		ToDate = dateConverter(ToDate, "dd-MMM-yyyy", "MM/dd/yy");
		waitUntillElementAppears(FromDateTextBox);
		FromDateTextBox.clear();
		FromDateTextBox.sendKeys(FromDate);
		ToDateTextBox.clear();
		ToDateTextBox.sendKeys(ToDate);
		LocateButton.click();
		Thread.sleep(5000);
		// Convert from MM/dd/yy to MM/dd/yyyy format
		// this format is needed to check data in DateTimeColumn
		FromDate = dateConverter(FromDate, "MM/dd/yy", "MM/dd/yyyy");
		ToDate = dateConverter(ToDate, "MM/dd/yy", "MM/dd/yyyy");
		List<WebElement> NumberOfPages = driver.findElements(By.xpath(NumberOfPagesInHistoricalAuditsTable));
		logger.info("The total number of pages in HistoricalAudits table is: " + "\"" + NumberOfPages.size() + "\"");
		for (int i = 1; i <= NumberOfPages.size(); i++) {
			String pageElement_Xpath = ".//*[@id='linehaulAA:detailListAudit_paginatorbottom']/span/a[" + i + "]";
			WebElement pageElement = driver.findElement(By.xpath(pageElement_Xpath));
			pageElement.click();
			Thread.sleep(5000);
			List<WebElement> DateTimeColumnElements = driver.findElements(By.xpath(DateTimeColumnElements_Xpath));
			rowCount = rowCount + DateTimeColumnElements.size();
			// Check if FromDate is available in DateTimeColumn
			for (WebElement fromDateElement : DateTimeColumnElements) {
				if (fromDateElement.getText().contains(FromDate)) {
					logger.info("FromDate: " + FromDate + " is present in DateTimeColumn");
					flag1 = true;
					break;
				}
			}
			// Check if ToDate is available in DateTimeColumn
			for (WebElement toDateElement : DateTimeColumnElements) {
				if (toDateElement.getText().contains(ToDate)) {
					logger.info("ToDate: " + ToDate + " is present in DateTimeColumn");
					flag2 = true;
					break;
				}
			}
			if (flag1 && flag2) {
				break;
			}
		}
		logger.info("The rowCount until from date " + FromDate + " and " + ToDate + " are found is: " + "\"" + rowCount
				+ "\"");
		if (flag1 && flag2) {
			logger.info("Both FromDate and ToDate are present");
			flag = true;
		}
		if (!flag1) {
			logger.info("Since fromDate is not present returning false");
			return false;
		}
		if (!flag2) {
			logger.info("Since toDate is not present returning false");
			return false;
		}
		return flag;
	}

	public static String dateConverter(String inputDate, String fromPattern, String toPattern) {
		locale = className + "." + "dateConverter()";
		logger.info("Entering: " + locale);
		logger.info("Converting from " + fromPattern + " to " + toPattern + " pattern");
		Date getDate = null;
		String convertedDate = null;
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat(fromPattern);
			SimpleDateFormat outputFormat = new SimpleDateFormat(toPattern);
			getDate = inputFormat.parse(inputDate);
			convertedDate = outputFormat.format(getDate);
		} catch (ParseException e) {
			logger.info("", e);
		}
		return convertedDate;
	}

	public static boolean verifyRecordInsertedForLanes(String UserID, String Action) {
		locale = className + "." + "verifyRecordInsertedForLanes()";
		logger.info("Entering: " + locale);
		boolean flag = false;
		int row = 0;
		String TableName = "LANE*";
		String xpathOfUserColumn;
		String xpathOfTableNameColumn;
		String UserColumnData = null;
		String TableNameColumnData = null;
		WebElement userColumnElement = null;
		WebElement TableNameColumnElement = null;
		List<WebElement> actionColumnElements = new ArrayList<WebElement>();
		actionColumnElements = driver.findElements(By.xpath(ActionColumnElements_Xpath));
		for (WebElement element : actionColumnElements) {
			if (element.getText().equals(Action)) {
				xpathOfUserColumn = ".//*[@id='linehaulAA:detailListAudit_row_" + row + "']/td[3]";
				xpathOfTableNameColumn = ".//*[@id='linehaulAA:detailListAudit_row_" + row + "']/td[2]";
				userColumnElement = driver.findElement(By.xpath(xpathOfUserColumn));
				TableNameColumnElement = driver.findElement(By.xpath(xpathOfTableNameColumn));
				UserColumnData = userColumnElement.getText();
				TableNameColumnData = TableNameColumnElement.getText();
				if (TableName.equals(TableNameColumnData)) {
					if (UserColumnData.equals(UserID)) {
						logger.info("Found row with Action:" + "\"" + Action + "\"" + " , TableName:" + TableName
								+ " and UserID:" + UserID);
						logger.info("Hence clicking on the row");
						userColumnElement.click();
						// flag =
						// verifyValuesAreSameInHistoricalAuditsRecordAndUpdateRowInlanesScreen(TransitTime);
						flag = true;
						break;
					}
				}
			}
			row++;
		}
		return flag;
	}

	public static boolean verifyRecordInSubHistoricalScreenForLanesScreenElementTransitTime(int TransitTime) {
		locale = className + "." + "verifyRecordInSubHistoricalScreenForLanesScreenElementTransitTime()";
		logger.info("Entering: " + locale);
		boolean flag = false;
		String LN_TM_NBR_Lanes_Value = "";
		waitUntillElementAppears(SubHistoricalTable_FirstElement);
		LN_TM_NBR_Lanes_Value = LN_TM_NBR_Lanes.getText();
		logger.info("LN_TM_NBR_Lanes_Value: " + "\"" + LN_TM_NBR_Lanes_Value + "\"");
		logger.info("TransitTime: " + "\"" + TransitTime + "\"");
		if (LN_TM_NBR_Lanes_Value.equals(TransitTime)) {
			logger.info("LN_TM_NBR_Lanes_Value is same as TransitTime");
			flag = true;
		} else {
			logger.info("LN_TM_NBR_Lanes_Value is not same as TransitTime");
		}
		return flag;
	}

	public static boolean verifyRecordInSubHistoricalScreenForLanesScreen(Map<String, String> map) {
		locale = className + "." + "verifyRecordInSubHistoricalScreenForLanesScreen()";
		logger.info("Entering: " + locale);
		boolean flag = false;
		String ORIG_CTR_CD_VALUE;
		String DEST_CTR_CD_VALUE;
		String SVC_LVL_CD_Lanes_VALUE;
		String SVC_DAY_NBR_Lanes_VALUE;
		String LN_MILE_NBR_Lanes_VALUE;
		String LAST_UPDT_USR_NM_Legs_Lanes_VALUE;

		waitUntillElementAppears(ORIG_CTR_CD);

		ORIG_CTR_CD_VALUE = ORIG_CTR_CD.getText();
		DEST_CTR_CD_VALUE = DEST_CTR_CD.getText();
		SVC_LVL_CD_Lanes_VALUE = SVC_LVL_CD_Lanes.getText();
		SVC_DAY_NBR_Lanes_VALUE = SVC_DAY_NBR_Lanes.getText();
		LN_MILE_NBR_Lanes_VALUE = LN_MILE_NBR_Lanes.getText();
		LAST_UPDT_USR_NM_Legs_Lanes_VALUE = LAST_UPDT_USR_NM_Legs_Lanes.getText();

		logger.info("Origin: " + map.get("Origin"));
		logger.info("ORIG_CTR_CD_VALUE: " + ORIG_CTR_CD_VALUE);
		logger.info("Destination: " + map.get("Destination"));
		logger.info("DEST_CTR_CD_VALUE: " + DEST_CTR_CD_VALUE);
		logger.info("Service: " + map.get("Service"));
		logger.info("SVC_LVL_CD_Lanes_VALUE: " + SVC_LVL_CD_Lanes_VALUE);
		logger.info("ServiceDays: " + map.get("ServiceDays"));
		logger.info("SVC_DAY_NBR_Lanes_VALUE: " + SVC_DAY_NBR_Lanes_VALUE);
		logger.info("Miles: " + map.get("Miles"));
		logger.info("LN_MILE_NBR_Lanes_VALUE: " + LN_MILE_NBR_Lanes_VALUE);
		logger.info("UserID: " + map.get("UserID"));
		logger.info("LAST_UPDT_USR_NM_Legs_Lanes_VALUE: " + LAST_UPDT_USR_NM_Legs_Lanes_VALUE);

		if (ORIG_CTR_CD_VALUE.equals(map.get("Origin"))) {
			logger.info("ORIG_CTR_CD_VALUE is same in Lanes Page Table and Historical Audits Table");
			if (DEST_CTR_CD_VALUE.equals(map.get("Destination"))) {
				logger.info("DEST_CTR_CD_VALUE is same in Lanes Page Table and Historical Audits Table");
				if (SVC_LVL_CD_Lanes_VALUE.equals(map.get("Service"))) {
					logger.info("SVC_LVL_CD_Lanes_VALUE is same in Legs Page Table and Historical Audits Table");
					if (SVC_DAY_NBR_Lanes_VALUE.equals(map.get("ServiceDays"))) {
						logger.info("SVC_DAY_NBR_Lanes_VALUE is same in Lanes Page Table and Historical Audits Table");
						if (LN_MILE_NBR_Lanes_VALUE.equals(map.get("Miles"))) {
							logger.info(
									"LN_MILE_NBR_Lanes_VALUE is same in Lanes Page Table and Historical Audits Table");
							if (LAST_UPDT_USR_NM_Legs_Lanes_VALUE.equals(map.get("UserID"))) {
								logger.info(
										"LAST_UPDT_USR_NM_Legs_Lanes_VALUE is same in Lanes Page Table and Historical Audits Table");
								logger.info(
										"Hence the record Updated in Lanes Page is successfully added in Historical Audits Record");
								flag = true;
							}
						}
					}
				}
			}
		}
		return flag;
	}

	// for inserted and updated record
	public static boolean verifyRecordInserted(String UserID, String Action, String TableName, String Date_Time) {
		locale = className + "." + "verifyRecordInserted()";
		logger.info("Entering: " + locale);
		boolean flag = false;
		int row = 0;
		List<WebElement> actionColumnElements = driver.findElements(By.xpath(ActionColumnElements_Xpath));
		for (WebElement element : actionColumnElements) {
			WebElement UserColumnElement = driver
					.findElement(By.xpath(".//*[@id='linehaulAA:detailListAudit_row_" + row + "']/td[3]"));
			WebElement TableNameColumnElement = driver
					.findElement(By.xpath(".//*[@id='linehaulAA:detailListAudit_row_" + row + "']/td[2]"));
			WebElement DateTimeColumnElement = driver
					.findElement(By.xpath(".//*[@id='linehaulAA:detailListAudit_row_" + row + "']/td[4]"));
			if (Action.equals(element.getText())) {
				if (TableName.equals(TableNameColumnElement.getText())) {
					if (UserID.equals(UserColumnElement.getText())) {
						if (Date_Time.equals(DateTimeColumnElement.getText())) {
							logger.info("Found row with Action: " + "\"" + Action + "\"" + " , TableName: " + "\""
									+ TableName + "\"" + " , UserID: " + "\"" + UserID + "\"" + " and Date_Time: "
									+ "\"" + Date_Time + "\"");
							logger.info("Hence clicking on the row");
							UserColumnElement.click();
							waitUntillElementAppears(SubHistoricalTable_FirstElement);
							flag = true;
							break;
						}
					}
				}
			}
			row++;
		}
		return flag;
	}

	// for deleted record
	public static boolean verifyRecordInserted(String UserID, String Action, String TableName) {
		locale = className + "." + "verifyRecordInserted()";
		logger.info("Entering: " + locale);
		boolean flag = false;
		int row = 0;
		List<WebElement> actionColumnElements = driver.findElements(By.xpath(ActionColumnElements_Xpath));
		for (WebElement element : actionColumnElements) {
			WebElement UserColumnElement = driver
					.findElement(By.xpath(".//*[@id='linehaulAA:detailListAudit_row_" + row + "']/td[3]"));
			WebElement TableNameColumnElement = driver
					.findElement(By.xpath(".//*[@id='linehaulAA:detailListAudit_row_" + row + "']/td[2]"));
			if (element.getText().equals(Action)) {
				if (TableName.equals(TableNameColumnElement.getText())) {
					if (UserID.equals(UserColumnElement.getText())) {
						logger.info("Found row with Action: " + "\"" + Action + "\"" + " , TableName: " + "\""
								+ TableName + "\"" + " , UserID: " + "\"" + UserID + "\"");
						logger.info("Hence clicking on the row");
						UserColumnElement.click();
						waitUntillElementAppears(SubHistoricalTable_FirstElement);
						flag = true;
						break;
					}
				}
			}
			row++;
		}
		return flag;
	}
}
