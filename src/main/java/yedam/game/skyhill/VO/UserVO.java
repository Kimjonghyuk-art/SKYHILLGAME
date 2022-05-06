package yedam.game.skyhill.VO;

import lombok.Data;

@Data
public class UserVO {

	int 시작플로어 = 100;
	int 진행중플로어 = 100;
	int 마지막플로어 = 100;
	int 체력 = 100; // 기본체력
	int 스태미너 = 50; // 기본 스태미너
	int 강도 = 4;
	int 속도 = 4;
	int 민첩 = 4;
	int 명중 = 4;
	int 경험치 = 0;

}
