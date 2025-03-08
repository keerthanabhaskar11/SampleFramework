package practicePOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class AutoHealing
{	
	
	@FindAll({@FindBy(name = "user_name"),@FindBy(xpath = "//input[@type = 'text']")} )
	WebElement usernameEdt;
	
	
	@FindBy(name = "user_password")
	WebElement passwordEdt;
	
	@FindBy(id = "submitButton")
	WebElement submitBtn;
	
	@Test
	public void login() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");
		AutoHealing exe = PageFactory.initElements(driver, AutoHealing.class);
		
		exe.usernameEdt.sendKeys("admin");
		exe.passwordEdt.sendKeys("admin");
		exe.submitBtn.click();
		Thread.sleep(2000);
		
		driver.close();
	}

}
