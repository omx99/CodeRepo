package TestLibrary.org.Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;



public class FunctionLibrary  extends ScreenshotUtil{

	
	private StringBuffer verificationErrors;
		
	public FunctionLibrary(){
		this.verificationErrors= new StringBuffer();
	}

	   
public String getLibMethodName() {
	String methodName = "";
	String className = null;
	StackTraceElement[] stack = null;
	
	try {
		stack = Thread.currentThread().getStackTrace();
		int stackIndex = stack.length - 1;
		
		for (int i = stackIndex; i > -1; i--) {
			StackTraceElement each = stack[i];

			if(each.getClassName().contains("TC")) {
				methodName = methodName + ">" + each.getMethodName() + ":" + each.getLineNumber();
				className = each.getClassName();
			}
		}
	} catch (Exception e) {}
	
	return  ">>" + methodName;
}



public void verifyTru(Boolean b, String msg) {
	try {
		
		//System.out.println("result= " +  b);
		AssertJUnit.assertTrue(b.booleanValue());
	} catch (Error e) 
	{
		
		this.verificationErrors.append(e);
		Reporter.log( "<div>>>>>" + msg + "</div><BR/>");
	
	
	} 
}




public void verifyTrue(Boolean b, String msg) {
	if(b==true) {
		
		Reporter.log( "<div>>>>>" + msg + "</div><BR/>");
	} else{
		Reporter.log( "<div>>>>>" + msg+ "</div><BR/>");
	}
}


public static boolean isElementPresent(By by, WebDriver driver) 
{
	//System.out.println("Value "+by);
  boolean present;
  try
    {
	 // WebDriverWait wait = new WebDriverWait(driver,120);
	 // wait.until(ExpectedConditions.visibilityOfElementLocated(by));
      driver.findElement(by);
//       driver.findElement( By.linkText: AMG Performance Studio styling);
  //    System.out.println("Bali Link--> "+driver.findElement(by).getText());
      present = true;
    }catch (NoSuchElementException e)
    {
    	
      present = false;
     }

 return present;
}



public boolean verifyEqualsWithDiff( String expected, String actual, String msg) {
    
    Boolean result=false;      
    try {
           String valueExpected = expected;
           String valueActual = actual;
           
           
           result = valueExpected.contains(valueActual);
           if(result==true){
        
        	   Reporter.log( "<div>>>>>" + msg + ":  PASSED" +"</div><BR/>");
           }
           else{
        	   msg=msg +":  FAILED";
           }
         //  verifyTrue(result, msg +"<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>>> DIFFERENCE =  "+ StringUtils.difference(valueExpected, valueActual) +"</div><div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>>> Expected &nbsp; = &nbsp;&nbsp;\"" + valueExpected + "\" </div><div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>>> Actual &nbsp;&nbsp; = &nbsp;&nbsp;\"" + valueActual +"\"</div>");
           verifyTru(result, msg +"<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </div><div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>>> Expected &nbsp; = &nbsp;&nbsp;\"" + valueExpected + "\" </div><div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>>> Actual &nbsp;&nbsp; = &nbsp;&nbsp;\"" + valueActual +"\"</div>");
    }
    catch (Exception e) {
           // TODO Auto-generated catch block
    	
           e.printStackTrace();
    }
    return result;
}


public boolean verifyEqualsWithDiff2( String expected, String actual, String msg) {
    
    Boolean result=false;      
    try {
           String valueExpected = expected;
           String valueActual = actual;
           
           
           result = valueExpected.equals(valueActual);
           if(result==true){
        
        	   Reporter.log( "<div>>>>>" + msg + ":  PASSED" +"</div><BR/>");
           }
           else{
        	   msg=msg +":  FAILED";
           }
         //  verifyTrue(result, msg +"<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>>> DIFFERENCE =  "+ StringUtils.difference(valueExpected, valueActual) +"</div><div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>>> Expected &nbsp; = &nbsp;&nbsp;\"" + valueExpected + "\" </div><div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>>> Actual &nbsp;&nbsp; = &nbsp;&nbsp;\"" + valueActual +"\"</div>");
           verifyTru(result, msg +"<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </div><div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>>> Expected &nbsp; = &nbsp;&nbsp;\"" + valueExpected + "\" </div><div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>>> Actual &nbsp;&nbsp; = &nbsp;&nbsp;\"" + valueActual +"\"</div>");
    }
    catch (Exception e) {
           // TODO Auto-generated catch block
    	
           e.printStackTrace();
    }
    return result;
}
/**
* Overridden checkForVerificationErrors method from SeleneseTestBase Class - 
 * Asserts that there were no verification errors during the current test, 
 * failing immediately if any are found
*/
public void checkForVerificationErrors() {
       String verificationErrorString = this.verificationErrors.toString();
       // Clear Verification Errors so that it is ready to test new verifications
       clearVerificationErrors();
              
       if (!"".equals(verificationErrorString))
              Assert.fail(verificationErrorString);
}
       
/**
* Overridden clearVerificationErrors method from SeleneseTestBase Class - 
 *  Clears out the list of verification errors
*/
public void clearVerificationErrors() {
       this.verificationErrors = new StringBuffer();
}
/*
 * screenshot code method
 */

		

	public void addScreenshot(WebDriver driver,String TestName) throws IOException,ClassNotFoundException, InterruptedException {
		String imageName = TestName+"_"+System.currentTimeMillis() + ".png";

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(".\\Screenshots\\" + imageName));
	}
	
	public void addScreenshotForFAIL(WebDriver driver,String TestName) throws IOException,ClassNotFoundException, InterruptedException {
		String imageName = TestName+"_"+System.currentTimeMillis() + ".png";

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(".\\Screenshots\\FAIL\\" + imageName));
	}
	
	public void addScreenshotForPASS(WebDriver driver,String TestName) throws IOException,ClassNotFoundException, InterruptedException {
		String imageName = TestName+"_"+System.currentTimeMillis() + ".png";

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(".\\Screenshots\\PASS\\" + imageName));
	}
	


}
