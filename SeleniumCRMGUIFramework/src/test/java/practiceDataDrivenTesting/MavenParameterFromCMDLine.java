package practiceDataDrivenTesting;

import org.testng.annotations.Test;

public class MavenParameterFromCMDLine
{
	@Test
	public void SampleTestMaven()
	{
		
		String BROWSER = System.getProperty("browser");
		String URL = System.getProperty("url");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");
		
		
		System.out.println("Environment Data=====================");
		System.out.println("BROWSER===="+BROWSER);
		System.out.println("URL===="+URL);
		System.out.println("USERNAME===="+USERNAME);
		System.out.println("PASSWORD===="+PASSWORD);
	}

}
