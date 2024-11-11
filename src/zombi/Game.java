package zombi;

import java.util.Scanner;

import units.Boss;
import units.Hero;
import units.Zombie;

public class Game {
	private Scanner scanner = new Scanner(System.in);
	private Hero hero = new Hero(1, 100, 20);
	private Zombie zombie = new Zombie(5, 100, 10);
	private Boss boss = new Boss(9, 300, 20);

	public void run() {
		while (true) {
			System.out.printf("현재 위치 (%d)\n", hero.getPosition());
			int select = inputNumber("(1)앞으로 이동 (2)종료");

			if (select == 2)
				break;

			hero.move();

			if (hero.getPosition() == zombie.getPosition())
				fightZombie(zombie);

			if (hero.getPosition() == boss.getPosition())
				fightBoss(boss);

			if (hero.getHp() == 0) {
				System.out.println("주거씀다");
				break;
			}

			if (hero.getPosition() == 10) {
				System.out.println("clear!!");
				break;
			}
		}
	}

	private void fightZombie(Zombie zombie) {
		System.out.println("좀비를 마주쳤습니다.");
		while (true) {
			System.out.printf("공격력 %d, 포션 %d개\n", hero.getAttackPower(), hero.getPotionCnt());
			int select = inputNumber("(1)공격 (2)체력회복");

			if (select == 1) {
				zombie.attack(hero);
				hero.attack(zombie);
			} else if (select == 2) {
				if (hero.getPotionCnt() > 0)
					hero.recovery();
				else
					System.out.println("포션이 부족합니다.");
			}

			if (hero.getHp() == 0)
				return;

			if (zombie.getHp() == 0) {
				System.out.println("좀비를 물리쳤습니다.");
				return;
			}

			System.out.printf("좀비 체력 : %d\n", zombie.getHp());
			System.out.printf("현재 체력 : %d\n", hero.getHp());
		}
	}

	private void fightBoss(Boss boss) {
		System.out.println("보스를 마주쳤습니다.");
		while (true) {
			System.out.printf("공격력 %d, 포션 %d개\n", hero.getAttackPower(), hero.getPotionCnt());
			int select = inputNumber("(1)공격 (2)체력회복");

			if (select == 1) {
				boss.attack(hero);
				hero.attack(boss);
			} else if (select == 2) {
				if (hero.getPotionCnt() > 0)
					hero.recovery();
				else
					System.out.println("포션이 부족합니다.");
			}

			if (hero.getHp() == 0)
				return;

			if (boss.getHp() == 0) {
				System.out.println("보스를 물리쳤습니다.");
				return;
			}

			System.out.printf("보스 체력 : %d\n", boss.getHp());
			System.out.printf("현재 체력 : %d\n", hero.getHp());
		}
	}

	private int inputNumber(String msg) {
		System.out.print(msg + " : ");
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