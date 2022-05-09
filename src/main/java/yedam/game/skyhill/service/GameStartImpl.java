package yedam.game.skyhill.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import yedam.game.skyhill.VO.FloorInfoVO;
import yedam.game.skyhill.VO.UserVO;

public class GameStartImpl implements GameStartService {

	UserVO uservo = new UserVO();
	FloorData fd = new FloorData(); // 층별 데이터 객체
	static List<FloorInfoVO> floorlist = new ArrayList<FloorInfoVO>();

	// 스테이터스
	@Override
	public void getStatus() {
		System.out.println("\t\t\t\t\t ─────────────");
		System.out.println("\t\t\t\t\t  현재 층 : " + uservo.get진행중플로어());
		System.out.println("\t\t\t\t\t ─────────────");
		System.out.println("\t\t\t\t\t\t\t\t 체력 : " + uservo.get체력());
		System.out.println("\t\t\t\t\t\t\t\t 스태미너 : " + uservo.get스태미너());
		System.out.println();
		System.out.println();
		System.out.println();
	}

	// 100층 플로어 메뉴
	@Override
	public void selectFloorMenu() {

		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t\t\t ─────────────");
		System.out.println("\t\t\t\t\t  현재 층 : " + uservo.get시작플로어());
		System.out.println("\t\t\t\t\t ─────────────");
		System.out.println("\t\t\t\t\t\t\t\t 체력 : " + uservo.get체력());
		System.out.println("\t\t\t\t\t\t\t\t 스태미너 : " + uservo.get스태미너());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("1.내려가기 2.장비제작 3.음식제작 4.업그레이드 5.수면 6.현재 스테이터스 확인 7.현재 진행상황 보기");
		System.out.print("선택 >>");
		try {
			int selectFloorMenu = sc.nextInt();

			while (true) {
				if (selectFloorMenu == 1) { // 내려가기
					staminaLogic(); // 스태미너 감소
					uservo.set진행중플로어(uservo.get시작플로어() - 1);
					floorlist = fd.FloorInfo(uservo.get진행중플로어()); // 층별셀렉트값 넘겨줌

					for (int i = 0; i < floorlist.size(); i++) {
						//방문 체크 이프문
						if (floorlist.get(i).getCheckfloor().equals("0")) { // 방문한 적 없다면
							floorlist.get(i).setCheckfloor("1"); // 방문 체크
						}
						//적 출현 메소드 작성 업데이트 쿼리
						if(floorlist.get(i).getEnemyInCount().equals("0")) { //에너미카운터 0
							//int ran = (int)(Math.random()*30)+1;
							Random ran = new Random();
							
							if(ran.nextInt(10) < 3) {//30%확률
								//업데이트 메소드 실행
							}
							
						}
						//아이템 메소드 작성
						
						//자판기or 엘리베이터 작성 
					}
					
					

					progressMenu(); // 진행 실행
				} else if (selectFloorMenu == 2) { // 장비제작

				} else if (selectFloorMenu == 3) { // 음식제작

				} else if (selectFloorMenu == 4) { // 업그레이드

				} else if (selectFloorMenu == 5) { // 수면제작

				} else if (selectFloorMenu == 6) { // 현재스테이터스

				} else if (selectFloorMenu == 7) { // 현재 진행상황보기

				}
			}

		} catch (InputMismatchException e) {
			System.out.println("잘못입력");

		} finally {
			selectFloorMenu();
		}

	}

	// 해당 층에 방문확인 체크
	private void checkFloor(String checkfloor) {
		// if(checkfloor =)

	}

	@Override
	public void progressMenu() {
		Scanner sc = new Scanner(System.in);
		getStatus();
		System.out.println("1.내려가기  2.왼쪽방 들어가기 3.오른쪽방 들어가기 4.올라가기 5.엘리베이터 ");
		System.out.print("선택>>");
		try {
			int selectMenu = sc.nextInt();

			while (true) {
				if (selectMenu == 1) { // 내려가기
					staminaLogic();
					uservo.set진행중플로어(uservo.get진행중플로어() - 1);
					FloorData fd = new FloorData(); // 층별 데이터 객체
					floorlist = fd.FloorInfo(uservo.get진행중플로어());
					System.out.println("몇층?" + floorlist.get(1));

					/*
					 * if(floorinfovo.getCheckfloor().equals("0")) { //방문여부체크후
					 * floorinfovo.setCheckfloor("1"); //값변경 }
					 */

					progressMenu();
				} else if (selectMenu == 2) { // 왼쪽방 들어가기
					staminaLogic();
					// fd.FloorInfo(uservo.get진행중플로어()); //vo에 층별 아이템및에너미값 초기화

				} else if (selectMenu == 3) { // 오른쪽 방 들어가기
					staminaLogic();
				} else if (selectMenu == 4) { // 올라가기
					uservo.set진행중플로어(uservo.get진행중플로어() + 1);
					staminaLogic();

					if (uservo.get진행중플로어() == 100) {
						selectFloorMenu();
					} else {
						progressMenu();

					}

				} else if (selectMenu == 5) { // 엘리베이터

				} else if (selectMenu == 6) {

				} else if (selectMenu == 7) {

				}
			}

		} catch (InputMismatchException e) {
			System.out.println("잘못입력");

		} finally {
			progressMenu();
		}

	}

	// 이동시 마다 스태미너 감소
	@Override
	public void staminaLogic() {

		if (uservo.get스태미너() > 0) {
			uservo.set스태미너(uservo.get스태미너() - 1);

		} else if (uservo.get스태미너() < 0) { // 스태미너가 0보다 작다면 체력 -2감소
			uservo.set스태미너(0);
			uservo.set체력(uservo.get체력() - 2);
		}

	}

}
