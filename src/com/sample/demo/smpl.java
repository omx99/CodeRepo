/*
		 * properties write code
		 */
		Properties prop = new Properties();
		FileOutputStream output = null;
		System.out.println(new File(".").getAbsolutePath());
		try {
		output = new FileOutputStream("D:/Workspace/SampleProj/db.properties");
		
		prop.setProperty("database", "localhost");
		prop.setProperty("dbuser", "mkyong");
		prop.setProperty("dbpassword", "password");
		
		prop.store(output, null);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			output.close();
		}
		

	/*
	 * end of properties write code
	 */


		/*
		 * other way
		 */
//		 FileReader reader=new FileReader("/db.properties");  
//	      
//		    Properties p=new Properties();  
//		    p.load(reader);  
//		      
//		    System.out.println(p.getProperty("user"));  
//		    System.out.println(p.getProperty("password"));  
		/*
		 * 
		 */
		
		    
		    /*
		     * webdriver setup code
		     */
		    /*
		FirefoxProfile ff = new FirefoxProfile();
		ff.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
		FirefoxDriver driver = new FirefoxDriver(ff);
		   
		driver.get("https://www.google.co.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("browser is open");

		driver.close();
		*/
		/*
		 * set code end
		 */