package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultiDataFromExcelOnCondition 
{
	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		String data1 = "";
		String data2= "";
		String data3="";
		String Expected_TC_ID ="tc_02";
		boolean flag = false;
		FileInputStream fis = new FileInputStream("F:\\Tek Pyramid\\Data\\TestScriptdata.xlsx");
		Workbook WB = WorkbookFactory.create(fis);
		Sheet SH = WB.getSheet("Org");
		int rowcount = SH.getLastRowNum();
		for(int i =0;i<=rowcount;i++)
		{
			String data = SH.getRow(i).getCell(0).toString();
			//System.out.println(data);
			if(data.equals(Expected_TC_ID))
			{
				flag=true;
				data1 = SH.getRow(i).getCell(1).toString();
				data2= SH.getRow(i).getCell(2).toString();
				data3 = SH.getRow(i).getCell(3).toString();
				
			}
		}
		if(flag==true)
		{
			System.out.println(data1+"\t"+data2+"\t"+data3);
		}
		else
		{
			System.out.println(Expected_TC_ID+" is not availbale");
		}
		
		
		WB.close();
	}

}
