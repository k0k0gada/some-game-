package characters;

import java.util.ArrayList;

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

	// stats:
	private int HP;
	private double dmg;
	private double defence;// dmg reduction
	private double attackSpeed;// 1-fastest,100-slowest
	private double critChance;
	private double critMultiplier;

	// items:
	private Weapon wep;
	private Armor armor;
	private Boots boots;
	private Gloves gloves;
	private Helmet helmet;

	// inventory:
	ArrayList<Item> inventory = new ArrayList<>();

	public Hero(String name) {
		this.name = name;
		this.dmg = 10;
		this.attackSpeed = 60;
		this.defence = 5;
		this.HP = 100;
		this.critChance = 5;// 5 %;5 out of 100
		this.critMultiplier = 1.5;
		
		this.armor=new Armor("shirt");
		this.boots=new Boots("galoshes");
		this.gloves=new Gloves("no gloves");
		this.helmet=new Helmet("baldness");
		this.wep=new Weapon("fists");
		
	}

	void equipItem(Item item) {
		switch (item.getType()) {
		case "armor":
			this.armor = (Armor) item;
			break;
		case "boots":
			this.boots = (Boots) item;
			break;
		case "gloves":
			this.gloves = (Gloves) item;
			break;
		case "helmet":
			this.helmet = (Helmet) item;
			break;
		case "weapon":
			this.wep = (Weapon) item;
			break;
		}
	}
	void changeItem(){
		
	}
}
