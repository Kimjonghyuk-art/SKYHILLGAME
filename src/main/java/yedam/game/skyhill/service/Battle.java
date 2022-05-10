package yedam.game.skyhill.service;

import yedam.game.skyhill.VO.EnemyVO;
import yedam.game.skyhill.VO.UserVO;

public interface Battle {

	
	public void battleInterface(EnemyVO enemyvo,UserVO uservo); //배틀 메소드
	public void battleStart(EnemyVO enemyvo,UserVO uservo); //배틀 시작 메소드
	public void attack(double 데미지, double 명중률,EnemyVO enemyvo,UserVO uservo);
}
