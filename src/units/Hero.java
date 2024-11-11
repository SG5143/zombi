package units;

import interfaces.Enemy;

public class Hero extends Unit {

	public Hero(int position, int hp, int attackPower) {
		super(position, hp, attackPower);
	}

	public void move() {
		if (position < 10)
			position++;
	}

	@Override
	public void attack(Unit unit) {
		if (unit instanceof Enemy) {
			int damage = r.nextInt(attackPower / 2) + (attackPower / 2) + 1;

			if (r.nextBoolean())
				unit.decreaseHp(damage * 2);
			else
				unit.decreaseHp(damage);
		}
	}

}