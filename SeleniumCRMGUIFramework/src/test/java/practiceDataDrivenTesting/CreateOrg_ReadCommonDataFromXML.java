package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class CreateOrg_ReadCommonDataFromXML 
{
	
	@Test
	public void CreateOrgTest(XmlTest test) throws EncryptedDocumentException, IOException
	{
		
		//Get Common data from XML file
		String BROWSER = test.getParameter("browser");
		String URL = test.getParameter("url");
		String USERNAME = test.getParameter("username");
		String PASSWORD = test.getParameter("password");
				
				//Generate random number to concatenate with Orgname
				Random random = new Random();
				int RandomNum = random.nextInt(1000);
				
				//Get Test script data from Excel sheet
				FileInputStream fise = new FileInputStream("F:\\Tek Pyramid\\Data\\TestScriptdata.xlsx");
				Workbook WB = WorkbookFactory.create(fise);
				Row row = WB.getSheet("Org").getRow(1);	
				String ORGNAME = row.getCell(2).toString()+RandomNum;
				String PHONENUM = row.getCell(3).toString();
				String EMAILID = row.getCell(4).toString();
				
				
				//launch the browser
				WebDriver driver = null;
				if(BROWSER.equals("chrome"))
				{
					driver = new ChromeDriver();
				}
				else if(BROWSER.equals("firefox"))
				{
					driver = new FirefoxDriver();
				}
				else if(BROWSER.equals("edge"))
				{
					driver = new EdgeDriver();
				}
				else
				{
					driver = new FirefoxDriver();
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
				//Open the application
				driver.get(URL);
				driver.manage().window().maximize();
				
				//Login to application
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				//Create Organization
				driver.findElement(By.linkText("Organizations")).click();
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				driver.findElement(By.xpath("//input[@name = 'accountname']")).sendKeys(ORGNAME);
				driver.findElement(By.id("phone")).sendKeys(PHONENUM);
				driver.findElement(By.name("email1")).sendKeys(EMAILID);
				driver.findElement(By.xpath("//input[contains(@title, 'Save [Alt+S]')]")).click();
				String Orgname = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
				System.out.println(Orgname);
				
				if(Orgname.contains(ORGNAME))
				{
					System.out.println("Organization name is same as entered");
				}
				else
				{
					System.out.println("Organization name is not same as entered");
				}
				
				//write data to excel sheet
				row.createCell(6).setCellValue(driver.findElement(By.xpath("(//td[@class='dvtCellInfo'])[2]")).getText());
				FileOutputStream fos = new FileOutputStream("F:\\Tek Pyramid\\Data\\TestScriptdata.xlsx");
				WB.write(fos);
				WB.close();
				
				//Log out from application		
				Actions A = new Actions(driver);
				A.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				driver.findElement(By.linkText("Sign Out")).click();
		        driver.close();
				

		
	}

}
