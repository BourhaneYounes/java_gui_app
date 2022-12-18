package gui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLDb {

	private Connection conn = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public Connection getConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/java_mini_projet","root","calimero");
			
			//conn = DriverManager.getConnection(
			//	"jdbc:mysql://" + System.getenv("JHOSTNAME")
				//+ "/feedback?" + "user=" + System.getenv("JUSER")
				//+ "&password=" + System.getenv("JPASSWORD"));
			
			
		}catch (Exception e) {
				System.out.println(e);
		}
		
		return conn;
	}
	public String[][] readDb(String att) throws Exception{
		
		List<List<String>> data = new ArrayList<List<String>>();
		
		conn = getConn();
			
			
		statement = conn.createStatement();
		
		if (att.isBlank())
			resultSet = statement.executeQuery("SELECT * FROM Students");
		else
			resultSet = statement.executeQuery("SELECT * FROM Students WHERE name = " + att);
			
		int i = 0;
		while(resultSet.next()) {
			data.add(new ArrayList<String>());
			for (int j = 0; j < 7; j++) {
				data.get(i).add(resultSet.getString(j+1));
			}
			i++;
		}
			
	conn.close();
		
	String[][] dataFormated = new String[data.size()][7];
	
	for (int c = 0; i < data.size(); c++) {
		for (int j = 0; j < 7; j++) {
			dataFormated[c][j] = data.get(c).get(j);
		}
	}
	
	return dataFormated;	
	}
	
}
