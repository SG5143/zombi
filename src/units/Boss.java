package units;

import interfaces.Enemy;

public class Boss extends Unit implements Enemy {
	private int shield;

	public Boss(int position, int hp, int attackPower) {
		super(position, hp, attackPower);
		this.shield = (int) ((int) hp * 0.2);
	}

	public int getShield() {
		return shield;
	}

	public void decreaseShield(int damage) {
		this.shield -= damage;
		if (shield < 0)
			shield = 0;
	}

	@Override
	public void attack(Unit unit) {
		int att = r.nextInt(4);

		if (att == 0)
			System.out.println("보스가 공격을 실패했습니다.");
		else {
			if (unit instanceof Hero) {
				int damage = r.nextInt(attackPower) + 1;

				switch (damage) {
				case 1 -> unit.decreaseHp(damage * 2);
				case 2, 3 -> unit.decreaseHp(damage);
				}

				String attMessage = att == 1 ? "[필살기]" : "[일반공격]";
				System.out.println(attMessage + "보스가 " + damage + "의 피해를 주었습니다");
			}
		}
	}

}
