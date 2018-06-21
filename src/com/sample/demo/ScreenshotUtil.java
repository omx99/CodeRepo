package TestLibrary.org.Utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author somasish
 *
 */
public class ScreenshotUtil {

	/**
	 * @author somasish
	 * @param Path or foldername where screenshot should be kept
	 * @throws IOException
	 * Method to take screenshot
	 */
	public  void TakeScreenshotForPASS(String Path,WebDriver driver,String UserstoryName) throws IOException{
	//WebDriver driver = SeleniumRepo.getWebDriver();
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  
		Path = Path +"\\"+UserstoryName+"_Pass.png";
	FileUtils.copyFile(scrFile, new File(Path));
	
}
	
	public  void TakeScreenshotForFAIL(String Path,WebDriver driver,String UserstoryName) throws IOException{
		//WebDriver driver = SeleniumRepo.getWebDriver();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  
		Path = Path +"\\"+UserstoryName+"_Fail.png";
		FileUtils.copyFile(scrFile, new File(Path));
		
	}
	
	/**
	 * @author somasish
	 * @return Path of the folder Created
	 * Create a folder with Time Stamp
	 */
	public  String CreateFolderWithTimeStamp(String UserstoryName)
	
	{
	Date today = Calendar.getInstance().getTime();
	// (2) create our date "formatter" (the date format we want)
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
	// (3) create a new String using the date format we want
	String folderName = formatter.format(today)+"_"+UserstoryName;
	
	folderName = ".//Screenshots//" + folderName;
	//String filename = String.Format("archive_{0:yyyyMMdd}", DateTime.Today);
	File dir = new File(folderName);
	 // Tests whether the directory denoted by this abstract pathname exists.
	boolean exists = dir.exists();
	System.out.println("Directory " + dir.getPath() + " exists: " + exists);
	if (exists) {
	 System.out.println("Main Folder Exist");
	}
	else
	{
		System.out.println("Main Folder Doesnt Exist");
		File directory = new File(folderName);
		directory.mkdir();
	}
	
return folderName ;
}


}