package yedam.game.skyhill.service;

import yedam.game.skyhill.VO.UserVO;

public interface GameStartService {
	
	public void getStatus();
	public void selectFloorMenu(); //100층 선택 메뉴
	public void progressMenu();
	public void staminaLogic(); // 스태미나 감소 메소드
	public void clearColumn(); //테이블 내 컬럼 초기화 실행 메소드
	public void setElevator();

	
	
}
