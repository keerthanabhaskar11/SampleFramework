package practiceTestNG;

import org.testng.annotations.Test;

public class PracticePriorirty
{

		@Test(priority = -9)
		public void CreateOrganization()
		{
			System.out.println("Executed CreateOrganization");
		}
		
		@Test(priority = 0)
		public void CreateContact()
		{
			System.out.println("Executed CreateContact");
		}

		@Test(priority = 1)
		public void ModifyContact()
		{
			System.out.println("Executed ModifyContact");
		}

		@Test(priority = 2)
		public void DeleteContact()
		{
			System.out.println("Executed DeleteContact");
		}

}


