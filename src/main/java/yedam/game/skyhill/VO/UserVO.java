package yedam.game.skyhill.VO;

import lombok.Data;

@Data
public class UserVO {

	int 시작플로어 = 100;
	int 진행중플로어 = 100;
	int 마지막플로어 = 100;
	double 체력 = 100; // 기본체력
	int 스태미너 = 50; // 기본 스태미너
	double 기본명중률 = 60;
	double 보정명중률 = 0.20;
	int 공격력 = 5;
	double 보정공격력 = 0.50;
	int 강도 = 4;
	int 속도 = 4;
	int 민첩 = 4;
	int 명중 = 4;
	int 경험치 = 0;
	int 어빌리티포인트 = 0;
}
