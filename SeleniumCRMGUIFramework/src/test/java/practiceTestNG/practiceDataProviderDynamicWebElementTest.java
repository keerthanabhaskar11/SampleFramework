package practiceTestNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class practiceDataProviderDynamicWebElementTest 
{
	@Test(dataProvider = "getData")
	public void PrintPriceTest(String Brand, String PhoneModel)
	{
		WebDriver driver = new EdgeDriver();
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Brand, Keys.ENTER);
		String PhonePrice = driver.findElement(By.xpath("//span[text()='"+PhoneModel+"']/../../../..//span[@class='a-price-whole']")).getText();
		System.out.println(PhonePrice);
		driver.close();
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("F:\\Tek Pyramid\\Data\\TestScriptdata.xlsx");
		Workbook WB = WorkbookFactory.create(fis);
		int rownum = WB.getSheet("AmazonProduct").getLastRowNum();
		
		Object[][] obj = new Object[rownum][2];
		for(int i =0;i<rownum;i++)
		{
			Row row = WB.getSheet("AmazonProduct").getRow(i+1);
			obj[i][0] = row.getCell(0).toString();
			obj[i][1] = row.getCell(1).toString();
		}
		return obj;
		
	}

}
