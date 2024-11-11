package units;

import interfaces.Enemy;

public class Hero extends Unit {
	int potion;

	public Hero(int position, int hp, int attackPower) {
		super(position, hp, attackPower);
		potion = 2;
	}

	public int getPotionCnt() {
		return potion;
	}

	public void recovery() {
		if (potion > 0) {
			potion--;
			increaseHp(50);
		}
	}

	public void move() {
		if (position < 10)
			position++;
	}

	@Override
	public void attack(Unit unit) {
		if (unit instanceof Enemy) {
			int damage = r.nextInt(attackPower / 2) + (attackPower / 2) + 1;

			if (unit instanceof Boss && ((Boss) unit).getShield() > 0) {
				Boss boss = (Boss) unit;
				boss.decreaseShield(damage);
			} else {
				if (r.nextBoolean())
					unit.decreaseHp(damage * 2);
				else
					unit.decreaseHp(damage);
			}

			System.out.println("적에게 " + damage + "의 피해를 가했습니다");
		}
	}

}