package items;

import characters.Hero;
import main.Main;

abstract public class Item {
	public static int counter = 0;
	private String name;
	private String type;
	private int level;

	private int attackInc;
	private int defInc;
	private int speedInc;
	private double critChanceInc;
	private double critMultiplierInc;
	private int maxHPInc;

	Item(String name) {
		this.name = name;
		this.level = 0;
		this.attackInc = 0;
		this.speedInc = 0;
		this.defInc = 0;
		this.critChanceInc = 0;
		this.critMultiplierInc = 0;
		this.maxHPInc = 0;
	}

	Item() {
		this.name = "item" + counter;
		countInc();
		this.level = 0;
		this.attackInc = 0;
		this.speedInc = 0;
		this.defInc = 0;
		this.critChanceInc = 0;
		this.critMultiplierInc = 0;
		this.maxHPInc = 0;
	}

	private static void countInc() {
		Item.counter++;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAttackInc() {
		return attackInc;
	}

	public double getCritChanceInc() {
		return critChanceInc;
	}

	public double getCritMultiplierInc() {
		return critMultiplierInc;
	}

	public int getMaxHPInc() {
		return maxHPInc;
	}

	public int getDefInc() {
		return defInc;
	}

	public int getSpeedInc() {
		return speedInc;
	}

	public void setAttackInc(int attackInc) {
		this.attackInc = attackInc;
	}

	public void setDefInc(int defInc) {
		this.defInc = defInc;
	}

	public void setSpeedInc(int speedInc) {
		this.speedInc = speedInc;
	}

	public void setCritChanceInc(double critChanceInc) {
		this.critChanceInc = critChanceInc;
	}

	public void setCritMultiplierInc(double critMultiplierInc) {
		this.critMultiplierInc = critMultiplierInc;
	}

	public void setMaxHPInc(int maxHPInc) {
		this.maxHPInc = maxHPInc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String s = "\n" + type + ": " + name + " \n";
		if (attackInc != 0) {
			s = s + " attack bonus= " + this.attackInc;
			s = s + "\n";
		}
		if (defInc != 0) {
			s = s + " defence bonus= " + this.defInc;
			s = s + "\n";
		}
		if (speedInc != 0) {
			s = s + " speed bonus= " + this.speedInc;
			s = s + "\n";
		}
		if (critChanceInc != 0) {
			s = s + " crit chance bonus= " + this.critChanceInc + "%";
			s = s + "\n";
		}
		if (critMultiplierInc != 0) {
			s = s + " crit multiplier bonus= " + this.critMultiplierInc;
			s = s + "\n";
		}
		if (maxHPInc != 0) {
			s = s + " HP bonus= " + this.maxHPInc;
			s = s + "\n";
		}
		return s;
	}

	public static void upgradeItems(Hero hero) {
		System.out.println("choose item to upgrade:");
		System.out.println(hero.showCoinsAndGemsAmount());
		System.out.println("prices:");
		for (Item item : hero.getItemSet()) {
			System.out.println(item.getCost());
		}
		System.out.println("a=armor;b=boots;g=gloves;h=helmet;w=weapon;e=exit");
		String[] options = { "a", "b", "g", "h", "w", "e" };
		String s = Main.sc.nextLine();
		s = s.toLowerCase();
		{
			boolean correctChoice = false;
			for (int i = 0; i < options.length; i++) {
				if (s.startsWith(options[i])) {
					s = options[i];
					correctChoice = true;
					break;
				}
			}
			if (!correctChoice) {
				s = "gfd";
			}
		}
		switch (s) {
		case "a":
			Armor a = hero.getArmor();
			a.upgradeArmor(hero);
			break;
		case "b":
			Boots b = hero.getBoots();
			b.upgradeBoots(hero);
			break;
		case "g":
			Gloves g = hero.getGloves();
			g.upgradeGloves(hero);
			break;
		case "h":
			Helmet h = hero.getHelmet();
			h.upgradeHelmet(hero);
			break;
		case "w":
			Weapon w = hero.getWep();
			w.upgradeWeapon(hero);
			break;
		case "e":
			Main.chooseOption(hero);
			break;
		default:
			System.out.println("wrong choice!choose again");
			upgradeItems(hero);
			break;
		}
	}

	public abstract String getCost();
}
