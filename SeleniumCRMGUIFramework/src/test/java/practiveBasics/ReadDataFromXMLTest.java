package practiveBasics;

import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadDataFromXMLTest 
{
	@Test
	public void SampleToReadFromXMLTest(XmlTest test)
	{
		System.out.println("Sample Test");
		String BROWSER = test.getParameter("browser");
		String URL = test.getParameter("url");
		String USERNAME = test.getParameter("username");
		String PASSWORD = test.getParameter("password");
		
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
	}

}
