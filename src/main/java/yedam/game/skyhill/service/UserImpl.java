package yedam.game.skyhill.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yedam.game.skyhill.DAO.DataSource;
import yedam.game.skyhill.VO.FoodsVO;
import yedam.game.skyhill.VO.InventoryVO;
import yedam.game.skyhill.VO.MaterialVO;
import yedam.game.skyhill.VO.WeaponVO;

public class UserImpl implements User {
	private DataSource dataSource = DataSource.getInstance();
	private Connection con = dataSource.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public int Insertitems(int randomNum,String tablename) { //아이템 추가 메소드 
		int result = 0;
		String sql = "INSERT INTO inventory (ccode,itemcode,name,effect,grade)"
				+ " SELECT ccode,itemcode,name,effect,grade"
				+ " FROM " + tablename +" WHERE itemcode = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, randomNum);
			result = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;

	}
	
	
	
	@Override
	public int InsertMeterial(MaterialVO meterfialvo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//인벤토리 전체 조회 메소드 
	@Override
	public List<InventoryVO> selectInventory() {
		List<InventoryVO> inventorylist = new ArrayList<InventoryVO>();

		String sql = "SELECT * FROM inventory";
		
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				
				InventoryVO inventoryvo = new InventoryVO();
				//vo.setId(rs.getString("sId"));
				
				inventoryvo.setCcode(rs.getInt("ccode"));
				inventoryvo.setItemcode(rs.getInt("itemcode"));
				inventoryvo.setName(rs.getString("name"));
				inventoryvo.setEffect(rs.getInt("effect"));
				inventoryvo.setGrade(rs.getString("grade"));
				inventoryvo.setCount(rs.getInt("count"));
				
				
				inventorylist.add(inventoryvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventorylist;
	}
	@Override
	public int inventoryUpdateitems(int randonNum, int ccode) {
		
		int result = -1;
		String sql = "UPDATE inventory SET count = count+1 WHERE itemcode=? AND ccode = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, randonNum+1);
			psmt.setInt(2, ccode);
			result = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	@Override
	public int deleteitems(String name,int ccode) {
		int result = -1;
		String sql = "DELETE FROM inventory WHERE name ='"+name+"'";
		
		try {
			psmt = con.prepareStatement(sql);
			//psmt.setString(1, "'"+name+"'");
			result = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	//응급 도구 추가 메소드 
	@Override
	public int insertKitItems(int randomNum) {
		int result = 0;
		System.out.println("응급 아이템 인덱스 >"+randomNum);
		String sql = "INSERT INTO inventory (ccode,itemcode,name,effect,grade)"
				+ " SELECT ccode,itemcode,name,effect,grade"
				+ " FROM kititems WHERE itemcode = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, randomNum);
			result = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;

	}

	@Override
	public int minousCountitems(String name, int ccode) {
		int result = -1;
		String sql = "UPDATE inventory SET count = count-1 WHERE name="+"'"+name+"'"+"AND ccode = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, ccode);
			
			result = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int deleteAllInventory() {
		int result = 0;
	
		String sql = "DELETE FROM inventory";
		try {
			psmt = con.prepareStatement(sql);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
		
	}



	@Override
	public List<InventoryVO> inventoryWeaponSelect() {
		
		List<InventoryVO> inventorylist = new ArrayList<InventoryVO>();
		
		String sql = "SELECT i.ccode,i.itemcode,i.grade,i.count,i.name,w.effect,w.dex,w.str,w.spd,w.usecheck "
				+ "FROM (select * from inventory WHERE inventory.ccode = 1) i "
				+ " LEFT JOIN weapons w "
				+ " ON(i.ccode = w.ccode AND i.itemcode = w.itemcode)";
	
		try {
			psmt = con.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				WeaponVO weaponvo = new WeaponVO();
				// vo.setId(rs.getString("sId"));
				weaponvo.setEffect(rs.getInt("effect"));
				weaponvo.setDex(rs.getInt("dex"));
				weaponvo.setStr(rs.getInt("str"));
				weaponvo.setSpd(rs.getInt("psd"));
				weaponvo.setUsecheck(rs.getString("usecheck"));
				
				InventoryVO inventoryvo = new InventoryVO(weaponvo,rs.getInt("ccode"),rs.getInt("itemcode"),
						rs.getString("name"),rs.getInt("effect"),rs.getString("grade"),
						rs.getInt("count"));
						
				inventorylist.add(inventoryvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return inventorylist;
	}
	



	
	
	
}
