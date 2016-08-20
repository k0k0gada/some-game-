package items;

import characters.Hero;

public class Weapon extends Item {
	private final int WEAPON_CONSTANT_GEMS_NEEDED = 5;
	private final int COINS_COST_PER_LEVEL_TILL_LEVEL5 = 300;
	private final int COINS_COST_PER_LEVEL_AFTER_LEVEL5 = 450;

	public Weapon(String name) {
		super(name);
		this.setType("weapon");
	}

	public void upgradeWeapon(Hero hero) {
		Weapon w = hero.getWep();
		int coins = hero.getCoins();
		int gems = hero.getGems();
		if (w.getLevel() < WEAPON_CONSTANT_GEMS_NEEDED) {
			if (coins >= this.getCoinCost() && gems >= this.getGemCost()) {
				System.out.println("upgrade is possible.Do you want to upgrade ? ");
				if (Hero.yesNoDecision()) {
					System.out.println(w.toString());
					hero.payCoins(w.getCoinCost());
					hero.payGems(w.getGemCost());
					setWeaponLevelStats(w.getLevel() + 1);
					System.out.println("new stats: ");
					System.out.println(w.toString());
				} else {
					System.out.println("you didn't upgrade anything .. ?");
				}
			} else {
				if (!(coins > this.getCoinCost())) {
					System.out.println("not enough coins!");
				}
				if (!(gems > this.getGemCost())) {
					System.out.println("not enough gems!");
				}
			}
		} else {
			if (coins >= this.getCoinCost() && gems >= this.getGemCost()) {
				System.out.println("upgrade is possible.Do you want to upgrade ? ");
				if (Hero.yesNoDecision()) {
					System.out.println(w.toString());
					hero.payCoins(w.getCoinCost());
					hero.payGems(w.getGemCost());
					setWeaponLevelStats(w.getLevel() + 1);
					System.out.println("new stats: ");
					System.out.println(w.toString());
				} else {
					System.out.println("you didn't upgrade anything .. ?");
				}
			} else {
				if (!(coins > getCoinCost())) {
					System.out.println("not enough coins!");
				}
				if (!(gems > getGemCost())) {
					System.out.println("not enough gems!");
				}
			}

		}
	}

	void setWeaponLevelStats(int level) {
		switch (level) {
		case 1:
			setName("some kind of a rusty knife");
			setLevel(level);
			setAttackInc(1);
			setCritChanceInc(10);
			setCritMultiplierInc(-0.24);
			setSpeedInc(10);
			setDefInc(1);
			setMaxHPInc(0);
			break;
		case 2:
			setName("good knife");
			setLevel(level);
			setAttackInc(5);
			setCritChanceInc(8);
			setCritMultiplierInc(-0.1);
			setSpeedInc(10);
			setDefInc(2);
			setMaxHPInc(0);
			break;
		case 3:
			setName("small sword");
			setLevel(level);
			setAttackInc(9);
			setCritChanceInc(10);
			setCritMultiplierInc(0.1);
			setSpeedInc(5);
			setDefInc(4);
			setMaxHPInc(0);
			break;
		case 4:
			setName("good swordS");
			setLevel(level);
			setAttackInc(14);
			setCritChanceInc(12);
			setCritMultiplierInc(0.3);
			setSpeedInc(5);
			setDefInc(4);
			setMaxHPInc(0);
			break;
		case 5:
			setName("best sword out there");
			setLevel(level);
			setAttackInc(20);
			setCritChanceInc(13);
			setCritMultiplierInc(0.5);
			setSpeedInc(5);
			setDefInc(5);
			setMaxHPInc(0);
			break;
		case 6:
			setName("unknown weapon");
			setLevel(level);
			setAttackInc(25);
			setCritChanceInc(2);
			setCritMultiplierInc(0.9);
			setSpeedInc(0);
			setDefInc(0);
			setMaxHPInc(0);
			break;
		case 7:
			setName("turns out it was just a pistol");
			setLevel(level);
			setAttackInc(25);
			setCritChanceInc(20);
			setCritMultiplierInc(1);
			setSpeedInc(0);
			setDefInc(0);
			setMaxHPInc(0);
			break;
		case 8:
			setName("a very strong desert eagle");
			setLevel(level);
			setAttackInc(35);
			setCritChanceInc(30);
			setCritMultiplierInc(1.8);
			setSpeedInc(-10);
			setDefInc(0);
			setMaxHPInc(0);
			break;
		case 9:
			setName("almost ultimate weapon");
			setLevel(level);
			setAttackInc(40);
			setCritChanceInc(20);
			setCritMultiplierInc(0.2);
			setSpeedInc(20);
			setDefInc(15);
			setMaxHPInc(0);
			break;
		case 10:
			setName("ultimate weapon");
			setLevel(level);
			setAttackInc(45);
			setCritChanceInc(20);
			setCritMultiplierInc(1.5);
			setSpeedInc(20);
			setDefInc(15);
			setMaxHPInc(5);
			break;
		default:
			setName("ULTIMATE WEAPON level" + (getLevel() - 9));
			setLevel(getLevel() + 1);
			setAttackInc(getAttackInc() + 6);
			setCritChanceInc(getCritChanceInc() + 2);
			setCritMultiplierInc(getCritMultiplierInc() + 0.25);
			setDefInc(getDefInc() + 3);
			setMaxHPInc(getMaxHPInc() + 2);
			setSpeedInc(getSpeedInc() + 1);
			break;
		}
	}

	public int getCoinCost() {
		if (this.getLevel() < WEAPON_CONSTANT_GEMS_NEEDED) {
			return ((this.getLevel() + 1) * COINS_COST_PER_LEVEL_TILL_LEVEL5);
		} else {
			return (this.getLevel() * COINS_COST_PER_LEVEL_AFTER_LEVEL5);
		}
	}

	public int getGemCost() {
		if (this.getLevel() < WEAPON_CONSTANT_GEMS_NEEDED) {
			return this.getLevel() + 1;
		} else {
			return WEAPON_CONSTANT_GEMS_NEEDED;
		}
	}

	public String getCost() {
		String s;
		int coins = this.getCoinCost();
		int gems = this.getGemCost();
		s = this.getType() + " coins cost:" + coins + "\tgems cost:" + gems;
		return s;
	}
}
