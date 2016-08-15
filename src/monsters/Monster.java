package monsters;

import characters.Hero;

public abstract class Monster {
	static int counter = 0;
	final int MIN_HP = 20;
	final int MIN_DMG = 7;
	final int MIN_DEF = 2;
	final int MIN_CRIT_CHANCE = 4;
	final int MIN_CRIT_MULTIPLIER = 1;

	final int HP_PER_LEVEL = 9;
	final int DMG_PER_LEVEL = 3;
	final int DEF_PER_LEVEL = 2;
	final int CRIT_CHANCE_PER_LEVEL = 4;
	final double CRIT_MULTIPLIER_PER_LEVEL = 0.13;
	final int ATTACK_SPEED_PER_LEVEL = 8;

	private String type;

	private String name;
	private int level;
	private Hero enemy;

	private int HP;
	private int MAXHP;
	private int dmg;
	private int defence;// dmg reduction
	private int attackSpeed;// 1-fastest,100-slowest;in game shown inverted
	private int critChance;
	private double critMultiplier;

	Monster(Hero enemy) {
		this.name = "monster" + counter;
		countINC();
		this.level = enemy.getLevel();
		this.MAXHP = this.MIN_HP + this.level * this.HP_PER_LEVEL;
		this.HP = this.MAXHP;
		this.dmg = this.MIN_DMG + this.DMG_PER_LEVEL * this.level;
		this.defence = this.MIN_DEF + this.DEF_PER_LEVEL * this.level;
		this.attackSpeed = 100 - this.ATTACK_SPEED_PER_LEVEL * this.level;
		this.critChance = this.MIN_CRIT_CHANCE + this.CRIT_CHANCE_PER_LEVEL * this.level;
		this.critMultiplier = this.MIN_CRIT_MULTIPLIER + this.MIN_CRIT_MULTIPLIER * this.level;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public void setMAXHP(int mAXHP) {
		MAXHP = mAXHP;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public void setCritChance(int critChance) {
		this.critChance = critChance;
	}

	public void setCritMultiplier(double critMultiplier) {
		this.critMultiplier = critMultiplier;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMAXHP() {
		return MAXHP;
	}

	public int getDmg() {
		return dmg;
	}

	public int getDefence() {
		return defence;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public int getCritChance() {
		return critChance;
	}

	public double getCritMultiplier() {
		return critMultiplier;
	}

	static void countINC() {
		counter++;
	}

	public boolean isAlive() {
		return (this.HP > 0 ? true : false);
	}

	@Override
	public String toString() {
		return "Monster [type=" + type + ", name=" + name + ", level=" + level + ", HP=" + HP + ", MAXHP=" + MAXHP
				+ ", dmg=" + dmg + ", defence=" + defence + ", attackSpeed=" + attackSpeed + ", critChance="
				+ critChance + ", critMultiplier=" + critMultiplier + "]";
	}

	public void takeDMG(int dmg) {
		this.HP = this.HP - (dmg - this.getDefence());
		System.out.println("the monster took " + (dmg - this.defence) + " dmg");
		System.out.println(this.isAlive() ? "the monster still has " + this.HP + " HP" : " the monster died");
	}
}
