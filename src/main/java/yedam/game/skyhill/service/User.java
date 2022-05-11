package yedam.game.skyhill.service;

import java.util.List;

import yedam.game.skyhill.VO.InventoryVO;
import yedam.game.skyhill.VO.MaterialVO;

public interface User {

	
	public int InsertFood(int randomNum); //음식 추가 메소드
	public int InsertMeterial(MaterialVO meterfialvo); //재료 추가 메소드
	public List<InventoryVO> selectInventory();//인벤토리 전체 조회 메소드 
	public int inventoryUpdateFood(int randonNum);
}
