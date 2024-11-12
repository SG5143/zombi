package zombi;

import java.util.Random;
import java.util.Scanner;

import constants.WeaponConstants;
import units.Boss;
import units.Hero;
import units.Zombie;

public class Game {
	private Random random = new Random();
	private Scanner scanner = new Scanner(System.in);
	private Hero hero = new Hero(1, 100, 5);
	private Zombie zombie = new Zombie(5, 100, 10);
	private Boss boss = new Boss(9, 300, 20);

	public void run() {
		while (true) {
			System.out.printf("현재 위치 ☆%d☆\n", hero.getPosition());
			int select = inputNumber("(1)앞으로 이동 (2)무기강화 (3)종료");

			if (select == 2) {
				upgradeWeapon();
				continue;
			} else if (select == 3)
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
		System.out.println("\n====================\n");
		System.out.println("<< 좀비를 마주쳤습니다. >>");
		while (true) {
			System.out.println("\n====================");
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
				System.out.println("좀비를 물리쳤습니다.\n");
				return;
			}

			System.out.printf("좀비 체력 : %d\n", zombie.getHp());
			System.out.printf("현재 체력 : %d\n", hero.getHp());
		}
	}

	private void fightBoss(Boss boss) {
		System.out.println("\n====================\n");
		System.out.println("<< 보스를 마주쳤습니다. >>");
		while (true) {
			System.out.println("\n====================\n");
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
				System.out.println("보스를 물리쳤습니다.\n");
				return;
			}

			System.out.printf("보스 체력 : %d (+ %d)\n", boss.getHp(), boss.getShield());
			System.out.printf("현재 체력 : %d\n", hero.getHp());
		}
	}

	private void upgradeWeapon() {

		if (hero.getWeaponLevel() == 4) {
			System.out.println("최고 단계의 무기입니다.");
			return;
		}

		String weapon = WeaponConstants.WEAPONS[hero.getWeaponLevel()][0];
		int att = Integer.parseInt(WeaponConstants.WEAPONS[hero.getWeaponLevel()][1]);
		int upgradeBonus = Integer.parseInt(WeaponConstants.WEAPONS[hero.getWeaponLevel() + 1][1]) - att;

		System.out.printf("\n현재 무기 %s, 공격력 %d\n", weapon, att);
		System.out.printf("강화 확률 25%% 증가량 %d\n", upgradeBonus);
		int select = inputNumber("(1)강화시도 (2)종료");

		if (select == 2)
			return;

		int upgrade = random.nextInt(4);

		if (upgrade == 0) {
			hero.setWeaponLevel(hero.getWeaponLevel() + 1);
			System.out.println("강화에 성공했습니다.\n");
		} else {
			hero.setWeaponLevel(0);
			System.out.println("강화에 실패했습니다.\n");
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