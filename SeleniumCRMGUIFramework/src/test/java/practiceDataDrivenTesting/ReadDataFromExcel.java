package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.poi.ss.usermodel.Cell;

public class ReadDataFromExcel 
{
	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		
		//Step 1: Get the excel path location and java object of the physical file
		FileInputStream fis = new FileInputStream("F:\\Tek Pyramid\\Data\\TestScriptdata.xlsx");
		
		//Step 2: Open WorkBook in read mode
		Workbook WB = WorkbookFactory.create(fis);
		
		//Step 3: Get control of the "Org" sheet
		Sheet SH = WB.getSheet("Org");
		
		//Step 4: Get control of the "1st" row
		Row R = SH.getRow(1);
		
		//Step 5: Get the control of the 2nd cell and read the data.
		 //String Orgname = R.getCell(3).toString();
		//(To get numeric value from cell then we can use toString() method - but it is best option to use single code for numeric value in Excel itself)
		Random random = new Random();
		int RandomNum = random.nextInt(1000);
		String Orgname = R.getCell(2).getStringCellValue() + RandomNum;
		System.out.println(Orgname);
		
		//Close the WorkBook
		WB.close();
	}

}
