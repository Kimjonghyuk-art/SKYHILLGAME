package yedam.game.skyhill.VO;

import lombok.Data;

@Data
public class FoodsVO {
	private int ccode; //아이템 분류코드
	private int itemcode; //각 아이템 코드
	private String name; //이름
	private String grade;
	private int effect; //효과
	private int meterial;
	private int meterial2;
	private int meterial3;
	private int meterial4;
}
