package yedam.game.skyhill.VO;

import lombok.Data;

@Data
public class KitItemsVO {

	int ccode; //아이템분류 코드
	int itemcode; //각아이템 코드
	String name;
	int effect; // 효과
	String grade; //아이템 등급
	int material;
	int material2;
	int fkccode;
	int fkccode2;
}
