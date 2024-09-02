package main;

import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	Connection con;
	Database(){
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flappy_bird", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(con == null);
	}
	
	public boolean insertPlayer(String name, String password) throws SQLException {
		if(isNameAvailable(name)) {
			String sql = "INSERT INTO player_details (p_name, p_password) VALUES ('"+name+"', '"+password+"')";
			Statement s = con.createStatement();
			return s.executeUpdate(sql) > 0;
		}
		return false;
	}
	
	public boolean deletePlayer(String name) throws SQLException {
		String sql = "DELETE FROM player_details WHERE p_name = '"+name+"'";
		Statement s = con.createStatement();
		return s.execute(sql);
	}
	
	public boolean isNameAvailable(String name) throws SQLException {
		CallableStatement cst = con.prepareCall("{call isNameAvailable(?,?)}");
		cst.setString(1, name);
		cst.execute();
		return cst.getBoolean(2);
	}
	
	public boolean isValid(String name, String password) throws SQLException {
		CallableStatement cst = con.prepareCall("{call isValid(?,?,?)}");
		cst.setString(1, name);
		cst.setString(2, password);
		cst.execute();
		return cst.getBoolean(3);
	}
	
	public void getLevelBooleans() throws SQLException {
		CallableStatement cst = con.prepareCall("{call getLevelBooleans(?,?,?,?)}");
		cst.setString(1, Frame.currentPlayerName);
		cst.execute();
		Frame.isLevel_1_Cleared = cst.getBoolean(2);
		Frame.isLevel_2_Cleared = cst.getBoolean(3);
		Frame.isLevel_3_Cleared = cst.getBoolean(4);
	}
	
	public void setLevelBooleans() throws SQLException {
		String sql = "UPDATE player_details SET is_level_1_cleared = ?, is_level_2_cleared = ?, is_level_3_cleared = ? WHERE p_name = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setBoolean(1, Frame.isLevel_1_Cleared);
		pst.setBoolean(2, Frame.isLevel_2_Cleared);
		pst.setBoolean(3, Frame.isLevel_3_Cleared);
		pst.setString(4, Frame.currentPlayerName);
		
		pst.executeUpdate();
	}
	
	public void setStart() throws SQLException {
		CallableStatement cst = con.prepareCall("{call setStart(?,?)}");
		cst.setString(1, Frame.currentPlayerName);
		cst.execute();
		Frame.currentSessionId = cst.getInt(2);
//		System.out.println(Frame.currentSessionId);
	}
	
	public void setEnd() throws SQLException {
		CallableStatement cst = con.prepareCall("{call setEnd(?)}");
		cst.setInt(1, Frame.currentSessionId);
//		System.out.println(Frame.currentSessionId);
		cst.execute();
	}
	
	public void addTry(int map, int score) throws SQLException {
		CallableStatement cst = con.prepareCall("{call addTry(?, ?, ?)}");
		cst.setString(1, Frame.currentPlayerName);
		cst.setInt(2, map);
		cst.setInt(3, score);
		cst.execute();
	}
	
	public ResultSet getTriesFromMap0() throws SQLException {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT p_id,score FROM tries WHERE map = 0 ORDER BY score DESC");
		return rs;
	}
	public ResultSet getTriesFromMap1() throws SQLException {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT p_id,score FROM tries WHERE map = 1 ORDER BY score DESC");
		return rs;
	}
	public ResultSet getTriesFromMap2() throws SQLException {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT p_id,score FROM tries WHERE map = 2 ORDER BY score DESC");
		return rs;
	}
	
	public String getName(int p_id) throws SQLException {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT p_name FROM player_details WHERE p_id = "+p_id+"");
		rs.next();
		return rs.getString(1);
	}
	
	public int getP_id() throws SQLException {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT p_id FROM player_details WHERE p_name = '"+Frame.currentPlayerName+"'");
		rs.next();
		return rs.getInt(1);
	}
	
	public void delete() throws SQLException {
		Statement s = con.createStatement();
		s.executeUpdate("DELETE FROM player_details WHERE p_name = '"+Frame.currentPlayerName+"'");
	}
	
	public String getPassword() throws SQLException {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT p_password FROM player_details WHERE p_id = "+getP_id()+"");
		rs.next();
		return rs.getString(1);
	}
	
	public void setPassword(String password) throws SQLException {
		Statement s = con.createStatement();
		s.executeUpdate("UPDATE player_details SET p_password = '"+password+"' WHERE p_name = '"+Frame.currentPlayerName+"'");
	}
	
	public void setName(String name) throws SQLException {
		Statement s = con.createStatement();
		s.executeUpdate("UPDATE player_details SET p_name = '"+name+"' WHERE p_name = '"+Frame.currentPlayerName+"'");
		Frame.currentPlayerName = name;
	}
}