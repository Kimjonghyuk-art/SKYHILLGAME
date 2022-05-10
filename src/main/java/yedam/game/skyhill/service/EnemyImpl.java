package yedam.game.skyhill.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import yedam.game.skyhill.DAO.DataSource;

public class EnemyImpl implements Enemyinfo {

	
	private DataSource dataSource = DataSource.getInstance();
	private Connection con = dataSource.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;
	
	//확률안에 들었을 시 에너미 인카운트 증가 쿼리
	@Override
	public int enemyInCount(int floorNum) {
		int result = 0;
		String sql = "UPDATE floorinfo SET enemyincount = 1 WHERE floornum = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, floorNum);
			result = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

}
