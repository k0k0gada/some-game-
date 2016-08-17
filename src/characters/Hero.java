package characters;

import java.util.ArrayList;
import java.util.HashSet;

import items.Armor;
import items.Boots;
import items.Gloves;
import items.Helmet;
import items.Item;
import items.Potion;
import items.Weapon;
import main.Main;
import monsters.Monster;

public class Hero {
	private final int POINTS_PER_LEVEL = 2;
	private int MAX_AMOUNT_OF_POTIONS = 7;
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
	ArrayList<Potion> Potions = new ArrayList<>();
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
		for (Item item : itemSet) {
			Main.allItems.add(item);
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
			this.expNeeded += this.level * 15;
			System.out.println("CONGRATULATIONS YOU LEVELED UP! ");
			getLevelUpBonus();
			return true;
		}
	}

	private void getLevelUpBonus() {

		System.out.println("what two stats would you like to increase ?");
		System.out.println(
				"1-max HP(+14);\t2-dmg(+2);\t3-def(+2);\n4-attack speed(+5);\t5-crit chance(+4%);\t6-crit multiplier(+0.15)");
		for (int i = 0; i < POINTS_PER_LEVEL; i++) {
			System.out.println("choose option:");
			String s = Main.sc.nextLine();
			switch (s.charAt(0)) {
			case '1':
				this.MAXHP += 14;
				System.out.println("max hp increased to:" + this.getMAXHP());
				break;
			case '2':
				this.dmg += 2;
				System.out.println("Hero's dmg increased to:" + this.dmg);
				break;
			case '3':
				this.defence += 2;
				System.out.println("Hero's def increased to:" + this.defence);
				break;
			case '4':
				this.attackSpeed -= 5;
				System.out.println("Attack speed increased to:" + (100 - this.attackSpeed));
				break;
			case '5':
				this.critChance += 4;
				System.out.println("Critical chance increased to:" + this.critChance);
				break;
			case '6':
				this.critMultiplier += 0.15;
				System.out.println("Critical multiplier increased to:" + this.critMultiplier);
				break;
			default:
				i--;
				break;
			}
			this.HP = this.getMAXHP();
		}
		System.out.println(this.toString());
	}

	public boolean isAlive() {

		if (this.HP < 0) {
			System.out.println("the hero's hp was " + this.HP);
			this.HP = 0;
		}
		return (this.HP > 0 ? true : false);
	}

	void usePotion(Potion p) {
		int bonusHP = p.getHpInc();
		if (this.HP >= this.getMAXHP()) {
			System.out.println("Already at max HP! The potion was not used");
			return;
		}
		if (this.HP + bonusHP < this.getMAXHP()) {
			this.HP = this.HP + bonusHP;
			System.out.println("the potion was used.Hero's HP is " + this.HP);
		} else {
			this.HP = this.getMAXHP();
			System.out.println("the potion heald the hero to MAX HP " + this.getMAXHP());
		}
		p.setAmount(p.getAmount() - 1);
		if (p.getAmount() == 0) {
			System.out.println("the potion can't be use any more.It's been removed");
			this.Potions.remove(p);
		} else {
			System.out.println(p.toString());
		}
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
				chooseFightOptions(enemy, (MonsterTurn + 1));
				break;
			}
			if (MonsterTurn == 0) {
				MonsterTurn = this.enemy.getAttackSpeed();
				this.monstersFightTurn();
			}
			this.checkWonStatusOnFight();
		} while (this.isAlive() && this.enemy.isAlive());
	}

	private void chooseFightOptions(Monster enemy, int monsterTurnMeter) {
		System.out.println("hero HP :" + this.HP + "\t\t Monster hp:" + this.enemy.getHP());
		System.out.println("1=fight till end;2=fight 5 rounds ;3=run(70 % success);else=fight 1 round");
		String s = Main.sc.nextLine();
		String[] options = { "1", "2", "3", "0" };
		for (int i = 0; i < options.length; i++) {
			if (s.startsWith(options[i])) {
				s = options[i];
				break;
			}
			if (i == options.length - 1) {
				s = "0";
			}
		}
		switch (s) {
		case "1":
			fightTillEnd(enemy, monsterTurnMeter);
			break;
		case "2":
			fightFiveTurns(enemy, monsterTurnMeter);
			break;
		case "3":
			tryToRun(enemy, monsterTurnMeter);
			break;
		case "0":
			fightOneRound(enemy, monsterTurnMeter);
			break;
		}

	}

	private void fightOneRound(Monster enemy, int monsterTurnMeter) {
		int heroTurn = 1;
		int monsterTurn = monsterTurnMeter;
		int cnt = 0;
		do {
			heroTurn--;
			monsterTurn--;
			if (heroTurn == 0) {
				if (cnt == 1) {
					this.chooseFightOptions(enemy, monsterTurn + 1);
					break;
				}
				cnt++;
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
			if (monsterTurn == 0) {
				monsterTurn = this.enemy.getAttackSpeed();
				this.monstersFightTurn();
			}
			this.checkWonStatusOnFight();
		} while (this.isAlive() && this.enemy.isAlive());
	}

	private void tryToRun(Monster enemy, int monsterTurnMeter) {
		int heroTurn = 1;
		int monsterTurn = monsterTurnMeter;
		int cnt = 0;
		do {
			heroTurn--;
			monsterTurn--;
			if (heroTurn == 0) {
				if (cnt == 1) {
					this.chooseFightOptions(enemy, monsterTurn + 1);
					break;
				}
				cnt++;
				heroTurn = this.getALLAttackSpeed();
				if (Main.randomNumTo100() < 70) {
					System.out.println("the hero escaped succesfully !!");
					this.enemy = null;
					Main.chooseOption(this);
					break;
				}
				System.out.println("the hero couldn't escape,the dmg for this round is halved!");
				if (Main.randomNumTo100() < 20) {
					System.out.println("the hero Missed!\n");
				} else {
					int dmg = this.CalculateCritAndFluctuating() / 2;
					System.out.println("the hero hits with " + dmg + " dmg");
					this.enemy.takeDMG(dmg);
					System.out.println();
				}

			}
			if (monsterTurn == 0) {
				monsterTurn = this.enemy.getAttackSpeed();
				this.monstersFightTurn();
			}
			this.checkWonStatusOnFight();
		} while (this.isAlive() && this.enemy.isAlive());

	}

	private void fightFiveTurns(Monster enemy, int monsterTurnMeter) {
		int heroTurn = 1;
		int monsterTurn = monsterTurnMeter;
		int counter = 0;
		do {
			heroTurn--;
			monsterTurn--;
			if (heroTurn == 0) {
				counter++;
				if (counter == 6) {
					chooseFightOptions(enemy, monsterTurn + 1);
					break;
				}
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
			if (monsterTurn == 0) {
				monsterTurn = this.enemy.getAttackSpeed();
				this.monstersFightTurn();
			}
			this.checkWonStatusOnFight();
		} while (this.isAlive() && this.enemy.isAlive());
	}

	private void fightTillEnd(Monster enemy, int MonsterTurnMeter) {
		int heroTurn = 1;
		int monsterTurn = MonsterTurnMeter;
		do {
			heroTurn--;
			monsterTurn--;
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
			if (monsterTurn == 0) {
				monsterTurn = this.enemy.getAttackSpeed();
				this.monstersFightTurn();
			}
			this.checkWonStatusOnFight();
		} while (this.isAlive() && this.enemy.isAlive());
	}

	private void checkWonStatusOnFight() {
		if (this.isAlive() != this.enemy.isAlive()) {
			if (this.isAlive()) {
				System.out.println("the hero won!\n");
				this.fightWonSpoils();
			} else {
				System.out.println("the monster won!\n");
			}
		}
	}

	private void monstersFightTurn() {
		if (Main.randomNumTo100() < 20) {
			System.out.println("the Monster Missed!\n");
		} else {
			int dmg = this.enemy.CalculateCritAndFluctuating();
			System.out.println("the monster hits with " + dmg + " dmg");
			this.takeDMG(dmg);
			System.out.println();
		}
	}

	void fightWonSpoils() {
		int exp = this.enemy.giveEXP();
		System.out.println("you gained " + exp + " exp!");
		this.exp += exp;
		this.checkLevelUp();
		int coins = this.enemy.dropCoins();
		System.out.println("the enemy dropped " + coins + " coins!");
		this.coins += this.enemy.dropCoins();
		Potion p = this.enemy.dropPotion();
		if (p != null) {
			System.out.println("the enemy dropped " + p.toString());
			if (this.Potions.size() < MAX_AMOUNT_OF_POTIONS) {
				System.out.println("You took that potion");
				this.Potions.add(p);
			} else {
				System.out.println("your potion bag is full!");
				System.out.println("your potions:" + this.Potions.toString());
				System.out.println("would you like to change one of them ?");
				if (!yesNoDecision()) {
					System.out.println("OK no new potions for you!");
				} else {
					System.out.println("which one would you like to change ?");
					this.Potions.toString();
					// for (int i = 0; i < this.Potions.size(); i++) {
					// System.out.println((i + 1) + " = " +
					// this.Potions.get(i).toString());
					// }
					System.out.println("choose now:");
					String s = Main.sc.nextLine();
					if (Character.isDigit(s.charAt(0))) {
						/*
						 * limits the max amount of potions to 9
						 */
						int i = Character.digit(s.charAt(0), 10);
						if (i < this.Potions.size()) {
							this.Potions.remove(i);
							this.Potions.add(p);
						}
					} else {
						System.out.println("you didn't choose a number from the list.the potion is discarded");
					}
				}
			}
		} else {
			System.out.println("the enemy dropped no potion! ");
		}
	}

	boolean yesNoDecision() {
		char z = Main.sc.nextLine().charAt(0);
		if (z == 'y' || z == 'Y') {
			return true;
		} else {
			return false;
		}
	}

	public void choosePotionToUse() {
		System.out.println("the hero's HP " + this.HP + "/" + this.getMAXHP());
		System.out.println("potions:");
		System.out.println(this.Potions.toString());
		System.out.println("(e=exit)choose now:");
		String s = new String(Main.sc.nextLine());
		if (s.startsWith("e")) {
			System.out.println("going back to main menu:");
			System.out.println();
			Main.chooseOption(this);
			return;
		}
		if (Character.isDigit(s.charAt(0))) {
			int i = Character.digit(s.charAt(0), 10) - 1;
			if (i < this.Potions.size()) {
				this.usePotion(this.Potions.get(i));
			}
		} else {
			System.out.println("you didn't choose a number from the list.Choose again! ");
			this.choosePotionToUse();
			return;
		}
	}

}
