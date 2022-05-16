package yedam.game.skyhill.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yedam.game.skyhill.DAO.DataSource;
import yedam.game.skyhill.VO.FloorInfoVO;
import yedam.game.skyhill.VO.FoodsVO;
import yedam.game.skyhill.VO.KitItemsVO;
import yedam.game.skyhill.VO.WeaponVO;

public class ItemsImpl implements Items {

	private DataSource dataSource = DataSource.getInstance();
	private Connection con ;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	//음식 목록전체 조회 메소드 
	@Override
	public List<FoodsVO> getFoods() {
		List<FoodsVO> foodslist = new ArrayList<FoodsVO>();
		con = dataSource.getConnection();
		String sql = "SELECT * FROM foods";
		
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				
				FoodsVO foodsvo = new FoodsVO();
				//vo.setId(rs.getString("sId"));
				foodsvo.setCcode(rs.getInt("ccode"));
				foodsvo.setItemcode(rs.getInt("itemcode"));
				foodsvo.setName(rs.getString("name"));
				foodsvo.setEffect(rs.getInt("effect"));
				foodsvo.setGrade(rs.getString("grade"));
				foodsvo.setMeterial(rs.getInt("meterial"));
				foodsvo.setMeterial2(rs.getInt("meterial2"));
				foodsvo.setMeterial3(rs.getInt("meterial3"));
				foodsvo.setMeterial4(rs.getInt("meterial4"));
				
				foodslist.add(foodsvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
	        if (con != null) try { con.close(); } catch(SQLException ex) {}
	    }
		return foodslist;
	}

	@Override
	public List<KitItemsVO> getKitItems() {
		con = dataSource.getConnection();
		List<KitItemsVO> kititemlist = new ArrayList<KitItemsVO>();

		String sql = "SELECT * FROM kititems";
		
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				
				KitItemsVO kititemvo = new KitItemsVO();
				//vo.setId(rs.getString("sId"));
				//foodsvo.setCcode(rs.getInt("ccode"));
				kititemvo.setCcode(rs.getInt("ccode"));
				kititemvo.setItemcode(rs.getInt("itemcode"));
				kititemvo.setName(rs.getString("name"));
				kititemvo.setEffect(rs.getInt("effect"));
				kititemvo.setGrade(rs.getString("grade"));
				kititemvo.setMaterial(rs.getInt("material"));
				kititemvo.setMaterial2(rs.getInt("material2"));
				kititemvo.setFkccode(rs.getInt("fkccode"));
				kititemvo.setFkccode2(rs.getInt("fkccode2"));
				kititemlist.add(kititemvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
	        if (con != null) try { con.close(); } catch(SQLException ex) {}
	    }
		return kititemlist;
	}

	@Override
	public List<WeaponVO> getWeapons() {
		con = dataSource.getConnection();
		List<WeaponVO> weaponlist = new ArrayList<WeaponVO>();

		String sql = "SELECT * FROM weapons";
		
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				
				WeaponVO weaponvo = new WeaponVO();
				//vo.setId(rs.getString("sId"));
				//foodsvo.setCcode(rs.getInt("ccode"));
				weaponvo.setCcode(rs.getInt("ccode"));
				weaponvo.setItemcode(rs.getInt("itemcode"));
				weaponvo.setName(rs.getString("name"));
				weaponvo.setEffect(rs.getInt("effect"));
				weaponvo.setDex(rs.getInt("dex"));
				weaponvo.setStr(rs.getInt("str"));
				weaponvo.setSpd(rs.getInt("spd"));
				weaponvo.setGrade(rs.getString("grade"));
				weaponvo.setUsecheck(rs.getNString("usecheck"));
				weaponlist.add(weaponvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (psmt != null) try { psmt.close(); } catch(SQLException ex) {}
	        if (con != null) try { con.close(); } catch(SQLException ex) {}
	    }
		return weaponlist;
	}
	
	
	
	
}
