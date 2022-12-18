package gui;

import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.INPUT_STREAM;

public class MySQLDb {

	private Connection conn = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public List<List<String>> readDb(String att) throws Exception{
		
		List<List<String>> data = new ArrayList<List<String>>();
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/java_mini_projet","root","calimero");
			//conn = DriverManager.getConnection(
				//	"jdbc:mysql://" + System.getenv("JHOSTNAME")
					//+ "/feedback?" + "user=" + System.getenv("JUSER")
					//+ "&password=" + System.getenv("JPASSWORD"));
			
			statement = conn.createStatement();
			
			if (att.isBlank())
				resultSet = statement.executeQuery("SELECT * FROM Students");
			else
				resultSet = statement.executeQuery("SELECT * FROM Students WHERE name = " + att);
			
			int i = 0;
			while(resultSet.next()) {
				data.add(new ArrayList<String>());
				for (int j = 0; j < 7; j++) {
					System.out.println(resultSet.getString(j+1));
					data.get(i).add(resultSet.getString(j+1));
				}
				i++;
			}
			
			conn.close();
			
					
		} catch (Exception e) {
			System.out.println(e);
		}
	return data;	
	}
	
}
