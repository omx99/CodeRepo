package objectRepository;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DashboardPageRepository extends CommonRepository {

	public final static String className = "DashboardPageRepository";

	public final static String KeyColumnElements_Xpath = ".//*[@id='linehaulDashboard2:codeList']//td[1]";

	// to locate key
	@FindBy(xpath = ".//*[@id='linehaulDashboard2:codeList_row_1']/td[1]")
	public static WebElement rowToClick;

	// to locate sibling td
	@FindBy(xpath = ".//*[@id='linehaulDashboard2:panel1_content']//tr[2]")
	public static WebElement keyAttribute;

	@FindBy(xpath = ".//*[@id='linehaulDashboard2:panel1_content']//tr[8]")
	public static WebElement updateAttribute;

	@FindBy(xpath = ".//td")
	public static WebElement tableData;

	// for key
	@FindBy(xpath = "//select[@id='linehaulDashboard2:detKey']")
	public static WebElement KeyDetail;

	// for key2
	@FindBy(id = "linehaulDashboard2:detKey2")
	public static WebElement Key2Detail;

	// Display Value
	@FindBy(id = "linehaulDashboard2:detdisplayValue")
	public static WebElement displayValuedetail;

	// Actual Value
	@FindBy(id = "linehaulDashboard2:detacutalValue")
	public static WebElement actualValueDetail;

	// add Button
	@FindBy(xpath = "//input[@id='linehaulDashboard2:add']")
	public static WebElement add;

	@FindBy(xpath = "//*[text()='Must enter a Key,Display Value, and Actual value for add/update.']")
	public static WebElement errorMsg;

	@FindBy(xpath = "//*[contains(text(),' cannot be deleted because it is system-required.')]")
	public static WebElement deleteErrorMsg;

	@FindBy(xpath = "//*[contains(text(),'Row deleted')]")
	public static WebElement rowDeleted;

	@FindBy(xpath = "//*[text()='A new entry for has been added.']")
	public static WebElement addMsg;

	@FindBy(xpath = "//input[@id='linehaulDashboard2:update']")
	public static WebElement update;

	@FindBy(xpath = "//input[@id='linehaulDashboard2:delete']")
	public static WebElement delete;

	@FindBy(xpath = "//span[text()='EQUIPMENT_MAX_SEQ']")
	public static WebElement EQUIPMENT_MAX_SEQ;

	@FindBy(xpath = "//span[text()='MAX_BB_NUM']")
	public static WebElement MAX_BB_NUM;

	@FindBy(xpath = "//span[text()='ROUTE_DELAY_VALUES']")
	public static WebElement ROUTE_DELAY_VALUES;

	@FindBy(xpath = "//span[text()='SVC_LEVEL_CORE']")
	public static WebElement SVC_LEVEL_CORE;

	@FindBy(id = "ui-dialog-title-linehaulDashboard2:deleteDialog_main")
	public static WebElement deleteDialog;

	@FindBy(id = "linehaulDashboard2:deleteDialogDeleteBtn")
	public static WebElement confirmDelete;

	@FindBy(id = "linehaulDashboard2:deleteDialogCancelBtn")
	public static WebElement cancel;

	static {
		PageFactory.initElements(driver, new DashboardPageRepository());
	}

	public static void clickOnKey() throws InterruptedException {
		rowToClick.click();
		Thread.sleep(3000);
	}

	public static void clickOnupdationAttribute() throws InterruptedException {
		updateAttribute.click();
		Thread.sleep(3000);
	}

	public static List<String> actualKeyAttributeValue() {
		List<WebElement> Attributes = keyAttribute.findElements(By.xpath(".//td"));
		List<String> actualAttributes = new ArrayList<String>();
		for (WebElement attribute : Attributes) {
			actualAttributes.add(attribute.getText());
		}
		return actualAttributes;
	}

	public static List<String> displayKeyAttributeValue() {
		List<String> displayAttribute = new ArrayList<String>();
		WebElement keyDetail = new Select(KeyDetail).getFirstSelectedOption();
		displayAttribute.add(keyDetail.getText());
		displayAttribute.add(Key2Detail.getAttribute("value"));
		displayAttribute.add(displayValuedetail.getAttribute("value"));
		displayAttribute.add(actualValueDetail.getAttribute("value"));
		return displayAttribute;
	}

	public static void enterDetail(String key, String key2, String displayValue, String actualValue) {
		locale = className + "." + "enterDetail()";
		logger.info("Entering: " + locale);
		Select select = new Select(KeyDetail);
		select.selectByVisibleText(key);
		Key2Detail.clear();
		Key2Detail.sendKeys(key2);
		displayValuedetail.clear();
		displayValuedetail.sendKeys(displayValue);
		actualValueDetail.clear();
		actualValueDetail.sendKeys(actualValue);
	}

	public static void addDetail() {
		add.click();
	}

	public static void updateDetail() throws InterruptedException {
		update.click();
		Thread.sleep(4000);
	}

	public static void deleteDetail() throws InterruptedException {
		delete.click();
		Thread.sleep(3000);
	}

	public static void deleteAttribute(String deleteAttribute) throws InterruptedException {
		if (deleteAttribute.equalsIgnoreCase("EQUIPMENT_MAX_SEQ")) {
			EQUIPMENT_MAX_SEQ.click();
			Thread.sleep(2000);
			delete.click();
		}
		if (deleteAttribute.equalsIgnoreCase("MAX_BB_NUM")) {
			MAX_BB_NUM.click();
			Thread.sleep(2000);
			delete.click();
		}
		if (deleteAttribute.equalsIgnoreCase("ROUTE_DELAY_VALUES")) {
			ROUTE_DELAY_VALUES.click();
			Thread.sleep(2000);
			delete.click();
		}

	}

	public static boolean isErrorMessageDisplayed() {
		boolean message = false;
		try {
			waitUntillElementAppears(errorMsg);
			message = errorMsg.isDisplayed();
			logger.info("will return true");
		} catch (Exception e) {
			message = false;
		}
		return message;

	}

	public static boolean isAddMessageDisplayed() {
		waitUntillElementAppears(addMsg);
		boolean message = addMsg.isDisplayed();
		return message;
	}

	public static boolean isDeleteErrorMessageDisplayed() {
		boolean message = false;
		try {
			waitUntillElementAppears(deleteErrorMsg);
			message = deleteErrorMsg.isDisplayed();
			logger.info("will return true");
		} catch (Exception e) {
			message = false;
		}
		return message;
	}

	public static void deleteSvcLevelCore() throws InterruptedException {
		SVC_LEVEL_CORE.click();
		Thread.sleep(2000);
		delete.click();
	}

	public static boolean isDeleteDialogDisplayed() {
		boolean message = false;
		try {
			waitUntillElementAppears(deleteDialog);
			message = deleteDialog.isDisplayed();
			logger.info("will return true");
		} catch (Exception e) {
			message = false;
		}
		return message;
	}

	public static void cancelDelete() throws InterruptedException {
		cancel.click();
		Thread.sleep(1500);
	}

	public static void confirmDelete() {
		confirmDelete.click();
	}

	public static boolean isRowDeletedMessageDisplayed() {
		boolean message = false;
		try {
			waitUntillElementAppears(rowDeleted);
			message = rowDeleted.isDisplayed();
			logger.info("will return true");
		} catch (Exception e) {
			message = false;
		}
		return message;
	}

	public static int DisplayValueOfMAX_BB_NUMRow() {
		int DisplayValue = 0;
		int row = 0;
		List<WebElement> KeyColumnElements = driver.findElements(By.xpath(KeyColumnElements_Xpath));
		for (WebElement element : KeyColumnElements) {
			if (element.getText().equals("MAX_BB_NUM")) {
				String DisplayValueColumnElement_Xpath = ".//*[@id='linehaulDashboard2:codeList_row_" + row
						+ "']/td[3]";
				WebElement DisplayValueColumnElement = driver.findElement(By.xpath(DisplayValueColumnElement_Xpath));
				DisplayValue = Integer.parseInt(DisplayValueColumnElement.getText());
				break;
			}
			row++;
		}
		return DisplayValue;
	}

}
