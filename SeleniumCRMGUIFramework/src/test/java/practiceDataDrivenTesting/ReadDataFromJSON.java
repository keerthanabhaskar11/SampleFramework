package practiceDataDrivenTesting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJSON
{

	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException   
	{
		//Step 1: Parsing JSON physical file into the java object using JSON parse class
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("F:\\Tek Pyramid\\Data\\jsonCommonData.json"));
		
		//Covert Java object into JSON object using down casting
		JSONObject map = (JSONObject) obj;
		String BROWSER = (String) map.get("browser");
		String URL = map.get("url").toString();
		String USERNAME = (String) map.get("username");
		String PASSWORD = (String) map.get("password");
		
		
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		System.out.println(map.get("timeout"));

		
	}

}
