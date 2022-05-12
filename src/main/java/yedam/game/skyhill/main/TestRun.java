package yedam.game.skyhill.main;

import java.util.Scanner;

import yedam.game.skyhill.service.FloorData;
import yedam.game.skyhill.service.GameStartImpl;
import yedam.game.skyhill.service.GameStartService;
import yedam.game.skyhill.service.UserImpl;

public class TestRun {
	Scanner sc = new Scanner(System.in);
	GameStartService Gamestart = new GameStartImpl();
	FloorData fd = new FloorData();
	UserImpl ul = new UserImpl();
	
	public void run() {
		test();
	}
	
	private void test() {
		
		
		// DataSource datasource = DataSource.getInstance();
		// Connection con = datasource.getConnection();
		
		try {
			//fd.floorCheckClear(); 
			//fd.leftFloorCheckClear();
			//fd.rightFloorCheckClear();
			//ul.deleteAllInventory();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("=========================");
		System.out.println("=========SKYHILL=========");
		System.out.println("=========================");
		
		System.out.println("1.실행 2. 종료");
		int startMenu = sc.nextInt();
		
		
		switch(startMenu) {
		case 1:
			Gamestart.selectFloorMenu();
			break;
		case 2:
			System.out.println("게임을 종료합니다.");
			System.exit(0);
			break;
		default :
			System.out.println("잘못 입력");
			break;
		}
	}
}
