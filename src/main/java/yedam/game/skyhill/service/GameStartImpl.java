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
import yedam.game.skyhill.VO.leftfloorinfoVO;
import yedam.game.skyhill.VO.rightfloorinfoVO;

public class GameStartImpl implements GameStartService {

	UserVO uservo = new UserVO();
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

					// for (int i = 0; i < floorlist.size(); i++) {
					// 방문 체크 이프문
					//// if (floorlist.get(i).getCheckfloor().equals("0")) { // 방문한 적 없다면
					// floorlist.get(i).setCheckfloor("1"); // 방문 체크
					// }
					// 적 출현 메소드 작성 업데이트 쿼리
					// if (floorlist.get(i).getEnemyInCount().equals("0")) { // 에너미카운터 0
					// int ran = (int)(Math.random()*30)+1;
					// Random ran = new Random();
					// if (ran.nextInt(10) < 3) {// 30%확률 //에너미 인카운트 변경
					// enemy.enemyInCount(floorlist.get(i).getFloorNum());
					// System.out.println("적출현");
					// }

					// }
					// 아이템 메소드 작성

					// 자판기or 엘리베이터 작성

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
		System.out.println("1.내려가기  2.왼쪽방 들어가기 3.오른쪽방 들어가기 4.올라가기 5.엘리베이터 6.내 인벤토리 확인");
		System.out.print("선택>>");
		try {
			int selectMenu = sc.nextInt();

			while (true) {
				if (selectMenu == 1) { // 내려가기
					staminaLogic(); // 스태미너 감소
					uservo.set진행중플로어(uservo.get진행중플로어() - 1);
					floorlist = fd.FloorInfo(uservo.get진행중플로어()); // 층별셀렉트값 넘겨줌
					// 적 생성 메소드 실행
					CreateEnemy(floorlist);

					if (fd.checkFloor(floorlist.get(0).getFloorNum()) == 0) {
						fd.checkFloor(floorlist.get(0).getFloorNum());// 방문 체크
					}

					progressMenu();
				} else if (selectMenu == 2) { // 왼쪽방 들어가기

					staminaLogic(); // 스태미너 감소
					// uservo.set진행중플로어(uservo.get진행중플로어());
					leftfloorlist = fd.leftFloorInfo(uservo.get진행중플로어()); // 층별셀렉트값 넘겨줌

					leftfloormenu(leftfloorlist);

					progressMenu();
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
		for (int i = 0; i < inventorylist.size(); i++) {
			System.out.println(inventorylist.get(i).toString());
		}

		System.out.println("1.뒤로가기 2.아이템사용 3. ");
		System.out.print("선택 >");
		int inventoryMenu = sc.nextInt();

		while (true) {
			if (inventoryMenu == 1) {
				progressMenu();
			} else if (inventoryMenu == 2) {
				selectinventoryitem(inventorylist);
				inventorymenu();
			}
		}

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
				if (inventorylist.get(i).getCcode() == 3) { //음식
					System.out.println("스태미나 " + inventorylist.get(i).getEffect() + "만큼 증가!");
					uservo.set스태미너(uservo.get스태미너() + inventorylist.get(i).getEffect());
				} else if(inventorylist.get(i).getCcode() == 4) { //응급도구
					System.out.println("체력 : "+ inventorylist.get(i).getEffect() + "만큼 증가!");
					uservo.set체력(uservo.get체력() + inventorylist.get(i).getEffect());				}
			} else if (inventorylist.get(i).getName().equals(selectitem) && // 이름이 같고
					inventorylist.get(i).getCount() == 1) { // 소지수가 1이면
				userimpl.deleteitems(selectitem, inventorylist.get(i).getCcode());
				System.out.println(selectitem + " 삭제");
				if(inventorylist.get(i).getCcode() == 3) { //음식
					System.out.println("스태미나 " + inventorylist.get(i).getEffect() + "만큼 증가!");
					uservo.set스태미너(uservo.get스태미너() + inventorylist.get(i).getEffect());
				} else if(inventorylist.get(i).getCcode() == 4) { //응급도구
					System.out.println("체력 : " + inventorylist.get(i).getEffect() + "만큼 증가!");
					uservo.set체력(uservo.get체력() + inventorylist.get(i).getEffect());
				}
			}
		}

	}

	// 왼쪽방 메뉴
	private void leftfloormenu(List<leftfloorinfoVO> leftfloorlist) {
		Scanner sc = new Scanner(System.in);
		int itemcount = (int) ((Math.random() * 3) + 1);
		if (leftfloorlist.get(0).getCheckfloor().equals("0")) {
			// 적 생성 메소드 실행
			// CreateEnemyleft(leftfloorlist);
			for (int i = 0; i < itemcount; i++) {
				dropitems();

			}

		}
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│                              │");
		System.out.println("│  Left Room                   │");
		System.out.println("│                              │");
		System.out.println("│                              │");
		System.out.println("│                              │");
		System.out.println("│                              │");
		System.out.println("│                              │");
		System.out.println("└──────────────────────────────┘");

		System.out.println("현재 방 > 왼쪽 방");
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
		}
	}

	private void dropitems() {
		Random ran = new Random();
		foodlist = getFoods(); // 모든 음식정보 담은 리스트
		kititemslist = items.getKitItems(); // 모든 응급도구 정보
		inventorylist = userimpl.selectInventory(); // 검색한 인벤토리리스트 담아줌
		String kititems = "kititems";
		String foods = "foods";
		// for (int i = 0; i < foodlist.size(); i++) {
		// 푸드 테이블의 수만큼 랜덤 수 생성
		int Arandom = 0;
		// boolean foodcheck = false;
		Arandom = (int) ((Math.random() * foodlist.size()) + 1);

		int itemcode = Arandom;
		if (foodlist.get(itemcode).getGrade().equals("C")) {
			// System.out.println("1."+itemcode);
			if (ran.nextInt(100) < 95) {// 30%확률
				if (check(itemcode,foodlist.get(itemcode).getCcode())) { // 기존에 있따면 
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
				} else if (!check(itemcode,foodlist.get(itemcode).getCcode())) {
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
			if (ran.nextInt(100) < 95) {// 30%확률
				if (check(itemcode,foodlist.get(itemcode).getCcode())) { // 기존인벤토리에 있따면 
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
				} else if (!check(itemcode,foodlist.get(itemcode).getCcode())) {
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
			if (ran.nextInt(100) < 95) {// 30%확률
				if (check(itemcode,foodlist.get(itemcode).getCcode())) {
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
				} else if (!check(itemcode,foodlist.get(itemcode).getCcode())) {
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
		Arandom = (int) ((Math.random() * kititemslist.size()) + 1);

		itemcode = Arandom;
		if (kititemslist.get(itemcode).getGrade().equals("C")) {
			// System.out.println("1."+itemcode);
			if (ran.nextInt(100) < 95) {// 30%확률
				if (check(itemcode,kititemslist.get(itemcode).getCcode())) {
					int update = userimpl.inventoryUpdateitems(itemcode, kititemslist.get(itemcode).getCcode());

					if (update != 0) {
						for (int i = 0; i < kititemslist.size(); i++) {
							if (kititemslist.get(i).getItemcode() == Arandom) {
								System.out.println(" >" + kititemslist.get(i).getName() + "+1 추가");
								// System.out.println("들어온 랜덤 아이템 코드 >" + foodlist.get(i).getItemcode());
							}
						}
					} else if (update == 0) {
						System.out.println("4.");
						System.out.println("업데이트를 실패하셨습니다.");
					}
				} else if (!check(itemcode,kititemslist.get(itemcode).getCcode())) {
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
			if (ran.nextInt(100) < 95) {// 30%확률
				if (check(itemcode,kititemslist.get(itemcode).getCcode())) {
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
						System.out.println("업데이트를 실패하셨습니다.");
					}
				} else if (!check(itemcode,kititemslist.get(itemcode).getCcode())) {
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
			if (ran.nextInt(100) < 95) {// 30%확률
				if (check(itemcode,kititemslist.get(itemcode).getCcode())) {
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
				} else if (!check(itemcode,kititemslist.get(itemcode).getCcode())) {
					userimpl.Insertitems(itemcode, foods);
					for (int i = 0; i < kititemslist.size(); i++) {
						if (kititemslist.get(i).getItemcode() == Arandom) {
							System.out.println(">" + kititemslist.get(i).getName() + "+1 획득");
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
		for (int i = 0; i < leftfloorlist.size(); i++) {
			// 적 출현 메소드 작성 업데이트 쿼리
			if (leftfloorlist.get(i).getCheckfloor().equals("0") // 방문한 적 없고
					&& leftfloorlist.get(i).getLeftEnemyInCount().equals("0")) { // 에너미카운터 0
				// int ran = (int)(Math.random()*30)+1;
				Random ran = new Random();
				if (ran.nextInt(100) < 20) {// 20%확률 //에너미 인카운트 변경
					enemy.enemyInCount(leftfloorlist.get(i).getFloorNum());
					System.out.println("적 출현");

					if (uservo.get진행중플로어() / 10 == 9 || // 90층대 80층대 70층대
							uservo.get진행중플로어() / 10 == 8 || uservo.get진행중플로어() / 10 == 7) {
						EnemyVO enemyvo = new EnemyVO("이웃집괴물", 20, 6, 20, 60);
						BattleImpl battle = new BattleImpl();
						battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

					}
				}
			}

		}
	}

	// 적 생성 메소드
	private void CreateEnemy(List<FloorInfoVO> floorlist) {
		for (int i = 0; i < floorlist.size(); i++) {
			// 적 출현 메소드 작성 업데이트 쿼리
			if (floorlist.get(i).getCheckfloor().equals("0") // 방문한 적 없고
					&& floorlist.get(i).getEnemyInCount().equals("0")) { // 에너미카운터 0
				// int ran = (int)(Math.random()*30)+1;
				Random ran = new Random();
				if (ran.nextInt(100) < 20) {// 20%확률 //에너미 인카운트 변경
					enemy.enemyInCount(floorlist.get(i).getFloorNum());
					System.out.println("적 출현");

					if (uservo.get진행중플로어() / 10 == 9 || // 90층대 80층대 70층대
							uservo.get진행중플로어() / 10 == 8 || uservo.get진행중플로어() / 10 == 7) {
						EnemyVO enemyvo = new EnemyVO("이웃집괴물", 20, 6, 20, 60);
						BattleImpl battle = new BattleImpl();
						battle.battleStart(enemyvo, uservo); // 배틀 메소드 넘겨줌

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

		} else if (uservo.get스태미너() < 0) { // 스태미너가 0보다 작다면 체력 -2감소
			uservo.set스태미너(0);
			uservo.set체력(uservo.get체력() - 2);
		}

	}

	public boolean check(int itemcode,int ccode) {
		boolean foodcheck = false;
		for (int i = 0; i < inventorylist.size(); i++) {
			if (inventorylist.get(i).getItemcode() == (itemcode) &&
					inventorylist.get(i).getCcode() == (ccode)) {

				foodcheck = true;
				break;
			} else {
				foodcheck = false;
			}
		}

		return foodcheck;
	}
}
