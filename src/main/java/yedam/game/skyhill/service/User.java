package yedam.game.skyhill.service;

import java.util.List;

import yedam.game.skyhill.VO.InventoryVO;
import yedam.game.skyhill.VO.MaterialVO;
import yedam.game.skyhill.VO.WeaponVO;

public interface User {

	
	public int Insertitems(int randomNum,String tablename); //음식 추가 메소드
	public int InsertMeterial(MaterialVO meterfialvo); //재료 추가 메소드
	public List<InventoryVO> selectInventory();//인벤토리 전체 조회 메소드 
	public int inventoryUpdateitems(int randonNum,int ccode); //인벤토리 카운터 증가
	public int deleteitems(String name,int ccode); // 음식 삭제
	public int minousCountitems(String name,int ccode); // 인벤토리 카운트 감소  
	
	
	public int insertKitItems(int randomNum); //응급도구 추가 메소드
	public List<InventoryVO> inventoryWeaponSelect(); //인벤토리내의 무기 정보 리스트
	public int useUpdateWeapon(String name,int usecheck);//장비 착용 여부 업데이트
	public WeaponVO currentUsedWeapon();
}
