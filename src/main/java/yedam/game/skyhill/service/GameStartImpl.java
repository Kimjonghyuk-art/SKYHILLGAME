package yedam.game.skyhill.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import yedam.game.skyhill.VO.UserVO;

public class GameStartImpl implements GameStartService {
	
	UserVO uservo = new UserVO();

	//스테이터스 
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
	
	//100층 플로어 메뉴 
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
					uservo.set진행중플로어(uservo.get시작플로어()-1);
					staminaLogic(); //스태미너 감소
					progressMenu(); // 진행 실행
				} else if (selectFloorMenu == 2) { // 장비제작
					
				} else if (selectFloorMenu == 3) { // 음식제작
					
				} else if (selectFloorMenu == 4) { // 업그레이드
					
				} else if (selectFloorMenu == 5) { // 수면제작
					
				} else if (selectFloorMenu == 6) { //현재스테이터스
					
				} else if (selectFloorMenu == 7) { // 현재 진행상황보기
					
				}
			}

		} catch (InputMismatchException e) {
			System.out.println("잘못입력");

		} finally {
			selectFloorMenu();
		}

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
					uservo.set진행중플로어(uservo.get진행중플로어()-1);
					staminaLogic();
					progressMenu();
				} else if (selectMenu == 2) { //왼쪽방 들어가기
					staminaLogic();
					
				} else if (selectMenu == 3) { //오른쪽 방 들어가기
					staminaLogic();
				} else if (selectMenu == 4) { // 올라가기
					staminaLogic();
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

	
	//이동시 마다 스태미너 감소 
	@Override
	public void staminaLogic() {
		
		if(uservo.get스태미너() >= 0) {
			uservo.set스태미너(uservo.get스태미너()-1);
			
		} else if(uservo.get스태미너() < 0) { //스태미너가 0보다 작다면 체력 -2감소 
			uservo.set체력(uservo.get체력()-2);
		}
		
	}








}
