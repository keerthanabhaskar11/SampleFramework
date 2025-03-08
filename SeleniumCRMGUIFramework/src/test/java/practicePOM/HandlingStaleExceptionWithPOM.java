package practicePOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class HandlingStaleExceptionWithPOM 
{
	@FindBy(name = "user_name")
	WebElement usernameEdt;
	
	@FindBy(name = "user_password")
	WebElement passwordEdt;
	
	@FindBy(id = "submitButton")
	WebElement submitBtn;
	
	@Test
	public void Login()
	{
		WebDriver driver = new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");
		HandlingStaleExceptionWithPOM exe = PageFactory.initElements(driver, HandlingStaleExceptionWithPOM.class);
			
		exe.usernameEdt.sendKeys("admin");
		exe.passwordEdt.sendKeys("admin");
		
		driver.navigate().refresh();
	
		exe.usernameEdt.sendKeys("admin");
		exe.passwordEdt.sendKeys("admin");
		exe.submitBtn.click();
	}
	

}
