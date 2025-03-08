package practiceDataDrivenTesting;

public class GenerateAlphanumericRandomData 
{
	public static void main(String[] args)
	{
		//Declaring the length of Alphanumeric random data to be generated
		int n = 20;
		
		//Choose a character random from this string
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		
		//Create String buffer size of Alphanumerric
		StringBuilder SB = new StringBuilder(n);
		for(int i =0;i<n;i++)
		{
			//Generate the random number between 0 to AlphaNumericString
			int index = (int) (AlphaNumericString.length()*Math.random());
			
			//Add the character one by one in end of SB
			SB.append(AlphaNumericString.charAt(index));
		}
		System.out.println(SB);
		
		
	}

}
