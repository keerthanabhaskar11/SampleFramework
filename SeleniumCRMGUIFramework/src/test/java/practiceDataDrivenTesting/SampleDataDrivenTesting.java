package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SampleDataDrivenTesting 
{
	public static void main(String[] args) throws IOException
	{
		//Step 1: Get the java representation object of the physical file
		FileInputStream fis = new FileInputStream("C:\\Users\\Bhaskar\\OneDrive\\Desktop\\CommonData.properties");
		
		//Step 2: using properties class, load all the keys
		Properties pObj = new Properties();
		pObj.load(fis);
		
		
		//Step 3: Get the value based on key
		System.out.println(pObj.getProperty("browser"));
	}

}
