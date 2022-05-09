package yedam.game.skyhill.VO;

import lombok.Data;

@Data
public class leftfloorinfoVO {

	private int floorNum; // 층 번호
	private String leftEnemyInCount; //왼쪽 적 등장 여부
	private String dropItems; //아이템 드랍 여부
	private String checkfloor; // 방 체크 여부 
	
}
