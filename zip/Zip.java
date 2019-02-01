package zip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
		
	
	
	
	public static void getAllFiles(File dir, List<File> fileList) {
			try {
				File[] files = dir.listFiles();
				for (File file : files) {
					fileList.add(file);
					if (file.isDirectory()) {
						getAllFiles(file, fileList);
					} else {
						System.out.println("No Files in path");
					}
				}
			} 
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
	
		public static void writeZipFile(String pathOfResultantZippedFiles,File directoryToZip) {
	
			try {
				FileOutputStream fos = new FileOutputStream(pathOfResultantZippedFiles+directoryToZip.getName() + ".zip");
				ZipOutputStream zos = new ZipOutputStream(fos);
				
				List<File> fileList = new ArrayList<File>();
				getAllFiles(directoryToZip, fileList);
				
				for (File file : fileList) {
					
					if (!file.isDirectory()) { 
						addToZip(directoryToZip, file, zos);
					}
				}
	
				zos.close();
				fos.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	
		public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) {
	
			try {
				
				FileInputStream fis = new FileInputStream(file);
		
				// we want the zipEntry's path to be a relative path that is relative
				// to the directory being zipped, so chop off the rest of the path
				String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
						file.getCanonicalPath().length());
				
					ZipEntry zipEntry = new ZipEntry(zipFilePath);
					zos.putNextEntry(zipEntry);
					IOUtils.copy(fis, zos);
					zos.closeEntry();
					fis.close();
				
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		}
}
