package units;

import constants.WeaponConstants;
import interfaces.Enemy;

public class Hero extends Unit {
	private int weaponLevel;
	private int potion;

	public Hero(int position, int hp, int attackPower) {
		super(position, hp, attackPower);
		this.potion = 2;
		this.weaponLevel = 0;
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

	public int getWeaponLevel() {
		return weaponLevel;
	}

	public void setWeaponLevel(int weaponLevel) {
		this.weaponLevel = weaponLevel;
		this.attackPower = Integer.parseInt(WeaponConstants.WEAPONS[weaponLevel][1]);
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