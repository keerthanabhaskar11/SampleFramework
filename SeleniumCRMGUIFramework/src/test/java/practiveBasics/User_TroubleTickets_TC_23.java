package practiveBasics;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class User_TroubleTickets_TC_23 
{
	public static void main(String[] args)
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		driver.manage().window().maximize();
		
		//launch web application
		driver.get("http://49.249.28.218:8888/");
		//Login to application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		System.out.println("Logged In");
		
		//create object for actions class
		Actions act = new Actions(driver);
		
		//Create User
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/mainSettings.PNG']"))).perform();
		driver.findElement(By.linkText("CRM Settings")).click();
		driver.findElement(By.linkText("Users")).click();
		driver.findElement(By.xpath("//input[@value='New User']")).click();
		driver.findElement(By.name("user_name")).sendKeys("Tekadmin");
		driver.findElement(By.name("user_password")).sendKeys("Tekadmin");
		driver.findElement(By.name("confirm_password")).sendKeys("Tekadmin");
		driver.findElement(By.name("first_name")).sendKeys("Tek");
		driver.findElement(By.name("last_name")).sendKeys("admin");
		driver.findElement(By.id("email1")).sendKeys("tekadmin@tek.com");
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		Set<String> windowcount = driver.getWindowHandles();
		for(String value: windowcount)
		{
			System.out.println(value);
		}
		
		
		
		//Log out from application
		
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Signed Out");
		//close the browser
		driver.close();
	}

}
