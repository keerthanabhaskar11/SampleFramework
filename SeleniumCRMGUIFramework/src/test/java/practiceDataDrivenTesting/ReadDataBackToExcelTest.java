package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBackToExcelTest 
{
	private static final CellType String = null;

	public static void main(String[] args) throws IOException
	{
		FileInputStream fis = new FileInputStream("F:\\Tek Pyramid\\Data\\TestScriptdata.xlsx");
		Workbook WB = WorkbookFactory.create(fis);
		Sheet SH = WB.getSheet("Org");
		Row row = SH.getRow(1);
		Cell cel = row.createCell(5);
		cel.setCellType(CellType.STRING);
		cel.setCellValue("PASS");
		
		FileOutputStream fos = new FileOutputStream("F:\\Tek Pyramid\\Data\\TestScriptdata.xlsx");
		WB.write(fos);
		
		WB.close();
		
	}

}
