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
import places.City;

public class Hero {
	private final int MAX_CRIT_CHANCE = 90;
	private final int CHANCE_TO_GAIN_SOME_STATS = 15;
	private final int POINTS_PER_LEVEL = 3;
	private int MAX_AMOUNT_OF_POTIONS = 7;
	private final int MAX_ATTACK_SPEED = 1;
	private String name;
	// level&resource
	private int level;
	private int exp;
	private int expNeeded;
	private int coins;
	private int gems;

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
		// stats & res& exp
		this.name = name;
		this.level = 1;
		this.expNeeded = this.level * 15;
		this.exp = 0;
		this.coins = 0;
		this.gems = 1;
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

	public Weapon getWep() {
		return wep;
	}

	public Armor getArmor() {
		return armor;
	}

	public Boots getBoots() {
		return boots;
	}

	public Gloves getGloves() {
		return gloves;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {

		HP = hP;
		this.checkHPmoreThanMaxHP();
		System.out.println("hero's HP is " + HP);
	}

	public Helmet getHelmet() {
		return helmet;
	}

	public int getLevel() {
		return level;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getGems() {
		return gems;
	}

	public void setGems(int gems) {
		this.gems = gems;
	}

	public void setAttackSpeed(int attackSpeed) {
		if (attackSpeed < MAX_ATTACK_SPEED) {
			this.attackSpeed = 1;
			System.out.println("Hero reached max speed of 100");
		} else {
			this.attackSpeed = attackSpeed;
			System.out.println("Hero's attackSpeed set to " + (100 - this.attackSpeed));
		}
	}

	public void setCritChance(int critChance) {
		if (critChance > MAX_CRIT_CHANCE) {
			this.critChance = MAX_CRIT_CHANCE;
			System.out.println("hero's crit chance is at it's max 95 % ");
		} else if (critChance < 0) {
			this.critChance = 0;
			System.out.println("hero's crit chance changed to 0");
		} else {
			this.critChance = critChance;
			System.out.println("hero's crit chance set to " + this.critChance + " %");
		}
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

	public int getMAXHP() {
		int tempHP = 0;
		for (Item item : itemSet) {
			tempHP += item.getMaxHPInc();
		}
		return (this.MAXHP + tempHP);
	}

	int getCritChance() {
		int tempCS = 0;// crit chance
		for (Item item : itemSet) {
			tempCS += item.getCritChanceInc();
		}
		if (this.critChance + tempCS > this.MAX_CRIT_CHANCE) {
			return this.MAX_CRIT_CHANCE;
		} else {
			return (this.critChance + tempCS);
		}
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
		s = s + "coins: " + this.coins + "\tgems: " + this.gems;
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
		if (this.HP > this.getMAXHP()) {
			this.HP = this.getMAXHP();
		}
	}

	void equipItem(Item item) {// looks like this will be useless
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
			if (s.length() > 0) {
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
					int tempSP = this.attackSpeed - 5;
					System.out.println("hero's current attack speed :" + (100 - this.attackSpeed));
					this.setAttackSpeed(tempSP);
					break;
				case '5':
					int tempCC = this.critChance + 4;
					System.out.println("hero;s current crit chance: " + this.critChance);
					this.setCritChance(tempCC);
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
			} else {
				i--;
			}
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

	void usePotion(Potion p, int n) {
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
			this.Potions.remove(n);
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
				chooseFightOptions(enemy, (MonsterTurn + 1), (heroTurn + 1));
				break;
			}
			if (MonsterTurn == 0) {
				MonsterTurn = this.enemy.getAttackSpeed();
				this.monstersFightTurn();
			}
			this.checkWonStatusOnFight();
		} while (this.isAlive() && this.enemy.isAlive());
	}

	private void chooseFightOptions(Monster enemy, int monsterTurnMeter, int heroTurnMeter) {
		System.out.println("hero HP :" + this.HP + "\t\t Monster hp:" + this.enemy.getHP());
		System.out.println("1=fight till end;2=fight 5 rounds ;3=run(70 % success);h=use potion;else=fight 1 round");
		String s = Main.sc.nextLine();
		s = s.toLowerCase();
		String[] options = { "1", "2", "3", "h", "0" };
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
			fightTillEnd(enemy, monsterTurnMeter, heroTurnMeter);
			break;
		case "2":
			fightFiveTurns(enemy, monsterTurnMeter, heroTurnMeter);
			break;
		case "3":
			tryToRun(enemy, monsterTurnMeter, heroTurnMeter);
			break;
		case "0":
			fightOneRound(enemy, monsterTurnMeter, heroTurnMeter);
			break;
		case "h":
			this.choosePotionToUseDuringCombat(monsterTurnMeter, heroTurnMeter);
			break;
		}

	}

	private void fightOneRound(Monster enemy, int monsterTurnMeter, int heroTurnMeter) {
		int heroTurn = heroTurnMeter;
		int monsterTurn = monsterTurnMeter;
		int cnt = 0;
		do {
			heroTurn--;
			monsterTurn--;
			if (heroTurn == 0) {
				if (cnt == 1) {
					this.chooseFightOptions(enemy, monsterTurn + 1, heroTurn + 1);
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

	private void tryToRun(Monster enemy, int monsterTurnMeter, int heroTurnMeter) {
		int heroTurn = heroTurnMeter;
		int monsterTurn = monsterTurnMeter;
		int cnt = 0;
		do {
			heroTurn--;
			monsterTurn--;
			if (heroTurn == 0) {
				if (cnt == 1) {
					this.chooseFightOptions(enemy, monsterTurn + 1, heroTurn + 1);
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

	private void fightFiveTurns(Monster enemy, int monsterTurnMeter, int heroTurnMeter) {
		int heroTurn = heroTurnMeter;
		int monsterTurn = monsterTurnMeter;
		int counter = 0;
		do {
			heroTurn--;
			monsterTurn--;
			if (heroTurn == 0) {
				counter++;
				if (counter == 6) {
					chooseFightOptions(enemy, monsterTurn + 1, heroTurn + 1);
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

	private void fightTillEnd(Monster enemy, int MonsterTurnMeter, int heroTurnMeter) {
		int heroTurn = heroTurnMeter;
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
		this.coins += coins;
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
					System.out.println("choose now:");
					String s = Main.sc.nextLine();
					if (s.length() > 0) {
						if (Character.isDigit(s.charAt(0))) {
							/*
							 * limits the max amount of potions to 9
							 */
							int i = Character.digit(s.charAt(0), 10) - 1;
							if (i < this.Potions.size() && i != -1) {
								this.Potions.remove(i);
								this.Potions.add(p);
								System.out.println("potions changed successfully");
							} else {
								System.out.println("you didn't choose a number from the list.the potion is discarded");
							}
						} else {
							System.out.println("you didn't choose a number from the list.the potion is discarded");
						}
					}
				}
			}
		} else {
			System.out.println("the enemy dropped no potion! ");
		}
		if (Main.randomNumTo100() < CHANCE_TO_GAIN_SOME_STATS) {
			System.out.println("Congrats! You learned something!!!");
			addRandomAttribute();
		}
		int tempGems = this.enemy.dropGem();
		System.out.println(
				tempGems == 0 ? "The monster didn't drop any gems" : "The monster dropped " + tempGems + " gems ");
		this.gems += tempGems;
	}

	private void addRandomAttribute() {
		switch (Main.rd.nextInt(6)) {
		case 0:// dmg
			System.out.println("Had you had more dmg,you would have won faster ! ");
			this.dmg++;
			System.out.println(
					"Your dmg increased by 1. Hero's dmg: " + this.dmg + " \t. Hero's total dmg : " + this.getAllDMG());
			break;
		case 1:// deff
			System.out.println("Had you  had more def,you would have lost less HP !");
			this.defence++;
			System.out.println("Your def increased by 1. Hero's def : " + this.defence + " \t. Hero's total def: "
					+ this.getAllDef());
			break;
		case 2:// attack speed
			System.out.println("Had you been faster,you would have won faster ! ");
			int tempSP = this.attackSpeed - 1;
			System.out.println("Hero's speed is " + (100 - this.attackSpeed));
			this.setAttackSpeed(tempSP);
			break;
		case 3:// crit chance
			System.out.println("Had you managed some more criticals,you would have won faster ! ");
			int tempCC = this.critChance + 1;
			System.out.println("Hero's crit chance is " + this.critChance);
			this.setCritChance(tempCC);
			break;
		case 4:// crit multiplier
			System.out.println("Had you done more dmg per critical ,you would have won faster ! ");
			this.critMultiplier += 0.05;
			System.out.println("Your crit multiplier increased by 0.05 . Hero's crit multipleir : "
					+ this.critMultiplier + " \t. Hero's total critMultiplier: " + this.getCritMultiplier());
			break;
		case 5:// HP
			System.out.println("It's always good to have some more HP! ");
			this.MAXHP += 2;
			this.HP += 2;
			System.out.println("Your max HP increased by 2 to: " + this.getMAXHP());
			break;
		}
		System.out.println();
	}

	public static boolean yesNoDecision() {
		System.out.println("!!!!!\ty=yes;else=no;\t!!!!!");
		String s = Main.sc.nextLine();
		s = s.toLowerCase();
		if (s.length() == 0) {
			return true;
		}
		if (s.charAt(0) != 'y') {
			return false;
		}
		return true;
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
		if (s.length() > 0) {
			if (Character.isDigit(s.charAt(0))) {
				int i = Character.digit(s.charAt(0), 10) - 1;
				if (i < this.Potions.size() && i != -1) {
					this.usePotion(this.Potions.get(i), i);
				}
			} else {
				System.out.println("you didn't choose a number from the list.Choose again! ");
				this.choosePotionToUse();
				return;
			}
		} else {
			System.out.println("you didn't choose a number from the list.Choose again! ");
			this.choosePotionToUse();
			return;
		}

	}

	public void choosePotionToUseDuringCombat(int monsterTurnMeter, int heroTurnMeter) {
		System.out.println("the hero's HP " + this.HP + "/" + this.getMAXHP());
		System.out.println("potions:");
		System.out.println(this.Potions.toString());
		System.out.println("(r=return to combat)choose now:");
		String s = new String(Main.sc.nextLine());
		if (s.startsWith("r")) {
			System.out.println("going back to combat menu:");
			System.out.println();
			this.chooseFightOptions(enemy, monsterTurnMeter, heroTurnMeter);
			return;
		}
		if (Character.isDigit(s.charAt(0))) {
			int i = Character.digit(s.charAt(0), 10) - 1;
			if (i < this.Potions.size() && i != -1) {
				this.usePotion(this.Potions.get(i), i);
				heroTurnMeter = this.getALLAttackSpeed();
				this.chooseFightOptions(enemy, monsterTurnMeter, heroTurnMeter);
			} else {
				System.out.println("You didn't choose a number from the list.Choose again!");
				this.choosePotionToUseDuringCombat(monsterTurnMeter, heroTurnMeter);
			}

		} else {
			System.out.println("you didn't choose a number from the list.Choose again! ");
			this.choosePotionToUseDuringCombat(monsterTurnMeter, heroTurnMeter);
			return;
		}
	}

	public String showCoinsAndGemsAmount() {
		String s = "hero's coins: " + this.getCoins() + "\thero's gems: " + this.getGems();
		return s;

	}

	public void payCoins(int coins) {
		this.coins = this.coins - coins;
		System.out.println("the hero paid " + coins + " coins.The hero now has: " + this.coins);
	}

	public void showItemInfo() {
		System.out.println("items info:");
		System.out.println(this.getItemSet().toString());
		Main.chooseOption(this);
	}

	public void payGems(int gems) {
		this.gems = this.gems - gems;
		System.out.println("the hero paid " + gems + " gems.The hero now has: " + this.gems);
	}

	public boolean checkGameOver(Hero hero) {
		if (this.isAlive() == false) {
			if (this.getCoins() < City.HEAL_PRICE) {
				if (City.FREE_HEALS == 0) {
					if (this.getGems() == 0) {
						if (Potions.size() == 0) {
							System.out.println("You are not alive!");
							System.out.println("You have no more free heals!");
							System.out.println("You don't have the money to heal!");
							System.out.println("you don't have gems to sell in order to get money to heal!");
							System.out.println("Your potion bag is empty!");
							System.out.println("GAME OVER ");
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public String getHelpInstructions() {
		String s;
		s = "There are some free heals in the city!\nThe marchant offers some heal for some 300 coins!\nHave some potions";
		return s;
	}

	public void godmode() {// cheat
		attackSpeed = MAX_ATTACK_SPEED;
		critChance = MAX_CRIT_CHANCE;
		critMultiplier = 25;
		dmg = 500;
		defence = 10000;
		MAXHP = 50000;

	}
}
