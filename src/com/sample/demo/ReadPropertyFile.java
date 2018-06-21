package TestLibrary.org.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReadPropertyFile {
	Properties propertyData = new Properties();
	
	public Properties ReadObject(String filePath) throws IOException{
		//System.getProperty("user.dir") + "/file.txt"
		InputStream stream = new FileInputStream(new File(filePath));
		
		propertyData.load(stream);
		return propertyData;
	}

}
