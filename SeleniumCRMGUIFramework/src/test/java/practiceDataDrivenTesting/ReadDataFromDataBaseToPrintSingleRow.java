package practiceDataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ReadDataFromDataBaseToPrintSingleRow 
{
	
	public static void main(String[] args) throws SQLException
	{
		//Step 1: Load or Register the database
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		
		//Step 2: Get Connection to the database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/performance_schema", "root", "root");
		System.out.println("DB connected");
		//Step 3: Create SQL statement
		Statement stmt = conn.createStatement();
		
		//Step 4: execute the query and get result
		ResultSet resultset = stmt.executeQuery("select * from accounts");
//		while(resultset.next())
//		{
//			System.out.println(resultset.getString(1));
//		}
		resultset.next();
		System.out.println(resultset.getString(1)+"\t"+resultset.getString(2)+"\t"+resultset.getInt(3)+"\t"+resultset.getInt(4)+"\t"+resultset.getInt(5)+"\t"+resultset.getInt(6));
		//Step 5: close the connection
		conn.close();
	}

}
