package TestLibrary.org.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dbConfig {

	static ReadPropertyFile dbPropFile = new ReadPropertyFile();
	static String ORpropertiesfilePath = ".\\src\\test\\java\\TestLibrary\\org\\PropertyFiles\\DBdetails.properties";


	/*
	 * 
	 * iput :::: String[] columnNames, String SQL_Query
	 */

	public static void selectRecordsFromDbUserTable(String[] columnNames,
			String SQL_Query, String testclassname,String path) throws SQLException,
			IOException {

		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = SQL_Query;
		String[] ColumnNames = columnNames;
		String Testclassname = testclassname;

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);

		//	String path = ;
			FileInputStream fileinp;
			try {
				fileinp = new FileInputStream(path);

				XSSFWorkbook workbook;

				workbook = new XSSFWorkbook(fileinp);
				XSSFSheet spreadsheet = workbook.createSheet(Testclassname);

				// data write in excel
				XSSFRow row2 = spreadsheet.createRow((short) 0);
				for (int i = 0; i < ColumnNames.length; i++) {
					row2.createCell(i).setCellValue(ColumnNames[i]);
				}

				// data write from db

				int rowcount = 0;
				while (rs.next()) {
					rowcount++;
					XSSFRow row3 = spreadsheet.createRow((short) rowcount);
					for (int i = 0; i < ColumnNames.length; i++) {
						row3.createCell(i).setCellValue(
								rs.getString(ColumnNames[i]));
					}

				}

				FileOutputStream fileOut = new FileOutputStream(path);
				workbook.write(fileOut);
				fileOut.close();
				System.out.println("File is written successfully");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	
	public static List<String> Select10RowsofDebtorAccount(
			String SQL_Query) throws SQLException,
			IOException {

		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = SQL_Query;
	
		
				List<String> DebtorAccountNumbers = new ArrayList<String>();
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);


				ResultSetMetaData rsmd = rs.getMetaData(); 
				int columnCount = rsmd.getColumnCount();

			 DebtorAccountNumbers = new ArrayList<String>(columnCount); 
				while (rs.next()) {              
				   int i = 1;
				   while(i <= columnCount) {
					   DebtorAccountNumbers.add(rs.getString(i++));
				   }
				}
			
				 

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		

		catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return DebtorAccountNumbers;
		
	}
	
	
	
	
	
	
	private static Connection getDBConnection() throws IOException {

		Connection dbConnection = null;
		Properties ObjRepoPropertyData = dbPropFile
				.ReadObject(ORpropertiesfilePath);
		System.out.println(ObjRepoPropertyData.getProperty("DB_CONNECTION"));
		System.out.println(ObjRepoPropertyData.getProperty("DB_DRIVER"));
		try {
			Class.forName(ObjRepoPropertyData.getProperty("DB_DRIVER"))
					.newInstance();
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			ex.printStackTrace();
			ex.getMessage();
			// System.exit(1);
		} catch (IllegalAccessException ex) {
			System.out.println("Error: access problem while loading!");
			// System.exit(2);
		} catch (InstantiationException ex) {
			System.out.println("Error: unable to instantiate driver!");
			// System.exit(3);
		}

		try {

			// dbConnection = DriverManager.getConnection(DB_CONNECTION,
			// DB_USER,DB_PASSWORD);
			dbConnection = DriverManager.getConnection(
					ObjRepoPropertyData.getProperty("DB_CONNECTION"),
					ObjRepoPropertyData.getProperty("DB_USER"),
					ObjRepoPropertyData.getProperty("DB_PASSWORD"));
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

	
	
	private static Connection getDBConnectionForOracle() throws IOException {

        Connection dbConnection = null;
        Properties ObjRepoPropertyData = dbPropFile.ReadObject(ORpropertiesfilePath);
        System.out.println(ObjRepoPropertyData.getProperty("DB_CONNECTION"));
        System.out.println(ObjRepoPropertyData.getProperty("DB_DRIVER"));
        try {
               Class.forName(ObjRepoPropertyData.getProperty("DB_DRIVER")).newInstance();
        } catch (ClassNotFoundException ex) {
               System.out.println("Error: unable to load driver class!");
               ex.printStackTrace();
               ex.getMessage();
               // System.exit(1);
        } catch (IllegalAccessException ex) {
               System.out.println("Error: access problem while loading!");
               // System.exit(2);
        } catch (InstantiationException ex) {
               System.out.println("Error: unable to instantiate driver!");
               // System.exit(3);
        }

        try {

               // dbConnection = DriverManager.getConnection(DB_CONNECTION,
               // DB_USER,DB_PASSWORD);
               dbConnection = DriverManager.getConnection(
                            ObjRepoPropertyData.getProperty("ORACLE_CONNECTION"),
                            ObjRepoPropertyData.getProperty("ORL_USER"),
                            ObjRepoPropertyData.getProperty("ORL_PASSWORD"));
               return dbConnection;

        } catch (SQLException e) {

               System.out.println(e.getMessage());

        }

        return dbConnection;

 }

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}


	

	

}
