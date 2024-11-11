package units;

import interfaces.Enemy;

public class Zombie extends Unit implements Enemy {

	public Zombie(int position, int hp, int attackPower) {
		super(position, hp, attackPower);
	}

	@Override
	public void attack(Unit unit) {
		int att = r.nextInt(4);

		if (att == 0)
			System.out.println("좀비가 공격을 실패했습니다.");
		else {
			if (unit instanceof Hero) {
				int damage = r.nextInt(attackPower) + 1;

				unit.decreaseHp(damage);
				System.out.println("좀비가 " + damage + "의 피해를 주었습니다");
			}
		}
		System.out.println("현재 좀비의 체력 : " + this.hp);
	}
}