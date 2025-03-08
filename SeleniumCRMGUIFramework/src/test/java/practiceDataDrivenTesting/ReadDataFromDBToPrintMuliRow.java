package practiceDataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ReadDataFromDBToPrintMuliRow 
{
	public static void main(String[] args) throws SQLException 
	{
		//Step 1: Load or Register the data base
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		
		//Step 2: Connect to data base
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/performance_schema", "root", "root");
		
		//Step 3: create SQL statement
		Statement stmt = conn.createStatement();
		
		//Step 4: Execute Query
		ResultSet resultset = stmt.executeQuery("select * from accounts");
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"\t"+resultset.getString(2)+"\t"+resultset.getInt(3)+"\t"+resultset.getInt(4)+"\t"+resultset.getInt(5)+"\t"+resultset.getInt(6));
		}
		//Step 5: Close DB connection
		conn.close();
	}

}
