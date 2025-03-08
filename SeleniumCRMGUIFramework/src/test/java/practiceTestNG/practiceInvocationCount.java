package practiceTestNG;

import org.testng.annotations.Test;

public class practiceInvocationCount 
{
	@Test
	public void CreateOrganization()
	{
		System.out.println("Executed CreateOrganization");
	}
	
	@Test(invocationCount = 2)
	public void CreateContact()
	{
		System.out.println("Executed CreateContact");
	}

	@Test
	public void ModifyContact()
	{
		System.out.println("Executed ModifyContact");
	}

	@Test(invocationCount = 4)
	public void DeleteContact()
	{
		System.out.println("Executed DeleteContact");
	}



}
