package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcelSheet 
{
	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		//Step 1: Get the excel path location and java object of the physical file
		FileInputStream fis = new FileInputStream("F:\\Tek Pyramid\\File\\TestScriptdata.xlsx");
		Workbook WB = WorkbookFactory.create(fis);
		Sheet SH = WB.getSheet("product");
		int rownum = SH.getLastRowNum();
		Row row;
		for(int i = 1;i<=rownum;i++)
		{
			row = SH.getRow(i);
			
			String Column1Data = row.getCell(0).toString();
			String Column2Data = row.getCell(1).toString();
			
			System.out.println(Column1Data+"\t"+Column2Data);
			
		}
		
		
		WB.close();
	}

}
