package locator;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLocators {
	
	private WebDriver driver;
	
	public TestLocators() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
		this.driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	public void tagLocatorTest(){
		
		WebElement we = driver.findElement(By.tagName("a"));
		System.out.println(we.getText());
	}

	
	public static void main(String[] args) {
		TestLocators locators = new TestLocators();
		locators.tagLocatorTest();
	}
}
