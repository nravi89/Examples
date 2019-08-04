package basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class T1BrowserDriver {
	
	public static void main(String[] args) {
		/*System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();*/
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		String baseUrl = "http://www.google.com";
		
		driver.get(baseUrl);
		
		System.out.println(driver.getTitle());
		
		//driver.close();
	}

}
