package yedam.game.skyhill.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yedam.game.skyhill.DAO.DataSource;
import yedam.game.skyhill.VO.FloorInfoVO;
import yedam.game.skyhill.VO.leftfloorinfoVO;

public class FloorData {
	private DataSource dataSource = DataSource.getInstance();
	private Connection con = dataSource.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;

	// 플로어 조회 메소드
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

				// vo.setId(rs.getString("sId"));
				floorVO.setFloorNum(rs.getInt("floorNum"));// 층번호
				floorVO.setCheckfloor(rs.getString("checkfloor"));// 입장여부
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

	// 플로어 조회 메소드
	public List<leftfloorinfoVO> leftFloorInfo(int floorNum) {
		List<leftfloorinfoVO> floorlist = new ArrayList<leftfloorinfoVO>();

		String sql = "SELECT * FROM leftfloorinfo WHERE floornum = ?";
		System.out.println("왼쪽 방넘겨받은 값 " + floorNum + " 층");

		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, floorNum);
			rs = psmt.executeQuery();
			if (rs.next()) {

				leftfloorinfoVO leftfloorVO = new leftfloorinfoVO();
				// vo.setId(rs.getString("sId"));
				leftfloorVO.setFloorNum(rs.getInt("floorNum"));// 층번호
				leftfloorVO.setDropItems(rs.getString("dropitems"));// 아이템여부
				leftfloorVO.setLeftEnemyInCount(rs.getString("leftenemyincount"));
				leftfloorVO.setCheckfloor(rs.getString("checkfloor"));// 입장여부
				floorlist.add(leftfloorVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return floorlist;
	}

	// 플로어 방문 여부 체크 메소드
	public int checkFloor(int floorNum) {
		int result = 0;
		String sql = "UPDATE floorinfo SET checkfloor = 1 WHERE floornum = ?";

		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, floorNum);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	// 왼쪽방 방문 여부 체크 메소드
	public int leftCheckFloor(int floorNum) {
		int result = 0;
		String sql = "UPDATE leftfloorinfo SET checkfloor = 1 WHERE floornum = ?";

		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, floorNum);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	//실행 시 테이블 초기화 메소드 
	public int floorCheckClear() {
		int result = 0;
		String sql = "UPDATE floorinfo SET checkfloor = 0, elevator = 0, vendingmachine = 0, enemyincount = 0";

		try {
			psmt = con.prepareStatement(sql);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	
	}

	//실행 시 왼쪽플로어체크 초기화 메소드 
	public int leftFloorCheckClear() {
		int result = 0;
		String sql = "UPDATE leftfloorinfo SET leftenemyincount = 0, dropitems = 0, checkfloor = 0";
		try {
			psmt = con.prepareStatement(sql);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//실행 시 오른쪽플로어체크 초기화 메소드 
		public int rightFloorCheckClear() {
			int result = 0;
			String sql = "UPDATE rightfloorinfo SET rightenemyincount = 0, dropitems = 0, checkfloor = 0";
			try {
				psmt = con.prepareStatement(sql);
				result = psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}

		
		public int elevatorCheckUpdate(int RanfloorNum) {
			int result = 0;
			String sql = "UPDATE floorinfo SET elevator = 1 WHERE floornum = ?";

			try {
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, RanfloorNum);
				result = psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return result;

		}
		public int clearElevator() {
			int result = 0;
			String sql = "UPDATE floorinfo SET elevator = 0";

			try {
				psmt = con.prepareStatement(sql);
				result = psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return result;

		}
		
		public int updateElevator(int floorNum) {
			int result = 0;
			String sql = "UPDATE floorinfo SET elevator = 1 WHERE floornum = ?";

			try {
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, floorNum);
				result = psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return result;

		}

		public List<FloorInfoVO> selectElevator() {
			List<FloorInfoVO> floorlist = new ArrayList<FloorInfoVO>();

			String sql = "SELECT * FROM floorinfo WHERE elevator = 1";
			try {
				psmt = con.prepareStatement(sql);
				rs = psmt.executeQuery();
				while (rs.next()) {

					FloorInfoVO floorinfovo = new FloorInfoVO();
					// vo.setId(rs.getString("sId"));
					floorinfovo.setFloorNum(rs.getInt("floornum"));
					floorinfovo.setEnemyInCount(rs.getString("enemyincount"));
					floorinfovo.setVendingMachine(rs.getString("vendingmachine"));
					floorinfovo.setElevator(rs.getString("elevator"));
					floorinfovo.setCheckfloor(rs.getString("checkfloor"));
					floorlist.add(floorinfovo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return floorlist;
			
		}
		
}
