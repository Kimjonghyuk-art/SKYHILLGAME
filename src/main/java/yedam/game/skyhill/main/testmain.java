package yedam.game.skyhill.main;

import java.util.Scanner;

import yedam.game.skyhill.service.GameStartImpl;
import yedam.game.skyhill.service.GameStartService;

public class testmain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		// DataSource datasource = DataSource.getInstance();
		// Connection con = datasource.getConnection();
		GameStartService Gamestart = new GameStartImpl();
		
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
