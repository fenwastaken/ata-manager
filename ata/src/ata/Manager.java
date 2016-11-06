package ata;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Manager {

	public static Vector<String> getAllAtas() throws SQLException{
		String sql = "SELECT number, name FROM ata ORDER BY number";
		PreparedStatement st = DB.getConnexion().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		Vector<String> vec = new Vector<String>();
		while(rs.next()){
			vec.add(rs.getInt("number") + ", " + rs.getString("name"));
		}
		return vec;
	}
	
	public static int saveLine(String name, String date, String type, String immat, String ata, String task, int formation, int execution, int controle, int encadrement, int aprs) throws SQLException{
	String sql = "INSERT INTO line "
			+ "(name, date, type, immat, ata, task, formation, execution, controle, encadrement, aprs)"
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	PreparedStatement st = DB.getConnexion().prepareStatement(sql);
	st.setString(1, name);
	st.setString(2, date);
	st.setString(3, type);
	st.setString(4, immat);
	st.setString(5, ata);
	st.setString(6, task);
	st.setInt(7, formation);
	st.setInt(8, execution);
	st.setInt(9, controle);
	st.setInt(10, encadrement);
	st.setInt(11, aprs);
	return st.executeUpdate();
	
	}
	
	public static Vector<String> getAllLines() throws SQLException{
		String sql = "SELECT date, type, immat, ata, task, formation, execution, controle,  encadrement, aprs, name FROM line ORDER BY date";
		PreparedStatement st = DB.getConnexion().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		Vector<String> vecRet = new Vector<String>();
		while(rs.next()){
			String date = rs.getString("date");
			String type = rs.getString("type");
			String immat = rs.getString("immat");
			String ata = rs.getString("ata");
			String task = rs.getString("task");
			int formation = rs.getInt("formation");
			int execution = rs.getInt("execution");
			int controle = rs.getInt("controle");
			int encadrement = rs.getInt("encadrement");
			int aprs = rs.getInt("aprs");
			String name = rs.getString("name");
			
			String ret = date + ", " + type + ", " + immat + ", " + ata + ", " + task ;
			if(formation > 0){
				ret += ", formation";
			}
			
			if(execution > 0){
				ret += ", execution";
			}
			
			
			if(controle > 0){
				ret += ", controle";
			}
			
			
			if(encadrement > 0){
				ret += ", encadrement";
			}
			
			if(aprs > 0){
				ret += ", arps";
			}
			
			ret += " : " + name;
			vecRet.add(ret);
		}
		
		return vecRet;
	}
	
}
