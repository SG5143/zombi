package units;

abstract class Unit {
	protected final int MAX_HP;
	protected int hp;
	protected int position;
	protected int attackPower;

	public Unit(int position, int hp, int attackPower) {
		this.MAX_HP = hp;
		this.hp = hp;
		this.position = position;
		this.attackPower = attackPower;
	}

	public int getMaxHp() {
		return this.MAX_HP;
	}

	public int getHp() {
		return this.hp;
	}

	public int getPosition() {
		return position;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void increaseHp(int hp) {
		this.hp += hp;
		if (this.hp > MAX_HP)
			this.hp = MAX_HP;
	}

	abstract void attack(Unit unit);
}