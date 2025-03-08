package practiceOrgTest;

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
import org.openqa.selenium.support.ui.Select;

public class CreateOrgWithIndustryTypeTest {
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
		Row row = WB.getSheet("Org").getRow(3);
		String OrgName = row.getCell(2).toString() + RandomNum;
		String Industry = row.getCell(3).toString();
		String Type = row.getCell(4).toString();

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
		Select sel_Ind = new Select(driver.findElement(By.name("industry")));
		sel_Ind.selectByVisibleText(Industry);
		Select sel_Type = new Select(driver.findElement(By.name("accounttype")));
		sel_Type.selectByVisibleText(Type);
		driver.findElement(By.xpath("//input[contains(@title, 'Save [Alt+S]')]")).click();

		String CreatedOrgName = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		System.out.println(CreatedOrgName);

		if (CreatedOrgName.contains(OrgName)) {
			System.out.println(OrgName + " Created. Organization name is same as entered - PASS");
		} else {
			System.out.println(OrgName + " is not same as entered - FAIL");
		}
		
		String DispIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if(DispIndustry.equals(Industry))
		{
			System.out.println(DispIndustry+" same as entered during Org Creation - PASS");
		}
		else
		{
			System.out.println(DispIndustry+" different from entered during Org Creation - FAIL");
		}
		
		String DispType = driver.findElement(By.id("mouseArea_Type")).getText();
		if(DispType.equals(Type))
		{
			System.out.println(DispType+" same as entered during Org Creation - PASS");
		}
		else
		{
			System.out.println(DispType+" different from entered during Org Creation - FAIL");
		}
			

		// Log out from application
		Actions A = new Actions(driver);
		A.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();

	}

}
