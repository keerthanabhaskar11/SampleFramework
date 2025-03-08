package practicePOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class StaleExceptionWithoutPOM 
{
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");
		WebElement username = driver.findElement(By.name("user_name"));
		WebElement password = driver.findElement(By.name("user_password"));
		
		username.sendKeys("admin");
		password.sendKeys("admin");
		
		driver.navigate().refresh();
		Thread.sleep(3000);
		
		username.sendKeys("admin");
		password.sendKeys("admin");
		
		driver.findElement(By.id("submitButton")).click();
		
	}

}
