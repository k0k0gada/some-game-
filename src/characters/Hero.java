package characters;

import java.util.ArrayList;
import java.util.HashSet;

import items.Armor;
import items.Boots;
import items.Gloves;
import items.Helmet;
import items.Item;
import items.Weapon;
import main.Main;
import monsters.Monster;

public class Hero {
	private String name;
	// level&gold
	private int level;
	private int exp;
	private int expNeeded;
	private int coins;

	// stats:
	private int HP;
	private int MAXHP;
	private int dmg;
	private int defence;// dmg reduction
	private int attackSpeed;// 1-fastest,100-slowest;in game shown inverted
	private int critChance;
	private double critMultiplier;

	// items:

	private java.util.HashSet<Item> itemSet = new HashSet<>();
	private Weapon wep;
	private Armor armor;
	private Boots boots;
	private Gloves gloves;
	private Helmet helmet;

	// inventory:
	ArrayList<Item> inventory = new ArrayList<>();

	// enemy
	Monster enemy;

	public Hero(String name) {
		this.name = name;
		this.level = 1;
		this.expNeeded = this.level * 15;
		this.exp = 0;
		this.dmg = 10;
		this.attackSpeed = 70;
		this.defence = 4;
		this.MAXHP = 100;
		this.HP = this.MAXHP;
		this.critChance = 5;// 5 %;5 out of 100
		this.critMultiplier = 1.5;

		this.armor = new Armor("shirt");
		this.armor.setDefInc(1);
		this.armor.setMaxHPInc(3);
		this.armor.setSpeedInc(5);
		this.itemSet.add(this.armor);

		this.boots = new Boots("galoshes");
		this.boots.setDefInc(1);
		this.boots.setSpeedInc(-10);
		this.boots.setMaxHPInc(2);
		this.itemSet.add(this.boots);

		this.gloves = new Gloves("no gloves");
		this.gloves.setCritMultiplierInc(-0.3);
		this.gloves.setAttackInc(-1);
		this.itemSet.add(this.gloves);

		this.helmet = new Helmet("baldness");
		this.helmet.setDefInc(-1);
		this.helmet.setMaxHPInc(-3);
		this.itemSet.add(this.helmet);

		this.wep = new Weapon("fists");
		this.wep.setAttackInc(-2);
		this.wep.setCritChanceInc(40);
		this.wep.setSpeedInc(30);
		this.itemSet.add(this.wep);
		if (this.HP < this.getMAXHP()) {
			this.HP = this.getMAXHP();
		}
	}

	public int getLevel() {
		return level;
	}

	int getAllDMG() {
		int tempDMG = 0;
		for (Item item : itemSet) {
			tempDMG += item.getAttackInc();
		}
		return (this.dmg + tempDMG);
	}

	int getAllDef() {
		int tempRed = 0;
		for (Item item : itemSet) {
			tempRed += item.getDefInc();
		}
		return (this.defence + tempRed);
	}

	int getALLAttackSpeed() {
		int tempSp = 0;
		for (Item item : itemSet) {
			tempSp += item.getSpeedInc();
		}
		if (this.attackSpeed - tempSp < 1) {
			return 1;
		} else {
			return (this.attackSpeed - tempSp);
		}
	}

	int getMAXHP() {
		int tempHP = 0;
		for (Item item : itemSet) {
			tempHP += item.getMaxHPInc();
		}
		checkHPmoreThanMaxHP();
		return (this.MAXHP + tempHP);
	}

	int getCritChance() {
		int tempCS = 0;// crit chance
		for (Item item : itemSet) {
			tempCS += item.getCritChanceInc();
		}
		return (this.critChance + tempCS);
	}

	public void setEnemy(Monster enemy) {
		this.enemy = enemy;
	}

	double getCritMultiplier() {
		double tempCM = 0;// crit multiplier
		for (Item item : itemSet) {
			tempCM += item.getCritMultiplierInc();
		}
		return (this.critMultiplier + tempCM);
	}

	public java.util.HashSet<Item> getItemSet() {
		return itemSet;
	}

	@Override
	public String toString() {
		String s = "Hero name:" + this.name;
		s = s + "\n";
		s = s + "level :" + this.level;
		s = s + "\n";
		s = s + "exp/exp Neded: " + this.exp + "/" + this.expNeeded;
		s = s + "\n";
		s = s + "coins: " + this.coins;
		s = s + "\n";
		s = s + "HP/MaxHP " + this.HP + "/" + this.getMAXHP();
		s = s + "\n";
		s = s + "hero's stats(hero's stats + item bonuses):";
		s = s + "\n";
		s = s + "dmg: " + this.getAllDMG() + " (" + this.dmg + " + " + (this.getAllDMG() - this.dmg) + ")"
				+ "\t dmg reduction: " + this.getAllDef() + " (" + this.defence + " + "
				+ (this.getAllDef() - this.defence) + ")" + "\tspeed="
				+ ((this.getALLAttackSpeed() == 1) ? "100=MAX SPEED"
						: (100 - this.getALLAttackSpeed()) + "(" + (100 - this.attackSpeed) + " + "
								+ (this.attackSpeed - this.getALLAttackSpeed()) + ")");
		s = s + "\n";
		int tempCS = 0;// crit chance
		for (Item item : itemSet) {
			tempCS += item.getCritChanceInc();
		}
		double tempCM = 0;// crit multiplier
		for (Item item : itemSet) {
			tempCM += item.getCritMultiplierInc();
		}
		s = s + "crit chance | multiplier :" + this.getCritChance() + " (" + this.critChance + " + " + tempCS + ") "
				+ "%|" + this.getCritMultiplier() + " (" + this.critMultiplier + " + " + tempCM + ")";
		s = s + "\n";
		return s;
	}

	void checkHPmoreThanMaxHP() {
		int tempHP = 0;
		for (Item item : itemSet) {
			tempHP += item.getMaxHPInc();
		}
		if (this.HP > (this.MAXHP + tempHP)) {
			this.HP = this.MAXHP;
		}
	}

	void equipItem(Item item) {
		this.inventory.remove(item);// removes the item from the inventory
		switch (item.getType()) {// what is the item ?
		case "armor":
			// moves the currently equipped armor to the inventory
			this.inventory.add(this.armor);
			this.itemSet.remove(this.armor);// removes the armor from item set
			this.armor = (Armor) item;// places the item on the correct place
			break;
		case "boots":
			this.inventory.add(this.boots);
			this.itemSet.remove(this.armor);
			this.boots = (Boots) item;
			break;
		case "gloves":
			this.inventory.add(this.gloves);
			this.itemSet.remove(this.armor);
			this.gloves = (Gloves) item;
			break;
		case "helmet":
			this.inventory.add(this.helmet);
			this.itemSet.remove(this.armor);
			this.helmet = (Helmet) item;
			break;
		case "weapon":
			this.inventory.add(this.wep);
			this.itemSet.remove(this.armor);
			this.wep = (Weapon) item;
			break;
		}
		checkHPmoreThanMaxHP();
		this.itemSet.add(item);// adds the item to the SET of equipped items
	}

	public boolean checkLevelUp() {
		if (this.exp < this.expNeeded) {
			return false;
		} else {
			this.level++;
			this.exp = 0;
			this.expNeeded = this.level * 15;
			System.out.println("CONGRATULATIONS YOU LEVELED UP! ");
			getLevelUpBonus();
			this.HP = this.MAXHP;
			return true;
		}
	}

	private void getLevelUpBonus() {

		System.out.println("what two stats would you like to increase ?");
		System.out.println(
				"1-max HP(+14);\t2-dmg(+2);\t3-def(+2);\n4-attack speed(+5);\t5-crit chance(+4%);\t6-crit multiplier(+0.15)");
		for (int i = 0; i < 2; i++) {
			System.out.println("choose option:");
			switch (Main.sc.nextInt()) {
			case 1:
				this.MAXHP += 14;
				System.out.println("max hp increased to:" + this.MAXHP);
				break;
			case 2:
				this.dmg += 2;
				System.out.println("Hero's dmg increased to:" + this.dmg);
				break;
			case 3:
				this.defence += 2;
				System.out.println("Hero's def increased to:" + this.defence);
				break;
			case 4:
				this.attackSpeed -= 5;
				System.out.println("Attack speed increased to:" + (100 - this.attackSpeed));
				break;
			case 5:
				this.critChance += 4;
				System.out.println("Critical chance increased to:" + this.critChance);
				break;
			case 6:
				this.critMultiplier += 0.15;
				System.out.println("Critical multiplier increased to:" + this.critMultiplier);
				break;
			}
		}
		System.out.println(this.toString());
	}

	public boolean isAlive() {
		return (this.HP > 0 ? true : false);
	}

	public void takeDMG(int dmg) {
		if (Main.randomNumTo100() > 79) {
			System.out.println("the hero dodged! ");
		} else {
			if (dmg - this.getAllDef() > 1) {
				this.HP = this.HP - (dmg - this.getAllDef());
				System.out.println("the hero took " + (dmg - this.getAllDef()) + " dmg");
			} else {
				this.HP--;
				System.out.println("the hero took 1 dmg");
			}
		}
		System.out.println(this.isAlive() ? "the hero still has " + this.HP + " HP" : "the hero died");
	}

	private int CalculateCritAndFluctuating() {
		// critical..
		int dmg;
		dmg = this.getAllDMG();
		if (Main.randomNumTo100() < this.getCritChance()) {
			dmg *= this.getCritMultiplier();
			System.out.print("the hero critted!");
		}
		double fluctiuationDMG = 0.9 + (Main.randomNum() / 5);// +-10% dmg
		return (int) (dmg * fluctiuationDMG);
	}

	public void fight(Monster enemy) {
		this.setEnemy(enemy);
		System.out.println(enemy.toString());
		System.out.println(this.toString());
		int heroTurn = this.getALLAttackSpeed();
		int MonsterTurn = this.enemy.getAttackSpeed();
		do {
			heroTurn--;
			MonsterTurn--;
			if (heroTurn == 0) {
				heroTurn = this.getALLAttackSpeed();
				if (Main.randomNumTo100() < 20) {
					System.out.println("the hero Missed!\n");
				} else {
					int dmg = this.CalculateCritAndFluctuating();
					System.out.println("the hero hits with " + dmg + " dmg");
					this.enemy.takeDMG(dmg);
					System.out.println();
				}
			}
			if (MonsterTurn == 0) {
				MonsterTurn = this.enemy.getAttackSpeed();
				if (Main.randomNumTo100() < 20) {
					System.out.println("the Monster Missed!\n");
				} else {
					int dmg = this.enemy.CalculateCritAndFluctuating();
					System.out.println("the monster hits with " + dmg + " dmg");
					this.takeDMG(dmg);
					System.out.println();
				}
			}

		} while (this.isAlive() && this.enemy.isAlive());
		if (this.isAlive()) {
			System.out.println("the hero won!");
		}
	}
}
