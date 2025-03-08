package practiceTestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class practiceDataProviderTest 
{
	@DataProvider
	public Object[][] getData()
	{
		Object[][] obj = new Object[3][2];
		obj[0][0] = "Keerthana";
		obj[0][1] = 9876543210l;
		obj[1][0] = "Bhavish";
		obj[1][1] = 1234567890l;
		obj[2][0] = "Medhansh";
		obj[2][1] = 9898989898l;
		return obj;
		
	}
	
	@Test(dataProvider = "getData")
	public void SampleDataProviderTest(String Name, Long PhoneNumber)
	{
		System.out.println(Name+"\t"+PhoneNumber);
	}
	

}
