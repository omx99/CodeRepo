package objectRepository;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonRepository {

	protected static WebDriver driver;
	protected static WebDriverWait wait = null;
	protected static WebDriverWait longWait = null;
	protected static Actions actions = null;

	protected static Logger logger = null;
	protected static String locale = null;
	protected static String className = "CommonRepository";

	public static void init(WebDriver driver, Logger logger) {
		CommonRepository.driver = driver;
		CommonRepository.logger = logger;
		PageFactory.initElements(driver, new CommonRepository());
		wait = new WebDriverWait(driver, 20);
		longWait = new WebDriverWait(driver, 90);
		actions = new Actions(driver);
	}

	protected static void waitUntillElementAppears(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected static void waitUntillElementDisappears(WebElement element) {
		wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
	}

	protected static void waitUntillTitleAppears(String title) {
		wait.until(ExpectedConditions.titleIs(title));
	}

	// protected static void waitUntillAttributeValueIs(WebElement element,
	// String Attribute, String Attribute_Value) {
	// wait.until(ExpectedConditions.attributeToBe(element, Attribute,
	// Attribute_Value));
	// }

	protected static void waitUntillElementClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected static void waitUntillElementIsInvisible(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	protected static void waitUntillElementNotStale(WebElement element) {
		wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
	}

	// protected static void waitUntillNumberOfElementAre(By locator, int
	// number) {
	// wait.until(ExpectedConditions.numberOfElementsToBe(locator, number));
	// }

	protected static void waitUntillElementIsSelected(WebElement element) {
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}

	protected static void waitUntilltextToBePresentInElementValue(WebElement element, String text) {
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	protected static void longWaitUntillElementAppears(WebElement element) {
		longWait.until(ExpectedConditions.visibilityOf(element));
	}

	protected static void longWaitUntillElementDisappears(WebElement element) {
		longWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
	}

	protected static void longWaitUntillTitleAppears(String title) {
		longWait.until(ExpectedConditions.titleIs(title));
	}

	// protected static void longWaitUntillAttributeValueIs(WebElement element,
	// String Attribute, String Attribute_Value) {
	// longWait.until(ExpectedConditions.attributeToBe(element, Attribute,
	// Attribute_Value));
	// }

	protected static void longWaitUntillElementClickable(WebElement element) {
		longWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected static void longWaitUntillElementIsInvisible(By locator) {
		longWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	protected static void longWaitUntillElementNotStale(WebElement element) {
		longWait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
	}

	// protected static void longWaitUntillNumberOfElementAre(By locator, int
	// number) {
	// longWait.until(ExpectedConditions.numberOfElementsToBe(locator, number));
	// }

	protected static void longWaitUntillElementIsSelected(WebElement element) {
		longWait.until(ExpectedConditions.elementToBeSelected(element));
	}

	protected static void longWaitUntilltextToBePresentInElementValue(WebElement element, String text) {
		longWait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

}
