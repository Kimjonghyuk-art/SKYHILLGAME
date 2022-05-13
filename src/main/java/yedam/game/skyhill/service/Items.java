package yedam.game.skyhill.service;

import java.util.List;

import yedam.game.skyhill.VO.FoodsVO;
import yedam.game.skyhill.VO.KitItemsVO;
import yedam.game.skyhill.VO.WeaponVO;

public interface Items {

	
	//음식 목록 조회 메소드
	public List<FoodsVO> getFoods ();
	public List<KitItemsVO> getKitItems();
	public List<WeaponVO> getWeapons();
}
