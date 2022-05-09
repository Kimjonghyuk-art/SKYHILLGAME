package yedam.game.skyhill.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yedam.game.skyhill.DAO.DataSource;
import yedam.game.skyhill.VO.FloorInfoVO;

public class FloorData {
	private DataSource dataSource = DataSource.getInstance();
	private Connection con = dataSource.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;

	//플로어 조회 메소드 
	public List<FloorInfoVO> FloorInfo(int floorNum) {
		List<FloorInfoVO> floorlist = new ArrayList<FloorInfoVO>();

		FloorInfoVO floorVO = new FloorInfoVO();

		String sql = "SELECT * FROM floorinfo WHERE floornum = ?";
		System.out.println("넘겨받은 값 " + floorNum + " 층");
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, floorNum);
			rs = psmt.executeQuery();
			while (rs.next()) {
				
				//vo.setId(rs.getString("sId"));
				floorVO.setFloorNum(rs.getInt("floorNum"));//층번호
				floorVO.setCheckfloor(rs.getString("checkfloor"));//입장여부
				floorVO.setElevator(rs.getString("elevator"));
				floorVO.setVendingMachine(rs.getString("vendingMachine"));
				floorVO.setEnemyInCount(rs.getString("enemyInCount"));
				
				floorlist.add(floorVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return floorlist;
	}
	
	
	

}
