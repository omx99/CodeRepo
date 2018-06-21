package TestLibrary.org.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class loggingCapsule {

    private static Logger logger = Logger.getLogger(loggingCapsule.class);
    
    public loggingCapsule(){
      	String log4JPropertyFile = ".\\log4j.properties";
    	Properties p = new Properties();

    	try {
    	    p.load(new FileInputStream(log4JPropertyFile));
    	    PropertyConfigurator.configure(p);
    	    logger.info("*************************************************");
    	} catch (IOException e) {
    	    //DAMN! I'm not....

    	}
    }
    
    
    
    public void info(String message) {

    	//Logger logger = Logger.getLogger(this.getClass());
  
        logger.info(message);
    }
    public  void error(String message){
    	 logger.error(message);
        System.out.println(message);
    }

}
