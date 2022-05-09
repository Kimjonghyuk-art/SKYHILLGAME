package yedam.game.skyhill.VO;

import lombok.Data;

@Data
public class FloorInfoVO {

	private int floorNum;//층번호
	private String enemyInCount; //적등장여부
	private String vendingMachine; //자판기
	private String elevator; //엘리베이터 이용가능여부
	private String checkfloor; //방문한 적 있는지 체크 
	
	
}
