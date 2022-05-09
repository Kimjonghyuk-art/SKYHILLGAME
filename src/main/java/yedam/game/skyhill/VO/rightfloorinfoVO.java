package yedam.game.skyhill.VO;

import lombok.Data;

@Data
public class rightfloorinfoVO {

	private int floorNum; //층 번호
	private String rightEnemyInCount; //오른쪽 적 등장 여부
	private String dropitmes; //아이템 드랍 여부 
	private String checkfloor; //방 체크 여부
	
}
