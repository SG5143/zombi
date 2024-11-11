package zombi;

import java.util.Scanner;

import units.Boss;
import units.Hero;
import units.Zombie;

public class Game {
	private Scanner scanner = new Scanner(System.in);
	private Hero hero = new Hero(1, 100, 20);
	private Zombie zombie = new Zombie(5, 100, 10);
	private Boss boss = new Boss(10, 300, 20);

	public void run() {
		while (true) {
			System.out.printf("현재 위치 (%d)\n", hero.getPosition());
			int select = inputNumber("(1)앞으로 이동 (2)종료");

			if (select == 2)
				break;

		}

	}

	private int inputNumber(String msg) {
		System.out.println(msg + " : ");
		String input = scanner.nextLine();

		int num = -1;
		try {
			num = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			// 숫자 입력 검사
		}
		return num;
	}
}