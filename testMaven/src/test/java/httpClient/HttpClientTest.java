package httpClient;


import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientTest {
  
  private static String url = "https://www.google.com/";
  private final static String USER_AGENT = "Mozilla/5.0";

  public static void main(String[] args) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException {
	    HttpClient client = getHttpClient();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : " 
	                + response.getStatusLine().getStatusCode()+" length:"+response.getEntity().getContentLength());

		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		rd.close();
		
		System.out.println(result);
  }
  
  
  public static HttpClient getHttpClient() throws NoSuchAlgorithmException, KeyManagementException{
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
	
	
	HttpClient client = HttpClientBuilder.create().setSSLContext(sslContext).build();
	return client;
  }
  
  
}
