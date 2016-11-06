


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLite {

	public static Connection cnx = null;
	public static String dirName = "DB";
	public static String dbName = "database";

	public static Connection getConnexion() {
		createDir();
		
		if(cnx == null){
			try {
				Class.forName("org.sqlite.JDBC");
				cnx = DriverManager.getConnection("jdbc:sqlite:" + dirName + "/" + dbName + ".db");
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getClass().getName()+": "+e.getMessage());
				System.exit(0);
			}
			System.out.println("Opened database successfully");
		}

		
		return cnx;
	}


	public static void closeConnection(){
		if(cnx != null){
			try {
				cnx.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void createDir(){
		File theDir = new File(dirName);

		// if the directory does not exist, create it
		if (!theDir.exists()){
			System.out.println("creating directory: " + dirName);
			theDir.mkdirs();
			System.out.println("created: " + theDir);
			try {
				initiate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void initiate() throws SQLException{
		System.out.println("initiate db");
		String sql = "";
		PreparedStatement st = SQLite.getConnexion().prepareStatement(sql);
		st.executeUpdate();
		System.out.println("db initiated");
	}

}
