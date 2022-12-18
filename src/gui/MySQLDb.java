package gui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLDb {

	private Connection conn;
	private Statement statement = null;
	private PreparedStatement prepstat = null;
	private ResultSet resultSet = null;
	
	
	private void getConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			this.conn = DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/java_mini_projet","root","calimero");
			
			//conn = DriverManager.getConnection(
			//	"jdbc:mysql://" + System.getenv("JHOSTNAME")
				//+ "/feedback?" + "user=" + System.getenv("JUSER")
				//+ "&password=" + System.getenv("JPASSWORD"));
			
			
		}catch (Exception e) {
				System.out.println(e);
		}
		
	}
	
	public void insertDb(String[] data) throws SQLException {
		getConn();
		String query = "INSERT INTO Students (cne, nom, prenom, email, filiere, tel, ville)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
	
			prepstat = this.conn.prepareStatement(query);

			for (int i = 0; i < data.length; i++)
				if(data[i] != null)
					prepstat.setString(i+1, data[i]);
				else
					prepstat.setString(i+1, "null");
			
			prepstat.execute();
			this.conn.close();
			
		} catch (Exception e) {
			System.err.println("Exception");
			e.printStackTrace();
			System.out.println(e);
		}
		
	}
	public String[][] readDb(String att) throws Exception{
		getConn();
		List<List<String>> data = new ArrayList<List<String>>();
		
		statement = this.conn.createStatement();
		
		if (att.isBlank())
			resultSet = statement.executeQuery("SELECT * FROM Students");
		else
			resultSet = statement.executeQuery("SELECT * FROM Students WHERE nom = '" + att + "'");
			
		int i = 0;
		while(resultSet.next()) {
			data.add(new ArrayList<String>());
			for (int j = 0; j < 7; j++) {
				//System.out.println(resultSet.getString(j+1));
				data.get(i).add(resultSet.getString(j+1));
			}
			i++;
		}
			
	this.conn.close();
		
	String[][] dataFormated = new String[data.size()][7];
	
	for (int c = 0; c < data.size(); c++) {
		for (int j = 0; j < 7; j++) {
			dataFormated[c][j] = data.get(c).get(j);
		}
	}
	
//	System.out.println(dataFormated[0][0]);
	
	return dataFormated;	
	}
	
}
