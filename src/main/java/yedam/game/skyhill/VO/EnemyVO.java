package yedam.game.skyhill.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class EnemyVO {

	private String 이름;
	private double 체력;
	private int 공격력;
	private int 경험치;
	private double 명중률;
}
