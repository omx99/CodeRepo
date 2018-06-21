package library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class GlobalFunctions {

	private WebDriver driver = null;
	private Properties properties = null;
	private InputStream inputStream = null;
	private String excelFile = null;
	public static String DataSheetName = null;
	public static String AppFunctionality = null;
	public static String browser = null;
	Logger logger = null;
	String locale = null;
	String className = "GlobalFunctions";

	public GlobalFunctions(Logger logger) {
		this.logger = logger;
	}

	public WebDriver getDriver() {
		locale = className + "." + "getDriver()";
		logger.info("Entering: " + locale);
		try {
			browser = getPropertyOf("browser");
			if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", "Driver/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				logger.info("So got instance of InternetExplorerDriver");
			} else if (browser.equalsIgnoreCase("FR")) {
				driver = new FirefoxDriver();
				logger.info("So got instance of FirefoxDriver");
			} else if (browser.equalsIgnoreCase("CR")) {
				System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
				driver = new ChromeDriver();
				logger.info("So got instance of ChromeDriver");
			} else {
				logger.info("Invalid browser");
			}
			driver.manage().window().maximize();
		} catch (IOException e) {
			logger.info("Exception while getting property value", e);
		} catch (Exception e) {
			logger.info("Exception while getting browser instance", e);
		}

		return driver;
	}

	public void goToURL(String url) {
		locale = className + "." + "goToURL()";
		logger.info("Entering: " + locale);
		logger.info("Connecting to URL: " + url);
		driver.get(url);
	}

	public void getExcelFilePath() {
		try {
			excelFile = getPropertyOf("excelFilePath");
		} catch (IOException e) {
			logger.info("", e);
		}
	}

	public String getDataFromExcel(String columnName) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(excelFile);
		XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
		String cellValue = null;
		int cellType;

		XSSFSheet sheet = workBook.getSheet(DataSheetName);
		int rowCount = sheet.getLastRowNum() + 1;
		int columnCount = sheet.getRow(0).getLastCellNum();
		int tempColumnNumber = 0;

		for (int iRow = 1; iRow < rowCount; iRow++) {
			XSSFRow row = sheet.getRow(iRow);
			if (AppFunctionality.equals(row.getCell(0).toString())) {
				for (int iColumn = 0; iColumn < columnCount; iColumn++) {
					XSSFRow firstRow = sheet.getRow(0);
					XSSFCell cell = firstRow.getCell(iColumn);
					if (columnName.equals(cell.getStringCellValue())) {
						tempColumnNumber = iColumn;
						break;
					}
				}
				XSSFRow currentRow = sheet.getRow(iRow);
				cellValue = currentRow.getCell(tempColumnNumber).toString();
				cellType = currentRow.getCell(tempColumnNumber).getCellType();
				if (cellType == Cell.CELL_TYPE_NUMERIC) {
					if (cellValue.endsWith(".0")) {
						cellValue = cellValue.substring(0, cellValue.indexOf("."));
					}
				}
				logger.info(
						"The cell value for row " + "\"" + AppFunctionality + "\"" + " and column " + "\"" + columnName
								+ "\"" + " in sheet " + "\"" + DataSheetName + "\"" + " is " + "\"" + cellValue + "\"");
				fileInputStream.close();
				workBook.close();
				break;
			}
		}
		return cellValue;
	}

	public void writeDataToExcel(String columnName, String status) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(excelFile);
		XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);

		XSSFSheet sheet = workBook.getSheet(DataSheetName);
		int rowCount = sheet.getLastRowNum() + 1;
		int columnCount = sheet.getRow(0).getLastCellNum();
		int tempColumnNumber = 0;

		for (int iRow = 1; iRow < rowCount; iRow++) {
			XSSFRow row = sheet.getRow(iRow);
			if (AppFunctionality.equals(row.getCell(0).toString())) {
				for (int iColumn = 0; iColumn < columnCount; iColumn++) {
					XSSFRow firstRow = sheet.getRow(0);
					XSSFCell cell = firstRow.getCell(iColumn);
					if (columnName.equals(cell.getStringCellValue())) {
						tempColumnNumber = iColumn;
						break;
					}
				}
				XSSFRow currentRow = sheet.getRow(iRow);
				currentRow.createCell(tempColumnNumber).setCellValue(status);
				logger.info("Setting the cell value for row " + "\"" + AppFunctionality + "\"" + " and column " + "\""
						+ columnName + "\"" + " in sheet " + "\"" + DataSheetName + "\"" + " to " + "\"" + status
						+ "\"");
				CellStyle cellStyle = getStyleSheet(workBook, status);
				currentRow.getCell(tempColumnNumber).setCellStyle(cellStyle);

				FileOutputStream fileOutputStream = new FileOutputStream(excelFile);
				workBook.write(fileOutputStream);
				workBook.close();
				fileInputStream.close();
				fileOutputStream.close();
				break;
			}
		}
	}

	public CellStyle getStyleSheet(XSSFWorkbook workBook, String status) {
		CellStyle cellStyle = workBook.createCellStyle();

		if (status.equalsIgnoreCase(Constants.PASS)) {
			cellStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		} else if (status.equalsIgnoreCase(Constants.SKIP)) {
			cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		} else if (status.equalsIgnoreCase(Constants.FAIL)) {
			cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		}
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return cellStyle;
	}

	public void setResultStatusInExcelAs(String status) {
		try {
			writeDataToExcel(Constants.ResultStatusUpdateColumnInExcel, status);
		} catch (IOException e) {
			logger.info("Exception while writing result status to excel", e);
		}
	}

	public void loadPropertiesFile() {
		locale = className + "." + "loadPropertiesFile()";
		logger.info("Entering: " + locale);
		try {
			properties = new Properties();
			inputStream = new FileInputStream(Constants.propertyFile);
			properties.load(inputStream);
			logger.info("Loaded the properties of " + "\"" + Constants.propertyFile + "\"" + " file");
		} catch (FileNotFoundException e) {
			logger.info(Constants.propertyFile + " not found", e);
		} catch (IOException e) {
			logger.info("Exception while reading property file", e);
		}
	}

	public String getPropertyOf(String keyName) throws IOException {
		String keyValue = null;
		try {
			keyValue = properties.getProperty(keyName);
			logger.info("The property value for " + "\"" + keyName + "\"" + " is " + "\"" + keyValue + "\"");
		} catch (Exception e) {
			logger.info("Exception while reading property value", e);
		}
		return keyValue;
	}

}
