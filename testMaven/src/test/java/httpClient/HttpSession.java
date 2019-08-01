package httpClient;


import java.io.*;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpSession {
  
	//private static String url = "https://www.google.com";
  private static String url = "https://192.168.112.184:10043/ycc/login.ukchannel.do";
  private final static String USER_AGENT = "Mozilla/5.0";

  public static void main(String[] args) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
	    HttpClient client = getHttpClient(true);
		//HttpGet request = new HttpGet(url);
        
	    URIBuilder urlBuilder = new URIBuilder(url);
	    urlBuilder.setParameter("loginName", "manju@yodlee").setParameter("password", "Yodlee@456");
	    
	    
	    HttpPost request = new HttpPost(urlBuilder.build());
		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		
		
		
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : " 
	                + response.getStatusLine().getStatusCode()+" length:"+response.getEntity().getContentLength());

		Header[] headers = response.getHeaders("SET-COOKIE");
		
		System.out.println("===========================================");
		for(Header header:headers){
			if(header.getValue().startsWith("rsession")){
				String str = header.getValue();
				System.out.println(str);
				str = (String) str.substring(0, str.indexOf("\";")+1);
				System.out.println(str);
			}
		
			
		}
		
		/*BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		rd.close();
		
		System.out.println(result);*/
  }
  
  
  public static HttpClient getHttpClient(boolean byPassSSL) throws NoSuchAlgorithmException, KeyManagementException{
	
	  if(!byPassSSL){
		  HttpClient client = HttpClientBuilder.create().build();
			return client;
	  }
	  
	SSLContext sslContext = SSLContext.getInstance("SSL");

	// set up a TrustManager that trusts everything
	sslContext.init(null, new TrustManager[] { new X509TrustManager() {
	            public X509Certificate[] getAcceptedIssuers() {
	                    System.out.println("getAcceptedIssuers =============");
	                    return null;
	            }

	            public void checkClientTrusted(X509Certificate[] certs,
	                            String authType) {
	                    System.out.println("checkClientTrusted =============");
	            }

	            public void checkServerTrusted(X509Certificate[] certs,
	                            String authType) {
	                    System.out.println("checkServerTrusted =============");
	            }
	} }, new SecureRandom());
	
	SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

    HttpClient client = HttpClientBuilder.create().setSSLSocketFactory(socketFactory).build();
    
	//HttpClient client = HttpClientBuilder.create().setSSLContext(sslContext).build();
	  
	return client;
  }
  
  
}
