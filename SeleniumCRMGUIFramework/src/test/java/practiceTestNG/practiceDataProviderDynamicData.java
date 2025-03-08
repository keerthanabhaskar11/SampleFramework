package practiceTestNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class practiceDataProviderDynamicData 
{
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("F:\\Tek Pyramid\\Data\\TestScriptdata.xlsx");
		Workbook WB = WorkbookFactory.create(fis);
		int rownum = WB.getSheet("DataProvider").getLastRowNum();
		Object[][] obj = new Object[rownum][2];
		for(int i = 0 ;i<rownum;i++)
		{
			
			Row row = WB.getSheet("DataProvider").getRow(i+1);
			obj[i][0] = row.getCell(0).getStringCellValue();
			obj[i][1] = row.getCell(1).getStringCellValue();
			//return obj;
		}
		return obj;
		
	}
	
	@Test(dataProvider = "getData")
	public void SampleDataProviderTest(String Name, String PhoneNumber)
	{
		System.out.println(Name+"\t"+PhoneNumber);
	}
	


}
