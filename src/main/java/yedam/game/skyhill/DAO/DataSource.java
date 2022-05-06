package yedam.game.skyhill.DAO;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

	private static Connection con;
	private String driver;
	private String url;
	private String id;
	private String pw;
	
	private static DataSource dataSource = new DataSource();
	private DataSource() {};
	
	
	public static DataSource getInstance() {
		return dataSource;
	}
	
	public Connection getConnection() {
		dbconfiguration();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,id,pw);
			
			System.out.println("DB 연동 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	private void dbconfiguration() {
		Properties properties = new Properties();
		String resource = getClass().getResource("/db.properties").getPath();
		try {
			properties.load(new FileReader(resource));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			id = properties.getProperty("id");
			pw = properties.getProperty("pw");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
