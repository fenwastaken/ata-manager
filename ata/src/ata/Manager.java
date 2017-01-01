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

	public static Vector<String> getAllLinesToString() throws SQLException{
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

			String ret = date + "\t \"" + type + "\"\t \"" + immat + "\"\t \"" + ata + "\"\t \"" + task + "\"" ;
			if(formation > 0){
				ret += "\t \"oui\"";
			}
			else{
				ret += "\t \"non\"";
			}


			if(execution > 0){
				ret += "\t \"oui\"";
			}
			else{
				ret += "\t \"non\"";
			}


			if(controle > 0){
				ret += "\t \"oui\"";
			}
			else{
				ret += "\t \"non\"";
			}


			if(encadrement > 0){
				ret += "\t \"oui\"";
			}
			else{
				ret += "\t \"non\"";
			}

			if(aprs > 0){
				ret += "\t \"oui\"";
			}
			else{
				ret += "\t \"non\"";
			}

			ret += "\t \"" + name + "\"";
			vecRet.add(ret);
		}

		return vecRet;
	}

	public static Vector<Line> getAllLinesToObject() throws SQLException{
		String sql = "SELECT date, type, immat, ata, task, formation, execution, controle,  encadrement, aprs, name FROM line ORDER BY date";
		PreparedStatement st = DB.getConnexion().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		Vector<Line> vecRet = new Vector<Line>();
		while(rs.next()){
			String date = rs.getString("date");
			String type = rs.getString("type");
			String immat = rs.getString("immat");
			String ata = rs.getString("ata");
			String task = rs.getString("task");
			boolean formation = rs.getBoolean("formation");
			boolean execution = rs.getBoolean("execution");
			boolean controle = rs.getBoolean("controle");
			boolean encadrement = rs.getBoolean("encadrement");
			boolean aprs = rs.getBoolean("aprs");
			String name = rs.getString("name");

			Line line = new Line(name, date, type, immat, ata, task, formation, execution, controle, encadrement, aprs);

			vecRet.add(line);
		}

		return vecRet;
	}

	public static boolean checkString(String str){
		boolean ret = false;
		if(str != null){
			if(!str.isEmpty()){
				ret = true;
			}
		}
		return ret;
	}
	
	public static Vector<Line> getFilteredLinesToObject(String sort, String dateTri, String nameTri) throws SQLException{
		String sql = "Select date, type, immat, ata, task, formation, execution, controle, encadrement, aprs, name FROM line";
		

		
		if(checkString(dateTri)){
			sql += " WHERE date LIKE ?";
		}
		
		if(checkString(dateTri) && checkString(nameTri)){
			sql += " AND NAME = ?";
		}
		else if(!checkString(dateTri) && checkString(nameTri)){
			sql += " WHERE NAME = ?";
		}
		
		sql += " ORDER BY " + sort;
		
		System.out.println(sql);
		
		PreparedStatement st = DB.getConnexion().prepareStatement(sql);
		
		if(checkString(dateTri) && !checkString(nameTri)){
			st.setString(1, dateTri);
		}
		else if(checkString(dateTri) && checkString(nameTri)){
			st.setString(1, dateTri);
			st.setString(2, nameTri);
		}
		else if(!checkString(dateTri) && checkString(nameTri)){
			st.setString(1, nameTri);
		}
		
		
		ResultSet rs = st.executeQuery();
		Vector<Line> vLine = new Vector<Line>();
		while(rs.next()){
			String date = rs.getString("date");
			String type = rs.getString("type");
			String immat = rs.getString("immat");
			String ata = rs.getString("ata");
			String task = rs.getString("task");
			boolean formation = rs.getBoolean("formation");
			boolean execution = rs.getBoolean("execution");
			boolean controle = rs.getBoolean("controle");
			boolean encadrement = rs.getBoolean("encadrement");
			boolean aprs = rs.getBoolean("aprs");
			String name = rs.getString("name");
			
			Line line = new Line(name, date, type, immat, ata, task, formation, execution, controle, encadrement, aprs);
			vLine.add(line);
			System.out.println(type + "|| " + line.toString());
		}
		
		return vLine;
		
	}

	public static Vector<String> getAllTypes() throws SQLException{
		String sql = "SELECT DISTINCT type FROM line ORDER BY type";
		PreparedStatement st = DB.getConnexion().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		Vector<String> vec = new Vector<String>();
		while(rs.next()){
			vec.add(rs.getString("type"));
		}
		return vec;
	}

	public static Vector<String> getAllImmats() throws SQLException{
		String sql = "SELECT DISTINCT immat FROM line ORDER BY immat";
		PreparedStatement st = DB.getConnexion().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		Vector<String> vec = new Vector<String>();
		while(rs.next()){
			vec.add(rs.getString("immat"));
		}
		return vec;
	}

	public static Vector<String> getAllTasks() throws SQLException{
		String sql = "SELECT DISTINCT task FROM line ORDER BY task";
		PreparedStatement st = DB.getConnexion().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		Vector<String> vec = new Vector<String>();
		while(rs.next()){
			vec.add(rs.getString("task"));
		}
		return vec;
	}

	public static Vector<String> getAllNames() throws SQLException{
		String sql = "SELECT DISTINCT name FROM line ORDER BY name";
		PreparedStatement st = DB.getConnexion().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		Vector<String> vec = new Vector<String>();
		while(rs.next()){
			vec.add(rs.getString("name"));
		}
		return vec;
	}

	public static boolean ImmatExists(String immat) throws SQLException{
		String sql = "SELECT immat FROM line WHERE immat = ?";
		PreparedStatement st = DB.getConnexion().prepareStatement(sql);
		st.setString(1, immat);
		ResultSet rs = st.executeQuery();
		int nb = 0;
		while(rs.next()){
			nb++;
		}
		boolean ret = false;
		if(nb > 0){
			ret = true;
		}
		return ret;
	}

	public static String getTypeFromImmat(String immat) throws SQLException{
		String sql = "SELECT type FROM line WHERE immat = ?";
		PreparedStatement st = DB.getConnexion().prepareStatement(sql);
		st.setString(1, immat);
		ResultSet rs = st.executeQuery();
		String type = "null";
		if(rs.next()){
			type = rs.getString("type");
		}
		return type;
	}

}
