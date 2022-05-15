package yedam.game.skyhill.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import yedam.game.skyhill.VO.EnemyVO;
import yedam.game.skyhill.VO.FloorInfoVO;
import yedam.game.skyhill.VO.FoodsVO;
import yedam.game.skyhill.VO.InventoryVO;
import yedam.game.skyhill.VO.KitItemsVO;
import yedam.game.skyhill.VO.UserVO;
import yedam.game.skyhill.VO.WeaponVO;
import yedam.game.skyhill.VO.leftfloorinfoVO;
import yedam.game.skyhill.VO.rightfloorinfoVO;

public class GameStartImpl implements GameStartService {

	UserVO uservo = new UserVO();
	WeaponVO weaponvo = new WeaponVO();
	FloorData fd = new FloorData(); // 층별 데이터 객체
	ItemsImpl items = new ItemsImpl();
	UserImpl userimpl = new UserImpl();
	EnemyImpl enemy = new EnemyImpl();
	List<FoodsVO> foodlist = new ArrayList<FoodsVO>();
	List<FloorInfoVO> floorlist = new ArrayList<FloorInfoVO>();
	List<leftfloorinfoVO> leftfloorlist = new ArrayList<leftfloorinfoVO>();
	List<rightfloorinfoVO> rightfloorlist = new ArrayList<rightfloorinfoVO>();
	List<InventoryVO> inventorylist = new ArrayList<InventoryVO>();
	List<KitItemsVO> kititemslist = new ArrayList<KitItemsVO>();
	List<WeaponVO> weaponlist = new ArrayList<WeaponVO>();

	// 컬럼 초기화 메소드
	@Override
	public void clearColumn() {
		fd.floorCheckClear();
		fd.leftFloorCheckClear();
		fd.rightFloorCheckClear();
		userimpl.deleteAllInventory();
		userimpl.AllWeaponsUpdate0();

		System.out.println("컬럼초기화");
	}

	// 엘레베이터 10층단위로 생성
	@Override
	public void setElevator() {
		fd.clearElevator();// 컬럼값 초기화

		for (int i = 99; i > 1; i -= 10) {
			int ran = (int) ((Math.random() * 4) + 1);
			// System.out.println("받은 랜덤 숫자 > "+ran);
			int a[] = new int[ran];
			for (int j = 0; j < ran; j++) {
				int ranFloorNum = (int) (Math.random() * 9) + 1;
				int tenNumber = ((i / 10) * 10) + ranFloorNum;
				a[j] = tenNumber;
				for (int k = 0; k < j; k++) {
					if (a[k] == a[j]) {
						j--;
					}
				}
			}
			for (int q = 0; q < a.length; q++) {
				int floor = a[q];

				fd.updateElevator(floor);

			}
		}

		System.out.println("엘리베이터 세팅 완료");
	}

	// 스테이터스
	@Override
	public void getStatus() {
		System.out.println("\t\t\t\t\t ─────────────");
		System.out.println("\t\t\t\t\t  현재 층 : " + uservo.get진행중플로어());
		System.out.println("\t\t\t\t\t ─────────────");
		System.out.printf("\t\t\t\t\t\t\t\t 체력 : %.1f\n " , uservo.get체력());
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
		System.out.println("1.내려가기 2.수면 3.현재 스테이터스 확인 4.현재 진행상황 보기");
		System.out.print("선택 >>");
		try {
			int selectFloorMenu = sc.nextInt();

			while (true) {
				if (selectFloorMenu == 1) { // 내려가기
					staminaLogic(); // 스태미너 감소
					uservo.set진행중플로어(uservo.get시작플로어() - 1);
					floorlist = fd.FloorInfo(uservo.get진행중플로어()); // 층별셀렉트값 넘겨줌

					// 아이템 메소드 작성

					// 자판기or 엘리베이터 작성

					progressMenu(); // 진행 실행
				} else if (selectFloorMenu == 2) { // 수면
					goSleep(uservo);
				} else if (selectFloorMenu == 3) { // 스테이터스
					getUserStatus(uservo);
				} else if (selectFloorMenu == 4) { // 진행상황 보기

				}
			}

		} catch (InputMismatchException e) {
			System.out.println("잘못입력");

		} finally {
			selectFloorMenu();
		}

	}

	private void goSleep(UserVO uservo) {
		Scanner sc = new Scanner(System.in);
		System.out.println("시간당 스태미너 -15 체력 + 20 회복 합니다.");
		System.out.println("────────수면 시간 선택──────  ");
		System.out.println("1. 1시간 2. 2시간 0.뒤로가기");
		int selectSleep = sc.nextInt();
		
		if(selectSleep == 1) {
			if(uservo.get스태미너() >= 15) {
				System.out.println("회복량 > 스태미너-15 체력+ 20");
				uservo.set체력(uservo.get체력()+20);
				uservo.set스태미너(uservo.get스태미너()-15);
				selectFloorMenu();	
			} else {
				System.out.println("현재 스태미너가 부족합니다.");
				selectFloorMenu();	
			}
		} else if (selectSleep == 2) {
			if(uservo.get스태미너() >= 30) {
				System.out.println("회복량 > 스태미너-30 체력+ 40");
				uservo.set체력(uservo.get체력()+40);
				uservo.set스태미너(uservo.get스태미너()-30);
				selectFloorMenu();
				
			} else {
				System.out.println("현재 스태미너가 부족합니다.");
				selectFloorMenu();	
			}
		}  else if (selectSleep == 0) {
			selectFloorMenu();
		}
		
		
	}

	private void getUserStatus(UserVO uservo) {
		Scanner sc = new Scanner(System.in);
		int usingCheckIndex = -1;
		boolean usingCheck = false;

		inventorylist = userimpl.inventoryWeaponSelect(); // 조인한 결과 값담은 리스트

		// 착용중인 장비가 있다면 착용중인 장비 인덱스값 저장
		for (int i = 0; i < inventorylist.size(); i++) {
			if (inventorylist.get(i).getWeaponvo().getUsecheck().equals("1")) {
				usingCheckIndex = i;
				usingCheck = true;
			}
		}
		System.out.println("┌───────────────────────────────────────┐");
		System.out.println("                user info              ");
		if (usingCheck) { // 착용중 장비가 있다면
			System.out.printf("  착용중 장비 : %s                        ", inventorylist.get(usingCheckIndex).getName());
		} else if (!usingCheck) {
			System.out.printf("  착용중 장비 : 없음                       ");
		}
		System.out.println();
		System.out.printf("  현재 공격력 > %s                          ", uservo.get공격력());
		System.out.println();
		System.out.printf("  Str > %s                           ", uservo.get강도());
		System.out.println();
		System.out.printf("  Spd > %s                           ", uservo.get속도());
		System.out.println();
		System.out.printf("  Dex > %s                           ", uservo.get민첩());
		System.out.println();
		System.out.printf("  Accuracy > %s                      ", uservo.get명중());
		System.out.println();
		System.out.printf("  어빌리티포인트 > %d                      ", uservo.get어빌리티포인트());
		System.out.println();
		System.out.println("└───────────────────────────────────────┘");

		System.out.println("1. 스탯 배분 2.뒤로가기");
		int setStatusMenu = sc.nextInt();
		if (setStatusMenu == 1) {

			if (uservo.get어빌리티포인트() == 0) {
				System.out.println("어빌리티포인트가 없습니다.");
			} else if (uservo.get어빌리티포인트() != 0) {
				System.out.println("┌──────────증가시킬 스탯 선택───────────┐ ");
				int currentAP = uservo.get어빌리티포인트();
				for (int i = 0; i < currentAP; i++) {
					System.out.printf(" Str > %s \n Spd > %s \n Dex > %s \n Accuracy > %s\n", uservo.get강도(),
							uservo.get속도(), uservo.get민첩(), uservo.get명중());
					System.out.println("잔여 어빌리티포인트 : " + uservo.get어빌리티포인트());
					System.out.println("1.Str(공격력 증가) 2.Spd(추가공격확률증가) 3.Dex(회피율증가) 4.Accuracy(명중률증가)");

					System.out.print("입력 >");
					int setStatus = sc.nextInt();

					if (setStatus == 1) {
						uservo.set강도((uservo.get강도() + 1));
						uservo.set공격력(0.8 + uservo.get공격력());
						uservo.set어빌리티포인트(uservo.get어빌리티포인트() - 1);

					} else if (setStatus == 2) {
						uservo.set속도(uservo.get속도() + 1);
						uservo.set어빌리티포인트(uservo.get어빌리티포인트() - 1);
					} else if (setStatus == 3) {
						uservo.set민첩(uservo.get민첩() + 1);
						uservo.set어빌리티포인트(uservo.get어빌리티포인트() - 1);
					} else if (setStatus == 4) {
						uservo.set명중(uservo.get명중() + 1);
						uservo.set어빌리티포인트(uservo.get어빌리티포인트() - 1);
					}

				}

			}

		} else if (setStatusMenu == 2) {
			selectFloorMenu();
		}
		selectFloorMenu();
	}

	// 해당 층에 방문확인 체크
	private void checkFloor(String checkfloor) {
		// if(checkfloor =)

	}

	@Override
	public void progressMenu() {
		Scanner sc = new Scanner(System.in);
		getStatus();
		System.out.println("1.내려가기  2.왼쪽방 들어가기 3.오른쪽방 들어가기 4.올라가기 5.엘리베이터 6.내 인벤토리 확인");
		System.out.print("선택>>");
		try {
			int selectMenu = sc.nextInt();

			while (true) {
				if (selectMenu == 1) { // 내려가기
					staminaLogic(); // 스태미너 감소
					uservo.set진행중플로어(uservo.get진행중플로어() - 1);
					if (uservo.get진행중플로어() < uservo.get마지막플로어()) {
						uservo.set마지막플로어(uservo.get진행중플로어()); // 마지막 플로어 저장
					}
					floorlist = fd.FloorInfo(uservo.get진행중플로어()); // 층별셀렉트값 넘겨줌
					// 방문 한 적 없으면 생성 메소드 실행
					if (floorlist.get(0).getCheckfloor().equals("0")) {
						CreateEnemy(floorlist);
					}

					if (fd.checkFloor(floorlist.get(0).getFloorNum()) == 0) {
						fd.checkFloor(floorlist.get(0).getFloorNum());// 방문 체크
					}

					progressMenu();
				} else if (selectMenu == 2) { // 왼쪽방 들어가기

					
					leftfloorlist = fd.leftFloorInfo(uservo.get진행중플로어()); // 층별셀렉트값 넘겨줌

					if (leftfloorlist.get(0).getCheckfloor().equals("0")) { // 플로어체크가 0이면
						staminaLogic(); // 스태미너 감소
						leftfloormenu(leftfloorlist);
					} else if (leftfloorlist.get(0).getCheckfloor().equals("1")) {
						System.out.println("이미 입장한 방입니다.");
					}

					progressMenu();
				} else if (selectMenu == 3) { // 오른쪽 방 들어가기
					
					rightfloorlist = fd.rightFloorInfo(uservo.get진행중플로어()); // 층별셀렉트값 넘겨줌
					
					if(rightfloorlist.get(0).getCheckfloor().equals("0")) {
						staminaLogic();
						rightfloormenu(rightfloorlist);
					}else if (rightfloorlist.get(0).getCheckfloor().equals("1")) {
						System.out.println("이미 입장한 방입니다.");
					}
					progressMenu();
				} else if (selectMenu == 4) { // 올라가기
					uservo.set진행중플로어(uservo.get진행중플로어() + 1);
					staminaLogic();

					if (uservo.get진행중플로어() == 100) {
						selectFloorMenu();
					} else {
						progressMenu();

					}

				} else if (selectMenu == 5) { // 엘리베이터

					floorlist = fd.selectElevator(); //
					/*
					 * for(int i = 0; i < floorlist.size(); i++ ) {
					 * System.out.println("엘리베이터 생성 층 > " + floorlist.get(i).getFloorNum()); }
					 */
					// int a[] = new int[floorlist.size()];
					boolean checkelevator = false;
					for (FloorInfoVO vo : floorlist) {
						if (vo.getFloorNum() == uservo.get진행중플로어()) {
							checkelevator = true;
							break;
						}
					}
					for (FloorInfoVO vo : floorlist) {
						if (vo.getFloorNum() >= uservo.get마지막플로어()) {
							System.out.println("이용가능한 층 >" + vo.getFloorNum());
						}
					}

					if (checkelevator) {
						System.out.println("엘리베이터 이용 가능한 플로어입니다.");
						System.out.print("이동할 층 선택 >");
						int select = sc.nextInt();
						if (select < uservo.get마지막플로어()) {
							System.out.println("이동 불가능한 층");
						} else {
							uservo.set진행중플로어(select);
							progressMenu();
						}

					} else {
						System.out.println("엘리베이터가 부서져있다.");
					}

					progressMenu();
				} else if (selectMenu == 6) { // 인벤토리 확인
					System.out.println("현재 인벤토리 확인");
					inventorymenu();

					progressMenu();
				} else if (selectMenu == 7) {

				}
			}

		} catch (InputMismatchException e) {
			System.out.println("잘못입력");

		} finally {
			progressMenu();
		}

	}

	

	private void inventorymenu() {
		Scanner sc = new Scanner(System.in);
		inventorylist = userimpl.selectInventory();
		System.out.println("┌────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t 무        기            ");
		System.out.println("이름\t\t공격력\t\t등급\t\t소지 개수 ");
		for (int i = 0; i < inventorylist.size(); i++) {
			if (inventorylist.get(i).getCcode() == 1) {
				System.out.print(inventorylist.get(i).getName() + "\t\t  ");
				System.out.print(inventorylist.get(i).getEffect() + "\t\t ");
				System.out.print(inventorylist.get(i).getGrade() + "\t\t   ");
				System.out.println(inventorylist.get(i).getCount() + "\t\t  ");
			}
		}
		System.out.println("└────────────────────────────────────────────────────────────┘");

		System.out.println("┌────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t 음        식            ");
		System.out.println("이름\t\t회복량\t\t등급\t\t소지 개수 ");
		for (int i = 0; i < inventorylist.size(); i++) {
			if (inventorylist.get(i).getCcode() == 3) {
				System.out.print(inventorylist.get(i).getName() + "\t\t  ");
				System.out.print(inventorylist.get(i).getEffect() + "\t\t ");
				System.out.print(inventorylist.get(i).getGrade() + "\t\t   ");
				System.out.println(inventorylist.get(i).getCount() + "\t\t  ");
			}
		}
		System.out.println("└────────────────────────────────────────────────────────────┘");

		System.out.println("┌────────────────────────────────────────────────────────────┐");
		System.out.println("\t\t\t 응   급   도  구          ");
		System.out.println("이름\t\t회복량\t\t등급\t\t소지 개수 ");
		for (int i = 0; i < inventorylist.size(); i++) {
			if (inventorylist.get(i).getCcode() == 4) {
				if (inventorylist.get(i).getName().length() > 4) {
					System.out.print(inventorylist.get(i).getName() + "\t  ");
				} else {
					System.out.print(inventorylist.get(i).getName() + "\t\t  ");

				}
				System.out.print(inventorylist.get(i).getEffect() + "\t\t ");
				System.out.print(inventorylist.get(i).getGrade() + "\t\t   ");
				System.out.println(inventorylist.get(i).getCount() + "\t\t  ");
			}
		}

		System.out.println("└────────────────────────────────────────────────────────────┘");
		System.out.println("1.뒤로가기 2.아이템사용 3.장비착용");
		System.out.print("선택 >");
		int inventoryMenu = sc.nextInt();

		while (true) {
			if (inventoryMenu == 1) { // 뒤로가기1
				progressMenu();
			} else if (inventoryMenu == 2) { // 아이템사용
				selectinventoryitem(inventorylist);
				inventorymenu();
			} else if (inventoryMenu == 3) { // 장비착용
				int usingCheckIndex = -1;
				boolean usingCheck = false;

				inventorylist = userimpl.inventoryWeaponSelect(); // 조인한 결과 값담은 리스트

				// 착용중인 장비가 있다면 착용중인 장비 인덱스값 저장
				for (int i = 0; i < inventorylist.size(); i++) {
					if (inventorylist.get(i).getWeaponvo().getUsecheck().equals("1")) {
						usingCheckIndex = i;
						usingCheck = true;
					}
				}

				if (usingCheck) { // 사용중인 장비가 있다면

					System.out.println("현재 착용중인 장비 > " + inventorylist.get(usingCheckIndex).getName());
					System.out.print("착용할 장비 선택 >");
					String selectEquipment = sc.next();

					for (int i = 0; i < inventorylist.size(); i++) {

						if (inventorylist.get(i).getName().equals(selectEquipment)) { // 입력받은 장비 있는지 비교
							System.out.println("현재 공격력 : " + uservo.get공격력());
							uservo.set공격력(uservo.get공격력() - inventorylist.get(usingCheckIndex).getEffect());
							uservo.set공격력(uservo.get공격력() + inventorylist.get(i).getEffect());
							System.out.println("착용 후 공격력 : " + uservo.get공격력());
							userimpl.useUpdateWeapon(inventorylist.get(usingCheckIndex).getName(), 0);// 착용해제
							userimpl.useUpdateWeapon(inventorylist.get(i).getName(), 1);// 사용체크
							break;
						} else if (i == inventorylist.size()-1) {
							System.out.println(selectEquipment + "(이)가 없습니다.");
						}

					}

				} else if (!usingCheck) { // 사용중인 장비가 없다면

					System.out.print("착용할 장비 선택 >");
					String selectEquipment = sc.next();

					for (int i = 0; i < inventorylist.size(); i++) {

						if (inventorylist.get(i).getName().equals(selectEquipment)) { // 입력받은 장비가 있는지 비교
							System.out.println("현재 공격력" + uservo.get공격력());
							uservo.set공격력(uservo.get공격력() + inventorylist.get(i).getWeaponvo().getEffect());
							System.out.println("착용 후 공격력 " + uservo.get공격력());
							userimpl.useUpdateWeapon(inventorylist.get(i).getName(), 1);// 사용체크
							break;
						} else if (i == inventorylist.size()-1) {
							System.out.println(selectEquipment + "(이)가 없습니다.");
						}

					}
				}

				// weaponvo = userimpl.currentUsedWeapon(); //현재 착용중인 장비 가지고오기

				inventorymenu();
			}
		} // 반복문 종료

	}

	// 인벤토리 아이템 선택 메소드
	private void selectinventoryitem(List<InventoryVO> inventorylist) {
		Scanner sc = new Scanner(System.in);
		System.out.print("사용 할 아이템 선택 > ");
		String selectitem = sc.next();

		for (int i = 0; i < inventorylist.size(); i++) {
			if (inventorylist.get(i).getName().equals(selectitem) && // 이름이같고
					inventorylist.get(i).getCount() > 1) { // 카운트가 1초과면
				userimpl.minousCountitems(selectitem, inventorylist.get(i).getCcode());
				System.out.println(selectitem + " 사용");
				if (inventorylist.get(i).getCcode() == 3) { // 음식
					System.out.println("스태미나 " + inventorylist.get(i).getEffect() + "만큼 증가!");
					uservo.set스태미너(uservo.get스태미너() + inventorylist.get(i).getEffect());
				} else if (inventorylist.get(i).getCcode() == 4) { // 응급도구
					System.out.println("체력 : " + inventorylist.get(i).getEffect() + "만큼 증가!");
					uservo.set체력(uservo.get체력() + inventorylist.get(i).getEffect());
				}
			} else if (inventorylist.get(i).getName().equals(selectitem) && // 이름이 같고
					inventorylist.get(i).getCount() == 1) { // 소지수가 1이면
				userimpl.deleteitems(selectitem, inventorylist.get(i).getCcode());
				System.out.println(selectitem + " 삭제");
				if (inventorylist.get(i).getCcode() == 3) { // 음식
					System.out.println("스태미나 " + inventorylist.get(i).getEffect() + "만큼 증가!");
					uservo.set스태미너(uservo.get스태미너() + inventorylist.get(i).getEffect());
				} else if (inventorylist.get(i).getCcode() == 4) { // 응급도구
					System.out.println("체력 : " + inventorylist.get(i).getEffect() + "만큼 증가!");
					uservo.set체력(uservo.get체력() + inventorylist.get(i).getEffect());
				}
			}
		}

	}

	// 왼쪽방 메뉴
	private void leftfloormenu(List<leftfloorinfoVO> leftfloorlist) {
		Scanner sc = new Scanner(System.in);
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│                              │");
		System.out.println("│  Left Room                   │");
		System.out.println("│                              │");
		System.out.println("│                              │");
		System.out.println("│                              │");
		System.out.println("│                              │");
		System.out.println("│                              │");
		System.out.println("└──────────────────────────────┘");

		int itemcount = (int) ((Math.random() * 3) + 1);
		if (leftfloorlist.get(0).getCheckfloor().equals("0")) {
			// 적 생성 메소드 실행
			CreateEnemyleft(leftfloorlist);
			for(int i = 0; i < itemcount; i++) {
				dropitems();
				
			}

		}

		System.out.println();
		System.out.println("1.밖으로 나가기");
		System.out.print("입력>");
		int selectNum = sc.nextInt();

		if (selectNum == 1) {
			// 방 체크 메소드 작성
			if (leftfloorlist.get(0).getCheckfloor().equals("0")) {
				fd.leftCheckFloor(leftfloorlist.get(0).getFloorNum());
			}
			progressMenu();
		} else {
			System.out.println("잘못 입력");
			if (leftfloorlist.get(0).getCheckfloor().equals("0")) {
				fd.leftCheckFloor(leftfloorlist.get(0).getFloorNum());
			}
			progressMenu();
		}
	}
	
	//오른쪽방 메뉴 
	private void rightfloormenu(List<rightfloorinfoVO> rightfloorlist) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t\t\t\t\t\t┌──────────────────────────────┐");
		System.out.println("\t\t\t\t\t\t\t\t│                              │");
		System.out.println("\t\t\t\t\t\t\t\t│                 right Room   │");
		System.out.println("\t\t\t\t\t\t\t\t│                              │");
		System.out.println("\t\t\t\t\t\t\t\t│                              │");
		System.out.println("\t\t\t\t\t\t\t\t│                              │");
		System.out.println("\t\t\t\t\t\t\t\t│                              │");
		System.out.println("\t\t\t\t\t\t\t\t│                              │");
		System.out.println("\t\t\t\t\t\t\t\t└──────────────────────────────┘");

		int itemcount = (int) ((Math.random() * 3) + 1);
		if (rightfloorlist.get(0).getCheckfloor().equals("0")) {
			// 적 생성 메소드 실행
			CreateEnemyRight(rightfloorlist);
			dropitems();

		}
		System.out.println();
		System.out.println("1.밖으로 나가기");
		System.out.print("입력>");
		int selectNum = sc.nextInt();

		if (selectNum == 1) {
			// 방 체크 메소드 작성
			if (rightfloorlist.get(0).getCheckfloor().equals("0")) {
				fd.rightCheckFloor(rightfloorlist.get(0).getFloorNum());
			}
			progressMenu();
		} else {
			System.out.println("잘못 입력");
			if (rightfloorlist.get(0).getCheckfloor().equals("0")) {
				fd.rightCheckFloor(rightfloorlist.get(0).getFloorNum());
			}
			progressMenu();
		}

		
	}

	

	private void dropitems() {
		Random ran = new Random();
		foodlist = getFoods(); // 모든 음식정보 담은 리스트
		kititemslist = items.getKitItems(); // 모든 응급도구 정보
		weaponlist = items.getWeapons(); // 모든 무기 정보
		inventorylist = userimpl.selectInventory(); // 검색한 인벤토리리스트 담아줌

		String kititems = "kititems";
		String foods = "foods";
		String weapons = "weapons";
		// for (int i = 0; i < foodlist.size(); i++) {
		// 푸드 테이블의 수만큼 랜덤 수 생성\
		int Arandom = 0;
		// boolean foodcheck = false;
		Arandom = (int) ((Math.random() * foodlist.size()));
		int itemcode = Arandom;
		if (foodlist.get(itemcode).getGrade().equals("C")) {
			// System.out.println("1."+itemcode);
			if (ran.nextInt(100) < 20) {// 30%확률
				if (check(itemcode, foodlist.get(itemcode).getCcode(), inventorylist)) { // 기존에 있따면
					int update = userimpl.inventoryUpdateitems(itemcode, foodlist.get(itemcode).getCcode());

					if (update != 0) {
						for (int i = 0; i < foodlist.size(); i++) {
							if (foodlist.get(i).getItemcode() == Arandom) {
								System.out.println(" >" + foodlist.get(i).getName() + "+1 추가");
								// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
							}
						}
					} else if (update == 0) {
						System.out.println("1.");
						System.out.println("업데이트를 실패하셨습니다.");
					}
				} else if (!check(itemcode, foodlist.get(itemcode).getCcode(), inventorylist)) {
					userimpl.Insertitems(itemcode, foods);
					for (int i = 0; i < foodlist.size(); i++) {
						if (foodlist.get(i).getItemcode() == Arandom) {
							System.out.println(">" + foodlist.get(i).getName() + "+1 획득");
							// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
						}
					}
				}
			}
		} else if (foodlist.get(itemcode).getGrade().equals("B")) {
			if (ran.nextInt(100) < 10) {// 20%확률
				if (check(itemcode, foodlist.get(itemcode).getCcode(), inventorylist)) { // 기존인벤토리에 있따면
					int update = userimpl.inventoryUpdateitems(itemcode, foodlist.get(itemcode).getCcode());

					if (update != 0) {
						for (int i = 0; i < foodlist.size(); i++) {
							if (foodlist.get(i).getItemcode() == Arandom) {
								System.out.println(" >" + foodlist.get(i).getName() + "+1 추가");
								// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
							}
						}
					} else if (update == 0) {
						System.out.println("2.");
						System.out.println("업데이트를 실패하셨습니다.");
					}
				} else if (!check(itemcode, foodlist.get(itemcode).getCcode(), inventorylist)) {
					userimpl.Insertitems(itemcode, foods);
					for (int i = 0; i < foodlist.size(); i++) {
						if (foodlist.get(i).getItemcode() == Arandom) {
							System.out.println(">" + foodlist.get(i).getName() + "+1 획득");
							// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
						}
					}
				}
			}
		} else if (foodlist.get(itemcode).getGrade().equals("A")) {
			if (ran.nextInt(100) < 5) {// 10%확률
				if (check(itemcode, foodlist.get(itemcode).getCcode(), inventorylist)) {
					int update = userimpl.inventoryUpdateitems(itemcode, foodlist.get(itemcode).getCcode());

					if (update != 0) {
						for (int i = 0; i < foodlist.size(); i++) {
							if (foodlist.get(i).getItemcode() == Arandom) {
								System.out.println(" >" + foodlist.get(i).getName() + "+1 추가");
								// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
							}
						}
					} else if (update == 0) {
						System.out.println("3.");
						System.out.println("업데이트를 실패하셨습니다.");
					}
				} else if (!check(itemcode, foodlist.get(itemcode).getCcode(), inventorylist)) {
					userimpl.Insertitems(itemcode, foods);
					for (int i = 0; i < foodlist.size(); i++) {
						if (foodlist.get(i).getItemcode() == Arandom) {
							System.out.println(">" + foodlist.get(i).getName() + "+1 획득");
							// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
						}
					}

				}

			}
		}

		// 킷 테이블의 수만큼 랜덤 수 생성
		Arandom = 0;
		// boolean foodcheck = false;
		Arandom = (int) ((Math.random() * kititemslist.size()));

		itemcode = Arandom;

		if (kititemslist.get(itemcode).getGrade().equals("C")) {
			// System.out.println("1."+itemcode);
			if (ran.nextInt(100) < 20) {// 30%확률
				if (check(itemcode, kititemslist.get(itemcode).getCcode(), inventorylist)) {
					int update = userimpl.inventoryUpdateitems(itemcode, kititemslist.get(itemcode).getCcode());

					if (update != 0) {
						for (int i = 0; i < kititemslist.size(); i++) {
							if (kititemslist.get(i).getItemcode() == Arandom) {
								System.out.println(" >" + kititemslist.get(i).getName() + "+1 추가");
								// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
							}
						}
					} else if (update == 0) {
						System.out.println("업데이트를 실패하셨습니다.");
					}
				} else if (!check(itemcode, kititemslist.get(itemcode).getCcode(), inventorylist)) {
					userimpl.Insertitems(itemcode, kititems);
					for (int i = 0; i < kititemslist.size(); i++) {
						if (kititemslist.get(i).getItemcode() == Arandom) {
							System.out.println(">" + kititemslist.get(i).getName() + "+1 획득");
							// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
						}
					}
				}
			}
		} else if (kititemslist.get(itemcode).getGrade().equals("B")) {
			if (ran.nextInt(100) < 10) {// 30%확률
				if (check(itemcode, kititemslist.get(itemcode).getCcode(), inventorylist)) {
					int update = userimpl.inventoryUpdateitems(itemcode, kititemslist.get(itemcode).getCcode());

					if (update != 0) {
						for (int i = 0; i < kititemslist.size(); i++) {
							if (kititemslist.get(i).getItemcode() == Arandom) {
								System.out.println(" >" + kititemslist.get(i).getName() + "+1 추가");
								// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
							}
						}
					} else if (update == 0) {
						System.out.println("5.");
						System.out.println("아이템이름 > " + kititemslist.get(itemcode).getName());
						System.out.println("업데이트를 실패하셨습니다.");
					}
				} else if (!check(itemcode, kititemslist.get(itemcode).getCcode(), inventorylist)) {
					userimpl.Insertitems(itemcode, kititems);
					for (int i = 0; i < kititemslist.size(); i++) {
						if (kititemslist.get(i).getItemcode() == Arandom) {
							System.out.println(">" + kititemslist.get(i).getName() + "+1 획득");
							// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
						}
					}
				}
			}
		} else if (kititemslist.get(itemcode).getGrade().equals("A")) {
			if (ran.nextInt(100) < 5) {// 30%확률
				if (check(itemcode, kititemslist.get(itemcode).getCcode(), inventorylist)) {
					int update = userimpl.inventoryUpdateitems(itemcode, kititemslist.get(itemcode).getCcode());

					if (update != 0) {
						for (int i = 0; i < kititemslist.size(); i++) {
							if (kititemslist.get(i).getItemcode() == Arandom) {
								System.out.println(" >" + kititemslist.get(i).getName() + "+1 추가");
								// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
							}
						}
					} else if (update == 0) {
						System.out.println("6.");
						System.out.println("업데이트를 실패하셨습니다.");
					}
				} else if (!check(itemcode, kititemslist.get(itemcode).getCcode(), inventorylist)) {
					userimpl.Insertitems(itemcode, kititems);
					for (int i = 0; i < kititemslist.size(); i++) {
						if (kititemslist.get(i).getItemcode() == Arandom) {
							System.out.println(">" + kititemslist.get(i).getName() + "+1 획득");
							// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
						}
					}

				}

			}
		}

		// 무기 테이블의 수만큼 랜덤 수 생성
		Arandom = 0;
		Arandom = (int) ((Math.random() * weaponlist.size()));
		itemcode = Arandom;
		if (weaponlist.get(itemcode).getGrade().equals("C")) {
			// System.out.println("1."+itemcode);
			if (ran.nextInt(100) < 20) {// 30%확률
				if (check(itemcode, weaponlist.get(itemcode).getCcode(), inventorylist)) {
					int update = userimpl.inventoryUpdateitems(itemcode, weaponlist.get(itemcode).getCcode());

					if (update != 0) {
						for (int i = 0; i < weaponlist.size(); i++) {
							if (weaponlist.get(i).getItemcode() == Arandom) {
								System.out.println(" >" + weaponlist.get(i).getName() + "+1 추가");
								// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
							}
						}
					} else if (update == 0) {
						System.out.println("업데이트를 실패하셨습니다.");
					}
				} else if (!check(itemcode, weaponlist.get(itemcode).getCcode(), inventorylist)) {
					userimpl.Insertitems(itemcode, weapons);
					for (int i = 0; i < weaponlist.size(); i++) {
						if (weaponlist.get(i).getItemcode() == Arandom) {
							System.out.println(">" + weaponlist.get(i).getName() + "+1 획득");
							// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
						}
					}
				}
			}
		}else if (weaponlist.get(itemcode).getGrade().equals("B")) {
			if (ran.nextInt(100) < 10) {// 30%확률
				if (check(itemcode, weaponlist.get(itemcode).getCcode(), inventorylist)) {
					int update = userimpl.inventoryUpdateitems(itemcode, weaponlist.get(itemcode).getCcode());

					if (update != 0) {
						for (int i = 0; i < weaponlist.size(); i++) {
							if (weaponlist.get(i).getItemcode() == Arandom) {
								System.out.println(" >" + weaponlist.get(i).getName() + "+1 추가");
								// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
							}
						}
					} else if (update == 0) {
						System.out.println("5.");
						System.out.println("아이템이름 > " + weaponlist.get(itemcode).getName());
						System.out.println("업데이트를 실패하셨습니다.");
					}
				} else if (!check(itemcode, weaponlist.get(itemcode).getCcode(), inventorylist)) {
					userimpl.Insertitems(itemcode, kititems);
					for (int i = 0; i < weaponlist.size(); i++) {
						if (weaponlist.get(i).getItemcode() == Arandom) {
							System.out.println(">" + weaponlist.get(i).getName() + "+1 획득");
							// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
						}
					}
				}
			}
		}

	}

	// 음식 목록 전체 조회
	private List<FoodsVO> getFoods() {
		foodlist = items.getFoods();
		/*
		 * for (int i = 0; i < foodlist.size(); i++) {
		 * System.out.println(foodlist.get(i).getName()); }
		 */
		return foodlist;
	}

	// 왼쪽방 적 생성 메소드
	private void CreateEnemyleft(List<leftfloorinfoVO> leftfloorlist) {
		BattleImpl battle = new BattleImpl();
		int enemyInCount = (int) (Math.random() * 2) + 1;
		for (int i = 0; i < leftfloorlist.size(); i++) {
			// 적 출현 메소드 작성 업데이트 쿼리
			if (leftfloorlist.get(i).getCheckfloor().equals("0")) { // 방문없으면 
				// int ran = (int)(Math.random()*30)+1;
				Random ran = new Random();
				if (ran.nextInt(100) < 20) {// 20%확률 //에너미 인카운트 변경
					enemy.enemyInCount(leftfloorlist.get(i).getFloorNum());
					System.out.println("적 출현");

					if (uservo.get진행중플로어() / 10 == 9 || // 90층대 80층대 70층대
							uservo.get진행중플로어() / 10 == 8 || uservo.get진행중플로어() / 10 == 7) {
						if (enemyInCount == 1) {
							EnemyVO enemyvo = new EnemyVO("이웃집괴물", 20, 6, 20, 60,10);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						} else if (enemyInCount == 2) {
							EnemyVO enemyvo = new EnemyVO("졸병", 25, 7, 25, 65,10);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						}

					} else if(uservo.get진행중플로어() / 10 == 6 || uservo.get진행중플로어() / 10 == 5 ||
							uservo.get진행중플로어() / 10 == 4) {
						if (enemyInCount == 1) {
							EnemyVO enemyvo = new EnemyVO("포효자", 40, 10, 45, 70,15);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						} else if (enemyInCount == 2) {
							EnemyVO enemyvo = new EnemyVO("균형이 무너진 자", 50, 10, 50, 80,19);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						}
					} else if(uservo.get진행중플로어() / 10 == 3 || uservo.get진행중플로어() / 10 == 2 ||
							uservo.get진행중플로어() / 10 == 1 ) {
						if (enemyInCount == 1) {
							EnemyVO enemyvo = new EnemyVO("시너", 80, 15, 100, 90,24);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						} else if (enemyInCount == 2) {
							EnemyVO enemyvo = new EnemyVO("균형이 무너진 자", 90, 16, 100, 90,30);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						}
					}
				}
			}

		}	}

	//오른쪽 방 적 생성 메소드
	private void CreateEnemyRight(List<rightfloorinfoVO> rightfloorlist) {
		BattleImpl battle = new BattleImpl();
		int enemyInCount = (int) (Math.random() * 2) + 1;
		for (int i = 0; i < rightfloorlist.size(); i++) {
			// 적 출현 메소드 작성 업데이트 쿼리
			if (rightfloorlist.get(i).getCheckfloor().equals("0")) { // 방문한 적 없다면 
				// int ran = (int)(Math.random()*30)+1;
				Random ran = new Random();
				if (ran.nextInt(100) < 20) {// 20%확률 //에너미 인카운트 변경
					enemy.enemyInCount(rightfloorlist.get(i).getFloorNum());
					System.out.println("\t\t\t\t┌────────────────────────┐");
					System.out.println("\t\t\t\t\t 적 출현");
					System.out.println("\t\t\t\t└────────────────────────┘");

					if (uservo.get진행중플로어() / 10 == 9 || // 90층대 80층대 70층대
							uservo.get진행중플로어() / 10 == 8 || uservo.get진행중플로어() / 10 == 7) {
						if (enemyInCount == 1) {
							EnemyVO enemyvo = new EnemyVO("이웃집괴물", 20, 6, 20, 60,10);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						} else if (enemyInCount == 2) {
							EnemyVO enemyvo = new EnemyVO("졸병", 25, 7, 25, 65,10);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						}

					} else if(uservo.get진행중플로어() / 10 == 6 || uservo.get진행중플로어() / 10 == 5 ||
							uservo.get진행중플로어() / 10 == 4) {
						if (enemyInCount == 1) {
							EnemyVO enemyvo = new EnemyVO("포효자", 40, 10, 45, 70,15);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						} else if (enemyInCount == 2) {
							EnemyVO enemyvo = new EnemyVO("균형이 무너진 자", 50, 10, 50, 80,19);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						}
					} else if(uservo.get진행중플로어() / 10 == 3 || uservo.get진행중플로어() / 10 == 2 ||
							uservo.get진행중플로어() / 10 == 1 ) {
						if (enemyInCount == 1) {
							EnemyVO enemyvo = new EnemyVO("시너", 80, 15, 100, 90,24);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						} else if (enemyInCount == 2) {
							EnemyVO enemyvo = new EnemyVO("균형이 무너진 자", 90, 16, 100, 90,30);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						}
					}
				}
			}

		}

		
	}
	
	
	// 적 생성 메소드
	private void CreateEnemy(List<FloorInfoVO> floorlist) {
		BattleImpl battle = new BattleImpl();
		int enemyInCount = (int) (Math.random() * 2) + 1;
		for (int i = 0; i < floorlist.size(); i++) {
			// 적 출현 메소드 작성 업데이트 쿼리
			if (floorlist.get(i).getCheckfloor().equals("0") // 방문한 적 없고
					&& floorlist.get(i).getEnemyInCount().equals("0")) { // 에너미카운터 0
				// int ran = (int)(Math.random()*30)+1;
				Random ran = new Random();
				if (ran.nextInt(100) < 20) {// 20%확률 //에너미 인카운트 변경
					enemy.enemyInCount(floorlist.get(i).getFloorNum());
					System.out.println("\t\t\t\t┌────────────────────────┐");
					System.out.println("\t\t\t\t\t 적 출현");
					System.out.println("\t\t\t\t└────────────────────────┘");

					if (uservo.get진행중플로어() / 10 == 9 || // 90층대 80층대 70층대
							uservo.get진행중플로어() / 10 == 8 || uservo.get진행중플로어() / 10 == 7) {
						if (enemyInCount == 1) {
							EnemyVO enemyvo = new EnemyVO("이웃집괴물", 20, 6, 20, 60,10);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						} else if (enemyInCount == 2) {
							EnemyVO enemyvo = new EnemyVO("졸병", 25, 7, 25, 65,10);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						}

					} else if(uservo.get진행중플로어() / 10 == 6 || uservo.get진행중플로어() / 10 == 5 ||
							uservo.get진행중플로어() / 10 == 4) {
						if (enemyInCount == 1) {
							EnemyVO enemyvo = new EnemyVO("포효자", 40, 10, 45, 70,15);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						} else if (enemyInCount == 2) {
							EnemyVO enemyvo = new EnemyVO("균형이 무너진 자", 50, 10, 50, 80,19);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						}
					} else if(uservo.get진행중플로어() / 10 == 3 || uservo.get진행중플로어() / 10 == 2 ||
							uservo.get진행중플로어() / 10 == 1 ) {
						if (enemyInCount == 1) {
							EnemyVO enemyvo = new EnemyVO("시너", 80, 15, 100, 90,24);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						} else if (enemyInCount == 2) {
							EnemyVO enemyvo = new EnemyVO("균형이 무너진 자", 90, 16, 100, 90,30);

							battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

						}
					}
				}
			}

		}
	}

	// 이동시 마다 스태미너 감소
	@Override
	public void staminaLogic() {

		if (uservo.get스태미너() > 0) {
			uservo.set스태미너(uservo.get스태미너() - 1);

		} else if (uservo.get스태미너() <= 0) { // 스태미너가 0보다 같거나작다면 체력 -2감소
			uservo.set스태미너(0);
			uservo.set체력(uservo.get체력() - 2);
			if (uservo.get체력() <= 0) {
				System.out.println("\t\t\t\t You Die!!");
				System.exit(0);
			}
		}

	}

	public boolean check(int itemcode, int ccode, List<InventoryVO> inventorylist2) {
		boolean foodcheck = false;
		// System.out.println("인벤토리리스트 사이즈 > " + inventorylist2.size());
		for (int i = 0; i < inventorylist2.size(); i++) {
			if (inventorylist2.get(i).getItemcode() == (itemcode) && inventorylist2.get(i).getCcode() == (ccode)) {

				foodcheck = true;
				break;
			} else {
				foodcheck = false;
			}
		}

		return foodcheck;
	}

}
