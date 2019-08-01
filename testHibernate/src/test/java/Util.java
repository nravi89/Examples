

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class Util {
	
	private static Logger logger = Logger.getLogger(Util.class);
	
	public static String getVersionInfoUsingFTP(String host, int port,
			String user,String pass,String filePath, boolean isSDP) throws IOException{
		
		String version = null;
		
		JSch jsch = new JSch();
		Session session = null;
		ChannelSftp sftpChannel= null;
	    InputStream inputStream = null;
		try {
			session = jsch.getSession(user, host);
			session.setPassword(pass);
			session.setConfig("StrictHostKeyChecking", "no");
			
			if(logger.isDebugEnabled())
			 logger.debug("Establishing Connection to remote machine");
			
			session.connect();
			
			if(logger.isDebugEnabled())
				logger.debug("Connection established - Crating SFTP Channel");
			
			sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();
			
			if(isSDP){
				filePath = filePath+"profile.xml";
				inputStream = sftpChannel.get(filePath);
				version = getVersionValueSDP(inputStream);
			}else{
				filePath = filePath+"version.info";
				inputStream = sftpChannel.get(filePath);
				version = getVersionValue(inputStream);
			}
		
			
		} catch (JSchException e) {
			logger.error("JSchException in getting jsch session",e);
			logger.error("host:"+host+" port:"+port+" user:"+user+" filePath:"+filePath+" sdp:"+isSDP);
		} catch (SftpException e) {
			logger.error("SftpException, issue in sftp connect",e);
			logger.error("host:"+host+" port:"+port+" user:"+user+" filePath:"+filePath+" sdp:"+isSDP);
		} catch (IOException e) {
			logger.error("IOException,issue in reading stream",e);
			logger.error("host:"+host+" port:"+port+" user:"+user+" filePath:"+filePath+" sdp:"+isSDP);
		}finally{
			if(sftpChannel!=null)
			 sftpChannel.exit();
			
			if(session!=null)
			 session.disconnect();
			
			if(inputStream!=null)
			 inputStream.close();
		}
		
		
		return version;
	}
	
	
	private static String getVersionValue(InputStream is) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		String version = "";
		
		while ((line = br.readLine()) != null) {
			String arr[] = line.split(":|=");
			if (arr[0].trim().equalsIgnoreCase("Release")) {
				continue;
			} else if (!arr[0].trim().equalsIgnoreCase("") && arr[0] != null) {
				if (version.equals("")) {
					version = version + arr[1].trim();
				} else {
					if (arr[0] != null && arr[0].trim().equalsIgnoreCase("PatchNumber")) {
						version = version + ".patch " + arr[1].trim();
					} else {
						version = version + "." + arr[1].trim();
					}
				}
			}
		}
		br.close();
		return version;
	}
	
	private static String getVersionValueSDP(InputStream is) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		String version = "";
		while ((line = br.readLine()) != null) {
			if (line.toLowerCase().contains("yodlee")) {
				String lineval = line.split("-")[1];
				String val[] = lineval.split("_");
				version = val[val.length - 1] + "." + val[val.length - 4] + "." + val[val.length - 3];
				break;

			}
		}
		br.close();
		return version;
	}
	
	public static void main(String[] args) {
		try {
			String str = getVersionInfoUsingFTP("192.168.112.73", 8080, "read", "read", "/opt/sdp/jboss-10.1.0/instance-2/deployments/ysl.war/", false);
		    System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
