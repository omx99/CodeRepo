package TestLibrary.org.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class archiveUtil {



 /*public  void xyz() throws Exception {
	         Define ZIP File System Properies in HashMap     
	        Map<String, String> zip_properties = new HashMap<String, String>(); 
	         We want to read an existing ZIP File, so we set this to False 
	        zip_properties.put("create", "false");
	         Specify the encoding as UTF -8 
	        zip_properties.put("encoding", "UTF-8");        
	         Specify the path to the ZIP File that you want to read as a File System 
	        URI zip_disk = URI.create("jar:file:/my_zip_file.zip");
	         Create ZIP file System 
	        try (FileSystem zipfs = FileSystems.newFileSystem(zip_disk, zip_properties)) {
	              Create a Path in ZIP File 
	            Path ZipFilePath = zipfs.getPath("TESTER.zip");
	             Path where the file to be added resides 
	            Path addNewFile = Paths.get("C:/temp/TESTER.zip");  
	             Append file to ZIP File 
	            Files.copy(addNewFile,ZipFilePath); 
	        } 
	    }
	
	
	public void backfile(){
	Path source = Paths.get(".\\Sprint");
    Path target = Paths.get(".\\Reports.txt");
    try {
        Files.copy(source, target);
    } catch (IOException e1) {
        e1.printStackTrace();
    }
	}*/
	
	public static void main(String[] args) throws IOException {
		File directoryToZip = new File("X:\\Documents\\FINAL_PROJ\\v2\\ShipmentInquiry_Automation_New\\Screenshots");

		List<File> fileList = new ArrayList<File>();
		System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
		getAllFiles(directoryToZip, fileList);
		System.out.println("---Creating zip file");
		writeZipFile(directoryToZip, fileList);
		System.out.println("---Done");
	}

	public static void getAllFiles(File dir, List<File> fileList) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				fileList.add(file);
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					getAllFiles(file, fileList);
				} else {
					System.out.println("     file:" + file.getCanonicalPath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeZipFile(File directoryToZip, List<File> fileList) {

		try {
			FileOutputStream fos = new FileOutputStream(directoryToZip.getName() + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {
				if (!file.isDirectory()) { // we only zip files, not directories
					addToZip(directoryToZip, file, zos);
				}
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
			IOException {

		FileInputStream fis = new FileInputStream(file);

		// we want the zipEntry's path to be a relative path that is relative
		// to the directory being zipped, so chop off the rest of the path
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		System.out.println("Writing '" + zipFilePath + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}

	
	
	
}
