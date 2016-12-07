package main;

import java.sql.*;
import java.util.*;

import javax.swing.JTextField;

public class Database
{
	private Connection conn;
	
	public boolean isConnected()
	{
		return (conn != null);
	}
	
	public Database() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/HighScoreDB",
				"root","root");
	}
	
	public Vector<Vector<Object>> selectScore() throws SQLException
	{
		Statement stmt = this.conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM HighScore");
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next())
		{
			Vector<Object> v = new Vector<Object>();
			v.add(rs.getString("name"));
			v.add(rs.getString("score"));
			data.add(v);
		}		
		return data;
	}
	
	public void insertScore(
			String name,
			String score) throws SQLException
	{
		String query = "INSERT INTO HighScore(name, score)" +
					   "VALUES(?, ?)";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, name);
		stmt.setString(2, score);
		stmt.execute();
		stmt.close();
	}
}
