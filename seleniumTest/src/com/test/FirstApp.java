package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirstApp {
	
	public static void main(String[] args) throws InterruptedException {
		// declaration and instantiation of objects/variables
    	System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
    	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");
		capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		capabilities.setCapability("acceptSslCerts", false);
		capabilities.setCapability("marionette",true);  
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, false);
		WebDriver driver = new FirefoxDriver(capabilities);
    	
    	//WebDriver driver = new FirefoxDriver();
    	
    	/* DesiredCapabilities capabilities = DesiredCapabilities.firefox();    
         capabilities.setCapability(CapabilityType.);    
         WebDriver driver= new FirefoxDriver(capabilities);  */
    	
    	
    	
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
    	
        String baseUrl = "https://as.aspsp.ob.forgerock.financial/oauth2/authorize?request=eyJhbGciOiJQUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImMwOWJkOTEyNDk0MzFmNzc1ZDYzMjM5MzBjMGUyNjJhNzFjMTE1YzAifQ.eyJpc3MiOiI0YmZhZDhmMS0xYWM1LTRlMDMtOTUyNy1lMTBmMjgyMjJiOTgiLCJhdWQiOiJodHRwczovL2FzLmFzcHNwLm9iLmZvcmdlcm9jay5maW5hbmNpYWwvb2F1dGgyIiwic2NvcGUiOiJvcGVuaWQgYWNjb3VudHMiLCJjbGFpbXMiOnsiaWRfdG9rZW4iOnsiYWNyIjp7InZhbHVlcyI6WyJ1cm46b3BlbmJhbmtpbmc6cHNkMjpzY2EiLCJ1cm46b3BlbmJhbmtpbmc6cHNkMjpjYSJdLCJlc3NlbnRpYWwiOnRydWV9LCJvcGVuYmFua2luZ19pbnRlbnRfaWQiOnsidmFsdWUiOiJBQUNfYzY0YjJiODAtZmQ4YS00NjI3LWI5MjYtY2EzN2FiNTY3MzAxIiwiZXNzZW50aWFsIjp0cnVlfX0sInVzZXJpbmZvIjp7Im9wZW5iYW5raW5nX2ludGVudF9pZCI6eyJ2YWx1ZSI6IkFBQ19jNjRiMmI4MC1mZDhhLTQ2MjctYjkyNi1jYTM3YWI1NjczMDEiLCJlc3NlbnRpYWwiOnRydWV9fX0sInJlc3BvbnNlX3R5cGUiOiJjb2RlIGlkX3Rva2VuIiwicmVkaXJlY3RfdXJpIjoiaHR0cHM6Ly9uZXh0Z2VuLnRpbWVseW9hdXRoLmNvcnAueW9kbGVlLmNvbS9vYXV0aGNsaWVudC9nZXRPQXV0aEFjY2Vzc1Rva2VuLmRvIiwic3RhdGUiOiJjM2EyYmQwNTMzOWFkMWEzOWYxYzViOThjNDQyNWU5ZWI1MzgwMTU0NmI5ZmM4YzlhNDM0ZTcwZjU2OTUyYzhkIiwibm9uY2UiOiJNVFUyTkRFeE9URXpPVEl3T1E9PSIsImNsaWVudF9pZCI6IjRiZmFkOGYxLTFhYzUtNGUwMy05NTI3LWUxMGYyODIyMmI5OCIsInN1YiI6IjRiZmFkOGYxLTFhYzUtNGUwMy05NTI3LWUxMGYyODIyMmI5OCIsImp0aSI6IjUyNDhkMzYxLTkxMzAtNDUwMC05MGQyLTI1YWQ0ZDBhMzk0NiIsImV4cCI6MTU2NDEyMDkzOSwiaWF0IjoxNTY0MTE5MTM5fQ.a0IR9gnlTMvM7wrcwJCF50ZT2OZY9nQVJVci2Ejz26XRjUZbk30WOKUdTMgehtYWBvyCq1CT235jz7sdJ0qAX5fUM7tcLkJ-bjb6rnZTqGCnKyC4_lyCFECipapQ6gFrhyGMqkt3ryewKG2ydZeq7jNZ66Q8ZTCqH1rGeOseKVo31nIfQAl--U1f70P0kTM_C9Qz6v9q_dTo0HVZ2H3Gt1STYBPSQllcuMUr_tMpGpuMqnRk-LrFFb9R2QwVMfHoLGE4ONJgSyLOqooHcPACR1Watz2j4UFEihH9gpOtsCAgsiOczXwyvqWjMdCu5Xy-yKAmJQvIwdp1StTkEC6X0A&scope=openid+accounts&response_type=code+id_token&state=c3a2bd05339ad1a39f1c5b98c4425e9eb53801546b9fc8c9a434e70f56952c8d&redirect_uri=https%3A%2F%2Fnextgen.timelyoauth.corp.yodlee.com%2Foauthclient%2FgetOAuthAccessToken.do&nonce=MTU2NDExOTEzOTIwOQ%3D%3D&client_id=4bfad8f1-1ac5-4e03-9527-e10f28222b98";
        // String expectedTitle = "Google";
        //String actualTitle = "";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Thread.sleep(10000);
        
        WebElement userInputWE = driver.findElement(By.xpath("//input[@id='IDToken1']"));
        userInputWE.sendKeys("yodemo");
        
        WebElement passInputWE = driver.findElement(By.xpath("//input[@id='mat-input-1']"));
        passInputWE.sendKeys("yoDemo@19");
        
        
        
        WebElement submitInputWE = driver.findElement(By.xpath("//span[@class='mat-button-wrapper']"));

        submitInputWE.click();
        Thread.sleep(10000);
        
       
        
        driver.findElement(By.xpath("//span[contains(text(),'Proceed')]")).click();
        
        String str = null;
        int i = 100;
        while(i!=0){
        	Thread.sleep(50);
        	str =driver.getCurrentUrl();
        	if(str.contains("#code"))
        		break;
        	
        	i--;
  
        }
        System.out.println(str);
        System.out.println("I::"+i);
       
        /*driver.navigate().back();
        Thread.sleep(5000);
        System.out.println( driver.getCurrentUrl());
        
        driver.navigate().forward();
        Thread.sleep(5000);
        System.out.println( driver.getCurrentUrl());*/
       // System.out.println(driver.manage().getCookies());
        
        
        // get the actual value of the title
        //actualTitle = driver.getTitle();

        /*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
       /* if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }*/
       
        //close Fire fox
        //driver.close();
       
		
	}

}
