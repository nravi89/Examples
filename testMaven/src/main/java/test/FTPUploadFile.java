package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;
 
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
 
public class FTPUploadFile {
 
    
	private static void uploadFile(){
        String server = "192.168.69.191";
        int port = 21;
        String user = "ravi";
        String pass = "ravi";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
  
            File firstLocalFile = new File("D:\\test\\jenkins\\pic\\pic.PNG");
 
            String firstRemoteFile = "image.png";
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("file is uploaded successfully.");
            }
 
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
	
	
	
	private static void readAndUploadFile(){
        String server = "192.168.69.191";
        int port = 21;
        String user = "ravi";
        String pass = "ravi";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            
   //=====================================================================================================//
            String remoteFile = "/image.png";
            File downloadFile = new File("D:/image.png");
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
            boolean success = ftpClient.retrieveFile(remoteFile, outputStream);
            outputStream.close();
 
            if (success) {
                System.out.println("File #1 has been downloaded successfully.");
            }
            
            
            
 
    //===============================================================================//
            File firstLocalFile = new File("D:/test/pic.PNG");
 
            String firstRemoteFile = "image.png";
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("file is uploaded successfully.");
            }
 
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
	
	
	
	
	public static void main(String[] args) {
		uploadFile();
	}
 
}
