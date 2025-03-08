package practiceContactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateContactWithOrganizationTest {
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
		int RandomNum = random.nextInt(1000);

		// Read test script data from excel sheet
		FileInputStream fise = new FileInputStream("F:\\Tek Pyramid\\Data\\TestScriptdata.xlsx");
		Workbook WB = WorkbookFactory.create(fise);
		Row row1 = WB.getSheet("Contact").getRow(1);
		String LASTNAME = row1.getCell(2).toString() + RandomNum;
		String OrgName = row1.getCell(5).toString() + RandomNum;
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

		// Create Organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name = 'accountname']")).sendKeys(OrgName);
		driver.findElement(By.xpath("//input[contains(@title, 'Save [Alt+S]')]")).click();
		String CreatedOrgName = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		System.out.println(CreatedOrgName);

		// Create Contact
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			
			String childwindow = it.next();
			driver.switchTo().window(childwindow);
			String ChildUrl = driver.getCurrentUrl();
			if(ChildUrl.contains("module=Accounts"))
			{	
				break;
			}
		}
		driver.findElement(By.id("search_txt")).sendKeys(OrgName);
		Select sel = new Select(driver.findElement(By.name("search_field")));
		sel.selectByVisibleText("Organization Name");
		//System.out.println("Selected org");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+OrgName+"']")).click();
		
		Set<String> set1 = driver.getWindowHandles();
		Iterator<String> it1 = set1.iterator();
		while(it1.hasNext())
		{
			
			String Parentwindow = it1.next();
			driver.switchTo().window(Parentwindow);
			String ParentUrl = driver.getCurrentUrl();
			if(ParentUrl.contains("module=Contacts"))
			{	
				break;
			}
		}
		
		 driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();

		// Validation of contact
		String DispContact = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		if (DispContact.contains(LASTNAME)) {
			System.out.println(LASTNAME + " contact created. Contact information is same as entered - PASS");
		} else {
			System.out.println(LASTNAME + " contact created. Contact information is not same as entered - FAIL");
		}
		//Validating Org
		String SelectedOrg = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		//System.out.println(SelectedOrg);
		if (SelectedOrg.trim().equals(OrgName)) {
			System.out.println(SelectedOrg + " displaying is same as selected - PASS");
		} else {
			System.out.println(SelectedOrg + " displaying is not same as selected - FAIL");
		}
		// Log out from application
		Actions A = new Actions(driver);
		A.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();


	}

}
