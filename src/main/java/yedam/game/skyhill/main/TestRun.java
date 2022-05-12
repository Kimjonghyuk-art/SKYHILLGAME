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
	//UserImpl ul = new UserImpl();
	
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
		
		 
		for(int i = 99; i > 1; i -= 10) {
			int ran = (int) ((Math.random()*4)+1);
			System.out.println("받은 랜덤 숫자 > "+ran);
			int a[] = new int[ran];
			for(int j = 0; j < ran; j++) {
				int ranFloorNum = (int) (Math.random() * 9) + 1;
				int tenNumber = ((i/10)*10) + ranFloorNum;
				a[j] = tenNumber;
				for(int k = 0; k < j; k++) {
					if(a[k] == a[j]) {
						j--;
					}
				}
			}
			for(int q = 0; q < a.length; q++) {
				System.out.println(a[q]);
			}
		}
		for (int i = 99; i > 1; i -= 10) {
			int ran = (int) (Math.random() * 4) + 1;
			//System.out.println("Ran " + ran);
			int a[] = new int[ran];
			for (int j = 0; j < ran; j++) {
				int floor = (int) (Math.random() * 9) + 1;
				int ten = ((i / 10) * 10) + floor;
				//System.out.println(ten);
				a[j] = ten;
				for (int k = 0; k < j; k++) {
					if (a[j] == a[k]) {
						j--;
					}
				}

			}
			for (int aa : a) {
				System.out.println("a no: " + aa);
			}
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
