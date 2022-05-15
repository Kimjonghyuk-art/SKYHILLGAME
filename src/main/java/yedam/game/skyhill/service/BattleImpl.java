package yedam.game.skyhill.service;

import java.util.Random;
import java.util.Scanner;

import yedam.game.skyhill.VO.EnemyVO;
import yedam.game.skyhill.VO.UserVO;

public class BattleImpl implements Battle {

	@Override
	public void battleInterface(EnemyVO enemyvo, UserVO uservo) {
		System.out.println("\t\t\t\t\t 적 : " + enemyvo.get이름());
		System.out.printf("\t\t적 체력 : %.1f \t\t\t\t\t 내 체력 : %.1f ", enemyvo.get체력(), uservo.get체력());
		System.out.println();

	}

	@Override
	public void battleStart(EnemyVO enemyvo, UserVO uservo) {
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		double 최대데미지 = uservo.get공격력() + uservo.get보정공격력();
		double 기본데미지 = uservo.get공격력();
		double 최소데미지 = uservo.get공격력() - uservo.get보정공격력();
		double 최대명중률 = uservo.get기본명중률() * uservo.get보정명중률() + uservo.get기본명중률();
		double 기본명중률 = uservo.get기본명중률();
		double 최소명중률 = uservo.get기본명중률() - uservo.get보정명중률() * uservo.get기본명중률();
		while (enemyvo.get체력() > 0) {
			battleInterface(enemyvo, uservo);
			double 데미지보정률 = ((ran.nextDouble() + 0.7));
			double 최소보정데미지 = (최소데미지 * 데미지보정률);
			double 기본보정데미지 = (기본데미지 * 데미지보정률);
			double 최대보정데미지 = (최대데미지 * 데미지보정률);
			System.out.printf("\t\t\t데미지: %.1f ~ %.1f  데미지 : %.1f ~ %.1f 데미지 : %.1f ~ %.1f", (최소데미지 * 0.7),
					(최소데미지 * 1.49), (기본데미지 * 0.7), (기본데미지 * 1.49), (최대데미지 * 0.7), (최대데미지 * 1.49));
			System.out.println();
			System.out.printf("\t\t\t      확률  %.1f         확률 %.1f          확률 %.1f ", 최대명중률-enemyvo.get회피율(), 기본명중률-enemyvo.get회피율(), 최소명중률-enemyvo.get회피율());
			System.out.println();
			System.out.println("\t\t\t\t\t  선택 >");
			int select = sc.nextInt();
			int count = 0;
			if (select == 1) {
				attack(최소보정데미지, 최대명중률-enemyvo.get회피율(), enemyvo, uservo, count, select);
			} else if (select == 2) {
				attack(기본보정데미지, 기본명중률-enemyvo.get회피율(), enemyvo, uservo, count, select);
			} else if (select == 3) {
				attack(최대보정데미지, 최대명중률-enemyvo.get회피율(), enemyvo, uservo, count, select);
			} else {
				System.out.println("잘못된 값 입력했슈");
			}
		}

		System.out.println("\t\t\t\t" + enemyvo.get이름() + "(이)가 죽었습니다.");
		System.out.println("\t\t\t\t " + enemyvo.get경험치() + " exp 획득");

		// 획득 경험치 저장
		uservo.set경험치(uservo.get경험치() + enemyvo.get경험치());
		if (uservo.get경험치() > uservo.get레벨업필요경험치()) {
			System.out.println("\t\t\t\tLEVEL UP!!");
			System.out.println("\t\t\t\t어빌리티포인트 +4 획득!");
			uservo.set경험치(uservo.get경험치() - uservo.get레벨업필요경험치()); // 경험치 초기화
			uservo.set레벨업필요경험치(uservo.get레벨업필요경험치() * 1.5);// 필요경험치 1.5배
			uservo.set어빌리티포인트((uservo.get어빌리티포인트() + 4)); // 어빌리티 저장
		}
		
		System.out.println("\t\t\t\t 현재 경험치 : " + uservo.get경험치());
		System.out.println("\t\t\t\t 다음레벨 까지 > " + (uservo.get레벨업필요경험치()-uservo.get경험치()));
		System.out.println();
		// 레벨업 필요 경험치보다 많다면

	}

	@Override
	public void attack(double 데미지, double 명중률, EnemyVO enemyvo, UserVO uservo, int count, int select) {
		Random ran = new Random();
		double 데미지보정률 = ((ran.nextDouble() + 0.7));
		double 최대데미지 = uservo.get공격력() + uservo.get보정공격력();
		double 기본데미지 = uservo.get공격력();
		double 최소데미지 = uservo.get공격력() - uservo.get보정공격력();
		double 최대명중률 = uservo.get기본명중률() * uservo.get보정명중률() + uservo.get기본명중률();
		double 기본명중률 = uservo.get기본명중률();
		double 최소명중률 = uservo.get기본명중률() - uservo.get기본명중률() * uservo.get보정명중률();
		double 최소보정데미지 = (최소데미지 * 데미지보정률);
		double 기본보정데미지 = (기본데미지 * 데미지보정률);
		double 최대보정데미지 = (최대데미지 * 데미지보정률);

		if (ran.nextInt(100) < 명중률) { // 명중률 확률
			if (ran.nextInt(100) < uservo.get치명타확률()) { // 치명타 확률 안에 들어오면 크리티컬
				System.out.println("\t\t\t\t\t크리티컬 히트!!");
				System.out.printf("\t\t\t\t" + enemyvo.get이름() + "에게 %.1f 의 피해를 입혔습니다!\n", 데미지 * 1.4);
				enemyvo.set체력(enemyvo.get체력() - 데미지 * 1.4);

			} else { // 크리티컬 안터지면 일반공격
				System.out.printf("\t\t\t\t" + enemyvo.get이름() + "에게 %.1f 의 피해를 입혔습니다!\n", 데미지);
				System.out.println();
				enemyvo.set체력(enemyvo.get체력() - 데미지);

			}

			if (ran.nextInt(100) < (uservo.get속도() * 2) && count == 0) { // 추가공격 확률
				System.out.println("\t\t\t\t\t추가공격!!");
				System.out.println();
				count += 1;
				if (select == 1) {
					attack(최소보정데미지, 최대명중률, enemyvo, uservo, count, select);
				} else if (select == 2) {
					attack(기본보정데미지, 기본명중률, enemyvo, uservo, count, select);
				} else if (select == 3) {
					attack(최대보정데미지, 최대명중률, enemyvo, uservo, count, select);
				}
			} else { // 추가공격 안터지면 적공격
				enemyAttack(enemyvo, uservo);

			}

		} else { // 빗나가면 적공격
			System.out.println("\t\t\t\t\t !감나빗");
			System.out.println();
			enemyAttack(enemyvo, uservo);
		}

	}

	private void enemyAttack(EnemyVO enemyvo, UserVO uservo) {
		Random ran = new Random();
		double 데미지보정률 = ((ran.nextDouble() + 0.7));
		double 보정데미지 = (enemyvo.get공격력())*데미지보정률;
		if (enemyvo.get체력() > 0) {
			if (ran.nextInt(100) < enemyvo.get명중률() - uservo.get회피율()) {
				System.out.printf("\t\t\t\t%s(이)가 %.1f 의 데미지로 공격!\n",enemyvo.get이름(),보정데미지);
				System.out.println();
				uservo.set체력(uservo.get체력() - 보정데미지);
				if (uservo.get체력() < 0) {
					System.out.println("\t\t\t\t You Die!!");
					System.exit(0);
				}
			} else {
				System.out.println("\t\t\t\t" + enemyvo.get이름() + " !감나빗");
				System.out.println();
			}
		}
	}

}
