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

public class Hero {
	private String name;
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

	private java.util.HashSet<Item> itemList = new HashSet<>();
	private Weapon wep;
	private Armor armor;
	private Boots boots;
	private Gloves gloves;
	private Helmet helmet;

	// inventory:
	ArrayList<Item> inventory = new ArrayList<>();

	public Hero(String name) {
		this.name = name;
		this.level = 1;
		this.expNeeded = this.level * 15;
		this.exp = 0;
		this.dmg = 10;
		this.attackSpeed = 70;
		this.defence = 5;
		this.MAXHP = 100;
		this.HP = this.MAXHP;
		this.critChance = 5;// 5 %;5 out of 100
		this.critMultiplier = 1.5;

		this.armor = new Armor("shirt");
		this.armor.setDefInc(2);
		this.armor.setMaxHPInc(5);
		this.armor.setSpeedInc(5);
		this.itemList.add(this.armor);

		this.boots = new Boots("galoshes");
		this.itemList.add(this.boots);

		this.gloves = new Gloves("no gloves");
		this.itemList.add(this.gloves);

		this.helmet = new Helmet("baldness");
		this.itemList.add(this.helmet);

		this.wep = new Weapon("fists");
		this.itemList.add(this.wep);

	}

	public java.util.HashSet<Item> getItemList() {
		return itemList;
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
		s = s + "HP/MaxHP " + this.HP + "/" + this.MAXHP;
		s = s + "\n";
		s = s + "hero's stats(hero's stats + item bonuses):";
		s = s + "\n";
		int tempDMG = 0;
		for (Item item : itemList) {
			tempDMG += item.getAttackInc();
		}
		int tempRed = 0;
		for (Item item : itemList) {
			tempRed += item.getDefInc();
		}
		int tempSp = 0;
		for (Item item : itemList) {
			tempSp += item.getSpeedInc();
		}
		s = s + "dmg: " + (this.dmg + tempDMG) + " (" + this.dmg + " + " + tempDMG + ")" + "\t dmg reduction: "
				+ (this.defence + tempRed) + " (" + this.defence + " + " + tempRed + ")" + "\tspeed="
				+ (100 - ((this.attackSpeed - tempSp < 1) ? 0 : (this.attackSpeed - tempSp))) + "("
				+ (100 - this.attackSpeed) + " + " + (tempSp) + ")";
		s = s + "\n";
		s = s + "crit chance | multiplier :" + this.critChance + "|" + this.critMultiplier;
		s = s + "\n";
		return s;
	}

	void equipItem(Item item) {
		this.inventory.remove(item);// removes the item from the inventory
		switch (item.getType()) {// what is the item ?
		case "armor":
			this.inventory.add(this.armor);// moves the currently equipped armor
											// to the inventory
			this.itemList.remove(this.armor);// removes the armor from item set
			this.armor = (Armor) item;// places the item on the correct place
			break;
		case "boots":
			this.inventory.add(this.boots);
			this.itemList.remove(this.armor);
			this.boots = (Boots) item;
			break;
		case "gloves":
			this.inventory.add(this.gloves);
			this.itemList.remove(this.armor);
			this.gloves = (Gloves) item;
			break;
		case "helmet":
			this.inventory.add(this.helmet);
			this.itemList.remove(this.armor);
			this.helmet = (Helmet) item;
			break;
		case "weapon":
			this.inventory.add(this.wep);
			this.itemList.remove(this.armor);
			this.wep = (Weapon) item;
			break;
		}
		this.itemList.add(item);// adds the item to the SET of equipped items
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

	public void setExp(int exp) {// remove !!!
		this.exp = exp;
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
	}
}
