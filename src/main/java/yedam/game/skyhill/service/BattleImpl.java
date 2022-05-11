package yedam.game.skyhill.service;

import java.util.Random;
import java.util.Scanner;

import yedam.game.skyhill.VO.EnemyVO;
import yedam.game.skyhill.VO.UserVO;

public class BattleImpl implements Battle {
	
	private int levelUpExp = 100;
	
	@Override
	public void battleInterface(EnemyVO enemyvo,UserVO uservo) {
	  	System.out.println("\t\t\t\t\t 적 : "+enemyvo.get이름());
    	System.out.printf("\t\t적 체력 : %.1f \t\t\t\t\t 내 체력 : %.1f ",enemyvo.get체력(),uservo.get체력());
    	System.out.println();
    	
	}
	@Override
	public void battleStart(EnemyVO enemyvo,UserVO uservo) {
		Scanner sc = new Scanner(System.in);
		double 최대데미지 = uservo.get공격력()+uservo.get보정공격력();
		double 기본데미지 = uservo.get공격력();
		double 최소데미지 = uservo.get공격력()-uservo.get보정공격력();
		double 최대명중률 = uservo.get기본명중률()*uservo.get보정명중률()+uservo.get기본명중률();
		double 기본명중률 = uservo.get기본명중률();
		double 최소명중률 = uservo.get기본명중률()-uservo.get기본명중률()*uservo.get보정명중률();
		while(enemyvo.get체력() > 0) {
			battleInterface(enemyvo,uservo);
			System.out.printf("\t\t\t\t데미지: %.1f 데미지 : %.1f 데미지 : %.1f",
					최소데미지,
					기본데미지,
					최대데미지);
			System.out.println();
			System.out.printf("\t\t\t\t 확률  %.1f  확률 %.1f  확률 %.1f ",
	    			최대명중률,
	    			기본명중률,
	    			최소명중률);	
	    	System.out.println();
			System.out.println("\t\t\t\t\t  선택 >");
			int select = sc.nextInt();
			if(select == 1) {
				attack(최소데미지,최대명중률,enemyvo,uservo);
			} else if(select == 2) {
				attack(기본데미지,기본명중률,enemyvo,uservo);
			} else if(select == 3) {
				attack(최대데미지,최대명중률,enemyvo,uservo);
			} else {
				System.out.println("잘못된 값 입력했슈");
			}
		}
		
		System.out.println("\t\t\t\t\t"+enemyvo.get이름()+"(이)가 죽었습니다.");
		System.out.println("\t\t\t\t\t"+enemyvo.get경험치()+"   exp획득");
		System.out.println();
		//획득 경험치 저장
		uservo.set경험치(uservo.get경험치()+enemyvo.get경험치());
		System.out.println("\t\t\t\t\t 현재 경험치 : " + uservo.get경험치()+"exp");
		System.out.println("\t\t\t\t\t 레벨업 필요 경험치 : "+levelUpExp);
		System.out.println();
		//레벨업 필요 경험치보다 많다면 
		if(uservo.get경험치() > levelUpExp) {
			uservo.set경험치(uservo.get경험치()-levelUpExp); //경험치 초기화 
			levelUpExp = (int)(levelUpExp*1.5);//필요경험치 1.5배
			uservo.set어빌리티포인트(uservo.get어빌리티포인트()+4); //어빌리티 저장
		}
		
	}
	@Override
	public void attack(double 데미지, double 명중률,EnemyVO enemyvo,UserVO uservo) {
		Random ran = new Random();
		if (ran.nextInt(100) < 명중률) { //명중률 확률 
			System.out.println("\t\t\t\t"+enemyvo.get이름()+"에게 "+데미지+"의 피해를 입혔습니다!");
			System.out.println();
			enemyvo.set체력(enemyvo.get체력()-데미지);
			enemyAttack(enemyvo,uservo);
		} else {
			System.out.println("\t\t\t\t\t감나빗!");
			System.out.println();
			enemyAttack(enemyvo,uservo);
		}
	}


	private void enemyAttack(EnemyVO enemyvo, UserVO uservo) {
		Random ran = new Random();
		if(ran.nextInt(100) < enemyvo.get명중률()) {
			System.out.println("\t\t\t\t"+enemyvo.get이름()+"(이) 가" +enemyvo.get공격력()+"의 데미지"
					+ "로 공격함");
			System.out.println();
			uservo.set체력(uservo.get체력()-enemyvo.get공격력());
		} else {
			System.out.println("\t\t\t\t"+enemyvo.get이름()+"(이) 가 공격 실패!");
			System.out.println();
		}
		
	}


}
