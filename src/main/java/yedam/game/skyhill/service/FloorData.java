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
import yedam.game.skyhill.VO.rightfloorinfoVO;

public class FloorData {
	private DataSource dataSource = DataSource.getInstance();
	private Connection con;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 플로어 조회 메소드
	public List<FloorInfoVO> FloorInfo(int floorNum) {
		con = dataSource.getConnection();
		List<FloorInfoVO> floorlist = new ArrayList<FloorInfoVO>();

		FloorInfoVO floorVO = new FloorInfoVO();

		String sql = "SELECT * FROM floorinfo WHERE floornum = ?";
		

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
		}finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
	        if (con != null) try { con.close(); } catch(SQLException ex) {}
	    }
		return floorlist;
	}

	// 플로어 조회 메소드
	public List<leftfloorinfoVO> leftFloorInfo(int floorNum) {
		con = dataSource.getConnection();
		List<leftfloorinfoVO> floorlist = new ArrayList<leftfloorinfoVO>();

		String sql = "SELECT * FROM leftfloorinfo WHERE floornum = ?";
		

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
		}finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
	        if (con != null) try { con.close(); } catch(SQLException ex) {}
	    }
		return floorlist;
	}
	//오른쪽 방 플로어 조회 메소드 
	public List<rightfloorinfoVO> rightFloorInfo(int floorNum) {
		con = dataSource.getConnection();
		List<rightfloorinfoVO> floorlist = new ArrayList<rightfloorinfoVO>();

		String sql = "SELECT * FROM rightfloorinfo WHERE floornum = ?";

		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, floorNum);
			rs = psmt.executeQuery();
			if (rs.next()) {

				rightfloorinfoVO rightfloorvo = new rightfloorinfoVO();
				// vo.setId(rs.getString("sId"));
				rightfloorvo.setFloorNum(rs.getInt("floorNum"));// 층번호
				rightfloorvo.setDropItems(rs.getString("dropitems"));// 아이템여부
				rightfloorvo.setRightEnemyInCount(rs.getString("rightenemyincount"));
				rightfloorvo.setCheckfloor(rs.getString("checkfloor"));// 입장여부
				floorlist.add(rightfloorvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
	        if (con != null) try { con.close(); } catch(SQLException ex) {}
	    }
		return floorlist;

	}
	
	
	// 플로어 방문 여부 체크 메소드
	public int checkFloor(int floorNum) {
		con = dataSource.getConnection();
		int result = 0;
		String sql = "UPDATE floorinfo SET checkfloor = 1 WHERE floornum = ?";

		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, floorNum);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
	        if (con != null) try { con.close(); } catch(SQLException ex) {}
	    }

		return result;

	}

	// 왼쪽방 방문 여부 체크 메소드
	public int leftCheckFloor(int floorNum) {
		con = dataSource.getConnection();
		int result = 0;
		String sql = "UPDATE leftfloorinfo SET checkfloor = 1 WHERE floornum = ?";

		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, floorNum);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
	        if (con != null) try { con.close(); } catch(SQLException ex) {}
	    }

		return result;

	}
	//오른쪽방 방문 여부 체크 메소드 
	public int rightCheckFloor(int floorNum) {
		con = dataSource.getConnection();
		int result = 0;
		String sql = "UPDATE rightfloorinfo SET checkfloor = 1 WHERE floornum = ?";

		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, floorNum);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
	        if (con != null) try { con.close(); } catch(SQLException ex) {}
	    }

		return result;

		
	}
	
	

	//실행 시 테이블 초기화 메소드 
	public int floorCheckClear() {
		con = dataSource.getConnection();
		int result = 0;
		String sql = "UPDATE floorinfo SET checkfloor = 0, elevator = 0, vendingmachine = 0, enemyincount = 0";

		try {
			psmt = con.prepareStatement(sql);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
	        if (con != null) try { con.close(); } catch(SQLException ex) {}
	    }

		return result;
	
	}

	//실행 시 왼쪽플로어체크 초기화 메소드 
	public int leftFloorCheckClear() {
		con = dataSource.getConnection();
		int result = 0;
		String sql = "UPDATE leftfloorinfo SET leftenemyincount = 0, dropitems = 0, checkfloor = 0";
		try {
			psmt = con.prepareStatement(sql);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
	        if (con != null) try { con.close(); } catch(SQLException ex) {}
	    }
		return result;
	}
	//실행 시 오른쪽플로어체크 초기화 메소드 
		public int rightFloorCheckClear() {
			con = dataSource.getConnection();
			int result = 0;
			String sql = "UPDATE rightfloorinfo SET rightenemyincount = 0, dropitems = 0, checkfloor = 0";
			try {
				psmt = con.prepareStatement(sql);
				result = psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
		        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
		        if (con != null) try { con.close(); } catch(SQLException ex) {}
		    }
			return result;
		}

		
		public int elevatorCheckUpdate(int RanfloorNum) {
			con = dataSource.getConnection();
			int result = 0;
			String sql = "UPDATE floorinfo SET elevator = 1 WHERE floornum = ?";

			try {
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, RanfloorNum);
				result = psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
		        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
		        if (con != null) try { con.close(); } catch(SQLException ex) {}
		    }

			return result;

		}
		public int clearElevator() {
			con = dataSource.getConnection();
			int result = 0;
			String sql = "UPDATE floorinfo SET elevator = 0";

			try {
				psmt = con.prepareStatement(sql);
				result = psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
		        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
		        if (con != null) try { con.close(); } catch(SQLException ex) {}
		    }

			return result;

		}
		
		public int updateElevator(int floorNum) {
			con = dataSource.getConnection();
			int result = 0;
			String sql = "UPDATE floorinfo SET elevator = 1 WHERE floornum = ?";

			try {
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, floorNum);
				result = psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
		        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
		        if (con != null) try { con.close(); } catch(SQLException ex) {}
		    }

			return result;

		}

		public List<FloorInfoVO> selectElevator() {
			con = dataSource.getConnection();
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
			}finally {
		        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
		        if (con != null) try { con.close(); } catch(SQLException ex) {}
		    }
			return floorlist;
			
		}

		//모든 정보 가지고 있는 리스트 
		public List<rightfloorinfoVO> getAllRightfloorinfo() {
			con = dataSource.getConnection();
			List<rightfloorinfoVO> floorlist = new ArrayList<rightfloorinfoVO>();

			String sql = "SELECT * FROM rightfloorinfo ORDER BY floornum DESC";

			try {
				psmt = con.prepareStatement(sql);
				rs = psmt.executeQuery();
				while (rs.next()) {

					rightfloorinfoVO rightfloorvo = new rightfloorinfoVO();
					// vo.setId(rs.getString("sId"));
					rightfloorvo.setFloorNum(rs.getInt("floorNum"));// 층번호
					rightfloorvo.setDropItems(rs.getString("dropitems"));// 아이템여부
					rightfloorvo.setRightEnemyInCount(rs.getString("rightenemyincount"));
					rightfloorvo.setCheckfloor(rs.getString("checkfloor"));// 입장여부
					floorlist.add(rightfloorvo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
		        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
		        if (con != null) try { con.close(); } catch(SQLException ex) {}
		    }
			return floorlist;

		}
		//모든 정보 가지고 있는 리스트 
		public List<FloorInfoVO> getAllFloorinfo() {
			con = dataSource.getConnection();
			List<FloorInfoVO> floorlist = new ArrayList<FloorInfoVO>();

			String sql = "SELECT * FROM floorinfo ORDER BY floornum DESC";
			try {
				psmt = con.prepareStatement(sql);
				rs = psmt.executeQuery();
				while (rs.next()) {

					FloorInfoVO floorinfovo = new FloorInfoVO();
					floorinfovo.setFloorNum(rs.getInt("floornum"));
					floorinfovo.setEnemyInCount(rs.getString("enemyincount"));
					floorinfovo.setVendingMachine(rs.getString("vendingmachine"));
					floorinfovo.setElevator(rs.getString("elevator"));
					floorinfovo.setCheckfloor(rs.getString("checkfloor"));
					floorlist.add(floorinfovo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
		        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
		        if (con != null) try { con.close(); } catch(SQLException ex) {}
		    }
			return floorlist;
			
		}
		//모든 정보 담고 있는 메소드 
		public List<leftfloorinfoVO> getAllLeftfloorinfo() {
			con = dataSource.getConnection();
			List<leftfloorinfoVO> floorlist = new ArrayList<leftfloorinfoVO>();

			String sql = "SELECT * FROM leftfloorinfo ORDER BY floornum DESC";
			

			try {
				psmt = con.prepareStatement(sql);
				rs = psmt.executeQuery();
				while (rs.next()) {

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
			}finally {
		        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
		        if (con != null) try { con.close(); } catch(SQLException ex) {}
		    }
			return floorlist;
		}
}
