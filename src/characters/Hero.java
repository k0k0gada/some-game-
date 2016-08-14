package characters;

import java.util.ArrayList;
import java.util.HashSet;

import items.Armor;
import items.Boots;
import items.Gloves;
import items.Helmet;
import items.Item;
import items.Weapon;

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

}
