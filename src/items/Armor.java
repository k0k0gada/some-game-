package items;

import characters.Hero;

public class Armor extends Item {
	public Armor(String name) {
		super(name);
		this.setType("armor");
	}

	public void upgradeArmor(Hero hero) {
		Armor a = hero.getArmor();
		a.levelINC();
	}
	// this.armor = new Armor("shirt");
	// this.armor.setDefInc(1);
	// this.armor.setMaxHPInc(3);
	// this.armor.setSpeedInc(5);
	// this.itemSet.add(this.armor);

}
