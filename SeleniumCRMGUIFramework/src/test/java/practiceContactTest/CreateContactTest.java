package practiceContactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactTest {
	public static void main(String[] args) throws IOException {

		// Read common data from properties file
		FileInputStream fis = new FileInputStream("F:\\Tek Pyramid\\Data\\CommonData.properties");
		// Step 2: using properties class, load all the keys
		Properties pObj = new Properties();
		pObj.load(fis);
		// Step 3: Get the value based on key
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");

		// Random number generation
		Random random = new Random();
		int RandomNum = random.nextInt(100);

		// Read test script data from excel sheet
		FileInputStream fise = new FileInputStream("F:\\Tek Pyramid\\Data\\TestScriptdata.xlsx");
		Workbook WB = WorkbookFactory.create(fise);
		Row row = WB.getSheet("Contact").getRow(1);
		String LASTNAME = row.getCell(2).toString() + RandomNum;
		WB.close();

		// launch the browser
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		// Open the application
		driver.get(URL);
		driver.manage().window().maximize();

		// Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Create Contact
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		
		//Validation of contact
		String DispContact = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		if(DispContact.contains(LASTNAME))
		{
			System.out.println(LASTNAME+" contact created. Contact information is same as entered");
		}
		else
		{
			System.out.println(LASTNAME+" contact created. Contact information is not same as entered");
		}
		String CreatedLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(CreatedLastName.equals(LASTNAME))
		{
			System.out.println(CreatedLastName+" contact created is same as entered");
		}
		else
		{
			System.out.println(CreatedLastName+" contact created is not same as entered");
		}

		// Log out from application
		Actions A = new Actions(driver);
		A.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();

	}

}
