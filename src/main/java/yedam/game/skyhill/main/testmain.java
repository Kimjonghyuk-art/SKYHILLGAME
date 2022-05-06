package yedam.game.skyhill.main;

import java.sql.Connection;

import yedam.game.skyhill.DAO.DataSource;

public class testmain {
	public static void main(String[] args) {
	
		 DataSource datasource = DataSource.getInstance();
		 Connection con = datasource.getConnection();
		System.out.println(con);
		
	}
}
